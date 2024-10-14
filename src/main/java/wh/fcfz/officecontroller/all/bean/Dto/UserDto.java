package wh.fcfz.officecontroller.all.bean.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String empNum;
    private String userName;
    private String roleName;
    private String departName;
    private Integer status;
    private java.sql.Timestamp timeIn;
}
