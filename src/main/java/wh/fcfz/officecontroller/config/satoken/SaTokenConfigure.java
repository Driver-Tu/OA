package wh.fcfz.officecontroller.config.satoken;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                //拦截所有
                .addPathPatterns("/**")
                //允许通过
                // 放行以下路径
                .excludePathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/isLogin")
                .excludePathPatterns("/captcha/get")
                .excludePathPatterns("/captcha/validate")
                .excludePathPatterns("/file/downloadFile")
                .excludePathPatterns("file/image/**");
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }
}
