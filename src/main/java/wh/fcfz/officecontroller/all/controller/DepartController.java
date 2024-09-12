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

    @SaCheckPermission("admin")
    @DeleteMapping("/delete-batch")
    public Result<String> deleteDeptsBatch(@RequestBody List<Integer> ids){
        return deptService.deleteDeptsBatch(ids);
    }

}
