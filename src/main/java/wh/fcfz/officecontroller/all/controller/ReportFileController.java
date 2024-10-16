package wh.fcfz.officecontroller.all.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.service.Impl.ReportServiceImpl;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report/file")
public class ReportFileController {

    @Autowired
    private ReportServiceImpl reportService;
    @PostMapping("/upload")
   public Result upload(@RequestBody MultipartFile file) throws IOException {
        List<String> list = new ArrayList<>();
        File filePath=new File("D:\\Project\\OfficeController\\src\\main\\resources\\static\\"+ IdUtil.simpleUUID());
        if(file!=null){
            if(!filePath.exists()){
                filePath.mkdirs();
            }
            File fileFinal = new File(filePath.getPath() + "\\" + file.getOriginalFilename());
            file.transferTo(fileFinal);
            list.add(fileFinal.getPath());
        }else {
            return new Result(ResponseEnum.FILE_UPLOAD_ERROR,"上传文件为空");
        }
        return new Result(ResponseEnum.SUCCESS,list);
    }
}
