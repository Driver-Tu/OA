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
@TableName("approval_logs")
public class ApprovalLogs {
@TableId(value = "log_id",type = IdType.AUTO)
  private Integer logId;
  private String formId;
  private String operator;
  private String operationType;
  private java.sql.Timestamp operationDate;
  private String operationDescription;


  public Integer getLogId() {
    return logId;
  }

  public void setLogId(Integer logId) {
    this.logId = logId;
  }


  public String getFormId() {
    return formId;
  }

  public void setFormId(String formId) {
    this.formId = formId;
  }


  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }


  public String getOperationType() {
    return operationType;
  }

  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }


  public java.sql.Timestamp getOperationDate() {
    return operationDate;
  }

  public void setOperationDate(java.sql.Timestamp operationDate) {
    this.operationDate = operationDate;
  }


  public String getOperationDescription() {
    return operationDescription;
  }

  public void setOperationDescription(String operationDescription) {
    this.operationDescription = operationDescription;
  }

}
