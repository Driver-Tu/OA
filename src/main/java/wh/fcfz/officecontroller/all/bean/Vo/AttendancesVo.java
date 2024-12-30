package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.Dao.Attendance;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendancesVo extends Attendance {
    private String userName;
    private String departName;
    private String year;
    private String month;
    public AttendancesVo(Attendance attendance) {
        super(attendance);
    }
}
