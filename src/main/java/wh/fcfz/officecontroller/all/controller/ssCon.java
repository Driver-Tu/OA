package wh.fcfz.officecontroller.all.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.tool.FileTool;

@RestController
@RequestMapping("/ss")
public class ssCon {
    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestParam MultipartFile[] files,String path){
        return FileTool.uploadFile(files,path);
    }
}
