package wh.fcfz.officecontroller.all.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.service.Impl.CaptchaServiceImpl;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.Map;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    @Autowired
    private CaptchaServiceImpl captchaService;

    @GetMapping("/get")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        captchaService.createCaptcha(request, response);
    }

    @PostMapping("/validate")
    public Result validateCaptcha(HttpServletRequest request, @RequestBody Map<String, String> requestBody) {
        String captchaInput = requestBody.get("captcha");
        System.out.println("输入的验证码：" + captchaInput);
        boolean isValid = captchaService.validateCaptcha(request, captchaInput);

        if (isValid) {
            return new Result("200","登录成功" ,true); // 验证通过
        } else {
            return new Result("200","验证码错误" , null); // 验证失败
        }
    }
}
