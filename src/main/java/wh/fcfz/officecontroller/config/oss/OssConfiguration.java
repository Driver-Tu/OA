package wh.fcfz.officecontroller.config.oss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wh.fcfz.officecontroller.all.tool.AliOssUtil;

@Slf4j
@Configuration
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil getAliOssUtil(AliOssProperties aliOssProperties) {
        log.info("创建OssUtil");
        AliOssUtil aliOssUtil = new AliOssUtil(
                aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName()
        );
        return aliOssUtil;
    }
}
