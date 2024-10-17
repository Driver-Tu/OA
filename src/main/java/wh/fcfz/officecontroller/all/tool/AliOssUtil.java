package wh.fcfz.officecontroller.all.tool;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;


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
}