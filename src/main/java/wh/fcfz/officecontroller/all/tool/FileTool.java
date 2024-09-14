package wh.fcfz.officecontroller.all.tool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.config.file.SystemConfig;

import java.io.File;
import java.io.IOException;

public class FileTool {
    private static String filePathAll= SystemConfig.FILE_UPLOAD_ROOT_DIR;

    //批量存储文件
    public static ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile[] files,String path) {
        if (files==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("文件为空，上传失败");
        }
        try {

            // 文件存储逻辑
            for (MultipartFile file:files) {
                String fileName = file.getOriginalFilename();
                saveFile(file,path);
            }
            return ResponseEntity.ok("文件上传成功,以经存储到："+filePathAll+path);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败: " + e.getMessage());
        }
    }

    // 文件存储方法
    private static String saveFile(MultipartFile file, String path) throws IOException {
        File uploadDirFile = new File( filePathAll + path);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs(); // 创建上传目录
        }
        String filePath = filePathAll + path+"/"+file.getOriginalFilename();
        File destFile = new File(filePath);
        file.transferTo(destFile); // 保存文件
        return filePath;
    }
}

