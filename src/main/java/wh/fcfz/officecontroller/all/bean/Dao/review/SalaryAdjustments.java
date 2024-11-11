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
  private Long adjustmentId;
  private Long employeeId;
  private Long currentSalary;
  private Long proposedSalary;
  private String reason;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

}
