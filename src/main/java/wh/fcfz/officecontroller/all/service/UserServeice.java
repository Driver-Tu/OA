package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Result;
import wh.fcfz.officecontroller.all.bean.User;

public interface UserServeice extends IService<User> {
    Result<User> login(String empNum,String password);

    //根据id查个人信息
    Result<User> SelectByUserId();
}
