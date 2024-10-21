package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.UUID;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.mapper.FileMapper;
import wh.fcfz.officecontroller.all.service.Impl.ReportServiceImpl;
import wh.fcfz.officecontroller.all.tool.AliOssUtil;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/report/file")
public class ReportFileController {


    @Autowired
    private ReportServiceImpl reportService;
    @Autowired
    private AliOssUtil aliOssUtil;
    @Autowired
    private FileMapper fileMapper;

    @PostMapping("/upload")
    @Transactional
    public Result upload(@RequestBody MultipartFile files) throws IOException {
        try {
            if (files != null) {
                //如果文件存在，则上传至阿里云服务器
                String originalFilename = files.getOriginalFilename();
                UUID uuid = UUID.randomUUID();
                String fileType = null;
                if (originalFilename != null) {
                    fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String s =  uuid + fileType;
                String upload = aliOssUtil.upload(files, s);
                File file = new File();
                file.setFileName(originalFilename);
                file.setFileUrl(upload);
                file.setFileType(fileType);
                file.setFileSize(files.getSize());
                file.setFileUuid(uuid.toString());
                file.setUploaderId(StpUtil.getLoginIdAsInt());
                file.setUploadTime(new java.sql.Timestamp(System.currentTimeMillis()));
                file.setBusinessType("报告");
                file.setFileOwnerId(StpUtil.getLoginIdAsInt());
                file.setFileStatus((byte) 1);
                try {
                    int insert = fileMapper.insert(file);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return new Result(ResponseEnum.SUCCESS, uuid.toString());
            } else {
                return new Result(ResponseEnum.FILE_UPLOAD_ERROR, "文件上传失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/getData")
    public void getData(@RequestParam("objectName") String objectName, HttpServletResponse response) {
        aliOssUtil.readImage(objectName, response);
    }
}
