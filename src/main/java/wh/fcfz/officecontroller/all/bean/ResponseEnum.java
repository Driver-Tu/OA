package wh.fcfz.officecontroller.all.bean;



public enum ResponseEnum {

    SUCCESS("200", "操作成功"),

    USER_NOT_EXIST("301","用户不存在"),
    USER_NOT_LOGIN("302","用户未登录"),
    USER_IS_LOGIN("303","用户已登录"),
    PARAM_ERROR("601","密码或者工号为空");

    private String code;
    private String message;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // 没有public  这个构造方法 不能用到外面去  只能在这个类中被使用（提示的信息 只能在内部生成）
    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
