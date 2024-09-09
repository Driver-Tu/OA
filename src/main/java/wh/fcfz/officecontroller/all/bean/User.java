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
  private long userId;
  private long roleId;
  private long departmentId;
  private String userName;
  private String userImage;
  private String empNum;
  private String userPassword;
  private String telephone;
  private String email;
  private long status;

}
