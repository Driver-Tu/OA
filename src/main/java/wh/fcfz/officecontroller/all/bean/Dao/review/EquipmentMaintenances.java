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
  private Long maintenanceId;
  private Long equipmentId;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private String problemDescription;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

}
