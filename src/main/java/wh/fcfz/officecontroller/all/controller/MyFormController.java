package wh.fcfz.officecontroller.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.bean.Dao.form.Form;
import wh.fcfz.officecontroller.all.bean.Vo.FormFieldValueVo;
import wh.fcfz.officecontroller.all.bean.Vo.FormVo;
import wh.fcfz.officecontroller.all.mapper.FormFieldValueMapper;
import wh.fcfz.officecontroller.all.mapper.FormMapper;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

@RestController
@RequestMapping("/form")
public class MyFormController {

    @Autowired
    private FormFieldValueMapper formFieldValueMapper;

    @Autowired
    private FormMapper formMapper;

    @GetMapping("/{id}")
    public Result<FormVo> getFormById(@PathVariable("id") Integer id) {
        List<FormFieldValueVo> formFieldValues = formFieldValueMapper.selecFormFieldValueListByFormId(id);
        FormVo formVo = new FormVo();
        Form form = formMapper.selectById(id);
        formVo.setForm(form);
        formVo.setFormFieldValues(formFieldValues);
        return new Result<>("200","success",formVo);
    }
}
