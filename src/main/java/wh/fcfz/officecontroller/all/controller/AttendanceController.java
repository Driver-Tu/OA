package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Dao.Attendance;
import wh.fcfz.officecontroller.all.bean.Vo.AttendancesVo;
import wh.fcfz.officecontroller.all.mapper.AttendanceMapper;
import wh.fcfz.officecontroller.all.service.Impl.AttendanceServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceServiceImpl attendanceService;
    @Autowired
    private AttendanceMapper attendanceMapper;

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

    /**
     * 获取当月打卡次数
     */
    @GetMapping("getCountByMonth")
    @Operation(summary = "获取当月打卡次数")
    public Result<Map<String, Integer>> getCountByMonth(@RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        List<Attendance> countByMonth = attendanceMapper.getCountByMonth(year, month, StpUtil.getLoginIdAsInt());
        Map<String,Integer> map = countByMonth.stream().parallel().collect(Collectors.groupingBy(Attendance::getStatus,Collectors.summingInt(e->1)));
        //将map的键换成英文
        Integer i = map.get("打卡成功");
        map.put("success",i==null?0:i);
        map.remove("打卡成功");
        i = map.get("打卡失败");
        map.put("fail",i==null?0:i);
        map.remove("打卡失败");
        return new Result<>(ResponseEnum.SUCCESS,map);
    }
}
