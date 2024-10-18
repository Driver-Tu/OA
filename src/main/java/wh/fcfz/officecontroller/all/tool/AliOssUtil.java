package wh.fcfz.officecontroller.all.tool;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import wh.fcfz.officecontroller.config.oss.AliOssProperties;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLEncoder;


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
     * @param bytes ：传入的文件要转为byte[]
     * @param objectName ：表示在oss中存储的文件名字。
     */
    public String upload(byte[] bytes, String objectName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        } catch (OSSException oe) {
            log.error("Error Message:" + oe.getErrorMessage());
        } catch (ClientException ce) {
            log.error("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        return stringBuilder.toString();
    }

    /**
     * 文件下载
     * @param objectName 下载的文件名
     * @param originalFileName 文件原名
     * @param response HTTP 响应对象
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

    /**
     * 从阿里云 OSS 读取图片并通过 HTTP 响应返回
     * @param objectName OSS 中的文件名
     * @param response HTTP 响应对象
     */
    public void readImage(String objectName, HttpServletResponse response) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 获取 OSS 中的对象
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            try (InputStream inputStream = ossObject.getObjectContent();
                 BufferedInputStream in = new BufferedInputStream(inputStream);
                 ServletOutputStream out = response.getOutputStream()) {

                // 设置响应头，表明返回的内容是图片
                response.setContentType("image/jpeg"); // 根据图片类型设置
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

}