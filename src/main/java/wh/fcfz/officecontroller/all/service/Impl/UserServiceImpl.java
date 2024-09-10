package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.ResponseEnum;
import wh.fcfz.officecontroller.all.bean.Result;
import wh.fcfz.officecontroller.all.bean.User;
import wh.fcfz.officecontroller.all.dto.UserMessage;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.UserServeice;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserServeice {

    @Autowired
    private UserMapper userMapper;
    /**
     * 登录
     * */
    @Override
    public Result<User> login(String empNum, String password) {
        if (empNum==null || password==null){
            return new Result(ResponseEnum.PARAM_ERROR,null);
        }

        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getEmpNum,empNum)
                .eq(User::getUserPassword,password);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if(user == null){
            return new Result(ResponseEnum.USER_NOT_EXIST,null);
        }
        if(StpUtil.isLogin()){
            return new Result(ResponseEnum.USER_IS_LOGIN,null);
        }
        StpUtil.login(user.getUserId(),
                //设置登录token存在时间
                new SaLoginModel().setTimeout(60*60));
        return new Result(ResponseEnum.SUCCESS,StpUtil.getTokenInfo());
    }

    /**
     * 获取信息
     * */
    @Override
    public Result<User> SelectByUserId() {
        if(!StpUtil.isLogin()){
            return new Result(ResponseEnum.USER_NOT_LOGIN,null);
        }
        Long userId = StpUtil.getLoginIdAsLong();
        User user = userMapper.selectById(userId);
        String departName = userMapper.selectDepartName(user.getDepartmentId());
        String roleName = userMapper.selectRoleName(user.getRoleId());
        UserMessage userMessage=new UserMessage();
        userMessage.setDepartmentName(departName);
        userMessage.setRoleName(roleName);
        userMessage.setUserName(user.getUserName());
        userMessage.setEmpNum(user.getEmpNum());
        userMessage.setTelephone(user.getTelephone());
        userMessage.setUserImage(user.getUserImage());
        userMessage.setEmail(user.getEmail());
        userMessage.setStatus(user.getStatus());
        userMessage.setCtTime(user.getCtTime());
        userMessage.setUpTime(user.getUpTime());
        if(user==null){
            return new Result(ResponseEnum.USER_NOT_EXIST,null);
        }
        return new Result(ResponseEnum.SUCCESS,userMessage);
    }

    /**
     * 退出登录
     * */
    @Override
    public Result<User> logout() {
        StpUtil.logout();
        return new Result(ResponseEnum.SUCCESS,null);
    }

    /**
     * 修改密码
     * */
    @Override
    public Result<User> updatePassword(String oldPassword,String newPassword) {
        if(!StpUtil.isLogin()){
            return new Result(ResponseEnum.USER_NOT_LOGIN,null);
        }
        if(oldPassword==null||newPassword==null){
            return new Result(ResponseEnum.PARAM_ERROR,null);
        }
        LambdaUpdateWrapper<User> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper
                .set(User::getUpTime, DateTime.now())
                .set(User::getUserPassword,newPassword)
                .eq(User::getUserPassword,oldPassword)
                .eq(User::getUserId,StpUtil.getLoginIdAsLong());
        if(userMapper.update(lambdaUpdateWrapper)>0){
            return new Result(ResponseEnum.SUCCESS,null);
        }else {
            return new Result(ResponseEnum.PASSWORD_IS_NOT_TRUE,null);
        }
    }

    /**
     * 修改个人信息
     * */
    @Override
    public Result<User> updateUserInfo(User user) {
        if(!StpUtil.isLogin()){
            return new Result(ResponseEnum.USER_NOT_LOGIN,null);
        }
        LambdaUpdateWrapper<User> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getUpTime, DateTime.now())
                .eq(User::getUserId,StpUtil.getLoginIdAsLong());
        if(userMapper.update(user,lambdaUpdateWrapper)>0){
            return new Result(ResponseEnum.SUCCESS,null);
        }else {
            return new Result(ResponseEnum.USER_NOT_EXIST,null);
        }
    }


}
