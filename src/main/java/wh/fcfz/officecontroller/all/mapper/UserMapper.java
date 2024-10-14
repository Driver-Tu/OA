package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.User;
import wh.fcfz.officecontroller.all.bean.Dto.UserDto;
import wh.fcfz.officecontroller.all.bean.Vo.UserVo;
import wh.fcfz.officecontroller.all.tool.MyPage;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<UserVo>  selectUserList(MyPage<UserDto> userDto);

    String selectDepartName(Integer departmentId);
//33
    String selectRoleName(Integer roleId);

}
