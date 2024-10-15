package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Dao.User;
import wh.fcfz.officecontroller.all.bean.Dto.UserDto;
import wh.fcfz.officecontroller.all.bean.Vo.UserVo;
import wh.fcfz.officecontroller.all.service.Impl.UserServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

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
    public Result<UserVo> info(){
        return userService.SelectByUserId();
    }

   /**
    * 查询所有员工数据
    * */
     @SaCheckPermission("admin")
     @PostMapping("/list")
    public Result<List<UserVo>> selectALL(@RequestBody MyPage<UserDto> page){
        return userService.selectALL(page);
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

    /*
     *新增员工
     * */
    @SaCheckPermission("admin")
    @PostMapping("/save")
    public Result<User> save(@RequestBody User user){
        return userService.saveUser(user);
    }

    /*
    * 批量删除
    * */
    @SaCheckPermission("admin")
    @DeleteMapping("/deleteUser")
    public Result<String> deleteUser(@RequestBody List<Integer> ids){
        return userService.deleteUser(ids);
    }
}
