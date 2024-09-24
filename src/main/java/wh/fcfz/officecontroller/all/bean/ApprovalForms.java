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
@TableName("approval_forms")
public class ApprovalForms {
@TableId(value = "form_id",type= IdType.AUTO)
  private String formId;
  private Integer applicantId;
  private java.sql.Timestamp applicationDate;
  private String status;
  private String type;
  private String description;


  public String getFormId() {
    return formId;
  }

  public void setFormId(String formId) {
    this.formId = formId;
  }


  public Integer getApplicantId() {
    return applicantId;
  }

  public void setApplicantId(Integer applicantId) {
    this.applicantId = applicantId;
  }


  public java.sql.Timestamp getApplicationDate() {
    return applicationDate;
  }

  public void setApplicationDate(java.sql.Timestamp applicationDate) {
    this.applicationDate = applicationDate;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
