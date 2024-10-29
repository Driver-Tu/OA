package wh.fcfz.officecontroller.all.bean.Dao;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("approval_steps")
public class ApprovalSteps {
@TableId(value = "step_id",type = IdType.AUTO)
  private Long stepId;
  private Long formId;
  private Integer approver;
  private String opinion;
  private java.sql.Timestamp approvalDate;
  private String result;

}
