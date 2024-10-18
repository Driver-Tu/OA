package wh.fcfz.officecontroller.all.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<Integer> uploadFile(List<MultipartFile> files, String businessType, Integer businessId);

    void getFileAsResource(HttpServletResponse response, Integer fileId);
}
