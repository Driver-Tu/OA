package wh.fcfz.officecontroller.all.bean.Dao.review;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruitments {
@TableId(value = "recruitment_id",type = IdType.AUTO)
  private Long recruitmentId;
  private String position;
  private String department;
  private String description;
  private String status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
}
