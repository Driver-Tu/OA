package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.UUID;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.mapper.FileMapper;
import wh.fcfz.officecontroller.all.tool.AliOssUtil;
import wh.fcfz.officecontroller.all.tool.FileUtil;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.config.file.SystemConfig;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileTestController {
    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private FileMapper fileMapper; // 注入 FileMapper

    @PostMapping("/uploadFile")
    @Transactional
    public Result<List<Integer>> uploadFile(
            @RequestParam List<MultipartFile> files,
            @RequestParam String businessType, // 假设传递业务类型
            @RequestParam Integer businessId // 假设传递业务 ID
    ) throws IOException {

        List<File> fileEntities = files.stream()
                .map(file -> {
                    try {
                        // 避免上传的文件重名，重新生成一个文件名
                        String fileUUID = String.valueOf(UUID.randomUUID());
                        String newFileName = fileUUID + "_" + file.getOriginalFilename();
                        // 创建 File 对象并设置属性
                        File fileEntity = new File();
                        fileEntity.setFileName(file.getOriginalFilename());
                        fileEntity.setFileUuid(fileUUID); // 存储生成的 UUID 文件名
                        fileEntity.setFilePath("/"); // 存储路径（可以根据需求调整）
                        fileEntity.setFileSize(file.getSize());
                        fileEntity.setFileType(file.getContentType());
                        fileEntity.setUploaderId(StpUtil.getLoginIdAsInt());
                        fileEntity.setFileOwnerId(StpUtil.getLoginIdAsInt());
                        fileEntity.setUploadTime(new Timestamp(System.currentTimeMillis())); // 设置上传时间
                        fileEntity.setBusinessType(businessType);
                        fileEntity.setBusinessId(businessId);
                        fileEntity.setFileStatus((byte) 1); // 状态设为正常
                        String fileUrl = null;
                        fileUrl = aliOssUtil.upload(file.getBytes(), newFileName);
                        fileEntity.setFileUrl(fileUrl);
                        // 将文件信息批量插入数据库
                        try {
                            fileMapper.insert(fileEntity);
                        } catch (Exception e) {
                            throw new RuntimeException("文件插入数据库失败", e);
                        }
                        // 返回文件在 OSS 中的访问地址
                        return fileEntity;
                    } catch (IOException e) {
                        // 处理文件上传异常，您可以选择记录日志或抛出自定义异常
                        throw new RuntimeException("文件上传失败: " + file.getOriginalFilename(), e);
                    }
                }).toList();
        // 收集插入后的 ID
        List<Integer> fileIds = fileEntities.stream().parallel()
                .map(File::getId) // 确保 File 类中有 id 属性
                .collect(Collectors.toList());
        // 返回操作成功的结果，包含所有文件的 URL
        return new Result<>("200", "操作成功", fileIds);
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

    @GetMapping("/images")
    public ResponseEntity<Resource> getFile() {
        // 构建文件的完整路径，添加 file: 前缀
        String filePath = "https://fcfz-oa.oss-cn-shanghai.aliyuncs.com/a74a5d5d-e731-40c2-a558-ee349ed31750.png";

        // 加载资源
        Resource resource = resourceLoader.getResource(filePath);

        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
