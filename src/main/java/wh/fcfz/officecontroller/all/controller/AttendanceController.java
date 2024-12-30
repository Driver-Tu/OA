package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.converters.date.DateStringConverter;
import com.alibaba.excel.util.MapUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Dao.Attendance;
import wh.fcfz.officecontroller.all.bean.Vo.AttendancesVo;
import wh.fcfz.officecontroller.all.bean.excel.AttendanceExport;
import wh.fcfz.officecontroller.all.mapper.AttendanceMapper;
import wh.fcfz.officecontroller.all.service.Impl.AttendanceServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.io.IOException;
import java.net.URLEncoder;
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

    /**
     * 获取今日打卡信息
     */
    @GetMapping("getTodayAttendance")
    @Operation(summary = "获取今日打卡信息")
    public Result<Attendance> getTodayAttendance() {
        Attendance attendance = attendanceMapper.getTodayAttendance(StpUtil.getLoginIdAsInt());
        return new Result<>(ResponseEnum.SUCCESS,attendance);
    }

    @Operation(summary = "下载excel")
    @GetMapping("/exportAttendance")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        AttendancesVo attendanceVo = new AttendancesVo();

        List<AttendancesVo> attendancesVos = attendanceMapper.selectAllAttendances(attendanceVo);
        List<AttendanceExport> attendanceExports = BeanUtil.copyToList(attendancesVos, AttendanceExport.class);

        System.out.println(attendanceExports.toString());

        try {
            // 设置响应内容类型为 Excel
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");

            // 设置响应头，确保浏览器下载文件并正确显示文件名，文件名前面拼接年月日

            String fileName = "考勤记录" + System.currentTimeMillis() + ".xlsx";  // 使用英文文件名避免问题
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            设置为blob

            // 通过 EasyExcel 写入响应的输出流
            EasyExcel.write(response.getOutputStream(), AttendanceExport.class)
                    .sheet("全部打卡记录")
                    .registerConverter(new DateStringConverter())
                    .doWrite(attendanceExports);

            // 刷新输出流，确保数据写入完成
//            response.flushBuffer();
        } catch (Exception e) {
            // 如果发生错误，重置响应并返回错误信息
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "下载文件失败: " + e.getMessage());

            // 将错误信息转换为 JSON 并返回
            response.getWriter().println(JSONUtil.toJsonStr(map));
        }
    }
}
