package wh.fcfz.officecontroller.all.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("approval_attachments")
public class ApprovalAttachments {
  @TableId(value = "attachment_id",type = IdType.AUTO)
  private Integer attachmentId;
  private String formId;
  private String fileName;
  private String filePath;
  private String project;
  private double budgetAmount;
  private double actualAmount;


  public Integer getAttachmentId() {
    return attachmentId;
  }

  public void setAttachmentId(Integer attachmentId) {
    this.attachmentId = attachmentId;
  }

  public String getFormId() {
    return formId;
  }

  public void setFormId(String formId) {
    this.formId = formId;
  }


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }


  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }


  public double getBudgetAmount() {
    return budgetAmount;
  }

  public void setBudgetAmount(double budgetAmount) {
    this.budgetAmount = budgetAmount;
  }


  public double getActualAmount() {
    return actualAmount;
  }

  public void setActualAmount(double actualAmount) {
    this.actualAmount = actualAmount;
  }

}
