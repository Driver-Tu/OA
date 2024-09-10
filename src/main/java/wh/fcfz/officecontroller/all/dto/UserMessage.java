package wh.fcfz.officecontroller.all.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessage {
  private String userName;
  private String roleName;
  private String departmentName;
  private String userImage;
  private String empNum;
  private String telephone;
  private String email;
  private Integer status;
  private java.sql.Timestamp ctTime;
  private java.sql.Timestamp upTime;
}
