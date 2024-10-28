package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Dao.Report;
import wh.fcfz.officecontroller.all.bean.Dto.ReportDto;
import wh.fcfz.officecontroller.all.bean.Dto.UserDto;
import wh.fcfz.officecontroller.all.bean.Vo.ReportVo;
import wh.fcfz.officecontroller.all.bean.Vo.UserOnVo;
import wh.fcfz.officecontroller.all.bean.Vo.UserVo;
import wh.fcfz.officecontroller.all.mapper.ReportMapper;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.Impl.ReportServiceImpl;
import wh.fcfz.officecontroller.all.service.Impl.UserServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public Result<Page<ReportVo>> shareReport(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        ReportDto reportDto = new ReportDto();
        reportDto.setShare("1");
        List<ReportVo> reportVos = reportMapper.selectReport(reportDto);
        List<ReportVo> list = new ArrayList<>();
        reportVos.stream().forEach(reportVo -> {
            String[] split = reportVo.getShareUserId().split(",");
            log.error(Arrays.toString(split));
            int loginIdAsInt = StpUtil.getLoginIdAsInt();
            for (String s : split) {
                log.info(s);
                if (Integer.parseInt(s) == loginIdAsInt) {
                    //只要有一个是对的就添加该条数据
                    list.add(reportVo);
                }
            }
        });
        Page<ReportVo> page = new Page<>(pageNum,pageSize);
        List<ReportVo> collect = list.stream().skip((long) (pageNum - 1) * pageSize).limit(pageSize).toList();
        page.setRecords(collect);
        page.setTotal(list.size());
        return new Result<>(ResponseEnum.SUCCESS, page);
    }

    @PostMapping("/shareReportToUser")
    public Result<List<UserOnVo>> shareReportToUser(@RequestBody List<Integer> ids) {
        List<UserOnVo> userOnVos=new ArrayList<>();
        for (Integer id : ids) {
            UserDto userDto = new UserDto();
            userDto.setUserId(id);
            List<UserVo> userVos = userMapper.selectUserList(userDto);
            UserOnVo userOnVo = new UserOnVo();
            //只可能有一个
            BeanUtil.copyProperties(userVos.get(0), userOnVo);
            userOnVos.add(userOnVo);
        }
        return new Result<>(ResponseEnum.SUCCESS, userOnVos);
    }


}
