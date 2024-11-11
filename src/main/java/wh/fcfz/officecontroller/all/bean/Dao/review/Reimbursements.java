package wh.fcfz.officecontroller.all.bean.Dao.review;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reimbursements {
@TableId(value = "reimbursement_id", type = IdType.AUTO)
  private Long reimbursementId;
  private Long employeeId;
  private Long amount;
  private String receipt;
  private String description;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
}
