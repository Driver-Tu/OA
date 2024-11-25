package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Dao.form.Form;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormFieldValue;
import wh.fcfz.officecontroller.all.bean.Dto.FormDto;
import wh.fcfz.officecontroller.all.bean.Dto.FormValueDto;
import wh.fcfz.officecontroller.all.bean.Vo.FieldOptionVo;
import wh.fcfz.officecontroller.all.bean.Vo.FormFieldValueVo;
import wh.fcfz.officecontroller.all.bean.Vo.FormVo;
import wh.fcfz.officecontroller.all.mapper.FormFieldValueMapper;
import wh.fcfz.officecontroller.all.mapper.FormMapper;
import wh.fcfz.officecontroller.all.mapper.FormTemplateFieldOptionMapper;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/form")
public class MyFormController {

    @Autowired
    private FormFieldValueMapper formFieldValueMapper;

    @Autowired
    private FormMapper formMapper;

    @Autowired
    private FormTemplateFieldOptionMapper formTemplateFieldOptionMapper;

    /**
     * 根据 id 查询表单结构与数据
     * @param id
     * @return 表单结构和数据
     */
    @GetMapping("/{id}")
    public Result<FormVo> getFormById(@PathVariable("id") Integer id) {
        List<FormFieldValueVo> formFieldValues = formFieldValueMapper.selecFormFieldValueListByFormId(id);
        FormVo formVo = new FormVo();
        Form form = formMapper.selectById(id);
        BeanUtil.copyProperties(form, formVo);
        formVo.setFormFieldValues(formFieldValues);

        // 遍历处理 "multi-varchar" 和 "multi-bigint" 类型
        formFieldValues.forEach(formFieldValue -> {
            String fieldType = formFieldValue.getFieldType();
            Object fieldValue = formFieldValue.getFieldValue();

            if ("int".equals(fieldType)) {
                // 将 fieldValue 转为 Integer 并赋值给 fieldValue
//                formFieldValue.setFieldValue(Long.valueOf((String) fieldValue));
                Optional.ofNullable(fieldValue)
                        .map(String::valueOf)
                        .ifPresent(value -> formFieldValue.setFieldValue(Long.valueOf(value)));
            } else if ("multi-varchar".equals(fieldType)) {
                // 将 fieldValue 转为 List<String> 并赋值给 fieldValue
                JSONArray jsonArray = JSONUtil.parseArray(fieldValue);
                List<String> stringList = jsonArray.toList(String.class);
                formFieldValue.setFieldValue(stringList);
            } else if ("multi-int".equals(fieldType)) {
                // 将 fieldValue 转为 List<Long> 并赋值给 fieldValue
                JSONArray jsonArray = JSONUtil.parseArray(fieldValue);
                List<Integer> longList = jsonArray.toList(Integer.class);
                formFieldValue.setFieldValue(longList);
            } else if ("multi-bigint".equals(fieldType)) {
                // 将 fieldValue 转为 List<Long> 并赋值给 fieldValue
                JSONArray jsonArray = JSONUtil.parseArray(fieldValue);
                List<Long> longList = jsonArray.toList(Long.class);
                formFieldValue.setFieldValue(longList);
            }
        });

        // 处理 "single_select" 和 "multiple_select" 的 fieldOptions 属性
        formFieldValues.stream()
                .filter(formFieldValue ->
                        "single_select".equals(formFieldValue.getFormItemType())
                                || "multiple_select".equals(formFieldValue.getFormItemType())
                )
                .forEach(formFieldValue -> {
                    List<FieldOptionVo> fieldOptions = formTemplateFieldOptionMapper.selecFormFieldOptionListByFormFieldId(formFieldValue.getFieldId());
                    log.warn("fieldOptions: " + fieldOptions);
                    formFieldValue.setFieldOptions(fieldOptions);
                });

        return new Result<>("200", "success", formVo);
    }

    /**
     * 添加表单
     * @param formDto
     * @return
     */
    @PostMapping
    public Result<Void> addForm(@RequestBody FormDto formDto) {
        // 1. 保存表单基本信息
        Form form = new Form();
        BeanUtil.copyProperties(formDto, form); // 使用 Hutool 的 BeanUtil 进行属性拷贝
        formMapper.insert(form);

        // 2. 转换字段数据并进行批量插入
        List<FormFieldValue> formFieldValues = convertFormValues(formDto.getFormValues(), form.getFormId());
        log.warn("formFieldValues: " + formFieldValues);
        int insertedCount = formFieldValueMapper.batchInsertFormFieldValues(formFieldValues);

        // 3. 验证插入结果
        if (insertedCount != formFieldValues.size()) {
            throw new RuntimeException("部分字段数据插入失败！");
        }

        return new Result<>("200", "success", null);
    }

    /**
     * 将 List<FormValueDto> 转换为 List<FormFieldValue>
     *
     * @param formValueDtos 表单字段的 DTO 列表
     * @param formId        表单 ID，用于关联字段
     * @return 转换后的实体列表
     */
    private List<FormFieldValue> convertFormValues(List<FormValueDto> formValueDtos, Integer formId) {
        return formValueDtos.stream().map(dto -> {
            FormFieldValue fieldValue = new FormFieldValue();
            BeanUtil.copyProperties(dto, fieldValue); // 属性拷贝
            fieldValue.setFormId(formId);             // 设置表单 ID
            fieldValue.setCreateBy(StpUtil.getLoginIdAsInt());     // 设置创建时间
            fieldValue.setCreateTime(new Date());     // 设置创建时间
            fieldValue.setUpdateBy(StpUtil.getLoginIdAsInt());     // 设置创建时间
            fieldValue.setUpdateTime(new Date());     // 设置更新时间
            return fieldValue;
        }).collect(Collectors.toList()); // 收集为 List<FormFieldValue>
    }



}
