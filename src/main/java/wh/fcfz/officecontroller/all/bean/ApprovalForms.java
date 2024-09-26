package wh.fcfz.officecontroller.all.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("approval_forms")
public class ApprovalForms {
@TableId(value = "form_id",type= IdType.AUTO)
  private String formId;
  private Integer applicantId;
  private java.sql.Timestamp applicationDate;
  private String status;
  private String type;
  private String description;
  @TableField(exist = false)
  private String UserName;
  @TableField(exist = false)
  private String departmentName;
}
