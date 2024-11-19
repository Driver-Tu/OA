package wh.fcfz.officecontroller.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.bean.Dao.form.Form;
import wh.fcfz.officecontroller.all.service.FormService;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    @Autowired
    private FormService formService;
    @PostMapping("getAllForms")
    public Result<List<Form>> getAllForms(@RequestBody MyPage<Form> form){
        return new Result<>("200","获取成功",formService.getAllForms(form));
    }
}
