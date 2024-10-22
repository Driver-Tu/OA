package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.bean.Dao.Attendance;
import wh.fcfz.officecontroller.all.bean.Vo.AttendancesVo;
import wh.fcfz.officecontroller.all.mapper.AttendanceMapper;
import wh.fcfz.officecontroller.all.service.Impl.AttendanceServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceServiceImpl attendanceService;
    @Autowired
    private AttendanceMapper attendanceMapper;

    @SaCheckPermission("admin")
    @PostMapping("/getAllAttendance")
    public Result getAllAttendance(@RequestBody MyPage<AttendancesVo> myPage) {
        return attendanceService.getAllAttendance(myPage);
    }

    @PostMapping("/getSelfAttendance")
    public Result getSelfAttendance(@RequestBody MyPage<AttendancesVo> myPage) {
        myPage.getData().setAttendanceUserId(StpUtil.getLoginIdAsInt());
        return attendanceService.getAllAttendance(myPage);
    }

    @PostMapping("/addAttendance")
    public Result addAttendance(@RequestBody Attendance attendance) {
        return attendanceService.addAttendance(attendance);
    }
}
