package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.bean.Dao.Report;
import wh.fcfz.officecontroller.all.bean.Dto.ReportDto;
import wh.fcfz.officecontroller.all.bean.Vo.ReportVo;
import wh.fcfz.officecontroller.all.mapper.FileMapper;
import wh.fcfz.officecontroller.all.mapper.ReportMapper;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.ReportService;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public Result<Boolean> addReport(ReportDto reportdto) {
        log.info(reportdto.toString());
        //将传递的基本信息添加进来
        Report report = new Report();
        BeanUtil.copyProperties(reportdto, report);
        report.setReportUserId(StpUtil.getLoginIdAsInt());
        //两个时间ct和up值获取
        report.setCtDate(Timestamp.valueOf(java.time.LocalDateTime.now()));
        //添加时为第一次修改，值一样
        report.setUpDate(report.getCtDate());
        report.setReportDate(Date.valueOf(LocalDate.now()));
        //全部存在这个路径里面，加type为了分组
        //获取分享人的id
        if(!reportdto.getUserIDS().isEmpty()){
            List<String> userIDS = reportdto.getUserIDS();
            StringBuilder userIDStr = new StringBuilder();
            for (int i = 0; i < userIDS.size(); i++) {
                if(i<userIDS.size()-1){
                    userIDStr.append(userIDS.get(i)).append(",");
                }else{
                    userIDStr.append(userIDS.get(i));
                }
            }
            report.setShareUserId(userIDStr.toString());
        }
        try {
            this.save(report);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //获取文件的uuid集合，查询每一个file数据，修改业务id
        if (reportdto.getFilePath() != null) {
            List<String> filePaths = reportdto.getFilePath();
            for (String uuid : filePaths) {
                File file = fileMapper.selectOne(new LambdaQueryWrapper<File>().eq(File::getFileUuid, uuid));
                try {
                    file.setBusinessId(report.getReportId());
                    fileMapper.updateById(file);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return new Result<>(ResponseEnum.SUCCESS, true);
    }

    @Override
    @Transactional
    public Result<Boolean> deleteReport(List<Integer> reportIds) {
        try {
            reportIds.stream().parallel().forEach(reportId -> {
                if (!reportMapper.selectFile(reportId).isEmpty()) {
                    //查询所有日报对应reportId的file，并且删除所有report文件
                    try {
                        fileMapper.deleteBatchIds(reportMapper.selectFile(reportId).stream().map(File::getId).toList());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            int i = reportMapper.deleteBatchIds(reportIds);
            if (i > 0) return new Result<>(ResponseEnum.SUCCESS, true);
            else return new Result<>(ResponseEnum.DELETE_SERVER_FAILED, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result<Page<ReportVo>> selectReport(MyPage<ReportDto> myPage) {
        List<ReportVo> reportVos = reportMapper.selectReport(myPage.getData());
        List<ReportVo> list = reportVos.stream().parallel().peek(reportVo -> {
            List<File> files = reportMapper.selectFile(reportVo.getReportId());
            List<String> fileUrlList = files.stream().parallel().map(File::getFileUrl).toList();
            reportVo.setFileUrls(fileUrlList);
        }).toList();
        if(myPage.getData().getReportUserId()!=null){
           list=list.stream().filter(reportVo -> Objects.equals(reportVo.getReportUserId(), myPage.getData().getReportUserId())).toList();
        }
        List<ReportVo> collect = list.stream()
                .skip((long) (myPage.getPageNum() - 1) * myPage.getPageSize())
                .limit(myPage.getPageSize()).toList();
        Page<ReportVo> pageMessages = new Page<>(myPage.getPageNum(), myPage.getPageSize());
        pageMessages.setRecords(collect);
        pageMessages.setTotal(reportVos.size());
        return new Result(ResponseEnum.SUCCESS, pageMessages);
    }

    @Override
    @Transactional
    public Result<Report> updateReport(ReportDto reportdto) {
        Report report = reportMapper.selectById(reportdto.getReportId());
        //内容
        report.setContent(reportdto.getContent());
        //汇报时间
        report.setReportDate(Date.valueOf(LocalDate.now()));
        //汇报名称
        report.setReportName(reportdto.getReportName());
        //汇报类型
        report.setType(reportdto.getType());
        //修改时间
        report.setUpDate(Timestamp.valueOf(java.time.LocalDateTime.now()));
        if (report.getFilePath() != null) {
            List<String> filePaths = reportdto.getFilePath();
            StringBuilder FilePathAll = new StringBuilder();
            for (int i = 0; i < filePaths.size(); i++) {
                if (i != filePaths.size() - 1) {
                    FilePathAll.append(filePaths.get(i)).append(",");
                } else {
                    FilePathAll.append(filePaths.get(i));
                }
            }
            report.setFilePath(FilePathAll.toString());
        }
        try {
            int i = reportMapper.updateById(report);
            if (i > 0) return new Result(ResponseEnum.SUCCESS, report);
            else return new Result(ResponseEnum.UPDATE_SERVER_ERROR, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
