package wh.fcfz.officecontroller.all.bean.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalForms;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddApprovalFormsDto {
    /**
     * 表单信息
     * */
    private ApprovalForms approvalForms;
    /**
     * 审批人们
     * */
    private List<Integer> approvers;

    /**
     * 额外类型的对象
     * */
    private Object object;
}
