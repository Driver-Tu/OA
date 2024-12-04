package wh.fcfz.officecontroller.all.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplate;
import wh.fcfz.officecontroller.all.bean.Vo.ApprovalForReviewVo;
import wh.fcfz.officecontroller.all.bean.Vo.FieldOptionVo;
import wh.fcfz.officecontroller.all.bean.Vo.FormTemplateVo;
import wh.fcfz.officecontroller.all.bean.Vo.TemplateFieldVo;
import wh.fcfz.officecontroller.all.mapper.FormTemplateFieldMapper;
import wh.fcfz.officecontroller.all.mapper.FormTemplateFieldOptionMapper;
import wh.fcfz.officecontroller.all.mapper.FormTemplateMapper;
import wh.fcfz.officecontroller.all.service.FormTemplateService;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author admin
 * @description 针对表【form_template(表单类型表)】的数据库操作Service实现
 * @createDate 2024-11-19 11:28:14
 */
@Service
public class FormTemplateServiceImpl extends ServiceImpl<FormTemplateMapper, FormTemplate>
        implements FormTemplateService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private FormTemplateMapper formTemplateMapper;

    @Autowired
    private FormTemplateFieldMapper formTemplateFieldMapper;
    @Autowired
    private FormTemplateFieldOptionMapper formTemplateFieldOptionMapper;

    @Override
    public List<ApprovalForReviewVo> selectApprovalForReview() {
        if (!Objects.requireNonNull(stringRedisTemplate.opsForList().range("approvalForReview", 0, -1)).isEmpty()) {
            List<String> approvalForReview = Objects.requireNonNull(stringRedisTemplate.opsForList().range("approvalForReview", 0, -1));
            return approvalForReview.stream().map(s -> JSONUtil.toBean(s, ApprovalForReviewVo.class)).toList();
        }
        LambdaQueryWrapper<FormTemplate> formTemplateLambdaQueryWrapper = new LambdaQueryWrapper<FormTemplate>()
                .eq(FormTemplate::getIsSys, 1);
        List<FormTemplate> formTemplates = formTemplateMapper.selectList(formTemplateLambdaQueryWrapper);
        Map<String, List<FormTemplate>> map = new HashMap<>();
        formTemplates.forEach(formTemplate -> {
            if (map.containsKey(formTemplate.getCategory())) {
                List<FormTemplate> formTemplates1 = map.get(formTemplate.getCategory());
                formTemplates1.add(formTemplate);
                map.put(formTemplate.getCategory(), formTemplates1);
            } else {
                List<FormTemplate> formTemplate1 = new java.util.ArrayList<>();
                map.put(formTemplate.getCategory(), formTemplate1);
            }
        });
        List<ApprovalForReviewVo> approvalForReviewVos = new ArrayList<>();
        //循环打印map
        map.forEach((k, v) -> {
            ApprovalForReviewVo approvalForReviewVo = new ApprovalForReviewVo();
            approvalForReviewVo.setCategory(k);
            approvalForReviewVo.setTemplateName(v);
            approvalForReviewVos.add(approvalForReviewVo);
        });
        for (ApprovalForReviewVo approvalForReviewVo : approvalForReviewVos) {
            stringRedisTemplate.opsForList().rightPush("approvalForReview", JSONUtil.toJsonStr(approvalForReviewVo));
        }
        return approvalForReviewVos;
    }

    @Override
    public FormTemplateVo getTemplateById(@PathVariable("id") Integer id) {
        List<TemplateFieldVo> templateFields = formTemplateFieldMapper.selectTemplateFieldListByFormId(id);
        FormTemplateVo templateVo = new FormTemplateVo();
        FormTemplate formTemplate = formTemplateMapper.selectById(id);
        BeanUtil.copyProperties(formTemplate, templateVo);
        templateVo.setTemplateFields(templateFields);

        Pattern pattern = Pattern.compile("^1[3-9]\\d{9}$");
//        // 遍历处理 "multi-varchar" 和 "multi-bigint" 类型
//        templateFields.forEach(templateField -> {
//            JSONObject validationRule = JsonUtils.parseObject(templateField.getValidationRule(),JSONObject.class);
//            templateField.setValidationRule(validationRule);
//        });

        // 处理 "single_select" 和 "multiple_select" 的 fieldOptions 属性
        templateFields.stream()
                .filter(templateField ->
                        "single_select".equals(templateField.getFormItemType())
                                || "multiple_select".equals(templateField.getFormItemType())
                )
                .forEach(templateField -> {
                    List<FieldOptionVo> fieldOptions = formTemplateFieldOptionMapper.selecFormFieldOptionListByFormFieldId(templateField.getFieldId());
                    log.warn("fieldOptions: " + fieldOptions);
                    templateField.setFieldOptions(fieldOptions);
                });

        templateVo.setTestPattern(pattern);
        return templateVo;
    }

}