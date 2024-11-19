package wh.fcfz.officecontroller.all.service.Impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplate;
import wh.fcfz.officecontroller.all.bean.Vo.ApprovalForReviewVo;
import wh.fcfz.officecontroller.all.mapper.FormTemplateFieldMapper;
import wh.fcfz.officecontroller.all.mapper.FormTemplateFieldOptionMapper;
import wh.fcfz.officecontroller.all.mapper.FormTemplateMapper;
import wh.fcfz.officecontroller.all.service.FormTemplateService;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplateField;

import java.util.*;

/**
 * @author admin
 * @description 针对表【form_template(表单类型表)】的数据库操作Service实现
 * @createDate 2024-11-19 11:28:14
 */
@Service
public class FormTemplateServiceImpl extends ServiceImpl<FormTemplateMapper, FormTemplate>
        implements FormTemplateService {


    private final StringRedisTemplate stringRedisTemplate;
    private final FormTemplateMapper formTemplateMapper;

    public FormTemplateServiceImpl(StringRedisTemplate stringRedisTemplate, FormTemplateMapper formTemplateMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.formTemplateMapper = formTemplateMapper;
    }

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

    @Autowired
    private FormTemplateFieldMapper formTemplateFieldMapper;
    @Autowired
    private FormTemplateFieldOptionMapper formTemplateFieldOptionMapper;

    @Override
    public List<FormTemplateField> getSysTemplateField(Integer templateId) {
        if (!Objects.requireNonNull(stringRedisTemplate.opsForList().range("template:" + templateId, 0, -1)).isEmpty()) {
            return Objects.requireNonNull(stringRedisTemplate.opsForList()
                            .range("template:" + templateId, 0, -1))
                    .stream()
                    .map(s -> JSONUtil.toBean(s, FormTemplateField.class))
                    .toList();
        }
        List<FormTemplateField> formTemplateFields = formTemplateFieldMapper
                .selectList(new LambdaQueryWrapper<FormTemplateField>()
                        .eq(FormTemplateField::getTemplateId, templateId)
                        .orderByAsc(FormTemplateField::getFieldSort));
        formTemplateFields.forEach(formTemplateField -> {
            stringRedisTemplate.opsForList().rightPush("template:" + templateId, JSONUtil.toJsonStr(formTemplateField));
        });
        return formTemplateFields;
    }


}