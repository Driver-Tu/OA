package wh.fcfz.officecontroller.all.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wh.fcfz.officecontroller.all.tool.Result;

public interface CaptchaService {
    Result<String> createCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception;

    boolean validateCaptcha(HttpServletRequest request, String captchaInput);
}
