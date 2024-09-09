package wh.fcfz.officecontroller.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Result;
import wh.fcfz.officecontroller.all.bean.User;
import wh.fcfz.officecontroller.all.service.Impl.UserServiceImpl;

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
    public Result<User> info(){
        return userService.SelectByUserId();
    }

    @GetMapping("/logout")
    public Result<User> logout(){
        return userService.logout();
    }
}
