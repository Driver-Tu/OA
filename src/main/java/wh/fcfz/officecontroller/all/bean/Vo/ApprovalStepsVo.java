package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalSteps;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalStepsVo {
    private ApprovalSteps approvalSteps;
    private ApprovalFormsVo approvalForms;
}
