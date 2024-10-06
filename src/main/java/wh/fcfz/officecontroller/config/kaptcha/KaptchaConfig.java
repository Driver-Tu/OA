package wh.fcfz.officecontroller.config.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 设置验证码的长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 设置字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体");
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        // 设置图片宽度
        properties.setProperty("kaptcha.image.width", "125");
        // 设置图片高度
        properties.setProperty("kaptcha.image.height", "45");
        // 设置背景色
        properties.setProperty("kaptcha.background.clear.to.white", "true");
        // 设置是否有边框
        properties.setProperty("kaptcha.border", "yes");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 验证码图片样式
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
