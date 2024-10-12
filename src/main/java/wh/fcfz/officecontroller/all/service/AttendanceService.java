package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Dao.Attendance;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

public interface AttendanceService extends IService<Attendance> {
    /**
     * 查
     * */
    //管理员查询所有考勤记录
    public Result getAllAttendance(MyPage<Attendance> myPage);
    /**
     * 增
     * */
    public Result addAttendance(Attendance attendance);
}
