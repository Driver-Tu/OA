package wh.fcfz.officecontroller.all.tool;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException{
    private String errorCode;

    public MyException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public MyException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.errorCode = responseEnum.getCode();
    }
}
