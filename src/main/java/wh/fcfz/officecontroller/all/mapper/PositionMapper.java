package wh.fcfz.officecontroller.all.mapper;

import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【position(岗位信息表，用于存储公司内部各个岗位的基本信息)】的数据库操作Mapper
* @createDate 2024-11-21 09:56:54
* @Entity wh.fcfz.officecontroller.all.bean.Dao.Position
*/
@Mapper
public interface PositionMapper extends BaseMapper<Position> {

}




