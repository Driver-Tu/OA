package wh.fcfz.officecontroller.all.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Attendance;
import wh.fcfz.officecontroller.all.mapper.AttendanceMapper;
import wh.fcfz.officecontroller.all.service.AttendanceService;

import java.util.List;

@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {
    @Override
    public List<Attendance> getAllAttendance() {
        LambdaQueryWrapper<Attendance> queryWrapper = new LambdaQueryWrapper<>();
        return null;
    }
}
