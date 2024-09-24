package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wh.fcfz.officecontroller.all.bean.Attendance;

@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {

    @Select("select u.user_name from user as u where u.user_id =#{attendanceId}")
    String selectAllUserName(Integer attendanceId);

    @Select("select d.depart_name from depart as d,user as u where u.user_id =#{attendanceId} and u.department_id=d.depart_id")
    String selectAllDepartName(Integer attendanceId);
}
