package wh.fcfz.officecontroller.all.mapper;

import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.UserScheduling;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wh.fcfz.officecontroller.all.bean.Vo.UserSchedulingVo;

import java.util.List;

/**
* @author admin
* @description 针对表【user_scheduling】的数据库操作Mapper
* @createDate 2024-12-30 13:57:48
* @Entity wh.fcfz.officecontroller.all.bean.Dao.UserScheduling
*/
@Mapper
public interface UserSchedulingMapper extends BaseMapper<UserScheduling> {

    List<UserSchedulingVo> selectUsers(String date);
}




