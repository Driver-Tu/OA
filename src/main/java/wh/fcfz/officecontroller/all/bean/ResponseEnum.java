package wh.fcfz.officecontroller.all.bean;



public enum ResponseEnum {

    SUCCESS("200", "操作成功"),

    USER_NOT_EXIST("10301","用户不存在"),
    USER_NOT_LOGIN("10302","用户未登录"),
    USER_IS_LOGIN("10303","用户已登录"),
    PARAM_ERROR("10601","密码或者工号为空"),


    DEPT_NOT_EXIST("10401","部门不存在"),
    DEPT_ID_NULL("10402","部门id为空"),
    DEPT_NAME_NULL("10403","部门名为空"),

    PASSWORD_IS_NOT_TRUE("10602","老密码错误，无法修改密码");

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
