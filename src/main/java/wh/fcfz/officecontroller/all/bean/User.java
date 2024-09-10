package wh.fcfz.officecontroller.all.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
  @TableId(type = IdType.AUTO)
  private Integer userId;
  private Integer roleId;
  private Integer departmentId;
  private String userName;
  private String userImage;
  private String empNum;
  private String userPassword;
  private String telephone;
  private String email;
  private Integer status;
  private java.sql.Timestamp ctTime;
  private java.sql.Timestamp upTime;
}
