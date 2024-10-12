package wh.fcfz.officecontroller.all.bean.Dao;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("approval_forms")
public class ApprovalForms {
@TableId(value = "form_id",type= IdType.AUTO)
  private String formId;
  private Integer applicantId;
  private Integer allId;
  private String fromName;
  private java.sql.Timestamp applicationDate;
  private String status;
  private String type;
  private String description;
  @TableField(exist = false)
  private String UserName;
  @TableField(exist = false)
  private String departmentName;
  @TableField(exist = false)
  private Map<String,Object> map;
}
