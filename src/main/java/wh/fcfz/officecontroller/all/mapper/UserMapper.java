package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wh.fcfz.officecontroller.all.bean.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select depart.depart_name from depart where depart_id=#{departmentId}")
    String selectDepartName(Integer departmentId);
//33
    @Select("select role.role_name from role where role_id=#{roleId}")
    String selectRoleName(Integer roleId);

}
