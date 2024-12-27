package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.converters.date.DateStringConverter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.bean.Dao.Report;
import wh.fcfz.officecontroller.all.bean.Dto.ReportDto;
import wh.fcfz.officecontroller.all.bean.Dto.UserDto;
import wh.fcfz.officecontroller.all.bean.Vo.ReportVo;
import wh.fcfz.officecontroller.all.bean.Vo.UserOnVo;
import wh.fcfz.officecontroller.all.bean.Vo.UserVo;
import wh.fcfz.officecontroller.all.bean.excel.ReportExport;
import wh.fcfz.officecontroller.all.mapper.ReportMapper;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.Impl.ReportServiceImpl;
import wh.fcfz.officecontroller.all.service.Impl.UserServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReportMapper reportMapper;

    @PostMapping("/addReport")
    public Result<Boolean> addReport(@RequestBody ReportDto reportDto) {
        return reportService.addReport(reportDto);
    }

    @DeleteMapping("/deleteReports")
    public Result<Boolean> deleteReport(@RequestBody List<Integer> reportIds) {
        return reportService.deleteReport(reportIds);
    }

    @SaCheckPermission(value={"admin","boss"}, mode= SaMode.OR)
    @PostMapping("/list")
    public Result<Page<ReportVo>> selectReport(@RequestBody MyPage<ReportDto> myPage) {
        return reportService.selectReport(myPage);
    }

    @PostMapping("/list-self")
    public Result<Page<ReportVo>> selectReportBySelf(@RequestBody MyPage<ReportDto> myPage) {
        myPage.getData().setReportUserId(StpUtil.getLoginIdAsInt());
        return reportService.selectReport(myPage);
    }

    @PostMapping("/updateReport")
    public Result<Report> updateReport(@RequestBody ReportDto report) {
        return reportService.updateReport(report);
    }

    //return:userOnVo
    @PostMapping("/shareReport")
    public Result<Page<ReportVo>> shareReport(@RequestBody MyPage<ReportDto> reportDto) {
        StringBuilder sb=new StringBuilder();
        sb.append("[").append(StpUtil.getLoginId().toString()).append("]");
        reportDto.getData().setShare(sb.toString());
        List<ReportVo> reportVos = reportMapper.selectReport(reportDto.getData());
        List<ReportVo> list = reportVos.stream().skip((long) (reportDto.getPageNum() - 1) * reportDto.getPageSize()).limit(reportDto.getPageSize()).toList();
        list= list.stream().parallel().peek(reportVo -> {
            List<File> files = reportMapper.selectFile(reportVo.getReportId());
            List<String> fileUrlList = files.stream().parallel().map(File::getFileUrl).toList();
            reportVo.setFileUrls(fileUrlList);
        }).toList();
        Page<ReportVo> page = new Page<>(reportDto.getPageNum(),reportDto.getPageSize());
//拿取分页的数据
        page.setRecords(list);
        page.setTotal(reportVos.size());
        return new Result<>(ResponseEnum.SUCCESS, page);
    }

    @PostMapping("/shareReportToUser")
    public Result<List<UserOnVo>> shareReportToUser(@RequestBody List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return new Result("200", "分享人为空", null);
        }
        List<UserOnVo> userOnVos=new ArrayList<>();
        for (Integer id : ids) {
            UserDto userDto = new UserDto();
            userDto.setUserId(id);
            List<UserVo> userVos = userMapper.selectUserList(userDto);
            if (userVos == null || userVos.isEmpty()) {
                continue;
            }
            UserOnVo userOnVo = new UserOnVo();
            //只可能有一个
            BeanUtil.copyProperties(userVos.get(0), userOnVo);
            userOnVos.add(userOnVo);
        }
        return new Result<>(ResponseEnum.SUCCESS, userOnVos);
    }

    /**
     * 查看自己的本月汇报数量统计
     */
    @Operation(summary = "查看自己的本月汇报数量统计")
    @GetMapping("/getSelfReportCount")
    public Result<Map<String, Integer>> getSelfReportCount(@RequestParam("year") Integer year , @RequestParam(value = "month", required = false) Integer month) {
        Integer userId = StpUtil.getLoginIdAsInt();
        return reportService.getSelfReportCount(year, month, userId);
    }

    /**
     * 下载excel
     */
    @Operation(summary = "下载excel")
    @GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        ReportDto reportDto = new ReportDto();
        StringBuilder sb=new StringBuilder();
        sb.append("[").append(StpUtil.getLoginId().toString()).append("]");
        reportDto.setShare(sb.toString());
        List<ReportVo> reportVos = reportMapper.selectReport(reportDto);
        List<ReportExport> reportExports=BeanUtil.copyToList(reportVos, ReportExport.class);
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ReportExport.class).sheet("首次导出").registerConverter(new DateStringConverter()).doWrite(reportExports);
    }
}
