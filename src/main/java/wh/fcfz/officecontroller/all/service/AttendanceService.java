package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Attendance;

import java.util.List;

public interface AttendanceService extends IService<Attendance> {
    /**
     * 查
     * */
    //管理员查询所有考勤记录
    public List<Attendance> getAllAttendance();
}
