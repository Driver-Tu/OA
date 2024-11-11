package wh.fcfz.officecontroller.config.satoken;

import cn.dev33.satoken.exception.NotLoginException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import wh.fcfz.officecontroller.all.tool.MyException;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return new Result<>("500", e.getMessage(),null);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<String> handleMaxSizeException(MaxUploadSizeExceededException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生文件上传异常.", requestURI, e);
        return new Result<>(ResponseEnum.FILE_SIZE_EXCEEDED, null);
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public Result<String> handleNotLoginException(NotLoginException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',未能读取到有效 token.", requestURI, e);
        return new Result<>(ResponseEnum.USER_NOT_LOGIN, null);
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result<String> handleMyException(MyException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生自定义异常.", requestURI, e);
        return new Result<>(e.getMessage(), e.getErrorCode(),null);
    }
}
