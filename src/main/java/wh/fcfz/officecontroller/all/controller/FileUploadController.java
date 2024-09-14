package wh.fcfz.officecontroller.all.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static wh.fcfz.officecontroller.config.SystemConfig.FILE_UPLOAD_ROOT_DIR;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("文件为空，上传失败");
        }

        try {
            // 文件存储逻辑
            String fileName = file.getOriginalFilename();
            String filePath = saveFile(file);

            return ResponseEntity.ok("文件上传成功，路径: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败: " + e.getMessage());
        }
    }

    // 文件存储方法
    private String saveFile(MultipartFile file) throws IOException {
        String uploadDir = FILE_UPLOAD_ROOT_DIR; // 本地存储目录

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs(); // 创建上传目录
        }

        String filePath = uploadDir + file.getOriginalFilename();
        File destFile = new File(filePath);
        file.transferTo(destFile); // 保存文件
        return filePath;
    }
}

