package com.chen.common.result;

/**
 * 业务返回状态
 */
public enum ResultStatusEnum implements ResultStatusHandler {

    //系统级 区间 100~199
    SYSTEM_ERROR(100, "系统错误"),
    NO_POWER(102, "无访问权限"),

    //业务状态 区间 200~299,
    SUCCESS(200, "操作成功"),
    FAIL(201, "操作失败"),

    //业务级 区间 300~399
    TOKEN_ERROR(300, "登录token过期"),
    PARAM_ERROR(301, "参数错误");

    //业务自定义 区间 400~499

    private final Integer code;
    private final String message;

    ResultStatusEnum(Integer code, String meseage) {
        this.code = code;
        this.message = meseage;
    }

    /** 业务状态码 */
    @Override
    public Integer getCode() {
        return this.code;
    }

    /** 业务说明 */
    @Override
    public String getMessage() {
        return this.message;
    }

}
