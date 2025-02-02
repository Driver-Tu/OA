package wh.fcfz.officecontroller.all.tool;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.VoidResult;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;


@Slf4j
@Data
@AllArgsConstructor
//固定代码，CV直接使用
public class AliOssUtil {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    /**
     * 文件上传
     *
     * @param file       ：上传的文件
     * @param objectName ：表示在OSS中存储的文件名字。
     * @return 文件访问路径
     */
    public String upload(MultipartFile file, String objectName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 创建ObjectMetadata对象
            ObjectMetadata metadata = new ObjectMetadata();
            String mimeType = file.getContentType();
            if (mimeType.startsWith("image/") || mimeType.startsWith("video/")) {
                metadata.setHeader("Content-Disposition", "inline"); // 设置 Content-Disposition
            } else {
                metadata.setHeader("Content-Disposition", "attachment");
            }

            // 上传文件
            ossClient.putObject(bucketName, objectName, file.getInputStream(), metadata);
        } catch (OSSException oe) {
            log.error("Error Message:" + oe.getErrorMessage());
        } catch (ClientException ce) {
            log.error("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            log.error("File upload failed: " + e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        // 文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        System.out.println("临时访问url："+ossClient.generatePresignedUrl(bucketName, objectName, new Date(new Date().getTime() + 3600 * 1000)).toString());

        return stringBuilder.toString();
    }

    /**
     * 文件下载
     *
     * @param objectName       下载的文件名
     * @param originalFileName 文件原名
     * @param response         HTTP 响应对象
     */
    public void download(String objectName, String originalFileName, HttpServletResponse response) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 设置响应头，支持文件下载
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(originalFileName, "UTF-8"));

            // 获取 OSS 中的对象
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            try (InputStream inputStream = ossObject.getObjectContent();
                 BufferedInputStream in = new BufferedInputStream(inputStream);
                 ServletOutputStream out = response.getOutputStream();
                 BufferedOutputStream bout = new BufferedOutputStream(out)) {

                byte[] buffer = new byte[64 * 1024]; // 16KB 缓冲区
                int len;
                while ((len = in.read(buffer)) != -1) {
                    bout.write(buffer, 0, len);
                }
                bout.flush();
            }
        } catch (Exception e) {
            log.error("下载失败：{}", e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    private static final String DEFAULT_IMAGE_OBJECT_NAME = "defaultImage/f6ee1317a9bb3ef11258a0297a4cabe7.jpg";

    /**
     * 从阿里云 OSS 读取图片并通过 HTTP 响应返回
     *
     * @param objectName OSS 中的文件名
     * @param response   HTTP 响应对象
     */
    public void readImage(String objectName, HttpServletResponse response) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 获取 OSS 中的对象
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            String contentType = ossObject.getObjectMetadata().getContentType();
            boolean isImageOrVideo = isImageOrVideo(contentType);
//            if (!isImageOrVideo) {
//                ossObject = ossClient.getObject(bucketName, DEFAULT_IMAGE_OBJECT_NAME);
//            }
            try (
                    InputStream inputStream = ossObject.getObjectContent();
                    BufferedInputStream in = new BufferedInputStream(inputStream);
                    ServletOutputStream out = response.getOutputStream()) {
                // 设置响应头，表明返回的内容是图片
                // 设置响应头
                response.setContentType(contentType);
                response.setHeader("Cache-Control", "max-age=3600"); // 设置缓存控制
                byte[] buffer = new byte[64 * 1024]; // 64KB 缓冲区
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            }
        } catch (Exception e) {
            log.error("读取图片失败：{}", e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    private boolean isImageOrVideo(String contentType) {
        return contentType.startsWith("image/") || contentType.startsWith("video/");
    }

    public String readResource(String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 获取 OSS 中的对象
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            try (InputStream inputStream = ossObject.getObjectContent();
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                // 将输入流转换为字节数组
                byte[] buffer = new byte[64 * 1024]; // 64KB 缓冲区
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, len);
                }

                // 将字节数组转换为 Base64 字符串
                byte[] bytes = byteArrayOutputStream.toByteArray();
                return Base64.getEncoder().encodeToString(bytes);
            }
        } catch (Exception e) {
            log.error("读取文件失败：{}", e.getMessage());
            return null; // 或者抛出自定义异常
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public String readDefaultImage() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 获取 OSS 中的对象
            OSSObject ossObject = ossClient.getObject(bucketName, DEFAULT_IMAGE_OBJECT_NAME);
            try (InputStream inputStream = ossObject.getObjectContent();
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                // 将输入流转换为字节数组
                byte[] buffer = new byte[64 * 1024]; // 64KB 缓冲区
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, len);
                }

                // 将字节数组转换为 Base64 字符串
                byte[] bytes = byteArrayOutputStream.toByteArray();
                return Base64.getEncoder().encodeToString(bytes);
            }
        } catch (Exception e) {
            log.error("读取文件失败：{}", e.getMessage());
            return null; // 或者抛出自定义异常
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public boolean deleteFile(String userImage) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        VoidResult voidResult = ossClient.deleteObject(bucketName, userImage);
        return voidResult.getRequestId() != null;
    }
}