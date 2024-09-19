package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.User;
import wh.fcfz.officecontroller.all.dto.UserMessage;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.UserService;
import wh.fcfz.officecontroller.all.tool.HashEncryption;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 登录
     * */
    @Override
    public Result<User> login(String empNum, String password) {
        if (empNum==null || password==null){
            return new Result<User>(ResponseEnum.PARAM_ERROR,null);
        }

        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getEmpNum,empNum)
                .eq(User::getUserPassword,password);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if(user == null){
            return new Result<User>(ResponseEnum.USER_NOT_EXIST,null);
        }
        //校验当前用户是否登录
        if (StpUtil.isLogin(user.getUserId())){
            return new Result<User>(ResponseEnum.USER_IS_LOGIN,null);
        }
        StpUtil.login(user.getUserId(),
                //设置登录token存在时间
                new SaLoginModel().setTimeout(60*10));
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
        }else {
            return new Result<User>(ResponseEnum.USER_NOT_LOGIN,null);
        }

    }

    /**
     * 获取信息
     *
     * @return*/
    @Override
    public Result<UserMessage> SelectByUserId() {
        if(!StpUtil.isLogin(StpUtil.getLoginId())){
            return new Result<UserMessage>(ResponseEnum.USER_NOT_LOGIN,null);
        }
        Long userId = StpUtil.getLoginIdAsLong();
        User user = userMapper.selectById(userId);
        String departName = userMapper.selectDepartName(user.getDepartmentId());
        String roleName = userMapper.selectRoleName(user.getRoleId());
        UserMessage userMessage=new UserMessage();
        userMessage.setDepartmentName(departName);
        userMessage.setRoleName(roleName);
        BeanUtil.copyProperties(user,userMessage);

        return new Result<UserMessage>(ResponseEnum.SUCCESS,userMessage);
    }
/**
 *
 * 管理员查询所有人
 * */
    @Override
    public Result<List<UserMessage>> selectALL(MyPage<User> page) {
        if(!StpUtil.isLogin(StpUtil.getLoginId())){
            return new Result<List<UserMessage>>(ResponseEnum.USER_NOT_LOGIN,null);
        }
        Page<User> pages = new Page<>(page.getPageNum(), page.getPageSize());
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                //状态
                .eq(null!=page.getData().getStatus(),User::getStatus,page.getData().getStatus())
                //部门
                .eq(null!=page.getData().getDepartmentId(),User::getDepartmentId,page.getData().getDepartmentId())
                //名称
                .eq(null!=page.getData().getUserName(),User::getRoleId,page.getData().getUserName())
                //排序为最新创建
                .orderByDesc(User::getCtTime);
        Page<User> userPage = userMapper.selectPage(pages, lambdaQueryWrapper);
        List<UserMessage> userMessageList = userPage.getRecords().stream().map(user -> {
            String departName = userMapper.selectDepartName(user.getDepartmentId());
            String roleName = userMapper.selectRoleName(user.getRoleId());
            UserMessage userMessage=new UserMessage();
            userMessage.setDepartmentName(departName);
            userMessage.setRoleName(roleName);
            BeanUtil.copyProperties(user,userMessage);
            return userMessage;
        }).collect(Collectors.toList());
        return new Result<List<UserMessage>>(ResponseEnum.SUCCESS,userMessageList);
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
     * 修改个人信息
     * */
    @Override
    public Result<User> updateUserInfo(User user) {
        if(!StpUtil.isLogin(StpUtil.getLoginId())){
            return new Result<User>(ResponseEnum.USER_NOT_LOGIN,null);
        }
        LambdaUpdateWrapper<User> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getUpTime, DateTime.now())
                .eq(User::getUserId,StpUtil.getLoginId());
        try {
            if(userMapper.update(user,lambdaUpdateWrapper)>0){
                return new Result<User>(ResponseEnum.SUCCESS,null);
            }else {
                return new Result<User>(ResponseEnum.USER_NOT_EXIST,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                user.getUserName(), user.getEmpNum(),
                user.getUserPassword(), user.getUserImage(),
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
            user.setUserPassword(HashEncryption.encrypt(user.getUserPassword()));
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
    public Result<String> deleteUser(List<Integer> ids) {
        if (!StpUtil.isLogin(StpUtil.getLoginId())) {
            return new Result<String>(ResponseEnum.USER_NOT_LOGIN, null);
        }
        List<String> deleteOkList=new ArrayList<>();
        List<String> deleteNoList=new ArrayList<>();
        //删除前先查询再删除
        ids.forEach(id-> {
            User user = userMapper.selectById(id);
            if(user!=null){
                deleteOkList.add(user.getUserName());
            }else {
                deleteNoList.add(user.getUserName());
            }
        });//ss
        try {
            deleteOkList.forEach(id->userMapper.deleteById(id));
            return new Result<String>(ResponseEnum.SUCCESS,"成功删除的数据为"+deleteOkList+"失败的数据为"+deleteNoList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<String>(ResponseEnum.DELETE_SERVER_FAILED,null);
        }
    }

}
