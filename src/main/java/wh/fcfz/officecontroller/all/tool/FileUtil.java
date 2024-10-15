package wh.fcfz.officecontroller.all.tool;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


@Slf4j
@Component
public class FileUtil {

    @Value("${my.files.upload.path}")
    private String fileRootPath;


    // 批量存储文件
    // 修改 uploadFile 方法以返回布尔值或状态信息
    public boolean uploadFile(MultipartFile[] files, String filePathByType, Integer uuid) {
        // 空值检查
        if (files == null || files.length == 0) {
            log.warn("文件为空");
            return false; // 返回 false 表示上传失败
        }
        if (filePathByType == null || filePathByType.trim().isEmpty()) {
            log.warn("文件路径为空");
            return false; // 返回 false 表示上传失败
        }
        if (uuid == null) {
            log.warn("UUID 为空");
            return false; // 返回 false 表示上传失败
        }

        try {
            boolean allFilesUploaded = true;
            // 文件存储逻辑
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (fileName != null && !fileName.trim().isEmpty()) {
                    String path = getNowFilePath(filePathByType, uuid);
                    String savePath = saveFile(file, path);
                    log.info(fileName + " 文件上传成功，路径为：" + savePath);
                } else {
                    allFilesUploaded = false;
                    log.warn("文件名为空，跳过文件上传");
                }
            }
            return allFilesUploaded; // 返回 true 表示所有文件都上传成功
        } catch (IOException e) {
            log.error("文件上传失败: " + e.getMessage());
            return false; // 返回 false 表示上传失败
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
