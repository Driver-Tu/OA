package wh.fcfz.officecontroller.all.bean.Vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.Dao.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends User {
  private String roleName;
  private String departmentName;
  private String birth;
  private String sex;
  public UserVo(User user) {
    super(user);
  }
}
