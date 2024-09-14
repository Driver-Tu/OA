package wh.fcfz.officecontroller.all.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.tool.FileTool;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.config.file.SystemConfig;


@RestController
@RequestMapping("/ss")
public class ssCon {
    @PostMapping("/reimburse")
    public Result<String> test(@RequestParam MultipartFile[] files, @RequestParam Integer uuid){
        return FileTool.uploadFile(files, SystemConfig.DECLARE_REIMBURSE_FILE_DIR,1);
    }
}
