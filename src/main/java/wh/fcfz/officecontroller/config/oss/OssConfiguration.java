package wh.fcfz.officecontroller.config.oss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wh.fcfz.officecontroller.all.bean.Dao.OssConfig;
import wh.fcfz.officecontroller.all.service.Impl.OssConfigServiceImpl;
import wh.fcfz.officecontroller.all.tool.AliOssUtil;
import wh.fcfz.officecontroller.all.tool.SpringUtils;

@Slf4j
@Configuration
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil getAliOssUtil(AliOssProperties aliOssProperties) {
        log.info("创建 OssUtil");
        OssConfigServiceImpl bean = SpringUtils.getBean(OssConfigServiceImpl.class);
        OssConfig byId = bean.getById(1);
        return new AliOssUtil(
                byId.getOssEndpoint(),
                byId.getAccessKeyId(),
                byId.getAccessKeySecret(),
                byId.getBucketName()
        );
    }
}
