package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.all.bean.User;
import wh.fcfz.officecontroller.all.dto.UserMessage;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService extends IService<User> {
    //登录
    Result<User> login(String empNum,String password);
    //根据id查个人信息
    Result<UserMessage> SelectByUserId();
    //查询所有用户
    Result<List<UserMessage>> selectALL(MyPage<User> page);
    //退出登录
    Result<User> logout();
    //修改密码
    Result<User> updatePassword(String oldPassword,String newPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    //修改个人信息
    Result<User> updateUserInfo(User user);
    //增加员工
    Result<User> saveUser(User user);
    //删除员工s
    Result<String> deleteUser(List<Integer> ids);
}
