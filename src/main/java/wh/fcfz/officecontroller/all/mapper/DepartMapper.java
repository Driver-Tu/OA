package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wh.fcfz.officecontroller.all.bean.Depart;
import wh.fcfz.officecontroller.all.dto.UserMessage;

import java.util.List;

@Mapper
public interface DepartMapper extends BaseMapper<Depart> {

    @Select("select count(*) from user where user.department_id=#{departmentId}")
    Integer selectAllUserForDepartment(Integer departmentId);

}
