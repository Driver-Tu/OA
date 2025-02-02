package wh.fcfz.officecontroller.all.service.Impl;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Dao.Attendance;
import wh.fcfz.officecontroller.all.bean.Vo.AttendancesVo;
import wh.fcfz.officecontroller.all.mapper.ApprovalFormsMapper;
import wh.fcfz.officecontroller.all.mapper.AttendanceMapper;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.AttendanceService;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;
    @Autowired
    private ApprovalFormsMapper approvalFormsMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result getAllAttendance(MyPage<AttendancesVo> myPage) {
        Page<AttendancesVo> page = new Page<>(myPage.getPageNum(), myPage.getPageSize());
        List<AttendancesVo> attendancesVos = attendanceMapper.selectAllAttendances(myPage.getData());
        List<AttendancesVo> collect;
        if(myPage.getData().getAttendanceUserId()==null){
            collect = attendancesVos.stream().skip((long) (myPage.getPageNum() - 1) * myPage.getPageSize()).limit(myPage.getPageSize()).collect(Collectors.toList());
       }else {
            collect=attendancesVos;
        }
        page.setRecords(collect);
        page.setTotal(attendancesVos.size());
        return new Result(ResponseEnum.SUCCESS,page);
    }

    @Override
    public Result addAttendance(Attendance attendance) {
        LambdaQueryWrapper<Attendance> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 判断用户，日期，查询今天是否打过卡，打了几次卡
        lambdaQueryWrapper.eq(Attendance::getAttendanceUserId, StpUtil.getLoginId())
                // 判断今天打没打过卡
                .eq(Attendance::getDate, attendance.getDate());
        Attendance attendance1 = attendanceMapper.selectOne(lambdaQueryWrapper);

        // 判断上班打卡还是下班打卡
        if (attendance1 == null) {
            // 上班卡
            attendance.setTimeIn(new Timestamp(System.currentTimeMillis()));
            attendance.setDate(new Date(System.currentTimeMillis()));
            attendance.setAttendanceUserId(Integer.parseInt(StpUtil.getLoginId().toString()));
            attendance.setStatus("打卡未完成");
            attendanceMapper.insert(attendance);
            return new Result(ResponseEnum.SUCCESS, "上班打卡成功");
        } else {
            if (attendance1.getTimeOut() != null && attendance1.getTimeOut().toLocalDateTime().isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 30, 0)))) {
                return new Result(ResponseEnum.SUCCESS, "已经在五点半之前，打过卡了，无法修改");
            }
            // 下班卡
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            attendance1.setTimeOut(timestamp);
            // 结算今日打卡成功或者失败
            Timestamp timeIn = attendance1.getTimeIn();
            LocalDateTime timeInLocal = timeIn.toLocalDateTime();
            LocalDateTime timeOutLocal = timestamp.toLocalDateTime();
            LocalDateTime startOfWork = LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0, 0));
            LocalDateTime endOfWork = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 30, 0));

            if (timeInLocal.isBefore(startOfWork) && timeOutLocal.isAfter(endOfWork)) {
                attendance1.setStatus("打卡成功");
            } else if (attendance1.getTimeOut() == null || attendance1.getTimeOut().toString().isEmpty()) {
                attendance1.setStatus("打卡未完成");
            } else {
                attendance1.setStatus("打卡失败");
            }

            attendanceMapper.updateById(attendance1);
            return new Result(ResponseEnum.SUCCESS, attendance1.getStatus());
        }
    }

}

