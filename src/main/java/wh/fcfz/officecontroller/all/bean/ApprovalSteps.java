package wh.fcfz.officecontroller.all.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("approval_steps")
public class ApprovalSteps {
@TableId(value = "step_id",type = IdType.AUTO)
  private Integer stepId;
  private String formId;
  private String approver;
  private String opinion;
  private java.sql.Timestamp approvalDate;
  private String result;


  public Integer getStepId() {
    return stepId;
  }

  public void setStepId(Integer stepId) {
    this.stepId = stepId;
  }


  public String getFormId() {
    return formId;
  }

  public void setFormId(String formId) {
    this.formId = formId;
  }


  public String getApprover() {
    return approver;
  }

  public void setApprover(String approver) {
    this.approver = approver;
  }


  public String getOpinion() {
    return opinion;
  }

  public void setOpinion(String opinion) {
    this.opinion = opinion;
  }


  public java.sql.Timestamp getApprovalDate() {
    return approvalDate;
  }

  public void setApprovalDate(java.sql.Timestamp approvalDate) {
    this.approvalDate = approvalDate;
  }


  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

}
