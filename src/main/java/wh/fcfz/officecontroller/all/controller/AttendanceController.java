package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.converters.date.DateStringConverter;
import com.alibaba.excel.util.MapUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import wh.fcfz.officecontroller.all.bean.Dao.Attendance;
import wh.fcfz.officecontroller.all.bean.Dao.User;
import wh.fcfz.officecontroller.all.bean.Vo.AttendancesVo;
import wh.fcfz.officecontroller.all.bean.Vo.UserSchedulingVo;
import wh.fcfz.officecontroller.all.bean.excel.AttendanceExport;
import wh.fcfz.officecontroller.all.bean.rest.RestBean;
import wh.fcfz.officecontroller.all.bean.rest.RestWorkData;
import wh.fcfz.officecontroller.all.mapper.AttendanceMapper;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.mapper.UserSchedulingMapper;
import wh.fcfz.officecontroller.all.service.Impl.AttendanceServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.all.tool.SpringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attendance")
@Slf4j
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
    public void downloadExcel(AttendancesVo attendanceVo, HttpServletResponse response) throws IOException {

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


    /**
     * 定时调度，进行未打卡的人也要进行打卡
     */
    //每天四点下午调用
    @Scheduled(cron = "0 0 19 * * ?")
    @GetMapping("/autoAttendance")
    public void autoAttendance() {
        Map<Integer, Set<Integer>> map = GetUserIds();
        //获取集合为空
        String date = DateUtil.format(new Date(), "yyyy-MM-dd");
        log.info("开始进行自动打卡,自动打卡日期为{}", date);
        boolean sysWork = isWork(date);
        map.forEach((key, value) -> {
           if (value.isEmpty()) {
               //则使用系统规则
               if(sysWork){
                   if(attendanceMapper.getTodayAttendance(key)==null){
                       Attendance attendance = new Attendance();
                       attendance.setAttendanceUserId(key);
                       attendance.setStatus("未打卡");
                       attendance.setDate(new java.sql.Date(System.currentTimeMillis()));
                       int insert = attendanceMapper.insert(attendance);
                       if (insert == 1) {
                           log.info("用户{}未打卡，已自动生成打卡记录，打卡结果为未完成", key);
                       }
                   }
               }
           }else {
               //判断用户绑定的规则日中是否包含今天
               value.forEach(e->{
                   if(e==new Date(System.currentTimeMillis()).getDate()){
                       //判断用户今天是否打卡
                       if(attendanceMapper.getTodayAttendance(key)==null){
                           Attendance attendance = new Attendance();
                           attendance.setAttendanceUserId(key);
                           attendance.setStatus("未打卡");
                           attendance.setDate(new java.sql.Date(System.currentTimeMillis()));
                           int insert = attendanceMapper.insert(attendance);
                           if (insert == 1) {
                               log.info("用户{}未打卡，已自动生成打卡记录，打卡结果为未完成", key);
                           }
                       }
                   }
               });
           }
       });
    }

    private static boolean isWork(String date) {
        //判断用户是否绑定了规则
        RestTemplate restTemplate = SpringUtils.getBean(RestTemplate.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://date.appworlds.cn/work?date="+ date, String.class);
        log.info("\n code:{}\n header:{}\n body:{}\n",responseEntity.getStatusCodeValue(),responseEntity.getHeaders(),responseEntity.getBody());
        //判断今天是否是工作日
        RestBean restBean = JSONUtil.toBean(responseEntity.getBody(), RestBean.class);
        if (restBean.getCode() == 200) {
            RestWorkData restWorkData = JSONUtil.toBean(restBean.getData(), RestWorkData.class);
            if(restWorkData.getWork()){
                return true;
            }else {
                //错误的话就不执行,输出今日不是工作日的日志
                log.error("今日不是工作日,不需要打卡");
            }
        }else {
            log.error("获取工作日失败,错误码{},错误信息{}", restBean.getCode(),restBean.getMsg());
        }
        return false;
    }


    @Autowired
    private UserSchedulingMapper userSchedulingMapper;
    @Autowired
    private UserMapper userMapper;
    //获取未绑定规则的userId
    //获取绑定规则的userId
    private Map<Integer,Set<Integer>> GetUserIds(){
        Map<Integer,Set<Integer>> map=new HashMap<>();
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>());
        users=users.stream().filter(user -> user.getRoleId()!= 1).toList();
        //获取当前所处月份xxxx-xx
        String date = DateUtil.format(new Date(), "yyyy-MM");
       List<UserSchedulingVo> userSchedulings= userSchedulingMapper.selectUsers(date);
           for (UserSchedulingVo userSchedulingVo : userSchedulings) {
               if(map.containsKey(userSchedulingVo.getUserId())){
                   List<Integer> list = JSONUtil.toList(userSchedulingVo.getRule(), Integer.class);
                   for (Integer i : list) {
                       map.get(userSchedulingVo.getUserId()).add(i);
                   }
               }else {
                   List<Integer> list = JSONUtil.toList(userSchedulingVo.getRule(), Integer.class);
                   Set<Integer> set = new HashSet<>(list);
                   map.put(userSchedulingVo.getUserId(),set);
               }
           }
           for (User user : users) {
               if(!map.containsKey(user.getUserId())){
                   map.put(user.getUserId(),new HashSet<>());
               }
           }
       return map;
    }

}