package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.all.bean.User;
import wh.fcfz.officecontroller.all.dto.UserMessage;

import java.util.List;

public interface UserServeice extends IService<User> {
    //登录
    Result<User> login(String empNum,String password);
    //根据id查个人信息
    Result<UserMessage> SelectByUserId();
    //查询所有用户
    Result<List<UserMessage>> selectALL(MyPage<User> page);
    //退出登录
    Result<User> logout();
    //修改密码
    Result<User> updatePassword(String oldPassword,String newPassword);
    //修改个人信息
    Result<User> updateUserInfo(User user);
}
