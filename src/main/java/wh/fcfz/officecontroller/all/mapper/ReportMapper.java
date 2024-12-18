package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.bean.Dao.Report;
import wh.fcfz.officecontroller.all.bean.Dto.ReportDto;
import wh.fcfz.officecontroller.all.bean.Vo.ReportVo;

import java.util.List;

@Mapper
public interface ReportMapper extends BaseMapper<Report> {
   //查询所有记录
    List<ReportVo> selectReport(ReportDto reportDto);

    //查询该路径下面所有file
    List<File> selectFile(Integer reportId);

    List<Report> getSelfReportCount(Integer year, Integer month, Integer userId);
}
