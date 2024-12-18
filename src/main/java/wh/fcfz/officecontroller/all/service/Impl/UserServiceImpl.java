package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.bean.Dao.User;
import wh.fcfz.officecontroller.all.bean.Dto.UserDto;
import wh.fcfz.officecontroller.all.bean.Vo.UserVo;
import wh.fcfz.officecontroller.all.mapper.DepartMapper;
import wh.fcfz.officecontroller.all.mapper.RoleMapper;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.UserService;
import wh.fcfz.officecontroller.all.tool.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartMapper departMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录
     * */
    @Override
    public Result<User> login(String empNum, String password,String device) {
        if (empNum==null || password==null){
            return new Result<User>(ResponseEnum.PARAM_ERROR,null);
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getEmpNum,empNum)
                .select(User::getEmpNum,User::getUserPassword,User::getUserId)
                .eq(User::getUserPassword,password);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if(user == null){
            return new Result<>(ResponseEnum.USER_NOT_EXIST, null);
        }
        SaSession sessionByLoginId = StpUtil.getSessionByLoginId(user.getUserId());
        if(StpUtil.isLogin(user.getUserId())&& !sessionByLoginId.getTokenValueListByDevice(device).isEmpty()){
            StpUtil.logout(user.getUserId(),device);
            throw new MyException("账户已登录，请重新登录","506");
        }
        StpUtil.login(user.getUserId(),device);
        return new Result(ResponseEnum.SUCCESS,StpUtil.getTokenInfo());
    }


    /**
     * 退出登录
     * */
    @Override
    public Result<User> logout() {
        if(StpUtil.isLogin(StpUtil.getLoginId())){
            StpUtil.logout();
            return new Result<User>(ResponseEnum.SUCCESS,null);
        } else {
            return new Result<User>(ResponseEnum.USER_NOT_LOGIN,null);
        }
    }

    /**
     * 获取信息
     *
     * @return*/
    @Override
    public Result<UserVo> SelectByUserId() {
        if(!StpUtil.isLogin(StpUtil.getLoginId())){
            return new Result<UserVo>(ResponseEnum.USER_NOT_LOGIN,null);
        }
        Long userId = StpUtil.getLoginIdAsLong();
        User user = userMapper.selectById(userId);
        String departName = userMapper.selectDepartName(user.getDepartmentId());
        String roleName = userMapper.selectRoleName(user.getRoleId());
        UserVo userVo =new UserVo();
        BeanUtil.copyProperties(user, userVo);
        userVo.setDepartmentName(departName);
        userVo.setRoleName(roleName);
        String[] birthdayAndGender = getBirthdayAndGender(user.getBirthdayNum());
        userVo.setBirth(birthdayAndGender[0]);
        userVo.setSex(birthdayAndGender[1]);
        userVo.setBirthdayNum(DesensitizedUtil.idCardNum(user.getBirthdayNum(),1,1));
        return new Result<>(ResponseEnum.SUCCESS, userVo);
    }
/**
 *
 * 管理员查询所有人
 * */
    @Override
    public Result<List<UserVo>> selectALL(MyPage<UserDto> page) {
        if(!StpUtil.isLogin(StpUtil.getLoginId())){
            return new Result<List<UserVo>>(ResponseEnum.USER_NOT_LOGIN,null);
        }
        Page<User> pages = new Page<>(page.getPageNum(), page.getPageSize());
        List<UserVo> userPage = userMapper.selectUserList(page.getData());
        List<UserVo> userVoList = userPage.stream().parallel().peek(userVo -> {
            String[] birthdayAndGender = getBirthdayAndGender(userVo.getBirthdayNum());
            userVo.setBirth(birthdayAndGender[0]);
            userVo.setSex(birthdayAndGender[1]);
//            userVo.setBirthdayNum(DesensitizedUtil.idCardNum(userVo.getBirthdayNum(),1,1));
            userVo.setBirthdayNum(userVo.getBirthdayNum());
        }).toList();
        Page<UserVo> pageMessages = new Page<>(page.getPageNum(), page.getPageSize());
        List<UserVo> collect = userVoList.stream()
                .skip((long) (page.getPageNum() - 1) * page.getPageSize())
                .limit(page.getPageSize()).collect(Collectors.toList());
        pageMessages.setRecords(collect);
        pageMessages.setTotal(userVoList.size());
        return new Result(ResponseEnum.SUCCESS,pageMessages);
    }


    public static String[] getBirthdayAndGender(String idNumber) {
        if (idNumber == null || (idNumber.length() != 15 && idNumber.length() != 18)) {
            // 返回默认值
            return new String[]{null, "1"};
        }
        String birthday = null;
        String gender = null;
        try {
            if (idNumber.length() == 18) {
                // 提取出生日期
                birthday = idNumber.substring(6, 14);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Date date = sdf.parse(birthday);
                SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
                birthday = sdfOut.format(date);

                // 提取性别
                gender = idNumber.substring(16, 17);
                if (Integer.parseInt(gender) % 2 == 0||idNumber.endsWith("X")) {
                    gender = "2";
                } else {
                    gender = "1";
                }
            } else {
                // 返回默认值
                return new String[]{null, "1"};
            }
        } catch (ParseException e) {
            // 返回默认值
            return new String[]{null, "1"};
        }

        return new String[]{birthday, gender};
    }


    /**
     * 修改密码
     * */
    @Override
    public Result<User> updatePassword(String oldPassword,String newPassword){
        try {
            String password=HashEncryption.encrypt(oldPassword);
            if(!StpUtil.isLogin(StpUtil.getLoginId())){
                return new Result<User>(ResponseEnum.USER_NOT_LOGIN,null);
            }
            if(password==null||newPassword==null){
                return new Result<User>(ResponseEnum.PARAM_ERROR,null);
            }
            User user = userMapper.selectById(StpUtil.getLoginIdAsLong());
            LambdaUpdateWrapper<User> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper
                    .eq(User::getUserPassword,oldPassword)
                    .eq(User::getUserId,StpUtil.getLoginIdAsLong())
                    .set(User::getUserPassword,HashEncryption.encrypt(newPassword))
                    .set(User::getUpTime, DateTime.now());
            try {
                if(userMapper.update(lambdaUpdateWrapper)>0){
                    return new Result<User>(ResponseEnum.SUCCESS,null);
                }else {
                    return new Result<User>(ResponseEnum.PASSWORD_IS_NOT_TRUE,null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new Result<User>(ResponseEnum.UPDATE_SERVER_ERROR,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<User>(ResponseEnum.PASSWORD_IS_NULL,null);
        }
    }

    /**
     * 管理员修改个人信息
     * */
    @Override
    public Result<User> updateUserInfo(User user) {
        if(!StpUtil.isLogin(StpUtil.getLoginId())){
            return new Result<User>(ResponseEnum.USER_NOT_LOGIN,null);
        }
        LambdaUpdateWrapper<User> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getUpTime, DateTime.now())
                .eq(User::getUserId, user.getUserId());
        try {
            if(userMapper.update(user,lambdaUpdateWrapper)>0){
                // 删除redis中rollerId
                stringRedisTemplate.delete("role:"+user.getUserId());
                stringRedisTemplate.delete("permission:"+user.getUserId());
                return new Result<User>(ResponseEnum.SUCCESS,null);
            }else {
                log.error("修改个人信息时出现异常，user: {}", user);
                return new Result<User>(ResponseEnum.USER_NOT_EXIST,null);
            }
        } catch (Exception e) {
           log.error("修改个人信息时出现异常，user: {}", user, e);
            return new Result<User>(ResponseEnum.UPDATE_SERVER_ERROR,null);
        }
    }

    /**
     * admin添加用户
     * */
    @Override
    public Result<User> saveUser(User user) {
        if (!StpUtil.isLogin(StpUtil.getLoginId())) {
            return new Result<User>(ResponseEnum.USER_NOT_LOGIN, null);
        }
        boolean b = ObjectUtil.hasEmpty(
                user.getUserName(), user.getEmpNum(),user.getStatus(),
                user.getDepartmentId(), user.getRoleId());
        if(b){
            return new Result<User>(ResponseEnum.DATA_NOT_EXIST,null);
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getEmpNum,user.getEmpNum());
        if(userMapper.selectOne(lambdaQueryWrapper)!=null){
            return new Result<User>(ResponseEnum.USER_IS_EXIST,null);
        }
        try {
            user.setCtTime(DateTime.now().toTimestamp());
            user.setTimeIn(DateTime.now().toTimestamp());
//            user.setUserPassword(HashEncryption.encrypt(user.getUserPassword()));
            if (user.getUserPassword() == null) {
                user.setUserPassword("123456");
            }
            user.setUserPassword(user.getUserPassword());
            userMapper.insert(user);
            return new Result<User>(ResponseEnum.SUCCESS, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<User>(ResponseEnum.INSERT_SERVER_ERROR,null);
        }

    }

    /*
    *批量删除数据
    *  */
    @Override
    @Transactional
    public Result<String> deleteUser(List<Integer> ids) {
        if (!StpUtil.isLogin(StpUtil.getLoginId())) {
            return new Result<String>(ResponseEnum.USER_NOT_LOGIN, null);
        }
        try {
            userMapper.deleteBatchIds(ids);
            return new Result<String>(ResponseEnum.SUCCESS,"成功删除的数据为"+ids);
        } catch (Exception e) {
            log.error("批量删除数据时出现异常，ids: {}", ids, e);
           throw e;
        }
    }

    @Override
    public Result<String> updateSelfUserInfo(User user) {
        //校验身份
        if (!StpUtil.isLogin(StpUtil.getLoginId())) {
            return new Result<String>(ResponseEnum.USER_NOT_LOGIN, null);
        }
        LambdaUpdateWrapper<User> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getUpTime, DateTime.now())
                .eq(User::getUserId, StpUtil.getLoginIdAsInt());
        try {
            if(userMapper.update(user,lambdaUpdateWrapper)>0){
                return new Result<String>(ResponseEnum.SUCCESS,null);
            }else {
                log.error("修改个人信息时出现异常，user: {}", user);
                return new Result<String>(ResponseEnum.USER_NOT_EXIST,null);
            }
        } catch (Exception e) {
            log.error("修改个人信息时出现异常，user: {}", user, e);
            return new Result<String>(ResponseEnum.UPDATE_SERVER_ERROR,null);
        }
    }

}
