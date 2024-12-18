package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Dao.Report;
import wh.fcfz.officecontroller.all.bean.Dto.ReportDto;
import wh.fcfz.officecontroller.all.bean.Vo.ReportVo;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ReportService  extends IService<Report> {
    //增加日报
    public Result<Boolean> addReport(ReportDto reportDto) throws IOException;

    //删除日报
    public Result<Boolean> deleteReport(List<Integer> reportId);

    //查询日报
    public Result<Page<ReportVo>> selectReport(MyPage<ReportDto> myPage);

    //修改日报
    public Result<Report> updateReport(ReportDto report);

    Result<Map<String, Integer>> getSelfReportCount(Integer year,Integer month,Integer userId);
}
