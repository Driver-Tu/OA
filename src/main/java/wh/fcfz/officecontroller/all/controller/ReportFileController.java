package wh.fcfz.officecontroller.all.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.bean.Dao.User;
import wh.fcfz.officecontroller.all.bean.Dto.ReportDto;
import wh.fcfz.officecontroller.all.service.Impl.ReportServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.all.tool.AliOssUtil;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/report/file")
public class ReportFileController {


    @Autowired
    private ReportServiceImpl reportService;
    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @Transactional
    public Result upload(@RequestBody MultipartFile files) throws IOException {
        try {
            if (files != null) {
                //如果文件存在，则上传至阿里云服务器
                String originalFilename = files.getOriginalFilename();
                String s = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
                String upload = aliOssUtil.upload(files.getBytes(), s);
                return new Result(ResponseEnum.SUCCESS, upload);
            } else {
                return new Result(ResponseEnum.FILE_UPLOAD_ERROR, "文件上传失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/getData")
    public Result getData(@RequestBody MyPage<ReportDto> reportDto) {
        Object user = reportDto.getParams().get("user");
        user=JSONUtil.toJsonStr(user);
        log.info("user: {}", JSONUtil.toBean((String) user, User.class));
        return new Result(ResponseEnum.SUCCESS, reportService.selectReport(reportDto));
    }
}
