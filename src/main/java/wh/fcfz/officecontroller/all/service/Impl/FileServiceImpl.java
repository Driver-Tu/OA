package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.mapper.FileMapper;
import wh.fcfz.officecontroller.all.service.FileService;
import wh.fcfz.officecontroller.all.tool.AliOssUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List<Integer> uploadFile(List<MultipartFile> files, String businessType, Integer businessId) {
        List<Integer> fileIds = files.stream()
                .map(file -> {
                    try {
                        // 避免上传的文件重名，重新生成一个文件名
                        String fileUUID = String.valueOf(UUID.randomUUID());
                        String newFileName = fileUUID + "_" + file.getOriginalFilename();
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
                        fileUrl = aliOssUtil.upload(file.getBytes(), newFileName);
                        fileEntity.setFileUrl(fileUrl);
                        // 将文件信息批量插入数据库
                        try {
                            save(fileEntity);
                        } catch (Exception e) {
                            throw new RuntimeException("文件插入数据库失败", e);
                        }
                        // 返回文件在 OSS 中的访问地址
                        return fileEntity.getId();
                    } catch (IOException e) {
                        // 处理文件上传异常，您可以选择记录日志或抛出自定义异常
                        throw new RuntimeException("文件上传失败: " + file.getOriginalFilename(), e);
                    }
                }).toList();
        // 返回操作成功的结果，包含所有文件的 URL
        return fileIds;
    }

    @Override
    public void getFileAsResource(HttpServletResponse response, Integer fileId) {
        // 根据文件 ID 获取文件记录
        File file = getById(fileId);
        String fileName = file.getFileUuid() + "_" + file.getFileName();

        // 调用工具类读取图片并返回响应
        aliOssUtil.readImage(fileName, response);
    }
}