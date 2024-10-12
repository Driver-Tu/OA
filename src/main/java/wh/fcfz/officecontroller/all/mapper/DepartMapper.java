package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wh.fcfz.officecontroller.all.bean.Dao.Depart;

@Mapper
public interface DepartMapper extends BaseMapper<Depart> {

    @Select("SELECT COUNT(*) FROM user WHERE department_id = #{departmentId}")
    Integer countByDepartmentId(@Param("departmentId") Integer departmentId);

    @Select("SELECT * from depart where depart_name= #{departName}")
    String getDepartName(String departName);

}
