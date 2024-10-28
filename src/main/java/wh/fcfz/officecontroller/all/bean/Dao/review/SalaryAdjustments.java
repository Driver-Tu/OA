package wh.fcfz.officecontroller.all.bean.Dao.review;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryAdjustments {
@TableId(value = "adjustment_id",type = IdType.AUTO)
  private long adjustmentId;
  private long employeeId;
  private double currentSalary;
  private double proposedSalary;
  private String reason;
  private String status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;


  public long getAdjustmentId() {
    return adjustmentId;
  }

  public void setAdjustmentId(long adjustmentId) {
    this.adjustmentId = adjustmentId;
  }


  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }


  public double getCurrentSalary() {
    return currentSalary;
  }

  public void setCurrentSalary(double currentSalary) {
    this.currentSalary = currentSalary;
  }


  public double getProposedSalary() {
    return proposedSalary;
  }

  public void setProposedSalary(double proposedSalary) {
    this.proposedSalary = proposedSalary;
  }


  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
    this.createdAt = createdAt;
  }


  public java.sql.Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(java.sql.Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

}
