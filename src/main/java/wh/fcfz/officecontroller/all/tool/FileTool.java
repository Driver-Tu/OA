package wh.fcfz.officecontroller.all.tool;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.config.file.SystemConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static wh.fcfz.officecontroller.all.tool.ResponseEnum.*;

public class FileTool {

    // 批量存储文件
    public static Result<String> uploadFile(MultipartFile[] files, String filePathByType, Integer uuid) {
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
            // 文件存储逻辑
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (fileName != null && !fileName.trim().isEmpty()) {
                    String path = getNowFilePath(filePathByType, uuid);
                    saveFile(file, path);
                }
            }
            return new Result<>(SUCCESS, SystemConfig.FILE_UPLOAD_ROOT_DIR);
        } catch (IOException e) {
            return new Result<>(FILE_UPLOAD_ERROR, e.getMessage());
        }
    }

    // 文件存储方法
    private static String saveFile(MultipartFile file, String path) throws IOException {
        File uploadDirFile = new File(path);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs(); // 创建上传目录
        }
        String filePath = Paths.get(path, file.getOriginalFilename()).toString();
        File destFile = new File(filePath);
        file.transferTo(destFile); // 保存文件
        return filePath;
    }

    // 整合特定文件的路径
    public static String getNowFilePath(String filePathByType, Integer uuid) {
        // 空值检查
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
        if (now == null) {
            throw new IllegalStateException("DateTime.now() returned null");
        }

        // 使用 DateTime 处理年、月、日
        int year = now.year();
        int month = now.month();
        int dayOfMonth = now.dayOfMonth();

        // 使用 Paths.get() 拼接根目录和文件路径，确保路径拼接正确
        return Paths.get(
                SystemConfig.FILE_UPLOAD_ROOT_DIR,
                filePathByType,
                String.valueOf(loginId),     // 将 loginId 转为 String
                String.valueOf(year),
                String.valueOf(month),
                String.valueOf(dayOfMonth),
                String.valueOf(uuid)
        ).toString();
    }
}
