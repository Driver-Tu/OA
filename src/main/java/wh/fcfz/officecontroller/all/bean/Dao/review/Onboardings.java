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
@TableName("onboardings")
public class Onboardings {
@TableId(value = "onboarding_id", type = IdType.AUTO)
  private Long onboardingId;
  private Long employeeId;
  private java.sql.Date startDate;
  private String department;
  private String position;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
}
