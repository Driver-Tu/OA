package wh.fcfz.officecontroller.all.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.service.Impl.CaptchaServiceImpl;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.Map;

@RestController
@RequestMapping("/")
public class CaptchaController {
    @Autowired
    private CaptchaServiceImpl captchaService;

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        captchaService.createCaptcha(request, response);
    }

    @PostMapping("/validate-captcha")
    public Result validateCaptcha(HttpServletRequest request, @RequestBody Map<String, String> requestBody) {
        String captchaInput = requestBody.get("captcha");
        System.out.println("输入的验证码：" + captchaInput);
        boolean isValid = captchaService.validateCaptcha(request, captchaInput);

        if (isValid) {
            return new Result("200","true" ,null); // 验证通过
        } else {
            return new Result("400","false" ,null); // 验证失败
        }
    }
}
