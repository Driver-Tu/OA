package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wh.fcfz.officecontroller.all.bean.Menu;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT user.role_id FROM user WHERE user_id = #{userId}")
    Integer selectByPermission(Integer userId);

    @Select("select * from menu where permission>=#{permission}")
    List<Menu> selectByPermissionMenu(Integer permission);
}