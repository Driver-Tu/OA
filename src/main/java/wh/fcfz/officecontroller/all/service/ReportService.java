package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Dao.Report;
import wh.fcfz.officecontroller.all.tool.Result;

public interface ReportService  extends IService<Report> {
    //增加日报
    public Result<Report> addReport(Report report);

    //删除日报
    public Result<String> deleteReport(Integer reportId);

    //查询日报
    public Result<Report> selectReport(Integer reportId);

    //修改日报
    public Result<Report> updateReport(Report report);
}
