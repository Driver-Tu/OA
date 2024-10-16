package wh.fcfz.officecontroller.all.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Result upload(@RequestBody MultipartFile files) throws IOException {
        List<String> list = new ArrayList<>();

        if (files != null) {
            File filePath = new File("D:\\Project\\OfficeController\\src\\main\\resources\\static\\" + IdUtil.simpleUUID());
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            File fileFinal = new File(filePath.getPath() + "\\" + files.getOriginalFilename());
            files.transferTo(fileFinal);
            list.add(fileFinal.getPath());

        } else {
            return new Result(ResponseEnum.FILE_UPLOAD_ERROR, "文件上传失败");
        }
        return new Result(ResponseEnum.SUCCESS, list);
    }
}
