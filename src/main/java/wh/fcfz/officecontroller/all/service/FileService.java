package wh.fcfz.officecontroller.all.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.bean.Dto.FileDto;

import java.util.List;

public interface FileService {

    List<File> selectFileList(FileDto fileDto);

    List<Integer> uploadFile(List<MultipartFile> files, String businessType, Integer businessId);

    void getFileAsResource(HttpServletResponse response, Integer fileId);
}
