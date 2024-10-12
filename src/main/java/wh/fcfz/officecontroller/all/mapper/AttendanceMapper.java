package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import wh.fcfz.officecontroller.all.bean.Dao.Attendance;
import wh.fcfz.officecontroller.all.bean.Vo.AttendancesVo;

import java.util.List;

@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {

    @Select("select u.user_name from user as u where u.user_id =#{attendanceId}")
    String selectAllUserName(Integer attendanceId);

    @Select("select d.depart_name from depart as d,user as u where u.user_id =#{attendanceId} and u.department_id=d.depart_id")
    String selectAllDepartName(Integer attendanceId);


    @Select("SELECT a.attendance_id as attendance_id,u.user_name as userName,d.depart_name as departName,a.time_in as time_in,a.time_out as time_out,a.date as date,a.status as status,a.address as address,a.type as type from user as u,depart as d,attendance as a WHERE u.department_id=d.depart_id and a.attendance_user_id=u.user_id;")
    @Results({
            @Result(column = "userName", property = "attendanceUserName"),
            @Result(column = "departName", property = "attendanceUserDepartName")
    })
    List<AttendancesVo> selectAllAttendances();
}
