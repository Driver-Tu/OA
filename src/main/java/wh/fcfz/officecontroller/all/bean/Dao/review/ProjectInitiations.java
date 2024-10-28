package wh.fcfz.officecontroller.all.bean.Dao.review;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInitiations {
@TableId(value="project_initiation_id",type = IdType.AUTO)
  private Long projectInitiationId;
  private String projectName;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private double budget;
  private String status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

}
