package wh.fcfz.officecontroller.all.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessage {
  private String roleName;
  private String departmentName;
  private String userName;
  private String userImage;
  private String empNum;
  private String telephone;
  private String email;
  private Long status;
}