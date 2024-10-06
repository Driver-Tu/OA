package wh.fcfz.officecontroller.all.service.Impl;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Attendance;
import wh.fcfz.officecontroller.all.bean.User;
import wh.fcfz.officecontroller.all.dto.AttendancesMessage;
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
import java.util.Date;
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
    public Result getAllAttendance(MyPage<Attendance> myPage) {
        Page<AttendancesMessage> page = new Page<>(myPage.getPageNum(), myPage.getPageSize());
        List<AttendancesMessage> attendancesMessages = attendanceMapper.selectAllAttendances();
        if (attendancesMessages.size() == 0) {
            //防止空数据判断
            return new Result(ResponseEnum.DATA_NOT_EXIST, null);
        }
        if(null!=myPage.getData().getAttendanceUserId()){
            User user = userMapper.selectById(myPage.getData().getAttendanceUserId());
            attendancesMessages=attendancesMessages.stream()
                    .filter(attendancesMessage -> attendancesMessage.getAttendanceUserName().equals(user.getUserName()))
                    .collect(Collectors.toList());
        }else {
            if(myPage.getParams().containsKey("departmentName")&&(null!=myPage.getParams().get("departmentName"))&&(!myPage.getParams().get("departmentName").equals(""))){
                Object departmentName = myPage.getParams().get("departmentName");
                attendancesMessages=attendancesMessages.stream()
                        .filter(attendancesMessage -> attendancesMessage.getAttendanceUserDepartName().equals(departmentName.toString()))
                        .collect(Collectors.toList());
            }
            if(myPage.getParams().containsKey("userName")&&(null!=myPage.getParams().get("userName"))&&(!myPage.getParams().get("userName").equals(""))){
                Object userName = myPage.getParams().get("userName");
                attendancesMessages=attendancesMessages.stream()
                        .filter(attendancesMessage -> attendancesMessage.getAttendanceUserName().equals(userName.toString()))
                        .collect(Collectors.toList());
            }
        }
        if(null!=myPage.getData().getStatus()&&(!myPage.getData().getStatus().equals(""))){
            attendancesMessages=attendancesMessages.stream()
                    .filter(attendancesMessage -> attendancesMessage.getStatus().equals(myPage.getData().getStatus()))
                    .collect(Collectors.toList());
        }
        if(null!=myPage.getData().getType()&&(!myPage.getData().getType().equals(""))){
            attendancesMessages=attendancesMessages.stream()
                    .filter(attendancesMessage -> attendancesMessage.getType().equals(myPage.getData().getType()))
                    .collect(Collectors.toList());
        }
        if(null!=myPage.getData().getDate()&&(!myPage.getData().getDate().equals(""))){
            attendancesMessages=attendancesMessages.stream()
                    .filter(attendancesMessage -> attendancesMessage.getDate().equals(myPage.getData().getDate()))
                    .collect(Collectors.toList());
        }
        List<AttendancesMessage> collect = attendancesMessages.stream().skip((long) (myPage.getPageNum() - 1) * myPage.getPageSize()).limit(myPage.getPageSize()).collect(Collectors.toList());
        page.setRecords(collect);
        page.setTotal(attendancesMessages.size());
        return new Result(ResponseEnum.SUCCESS,page);
    }

    @Override
    public Result addAttendance(Attendance attendance) {
        LambdaQueryWrapper<Attendance> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //判断用户，日期，查询今天是否打过卡，打了几次卡
        lambdaQueryWrapper.eq(Attendance::getAttendanceUserId,StpUtil.getLoginId())
                //判断今天打没打过卡
                .eq(Attendance::getDate,attendance.getDate());
        Attendance attendance1 = attendanceMapper.selectOne(lambdaQueryWrapper);
        //判断上班打卡还是下班打卡
        if(attendance1==null){
            //上班卡
            attendance.setTimeIn(new Timestamp(System.currentTimeMillis()));
            attendance.setDate(DateUtil.format(new Date(),"yyyy-MM-dd"));
            attendance.setAttendanceUserId(Integer.parseInt(StpUtil.getLoginId().toString()));
            attendanceMapper.insert(attendance);
            return new Result(ResponseEnum.SUCCESS,"上班打卡成功");
        }else {
            //下班卡
            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
            attendance1.setTimeOut(timestamp);
            //结算几今日打卡成功或者失败
            Timestamp timeIn = attendance1.getTimeIn();
            //小于0则早于九点，大于0则晚于九点，等于0则为九点（都是当天时间）
            if((timeIn.toLocalDateTime().compareTo(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0, 0)))<=0)||(timestamp.toLocalDateTime().compareTo(LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 30, 0)))>=0)){
                attendance1.setStatus("打卡成功");
                attendanceMapper.updateById(attendance1);
                return new Result(ResponseEnum.SUCCESS,"下班打卡成功");
            }else {
                attendance1.setStatus("打卡失败");
                attendanceMapper.updateById(attendance1);
                return new Result(ResponseEnum.SUCCESS,"下班打卡失败");
            }
        }
    }
}

