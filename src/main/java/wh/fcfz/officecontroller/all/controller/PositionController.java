package wh.fcfz.officecontroller.all.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Dao.Position;
import wh.fcfz.officecontroller.all.bean.Vo.UsersPositionVo;
import wh.fcfz.officecontroller.all.service.Impl.PositionServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionServiceImpl positionServiceImpl;
    private final StringRedisTemplate stringRedisTemplate;

    public PositionController(PositionServiceImpl positionServiceImpl, StringRedisTemplate stringRedisTemplate) {
        this.positionServiceImpl = positionServiceImpl;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    //查询职位信息
    @SaCheckPermission(value={"admin","boss"}, mode= SaMode.OR)
    @PostMapping("/selectPosition")
    public Result<Page<Position>> selectPosition(@RequestBody MyPage<Position> myPage){
        return new Result<>(ResponseEnum.SUCCESS,positionServiceImpl.selectPosition(myPage));
    }

    //个人查询岗位信息
    @GetMapping("getSelfPosition")
    public Result<Position> getSelfPosition(){
        return new Result<>(ResponseEnum.SUCCESS,positionServiceImpl.getSelfPosition());
    }

    //管理员修改岗位信息
    @SaCheckPermission(value={"admin","boss"}, mode= SaMode.OR)
    @PostMapping("/updatePosition")
    public Result<Boolean> updatePosition(@RequestBody Position position){
        return new Result<>(ResponseEnum.SUCCESS,positionServiceImpl.updatePosition(position));
    }

    //管理员删除岗位信息
    @SaCheckPermission(value={"admin","boss"}, mode= SaMode.OR)
    @PostMapping("/deletePosition")
    public Result<Boolean> deletePosition(@RequestBody Long id){
        return new Result<>(ResponseEnum.SUCCESS,positionServiceImpl.deleteById(id));
    }

    //获取岗位的人
    @SaCheckPermission(value={"admin","boss"}, mode= SaMode.OR)
    @GetMapping("/getUserByPosition")
    public Result<List<UsersPositionVo>> getUserByPosition(@RequestParam Long positionId){
        return new Result<>(ResponseEnum.SUCCESS,positionServiceImpl.getUserByPosition(positionId));
    }

    //新增岗位
    @SaCheckPermission(value={"admin","boss"}, mode= SaMode.OR)
    @PostMapping("/addPosition")
    public Result<Boolean> addPosition(@RequestBody Position position){
        return new Result<>(ResponseEnum.SUCCESS,positionServiceImpl.savePosition(position));
    }
}
