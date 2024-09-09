package wh.fcfz.officecontroller.all.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
