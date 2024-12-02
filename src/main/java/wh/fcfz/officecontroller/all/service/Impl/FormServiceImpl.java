package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import wh.fcfz.officecontroller.all.bean.Dao.form.Form;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormFieldValue;
import wh.fcfz.officecontroller.all.bean.Dto.FormDto;
import wh.fcfz.officecontroller.all.bean.Dto.FormValueDto;
import wh.fcfz.officecontroller.all.bean.Vo.FieldOptionVo;
import wh.fcfz.officecontroller.all.bean.Vo.FormFieldValueVo;
import wh.fcfz.officecontroller.all.bean.Vo.FormValueVo;
import wh.fcfz.officecontroller.all.bean.Vo.FormVo;
import wh.fcfz.officecontroller.all.mapper.FormFieldValueMapper;
import wh.fcfz.officecontroller.all.mapper.FormMapper;
import wh.fcfz.officecontroller.all.mapper.FormTemplateFieldOptionMapper;
import wh.fcfz.officecontroller.all.service.FormFieldValueService;
import wh.fcfz.officecontroller.all.service.FormService;
import wh.fcfz.officecontroller.all.tool.MyPage;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author admin
* @description 针对表【form(表单通用 ( 字段 ) 表)】的数据库操作Service实现
* @createDate 2024-11-19 16:39:04
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class FormServiceImpl extends ServiceImpl<FormMapper, Form> implements FormService{

    private final FormMapper formMapper;

    private final FormFieldValueMapper formFieldValueMapper;
    private final FormFieldValueService formFieldValueService;

    private final FormTemplateFieldOptionMapper formTemplateFieldOptionMapper;

    @Override
    public List<Form> getAllForms(MyPage<Form> data) {
        Page<Form> page=new Page<>(data.getPageNum(),data.getPageSize());
        formMapper.selectPage(page,new LambdaQueryWrapper<Form>()
                .eq(Form::getStatus,data.getData().getStatus()));
        return page.getRecords();
    }

    /**
     * 根据 id 查询表单结构与数据
     * @param id
     * @return 表单结构和数据
     */
    @Override
    public FormValueVo getFormById(@PathVariable("id") Integer id) {
        List<FormFieldValueVo> formFieldValues = formFieldValueMapper.selecFormFieldValueListByFormId(id);
        FormValueVo formValueVo = new FormValueVo();
        Form form = formMapper.selectById(id);
        BeanUtil.copyProperties(form, formValueVo);
        formValueVo.setFormFieldValues(formFieldValues);

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

        return formValueVo;
    }

    /**
     * 新增表单结构与数据
     * @param formDto
     */
    @Override
    @Transactional
    public void addForm(@RequestBody FormDto formDto) {
        // 1. 保存表单基本信息
        Form form = new Form();
        BeanUtil.copyProperties(formDto, form); // 使用 Hutool 的 BeanUtil 进行属性拷贝
        String formContent = convertFormContent(formDto);
        form.setContent(formContent);
        formMapper.insert(form);

        // 2. 转换字段数据并进行批量插入
        List<FormFieldValue> formFieldValues = convertFormValues(formDto.getFormValues(), form.getFormId());
        log.warn("formFieldValues: " + formFieldValues);
        boolean isSaved = formFieldValueService.saveBatch(formFieldValues);

        // 3. 验证插入结果
        if (!isSaved) {
            // 手动回滚
            throw new RuntimeException("表单字段数据插入失败！");
        }
    }

    /**
     * 根据 FormDto 组装表单的 content 静态内容的方法
     * @param formDto 表单数据对象
     */
    @Override
    @Transactional
    public void updateForm(@RequestBody FormDto formDto) {
        // 1. 保存表单基本信息
        Form form = new Form();
        BeanUtil.copyProperties(formDto, form); // 使用 Hutool 的 BeanUtil 进行属性拷贝
        String formContent = convertFormContent(formDto);
        form.setContent(formContent);
        updateById(form);

        // 2. 转换字段数据并进行批量插入
        List<FormFieldValue> formFieldValues = convertFormValues(formDto.getFormValues(), form.getFormId());
        log.warn("formFieldValues: " + formFieldValues);
        boolean isSaved = formFieldValueService.updateBatchById(formFieldValues);

        // 3. 验证插入结果
        if (!isSaved) {
            // 手动回滚
            throw new RuntimeException("表单字段数据更新失败！");
        }
    }

    @Override
    public Page<FormVo> selectPage(MyPage<FormDto> page) {

            Integer pageNum = page.getPageNum();

            Integer pageSize = page.getPageSize();

            // 分页参数校验
            if (Objects.isNull(pageNum) || Objects.isNull(pageSize)) {
                log.error("分页参数为空");
                return null;
            }

            Page<Form> pageQuery = new Page<>(pageNum, pageSize);
            LambdaQueryWrapper<Form> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            // 使用 MyBatis-Plus 提供的 StringUtils 进行字段非空检查

            Page<Form> formPage = formMapper.selectPage(pageQuery, lambdaQueryWrapper);

            if (formPage.getRecords().isEmpty()) {
                log.info("分页查询列表结果为空");
                return null;
            }

            // 将查询结果转换为自定义的 Vo 对象
            List<FormVo> formVoList = formPage.getRecords().stream().map(form -> {
                FormVo formVo = new FormVo();
                BeanUtil.copyProperties(form, formVo);
                return formVo;
            }).collect(Collectors.toList());

            Page<FormVo> formVoPage = new Page<>();
            formVoPage.setRecords(formVoList);
            formVoPage.setTotal(formVoList.size());
            formVoPage.setCurrent(formVoPage.getCurrent());

            return formVoPage;
    }

    /**
     * 根据 FormDto 组装表单的 content 静态内容的方法
     * @param formDto 表单数据对象
     * @return 转换后的 HTML 字符串
     */
    private String convertFormContent(FormDto formDto) {
        StringBuilder content = new StringBuilder();

        // 外层 div，设置全局样式
        content.append("<div style=\"font-family: Arial, sans-serif; line-height: 1.6; color: #333;\">");

        // 添加表单名称
        content.append("<span style=\"font-size: 0.9em; color: #888;\">表单名称：</span><br/>")
                .append("<div style=\"margin: 4px 0;\">")
                .append(formDto.getFormName())
                .append("</div></div>")
                .append("<br/>");

        // 遍历 formValues，生成字段的 HTML 内容
        formDto.getFormValues().forEach(field -> {
            Object fieldValue = field.getFieldValue();

            // 如果字段值为空，则跳过
            if (ObjectUtil.isNull(fieldValue)) {
                return;
            }

            // 每个字段的外层 div
            content.append("<div>");

            // 字段标题
            content
                    .append("<span style=\"font-size: 0.9em; color: #888;\">")
                    .append(field.getFieldTitle())
                    .append("：</span><br/>");

            // 字段值内容
            content.append("<div style=\"margin: 4px 0;\">");
            if (fieldValue instanceof String) {
                content.append(fieldValue);
            } else if (fieldValue instanceof List) {
                // 如果是 List 类型的字段值，生成无序列表
                content.append("<ul style=\"margin: 4px 0; padding-left: 20px;\">");
                ((List<?>) fieldValue).forEach(item ->
                        content.append("<li>").append(item).append("</li>")
                );
                content.append("</ul>");
            } else {
                // 其他复杂类型直接转为字符串
                content.append(fieldValue.toString());
            }
            content.append("</div>"); // 结束字段值内容

            content.append("</div>")
                    .append("<br/>"); // 结束字段的外层 div
        });

        // 关闭外层 div
        content.append("</div>");

        return content.toString(); // 返回生成的 HTML 字符串
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




