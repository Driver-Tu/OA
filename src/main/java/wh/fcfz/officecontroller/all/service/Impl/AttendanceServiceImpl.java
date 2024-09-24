package wh.fcfz.officecontroller.all.service.Impl;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Attendance;
import wh.fcfz.officecontroller.all.dto.AttendancesMessage;
import wh.fcfz.officecontroller.all.mapper.AttendanceMapper;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public Result getAllAttendance(MyPage<Attendance> myPage) {
        Page<AttendancesMessage> page = new Page<>(myPage.getPageNum(), myPage.getPageSize());
        LambdaQueryWrapper<Attendance> queryWrapper = new LambdaQueryWrapper<>();
        //查询已经打卡的数据
        queryWrapper.eq(null!=myPage.getData().getStatus()&&(!myPage.getData().getStatus().equals("")),Attendance::getStatus,myPage.getData().getStatus())
                //什么打卡方式（特殊/普通）
                .eq(null!=myPage.getData().getType()&&(!myPage.getData().getType().equals("")),Attendance::getType,myPage.getData().getType())
                //哪天
                .eq(null!=myPage.getData().getDate()&&(!myPage.getData().getDate().equals("")),Attendance::getDate,myPage.getData().getDate());
        List<Attendance> attendances = attendanceMapper.selectList(queryWrapper);
        if (attendances.size() == 0) {
            //防止空数据判断
            return new Result(ResponseEnum.DATA_NOT_EXIST, null);
        }
        List<AttendancesMessage> attendancesMessages = attendances.stream().map(attendance -> {
            String userName = attendanceMapper.selectAllUserName(attendance.getAttendanceUserId());
            String DepartName = attendanceMapper.selectAllDepartName(attendance.getAttendanceUserId());
            AttendancesMessage attendancesMessage = new AttendancesMessage();
            BeanUtil.copyProperties(attendance, attendancesMessage);
            attendancesMessage.setAttendanceUserName(userName);
            attendancesMessage.setAttendanceUserDepartName(DepartName);
            if(null!=myPage.getDepartmentName()&&(!myPage.getDepartmentName().equals(""))){
                if(attendancesMessage.getAttendanceUserDepartName().equals(myPage.getDepartmentName())) return attendancesMessage;
                else return null;
            }else if(null!=myPage.getUserName()&&(!myPage.getUserName().equals(""))){
                if(attendancesMessage.getAttendanceUserName().equals(myPage.getUserName())) return attendancesMessage;
                else return null;
            }else if((null!=myPage.getDepartmentName()&&(!myPage.getDepartmentName().equals("")))&&(null!=myPage.getUserName()&&(!myPage.getUserName().equals("")))){
                if(attendancesMessage.getAttendanceUserName().equals(myPage.getUserName())&&attendancesMessage.getAttendanceUserDepartName().equals(myPage.getDepartmentName())) return attendancesMessage;
                else return null;
            }else {
                return attendancesMessage;
            }
        }).collect(Collectors.toList());
        attendancesMessages.removeIf(Objects::isNull);
        List<AttendancesMessage> collect = attendancesMessages.stream().skip((long) (myPage.getPageNum() - 1) * myPage.getPageSize()).limit(myPage.getPageSize()).collect(Collectors.toList());
        page.setRecords(collect);
        page.setTotal(attendancesMessages.size());
        if(attendancesMessages.size()!=0) {
            return new Result(ResponseEnum.SUCCESS,page);
        }
        else
            log.error(page.getRecords().toString());
            return new Result(ResponseEnum.DATA_NOT_EXIST,null);
    }

    @Override
    public Result getUserAttendance(MyPage<Attendance> myPage) {
        Page<AttendancesMessage> page = new Page<>(myPage.getPageNum(), myPage.getPageSize());
        LambdaQueryWrapper<Attendance> queryWrapper = new LambdaQueryWrapper<>();
        //查询已经打卡的数据
        queryWrapper.eq(null != myPage.getData().getStatus() && (!myPage.getData().getStatus().equals("")), Attendance::getStatus, myPage.getData().getStatus())
                //什么打卡方式（特殊/普通）
                .eq(null != myPage.getData().getType() && (!myPage.getData().getType().equals("")), Attendance::getType, myPage.getData().getType())
                //哪天
                .eq(null != myPage.getData().getDate() && (!myPage.getData().getDate().equals("")), Attendance::getDate, myPage.getData().getDate())
                .eq(Attendance::getAttendanceUserId, StpUtil.getLoginId());
        List<Attendance> attendances = attendanceMapper.selectList(queryWrapper);
        if (attendances.size() == 0) {
            //防止空数据判断
            return new Result(ResponseEnum.DATA_NOT_EXIST, null);
        }
        //处理数据格式
        List<AttendancesMessage> attendancesMessages = attendances.stream().map(attendance -> {
            String userName = attendanceMapper.selectAllUserName(attendance.getAttendanceUserId());
            String DepartName = attendanceMapper.selectAllDepartName(attendance.getAttendanceUserId());
            AttendancesMessage attendancesMessage = new AttendancesMessage();
            BeanUtil.copyProperties(attendance, attendancesMessage);
            attendancesMessage.setAttendanceUserName(userName);
            attendancesMessage.setAttendanceUserDepartName(DepartName);
            return attendancesMessage;
        }).collect(Collectors.toList());
        List<AttendancesMessage> collectUser = attendancesMessages.stream().skip((long) (myPage.getPageNum() - 1) * myPage.getPageSize()).limit(myPage.getPageSize()).collect(Collectors.toList());
        page.setRecords(collectUser);
        page.setTotal(attendancesMessages.size());
        if (attendancesMessages.size() != 0) {
            return new Result(ResponseEnum.SUCCESS, page);
        } else {
            //处理完如果没有数据
            log.error(page.getRecords().toString());
            return new Result(ResponseEnum.DATA_NOT_EXIST, null);
        }
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
            if((timeIn.toLocalDateTime().compareTo(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0, 0)))<=0)&&(timestamp.toLocalDateTime().compareTo(LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 30, 0)))>=0)){
                attendance1.setStatus("打卡成功");
                attendanceMapper.updateById(attendance1);
                return new Result(ResponseEnum.SUCCESS,"打卡成功");
            }else {
                attendance1.setStatus("打卡失败");
                attendanceMapper.updateById(attendance1);
                return new Result(ResponseEnum.SUCCESS,"打卡失败");
            }
        }
    }

    @Override
    public Result updateAttendance(Attendance attendance) {
        return null;
    }

    @Override
    public Result deleteAttendance(Integer attendanceId) {
        return null;
    }


}

