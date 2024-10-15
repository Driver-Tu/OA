package wh.fcfz.officecontroller.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.tool.FileUtil;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.config.file.SystemConfig;

@RestController
@RequestMapping("/file")
public class FileTestController {
    @Autowired
    private FileUtil fileUtil;

    private final ResourceLoader resourceLoader;

    public FileTestController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
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
        String filePath = "file:D:\\file\\image.png";

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
