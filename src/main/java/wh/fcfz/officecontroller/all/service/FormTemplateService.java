package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplate;
import wh.fcfz.officecontroller.all.bean.Vo.ApprovalForReviewVo;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplateField;

import java.util.List;

/**
* @author admin
* @description 针对表【form_template(表单类型表)】的数据库操作Service
* @createDate 2024-11-19 11:28:14
*/
public interface FormTemplateService extends IService<FormTemplate> {
    //查询系统模版中分类模版信息
    List<ApprovalForReviewVo> selectApprovalForReview();

    List<FormTemplateField> getSysTemplateField(Integer templateId);
}
