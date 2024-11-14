package wh.fcfz.officecontroller.all.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.mapper.FileMapper;
import wh.fcfz.officecontroller.all.service.FileService;
import wh.fcfz.officecontroller.all.tool.AliOssUtil;
import wh.fcfz.officecontroller.all.tool.FileUtil;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.config.file.SystemConfig;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileTestController {
    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private FileService fileService; // 注入 FileMapper

    @Autowired
    private FileMapper fileMapper;

    @PostMapping("/uploadFile")
    @Transactional
    public Result<List<String>> uploadFile(
            @RequestParam List<MultipartFile> files,
            @RequestParam String businessType, // 假设传递业务类型
            @RequestParam Integer businessId // 假设传递业务 ID
    ) {
        try {
            // 调用Service层的方法进行文件上传
            List<String> fileUUIDs = fileService.uploadFile(files, businessType, businessId);
            return new Result("200","文件上传成功", fileUUIDs); // 返回成功结果和文件 ID 列表
        } catch (Exception e) {
            // 处理异常情况
            return new Result("500", "文件上传失败", null);
        }
    }


    @GetMapping("/downloadFile")
    public Result downloadFile(@RequestParam Integer fileId, HttpServletResponse response) {

        // 根据参数 fileId 从数据库查询文件
        File file = fileMapper.selectById(fileId);

        // 如果文件不存在，返回404错误
        if (file == null) {

        }

        String fileName = file.getFileUuid() + "_" + file.getFileName();

        aliOssUtil.download(fileName, file.getFileName(), response);
        return new Result<>("200", "操作成功", null);
    }

    @GetMapping("/downloadFileBig")
    public Result downloadFileBig(@RequestParam String fileName, @RequestParam String originalFileName, HttpServletResponse response) {

        aliOssUtil.download(fileName, originalFileName, response);
        return new Result<>("200", "操作成功", null);
    }


    @PostMapping("/reimburse")
    public Result<String> test(@RequestParam MultipartFile[] files, @RequestParam Integer reimburseId) {
        boolean success = fileUtil.uploadFile(files, SystemConfig.DECLARE_REIMBURSE_FILE_DIR, reimburseId);

        if (success) {
            return new Result<>(ResponseEnum.SUCCESS, "所有文件已经存储");
        } else {
            return new Result<>(ResponseEnum.FILE_UPLOAD_ERROR, "文件上传失败");
        }
    }

    @GetMapping("/image/{fileId}")
    public Result<Void> getImage(HttpServletResponse response,
                         @PathVariable Integer fileId) {
        try {
            // 调用 Service 层读取并返回文件流
            fileService.getFileAsResource(response, fileId);
        } catch (Exception e) {
            // 处理异常并返回 500 状态
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("文件读取失败: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return new Result<>("200", "操作成功", null);
    }

    @GetMapping("/resource/base64/{objectName}")
    public Result<String> getResourceAsBase64(@PathVariable String objectName) {
        String base64Data = aliOssUtil.readResource(objectName);
        if (base64Data != null) {
            return new Result<>("200", "操作成功", base64Data);
        } else {
            return new Result<>("500", "读取资源失败", null);
        }
    }


}
