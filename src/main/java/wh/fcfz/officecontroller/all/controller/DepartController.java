package wh.fcfz.officecontroller.all.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Depart;
import wh.fcfz.officecontroller.all.bean.Result;
import wh.fcfz.officecontroller.all.bean.User;
import wh.fcfz.officecontroller.all.service.Impl.DepartServiceImpl;

@RestController
@RequestMapping("/dept")
public class DepartController {

    @Autowired
    private DepartServiceImpl deptServiceImpl;

    @GetMapping("/list")
    public Result<Depart> SelectALL(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "3") Integer pageSize){
        return deptServiceImpl.SelectPageALL(pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public Result<Depart> SelectById(@PathVariable Integer id){
        return deptServiceImpl.SelectByID(id);
    }
}
