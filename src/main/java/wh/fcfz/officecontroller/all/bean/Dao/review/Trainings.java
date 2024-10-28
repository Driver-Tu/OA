package wh.fcfz.officecontroller.all.bean.Dao.review;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainings {
@TableId(value = "training_id", type = IdType.AUTO)
  private Long trainingId;
  private Long employeeId;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private String trainingType;
  private String provider;
  private String status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

}
