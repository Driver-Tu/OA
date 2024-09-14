package wh.fcfz.officecontroller.all.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Depart;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.all.service.Impl.DepartServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DepartController {

    @Autowired
    private DepartServiceImpl deptService;

    @GetMapping("/list")
    public Result<Depart> selectALL(@RequestBody(required = false) Depart depart,
                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "3") Integer pageSize){
        return deptService.selectPageAll(depart, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public Result<Depart> selectById(@PathVariable Integer id){
        return deptService.selectById(id);
    }

    //日志
    //1
    //申报附件
    //对审批进行增加时
    @SaCheckPermission("admin")
    @PostMapping("/save")
    public Result<Depart> selectById(@RequestBody(required = false) Depart depart){
        return deptService.saveDepart(depart);
    }


    @SaCheckPermission("admin")
    @PostMapping("/update")
    public Result<Depart> upDateDept(@RequestBody(required = false) Depart depart){
        return deptService.updateDept(depart);
    }
//上传到网页（变量）
    //提交
    //日志模块
    //审批模块
    //
    @SaCheckPermission("admin")
    @DeleteMapping("/delete-batch")
    public Result<String> deleteDeptsBatch(@RequestBody List<Integer> ids){
        return deptService.deleteDeptsBatch(ids);
    }
}
