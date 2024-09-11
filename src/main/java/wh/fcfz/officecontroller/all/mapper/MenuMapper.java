package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wh.fcfz.officecontroller.all.bean.Menu;


@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT user.role_id FROM user WHERE user_id = #{userId}")
    Integer selectByPermission(Integer userId);

    @Select("select * from menu,son_menu where permission<=#{permission} and son_menu_permission<=#{permission}")
    Integer selectByPermissionMenu(Integer permission);
}
