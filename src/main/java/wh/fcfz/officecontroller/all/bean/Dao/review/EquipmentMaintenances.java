package wh.fcfz.officecontroller.all.bean.Dao.review;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("equipment_maintenances")
public class EquipmentMaintenances {
@TableId(value = "maintenance_id",type = IdType.AUTO)
  private long maintenanceId;
  private long equipmentId;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private String problemDescription;
  private String status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;


  public long getMaintenanceId() {
    return maintenanceId;
  }

  public void setMaintenanceId(long maintenanceId) {
    this.maintenanceId = maintenanceId;
  }


  public long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(long equipmentId) {
    this.equipmentId = equipmentId;
  }


  public java.sql.Date getStartDate() {
    return startDate;
  }

  public void setStartDate(java.sql.Date startDate) {
    this.startDate = startDate;
  }


  public java.sql.Date getEndDate() {
    return endDate;
  }

  public void setEndDate(java.sql.Date endDate) {
    this.endDate = endDate;
  }


  public String getProblemDescription() {
    return problemDescription;
  }

  public void setProblemDescription(String problemDescription) {
    this.problemDescription = problemDescription;
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
