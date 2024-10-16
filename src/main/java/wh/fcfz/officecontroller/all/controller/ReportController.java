package wh.fcfz.officecontroller.all.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Dao.Report;
import wh.fcfz.officecontroller.all.bean.Dto.ReportDto;
import wh.fcfz.officecontroller.all.bean.Vo.ReportVo;
import wh.fcfz.officecontroller.all.service.Impl.ReportServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @RequestMapping("/addReport")
    public Result<Boolean> addReport(@RequestBody ReportDto reportDto) {
        return reportService.addReport(reportDto);
    }

    @DeleteMapping("/deleteReports")
    public Result<Boolean> deleteReport(@RequestBody List<Integer> reportIds) {
        return reportService.deleteReport(reportIds);
    }

    @PostMapping("/list")
    public Result<Page<ReportVo>> selectReport(@RequestBody MyPage<ReportDto> myPage) {
        return reportService.selectReport(myPage);
    }

    @PostMapping("/updateReport")
    public Result<Report> updateReport(@RequestBody ReportDto report) {
        return reportService.updateReport(report);
    }


}
