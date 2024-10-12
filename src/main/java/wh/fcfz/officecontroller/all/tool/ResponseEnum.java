package wh.fcfz.officecontroller.all.tool;



public enum ResponseEnum {

    SUCCESS("200", "操作成功"),
    USER_NOT_EXIST("10301","用户不存在"),
    USER_NOT_LOGIN("10302","用户未登录"),
    USER_IS_LOGIN("10303","用户已登录"),
    USER_IS_EXIST("10304", "用户已经存在"),
    USER_NOT_PERMISSION("10305", "用户权限不够"),
    DEPT_NOT_EXIST("10401","部门不存在"),
    DEPT_ID_NULL("10402","部门id为空"),
    DEPT_NAME_NULL("10403","部门名为空"),
    DEPT_LIST_NULL("10404","部门列表为空"),
    DEPT_SAVE_FAILED("10405", "部门新增失败" ),
    DEPT_DELETE_FAILED("10406", "部门删除失败"),
    DEPT_EXIST("10407", "部门已存在"),

    PARAM_ERROR("10601","密码或者工号为空"),
    DATA_NOT_EXIST("10604","数据不符合要求"),
    PASSWORD_IS_NULL("10605","密码转义错误"),
    PASSWORD_IS_NOT_TRUE("10602","老密码错误，无法修改密码"),
    INVALID_PARAM("10603", "查询条件为空"),
    DELETE_SERVER_FAILED("10703", "删除时异常"),
    INSERT_SERVER_ERROR("10706","新增时异常" ),
    UPDATE_SERVER_ERROR("10707","修改时异常" ),
    FILE_UPLOAD_ERROR("10801", "文件上传失败"),
    FILE_IS_NULL("10802", "文件为空"),
    FILE_SIZE_EXCEEDED("10803","存在大于10M的文件，无法上传" ),
    FILE_UPLOAD_PARTIALLY_SUCCESS("10804", "部分文件上传成功"),
    SYSTEM_ERRO("400", "系统发生异常");
//    FILE_UPLOAD_ERROR("10802", "文件上传成功");

    private String code;
    private String msg;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // 没有public  这个构造方法 不能用到外面去  只能在这个类中被使用（提示的信息 只能在内部生成）
    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
