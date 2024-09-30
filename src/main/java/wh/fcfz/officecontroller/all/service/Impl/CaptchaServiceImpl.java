package wh.fcfz.officecontroller.all.service.Impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.service.CaptchaService;
import wh.fcfz.officecontroller.all.tool.Result;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Properties;
import java.util.UUID;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    private DefaultKaptcha defaultKaptcha;

//    @Autowired
//    private StringRedisTemplate redisTemplate;

    // 这一部分是配置，可以转移到 KaptchaConfig 配置类中
    @PostConstruct
    public void init() {
        defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 设置验证码相关属性，如字体、大小、颜色等
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // ... 其他属性设置
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
    }

    @Override
    public Result<String> createCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("创建验证码的 session：" + request.getSession().getId());
        String captchaText = defaultKaptcha.createText();
        System.out.println("存入 redis 时的验证码：" + captchaText);
        String key = UUID.randomUUID().toString(); // 生成唯一的key
        System.out.println("生成的验证码 key：" + key);
//        redisTemplate.opsForValue().set(key, captchaText, 60, TimeUnit.SECONDS); // 将验证码保存到Redis，并设置过期时间
        request.getSession().setAttribute("captchaKey", key); // 将key保存到session中，用于前端验证
        request.getSession().setAttribute(key, captchaText); // 将text保存到session中，用于前端验证
        System.out.println("存入 session 时的验证码 key：" + request.getSession().getAttribute("captchaKey").toString());

        BufferedImage image = defaultKaptcha.createImage(captchaText);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byte[] captchaImage = byteArrayOutputStream.toByteArray();

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        ServletOutputStream out = response.getOutputStream();
        out.write(captchaImage);
        out.flush();
        out.close();

        return new Result("200","验证码生成成功", key);
    }

    @Override
    public boolean validateCaptcha(HttpServletRequest request, String captchaInput) {
        // 直接从Session中获取验证码文本

        String key = (String) request.getSession().getAttribute("captchaKey");
        String captchaText = (String) request.getSession().getAttribute(key);
        System.out.println("从 session 中取出的验证码：" + captchaText);

        System.out.println("验证验证码的 session：" + request.getSession().getId());

//         校验输入的验证码与生成的验证码是否一致
        return captchaText != null && captchaText.equals(captchaInput);
    }

}
