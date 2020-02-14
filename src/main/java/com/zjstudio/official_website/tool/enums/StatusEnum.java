package com.zjstudio.official_website.tool.enums;

/**
 * @author 添柴灬少年
 * @date 2020/2/14 - 18:17
 */
public enum StatusEnum {
    /**
     * 成功
     */
    SUCCESS(200,"success"),
    /**
     * 失败
     */
    FAIL(500,"fail"),
    /**
     * 缺少参数
     */
    MISS_PARAM(404,"missParam"),
    /**
     * 错误
     */
    ERROR(400,"error"),
    /**
     * 异常
     */
    EXCEPTION(401,"exception"),

    /**
     * 暂未登录或token已经过期
     */
    UNAUTHORIZED(402, "tokenExpires"),

    /**
     * 没有相关权限
     */
    FORBIDDEN(403, "noAuthority");

    private final int code;

    private final String status;

    private StatusEnum(final int code,final String status){
        this.code = code;
        this.status = status;
    }

    public String status(){
        return this.status;
    }

    public int code(){
        return this.code;
    }
}
