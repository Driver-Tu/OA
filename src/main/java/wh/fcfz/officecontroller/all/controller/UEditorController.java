package wh.fcfz.officecontroller.all.controller;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wh.fcfz.officecontroller.all.tool.FileUtil;
import wh.fcfz.officecontroller.config.file.SystemConfig;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ueditor")
public class UEditorController {

    @Resource
    private FileUtil fileUtil;

    // 1. 获取配置接口
    @GetMapping(params = "action=config")
    public ResponseEntity<Map<String, Object>> getConfig(@RequestParam String action) {
        Map<String, Object> config = getEditorConfig();
        return ResponseEntity.ok(config);
    }

    // 2. 上传文件接口
//    @PostMapping()
//    public ResponseEntity<Map<String, Object>> uploadFile(
//            @RequestParam("action") String action,
//            @RequestParam("file") MultipartFile file) {
//        if ("uploadImage".equals(action)) {
//            return uploadImage(file);
//        } else if ("video".equals(action)) {
//            return uploadVideo(file);
//        } else if ("uploadFile".equals(action)) {
//            return uploadGenericFile(file);
//        } else {
//            return ResponseEntity.badRequest().body(Map.of("state", "Invalid action"));
//        }
//    }

    // 3. 上传图片
    @PostMapping(params = "action=image")
    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(handleFileUpload(file, "image"));
    }

    // 4. 上传视频
    @PostMapping(params = "action=video")
    public ResponseEntity<Map<String, Object>> uploadVideo(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(handleFileUpload(file, "file"));
    }

    // 5. 上传其他文件
    public ResponseEntity<Map<String, Object>> uploadGenericFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("state", "No file uploaded"));
        }

        String fileName = file.getOriginalFilename();
        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")  // 假设文件上传到 /uploads 目录下
                .path(fileName)
                .toUriString();

        Map<String, Object> response = new HashMap<>();
        response.put("state", "SUCCESS");
        response.put("url", fileUri);
        response.put("title", fileName);
        response.put("original", fileName);
        return ResponseEntity.ok(response);
    }

    private Map<String, Object> handleFileUpload(MultipartFile file, String type) {
        Map<String, Object> response = new HashMap<>();
        if (file.isEmpty()) {
            response.put("state", "ERROR");
            return response;
        }
        String fileName = file.getOriginalFilename();
        MultipartFile[] files = new MultipartFile[1];
        files[0] = file;
        fileUtil.uploadFile(files, SystemConfig.DECLARE_REIMBURSE_FILE_DIR, 1);
//        try {
//            file.transferTo(destination);
            response.put("state", "SUCCESS");
            response.put("url", "http://localhost:8088/file/images"); // 返回相对路径
            response.put("title", fileName);
            response.put("original", fileName);
//        } catch (IOException e) {
//            response.put("state", "ERROR");
//            e.printStackTrace();
//        }
        return response;
    }

    // 6. 抓取图片接口
    @PostMapping("/catchImage")
    public ResponseEntity<Map<String, Object>> catchImage(
            @RequestParam("action") String action,
            @RequestParam("source") String source) {
        if ("catch".equals(action)) {
            // 此处可以根据 source URL 抓取图片，并进行相应处理
            // 这里假设抓取成功并返回示例数据
            Map<String, Object> response = new HashMap<>();
            response.put("state", "SUCCESS");
            response.put("list", List.of(Map.of("state", "SUCCESS", "url", "upload/demo.jpg", "title", "demo.jpg", "original", "demo.jpg")));
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(Map.of("state", "Invalid action"));
    }

    // 7. 图片列表接口
    @GetMapping("/listImage")
    public ResponseEntity<Map<String, Object>> listImage(@RequestParam String action) {
        if ("listImage".equals(action)) {
            // 假设从数据库或文件系统中获取图片列表
            Map<String, Object> response = new HashMap<>();
            response.put("state", "SUCCESS");
            response.put("list", List.of(Map.of("url", "upload/demo.jpg", "mtime", 1561365440)));
            response.put("start", 0);
            response.put("total", 1);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(Map.of("state", "Invalid action"));
    }

    // 8. 文件列表接口
    @GetMapping("/listFile")
    public ResponseEntity<Map<String, Object>> listFile(@RequestParam String action) {
        if ("listFile".equals(action)) {
            // 假设从数据库或文件系统中获取文件列表
            Map<String, Object> response = new HashMap<>();
            response.put("state", "SUCCESS");
            response.put("list", List.of(Map.of("url", "upload/demo.zip", "mtime", 1561365440)));
            response.put("start", 0);
            response.put("total", 1);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(Map.of("state", "Invalid action"));
    }

    // 9. 编辑器配置
    private Map<String, Object> getEditorConfig() {
        Map<String, Object> config = new HashMap<>();

        // 图片上传配置
        config.put("imageActionName", "image"); // 执行上传图片的action名称
        config.put("imageFieldName", "file"); // 提交的图片表单名称
        config.put("imageMaxSize", 10485760); // 上传大小限制，单位B，10MB
        config.put("imageAllowFiles", new String[]{".jpg", ".png", ".jpeg"}); // 上传图片格式显示
        config.put("imageCompressEnable", true); // 是否压缩图片
        config.put("imageCompressBorder", 5000); // 图片压缩最长边限制
        config.put("imageInsertAlign", "none"); // 插入的图片浮动方式
        config.put("imageUrlPrefix", ""); // 图片访问路径前缀

        // 涂鸦上传配置
        config.put("scrawlActionName", "crawl"); // 执行上传涂鸦的action名称
        config.put("scrawlFieldName", "file"); // 提交的涂鸦表单名称
        config.put("scrawlMaxSize", 10485760); // 上传涂鸦大小限制
        config.put("scrawlUrlPrefix", ""); // 涂鸦访问路径前缀
        config.put("scrawlInsertAlign", "none"); // 插入的涂鸦浮动方式

        // 截图上传配置
        config.put("snapscreenActionName", "snap"); // 执行上传截图的action名称
        config.put("snapscreenUrlPrefix", ""); // 截图访问路径前缀
        config.put("snapscreenInsertAlign", "none"); // 插入的截图浮动方式

        // 图片抓取配置
        config.put("catcherActionName", "catch"); // 执行抓取远程图片的action名称
        config.put("catcherFieldName", "source"); // 提交的图片列表表单名称
        config.put("catcherLocalDomain", List.of("127.0.0.1", "localhost")); // 例外的图片抓取域名
        config.put("catcherUrlPrefix", ""); // 抓取图片访问路径前缀
        config.put("catcherMaxSize", 10485760); // 抓取图片大小限制
        config.put("catcherAllowFiles", new String[]{".jpg", ".png", ".jpeg"}); // 抓取图片格式显示

        // 视频上传配置
        config.put("videoActionName", "video"); // 执行上传视频的action名称
        config.put("videoFieldName", "file"); // 提交的视频表单名称
        config.put("videoUrlPrefix", ""); // 视频访问路径前缀
        config.put("videoMaxSize", 104857600); // 上传视频大小限制
        config.put("videoAllowFiles", new String[]{".mp4"}); // 上传视频格式显示

        // 文件上传配置
        config.put("fileActionName", "uploadFile"); // 执行上传文件的action名称
        config.put("fileFieldName", "file"); // 提交的文件表单名称
        config.put("fileUrlPrefix", ""); // 文件访问路径前缀
        config.put("fileMaxSize", 104857600); // 上传文件大小限制
        config.put("fileAllowFiles", new String[]{".zip", ".pdf", ".doc"}); // 上传文件格式显示

        // 图片列表配置
        config.put("imageManagerActionName", "listImage"); // 执行图片管理的action名称
        config.put("imageManagerListSize", 20); // 每次列出文件数量
        config.put("imageManagerUrlPrefix", ""); // 图片列表访问路径前缀
        config.put("imageManagerInsertAlign", "none"); // 插入的图片列表浮动方式
        config.put("imageManagerAllowFiles", new String[]{".jpg", ".png", ".jpeg"}); // 列出的图片类型

        // 文件列表配置
        config.put("fileManagerActionName", "listFile"); // 执行文件管理的action名称
        config.put("fileManagerUrlPrefix", ""); // 文件列表访问路径前缀
        config.put("fileManagerListSize", 20); // 每次列出文件数量
        config.put("fileManagerAllowFiles", new String[]{".zip", ".pdf", ".doc"}); // 列出的文件类型

        // 公式配置
        Map<String, String> formulaConfig = new HashMap<>();
        formulaConfig.put("imageUrlTemplate", "https://r.latexeasy.com/image.svg?{}"); // 公式渲染的路径
        config.put("formulaConfig", formulaConfig); // 添加公式配置

        return config;
    }

}