package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.ResponseEnum;
import wh.fcfz.officecontroller.all.bean.Result;
import wh.fcfz.officecontroller.all.bean.User;
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
                new SaLoginModel().setTimeout(20));
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
        if(user==null){
            return new Result(ResponseEnum.USER_NOT_EXIST,null);
        }
        return new Result<>(ResponseEnum.SUCCESS,user);
    }
}
