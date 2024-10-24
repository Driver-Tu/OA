package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOnVo {
    private Integer userId;
    private String userName;
    private String departmentName;
    private String roleName;
    private String userImage;
}
