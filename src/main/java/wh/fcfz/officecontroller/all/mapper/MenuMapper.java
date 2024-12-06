package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wh.fcfz.officecontroller.all.bean.Dao.Menu;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT user.role_id FROM user WHERE user_id = #{userId}")
    Integer selectByPermission(Integer userId);

    @Select("select * from menu where perms_id>=#{permsId} and visible = '0' and status='0'")
    List<Menu> selectByPermissionMenu(Integer permsId);
}
