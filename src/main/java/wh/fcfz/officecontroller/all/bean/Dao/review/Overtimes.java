package wh.fcfz.officecontroller.all.bean.Dao.review;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Overtimes {
@TableId(value = "overtime_id",type = IdType.AUTO)
  private Long overtimeId;
  private Long employeeId;
  private java.sql.Date date;
  private java.sql.Time startTime;
  private java.sql.Time endTime;
  private String reason;
  private String status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
}
