package wh.fcfz.officecontroller.all.bean.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.Dao.Attendance;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendancesVo extends Attendance {
    @TableField(exist = false)
    private String attendanceUserName;
    @TableField(exist = false)
    private String attendanceUserDepartName;
    public AttendancesVo(Attendance attendance) {
        super(attendance);
    }
}
