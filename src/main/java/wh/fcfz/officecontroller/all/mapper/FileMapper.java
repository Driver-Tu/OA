package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.File;

import java.util.List;

@Mapper
public interface FileMapper extends BaseMapper<File> {
    int insertBatch(List<File> files);
}
