package wh.fcfz.officecontroller.all.tool;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Paths;

import static wh.fcfz.officecontroller.all.tool.ResponseEnum.*;

@Slf4j
@Component
public class FileUtil {

    @Value("${my.files.upload.path}")
    private String fileRootPath;


    // 批量存储文件
    public Result<String> uploadFile(MultipartFile[] files, String filePathByType, Integer uuid) {
        // 空值检查
        if (files == null || files.length == 0) {
            return new Result<>(FILE_IS_NULL, null);
        }
        if (filePathByType == null || filePathByType.trim().isEmpty()) {
            return new Result<>(FILE_UPLOAD_ERROR, null);
        }
        if (uuid == null || uuid.toString().trim().isEmpty()) {
            return new Result<>(FILE_UPLOAD_ERROR, null);
        }

        try {
            boolean allFilesUploaded = true;
            // 文件存储逻辑
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (fileName != null && !fileName.trim().isEmpty()) {
                    String path = getNowFilePath(filePathByType, uuid);
                    String savePath = saveFile(file, path);
                    log.info(fileName + "文件上传成功，路径为：" + savePath);
                } else {
                    allFilesUploaded = false;
                    log.warn("文件名为空，跳过文件上传");
                }
            }
            if (allFilesUploaded) {
                return new Result<>(SUCCESS, "所有文件已经存储");
            } else {
                return new Result<>(FILE_UPLOAD_PARTIALLY_SUCCESS, "部分文件上传成功");
            }
        } catch (IOException e) {
            log.error("文件上传失败: " + e.getMessage());
            return new Result<>(FILE_UPLOAD_ERROR, e.getMessage());
        }
    }



    // 文件存储方法
    private String saveFile(MultipartFile file, String path) throws IOException {
        // 创建以 UUID 命名的目录
        String uuidDirectory = UUID.randomUUID().toString();
        File uploadDirFile = new File(Paths.get(path, uuidDirectory).toString());
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // 拼接原文件路径
        String filePath = Paths.get(uploadDirFile.getPath(), file.getOriginalFilename()).toString();
        File destFile = new File(filePath);
        file.transferTo(destFile); // 保存文件
        return filePath;
    }

    // 整合特定文件的路径
    public String getNowFilePath(String filePathByType, Integer uuid) {
        // 空值检查
        if(fileRootPath == null || fileRootPath.trim().isEmpty()) {
            throw new IllegalStateException("fileRootPath cannot be null or empty");
        }
        if (filePathByType == null || filePathByType.trim().isEmpty()) {
            throw new IllegalArgumentException("filePathByType cannot be null or empty");
        }
        if (uuid == null || uuid.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("UUID cannot be null or empty");
        }

        Object loginId = StpUtil.getLoginId();
        if (loginId == null) {
            throw new IllegalArgumentException("Login ID cannot be null");
        }

        // 获取当前日期时间信息
        DateTime now = DateTime.now();

        // 使用 DateTime 处理年、月、日
        int year = now.year();
        int month = now.month();
        int dayOfMonth = now.dayOfMonth();

        // 使用 Paths.get() 拼接根目录和文件路径，确保路径拼接正确
        return Paths.get(
//                SystemConfig.FILE_UPLOAD_ROOT_DIR,
                fileRootPath,
                filePathByType,
                String.valueOf(loginId),     // 将 loginId 转为 String
                String.valueOf(year),
                String.valueOf(month),
                String.valueOf(dayOfMonth),
                String.valueOf(uuid)
        ).toString();
    }
}
