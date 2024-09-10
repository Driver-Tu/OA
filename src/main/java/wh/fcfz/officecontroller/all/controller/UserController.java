package wh.fcfz.officecontroller.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.User;
import wh.fcfz.officecontroller.all.dto.UserMessage;
import wh.fcfz.officecontroller.all.service.Impl.UserServiceImpl;
import wh.fcfz.officecontroller.all.tool.Result;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public Result<User> login(@RequestParam String empNum,
                              @RequestParam String password){
        return userService.login(empNum,password);
    }

    @GetMapping("/info")
    public Result<UserMessage> info(){
        return userService.SelectByUserId();
    }


    @GetMapping("/logout")
    public Result<User> logout(){
        return userService.logout();
    }


    @PostMapping("/updatePassword")
    public Result<User> updatePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword){
        return userService.updatePassword(oldPassword,newPassword);
    }

    @PostMapping("/updateUserInfo")
    public Result<User> updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
}
