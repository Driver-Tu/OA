package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Dao.Report;
import wh.fcfz.officecontroller.all.mapper.ReportMapper;
import wh.fcfz.officecontroller.all.service.ReportService;
import wh.fcfz.officecontroller.all.tool.Result;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
    @Autowired
    private ReportMapper reportMapper;


    @Override
    public Result<Report> addReport(Report report) {

        return null;
    }

    @Override
    public Result<String> deleteReport(Integer reportId) {
        return null;
    }

    @Override
    public Result<Report> selectReport(Integer reportId) {
        return null;
    }

    @Override
    public Result<Report> updateReport(Report report) {
        return null;
    }
}
