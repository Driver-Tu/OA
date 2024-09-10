package wh.fcfz.officecontroller.all.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Depart;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.all.service.Impl.DepartServiceImpl;

@RestController
@RequestMapping("/dept")
public class DepartController {

    @Autowired
    private DepartServiceImpl deptServiceImpl;

    @GetMapping("/list")
    public Result<Depart> selectALL(@RequestBody(required = false) Depart depart,
                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "3") Integer pageSize){
        return deptServiceImpl.selectPageAll(depart, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public Result<Depart> selectById(@PathVariable Integer id){
        return deptServiceImpl.selectById(id);
    }

    @PostMapping("/save")
    public Result<Depart> selectById(@RequestBody(required = false) Depart depart){
        return deptServiceImpl.saveDepart(depart);
    }

    @DeleteMapping("/{id}")
    public Result<Depart> deleteById(@PathVariable Integer id){
        return deptServiceImpl.deleteById(id);
    }

    @PostMapping("/update")
    public Result<Depart> upDateDept(@RequestBody(required = false) Depart depart){
        return deptServiceImpl.updateDept(depart);
    }

}
