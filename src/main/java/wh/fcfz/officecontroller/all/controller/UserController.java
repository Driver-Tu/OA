package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.bean.Dao.User;
import wh.fcfz.officecontroller.all.bean.Dto.UserDto;
import wh.fcfz.officecontroller.all.bean.Vo.UserOnVo;
import wh.fcfz.officecontroller.all.bean.Vo.UserVo;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.Impl.UserServiceImpl;
import wh.fcfz.officecontroller.all.tool.AliOssUtil;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public Result<User> login(@RequestParam String empNum,
                              @RequestParam String password,
    @RequestParam String device){
        return userService.login(empNum,password,device);
    }

    @GetMapping("isAdmin")
    public Result<Boolean> isAdmin(){
        return new Result<>(ResponseEnum.SUCCESS,StpUtil.hasPermission("admin"));
    }

    @GetMapping("/info")
    public Result<UserVo> info(){
        return userService.SelectByUserId();
    }

   /**
    * 查询所有员工数据
    * */
    @SaCheckPermission(value={"admin","boss"}, mode= SaMode.OR)
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
    @SaCheckPermission(value={"admin","boss"}, mode= SaMode.OR)
    @PostMapping("/save")
    public Result<User> save(@RequestBody User user){
        return userService.saveUser(user);
    }

    /*
    * 批量删除
    * */
    @SaCheckPermission(value={"admin","boss"}, mode= SaMode.OR)
    @DeleteMapping("/deleteUser")
    public Result<String> deleteUser(@RequestBody List<Integer> ids){
        return userService.deleteUser(ids);
    }

    @GetMapping("/isLogin")
    public Result<Boolean> isLogin(){
        return new Result<>(ResponseEnum.SUCCESS,StpUtil.isLogin(StpUtil.getLoginId()));
    }

    @GetMapping("/userImage")
    public Result<String> readDefaultImage(){
        User byId = userService.getById(StpUtil.getLoginIdAsInt());
        if(byId.getUserImage()==null){
            return new Result<>(ResponseEnum.DATA_NOT_EXIST,"https://mp-b8e53f5e-c503-4780-9859-ec2675b3d8cd.cdn.bspapp.com/unicloud/default.png");
        }else {
            return new Result<>(ResponseEnum.SUCCESS, byId.getUserImage());
        }
    }

    @Autowired
    private AliOssUtil aliOssUtil;

    //更换头像
    @PostMapping("/updateAvatar")
    @Transactional
    public Result<String> updateAvatar(@RequestParam("file") MultipartFile file){
        if (file != null) {
            //如果文件存在，则上传至阿里云服务器
            String upload = null;
            try {
                String originalFilename = file.getOriginalFilename();
                UUID uuid = UUID.randomUUID();
                String fileType = null;
                if (originalFilename != null) {
                    fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String s =  uuid + fileType;
                upload = aliOssUtil.upload(file, s);
                log.info("aliyun路径为:{}",upload);
            } catch (Exception e) {
                return new Result<>(ResponseEnum.FILE_UPLOAD_ERROR,null);
            }
            //上传到服务器后，需要改写user数据库的头像字段
            User user = userService.getById(StpUtil.getLoginIdAsInt());
            if(user!=null){
                user.setUserImage(upload);
                try {
                    boolean b = userService.updateById(user);
                    if(b){
                        return new Result<>(ResponseEnum.SUCCESS,upload);
                    }else {
                        return new Result<>(ResponseEnum.UPDATE_SERVER_ERROR,null);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                return new Result<>(ResponseEnum.USER_NOT_EXIST,null);
            }

        }else {
            return new Result<>(ResponseEnum.DATA_NOT_EXIST,null);
        }
    }

    @Autowired
    private UserMapper userMapper;
    /**
     * 查询所有员工数据
     * */
    @GetMapping("/shareUserList")
    public Result<List<UserOnVo>> selectShareUsers(){
        User byId = userService.getById(StpUtil.getLoginIdAsInt());
        String s = userMapper.selectDepartName(byId.getDepartmentId());
        // 查询所有roleId小于等于2的用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.le(User::getRoleId,2);
        List<User> list = userService.list(queryWrapper);
        List<UserOnVo> userOnVoList =new ArrayList<>();

        List<UserOnVo> userOnVoLists = list.stream().filter(item -> Objects.equals(item.getDepartmentId(), byId.getDepartmentId()) || item.getRoleId() == 1).map(item -> {
            UserOnVo userOnVo = new UserOnVo();
            BeanUtil.copyProperties(item, userOnVo);
            if (item.getRoleId() == 2) {
                userOnVo.setRoleName("admin");
                userOnVo.setDepartmentName(s);
            } else {
                userOnVo.setRoleName("boss");
            }
            return userOnVo;
        }).toList();
        return new Result<>(ResponseEnum.SUCCESS, userOnVoLists);
    }
}
