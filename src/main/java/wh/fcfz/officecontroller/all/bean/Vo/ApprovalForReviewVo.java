package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplate;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 查询系统审批模版
 */
public class ApprovalForReviewVo {
    /**
     * 审批类别
     */
    private String category;
    /**
     * 审批模版名称
     */
    private List<FormTemplate> templateName;
}
