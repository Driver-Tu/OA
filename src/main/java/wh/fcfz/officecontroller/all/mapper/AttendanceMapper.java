package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.Attendance;
import wh.fcfz.officecontroller.all.bean.Vo.AttendancesVo;

import java.util.List;

@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {
    String selectAllUserName(Integer attendanceId);

    String selectAllDepartName(Integer attendanceId);

    List<AttendancesVo> selectAllAttendances(AttendancesVo attendancesVo);

    Integer getCountByMonth(Integer year, Integer month, Integer userId);
}
