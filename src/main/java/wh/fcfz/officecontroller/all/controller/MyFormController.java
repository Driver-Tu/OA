package wh.fcfz.officecontroller.all.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Dto.FormDto;
import wh.fcfz.officecontroller.all.bean.Vo.FormTemplateVo;
import wh.fcfz.officecontroller.all.bean.Vo.FormInstanceVo;
import wh.fcfz.officecontroller.all.bean.Vo.FormVo;
import wh.fcfz.officecontroller.all.mapper.FormTemplateFieldMapper;
import wh.fcfz.officecontroller.all.mapper.FormTemplateFieldOptionMapper;
import wh.fcfz.officecontroller.all.mapper.FormTemplateMapper;
import wh.fcfz.officecontroller.all.service.FormService;
import wh.fcfz.officecontroller.all.service.FormTemplateService;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

@Slf4j
@RestController
@RequestMapping("/form")
public class MyFormController {

    @Autowired
    private FormTemplateMapper formTemplateMapper;

    @Autowired
    private FormTemplateFieldMapper formTemplateFieldMapper;

    @Autowired
    private FormTemplateFieldOptionMapper formTemplateFieldOptionMapper;

    @Autowired
    private FormService formService;

    @Autowired
    private FormTemplateService formTemplateService;


    /**
     * 查询所有员工数据
     * */
    @PostMapping("/list")
    public Result<Page<FormVo>> selectPage(@RequestBody MyPage<FormDto> page){
        Page<FormVo> pageData = formService.selectPage(page);
        return new Result<>("200", "success", pageData);
    }

    /**
     * 根据 id 查询表单结构与数据
     * @param id
     * @return 表单结构和数据
     */
    @GetMapping("/{id}")
    public Result<FormInstanceVo> getFormById(@PathVariable("id") Integer id) {
        FormInstanceVo formInstanceVo = formService.getFormById(id);
        return new Result<>("200", "success", formInstanceVo);
    }

    /**
     * 根据 id 查询表单结构与数据
     * @param id
     * @return 表单结构和数据
     */
    @GetMapping("/template/{id}")
    public Result<FormTemplateVo> getTemplateById(@PathVariable("id") Integer id) {
        FormTemplateVo templateVo = formTemplateService.getTemplateById(id);
        return new Result<>("200", "success", templateVo);
    }

    /**
     * 添加表单
     * @param formDto
     * @return
     */
    @PostMapping("/add")
    public Result<Void> addForm(@RequestBody FormDto formDto) {
        formService.addForm(formDto);
        return new Result<>("200", "success", null);
    }

    /**
     * 添加表单
     * @param formDto
     * @return
     */
    @PostMapping("/update")
    public Result<Void> updateForm(@RequestBody FormDto formDto) {
        formService.updateForm(formDto);
        return new Result<>("200", "success", null);
    }

}
