package wh.fcfz.officecontroller.config.oss;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AliOssProperties {
    @Value("${OSS_ENDPOINT}")
    private String endpoint;
    @Value("${ACCESS_KEY_ID}")
    private String accessKeyId;
    @Value("${ACCESS_KEY_SECRET}")
    private String accessKeySecret;
    @Value("${BUCKET_NAME}")
    private String bucketName;
}