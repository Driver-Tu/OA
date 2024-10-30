package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.bean.Dto.FileDto;
import wh.fcfz.officecontroller.all.mapper.FileMapper;
import wh.fcfz.officecontroller.all.service.FileService;
import wh.fcfz.officecontroller.all.tool.AliOssUtil;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private ResourceLoader resourceLoader;
    
    @Override
    public List<File> selectFileList(FileDto fileDto) {
        LambdaQueryWrapper<File> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(File::getBusinessType, fileDto.getBusinessType());
                return null;
    }

    @Override
    public List<String> uploadFile(List<MultipartFile> files, String businessType, Integer businessId) {
        List<String> fileUUIDs = files.stream()
                .map(file -> {

                    // 避免上传的文件重名，重新生成一个文件名
                    String fileUUID = String.valueOf(UUID.randomUUID());
                    // 获取文件后缀
                    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                    String newFileName = fileUUID + (extension.isEmpty() ? "" : "." + extension);
                    // 创建 File 对象并设置属性
                    File fileEntity = new File();
                    fileEntity.setFileName(file.getOriginalFilename());
                    fileEntity.setFileUuid(fileUUID); // 存储生成的 UUID 文件名
                    fileEntity.setFilePath("/"); // 存储路径（可以根据需求调整）
                    fileEntity.setFileSize(file.getSize());
                    fileEntity.setFileType(file.getContentType());
                    fileEntity.setUploaderId(StpUtil.getLoginIdAsInt());
                    fileEntity.setFileOwnerId(StpUtil.getLoginIdAsInt());
                    fileEntity.setUploadTime(new Timestamp(System.currentTimeMillis())); // 设置上传时间
                    fileEntity.setBusinessType(businessType);
                    fileEntity.setBusinessId(businessId);
                    fileEntity.setFileStatus((byte) 1); // 状态设为正常
                    String fileUrl = null;
                    fileUrl = aliOssUtil.upload(file, newFileName);
                    fileEntity.setFileUrl(fileUrl);
                    // 将文件信息批量插入数据库
                    try {
                        save(fileEntity);
                    } catch (Exception e) {
                        throw new RuntimeException("文件插入数据库失败", e);
                    }
                    // 返回文件在 OSS 中的访问地址
                    return fileEntity.getFileUuid();
                }).toList();
        // 返回操作成功的结果，包含所有文件的 URL
        return fileUUIDs;
    }

    @Override
    public void getFileAsResource(HttpServletResponse response, Integer fileId) {
        // 根据文件 ID 获取文件记录
        File file = getById(fileId);
        String fileName = file.getFileUuid() + "_" + file.getFileName();

        // 调用工具类读取图片并返回响应
        aliOssUtil.readImage(fileName, response);
    }

    // 更新文件信息
    public void updateFile() {

    }
}
