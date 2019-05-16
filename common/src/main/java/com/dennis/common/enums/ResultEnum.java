package com.dennis.common.enums;

/**
 * Created by Dennis on 2019/1/8.
 */
public enum ResultEnum {

    TOKEN_EXPIRE(-1, "认证失效"),
    USER_BAN(-2, "您的账户已被封禁"),
    ACCESS_DENIED(-1, "连接失败"),


    ERROR(1, "fail"),
    UPLOAD_FAIL(1, "上传失败"),
    REQUEST_FAIL(1, "请求失败"),

    SUCCESS(0, "返回成功"),
    REGISTER_SUCCESS(0, "注册成功"),
    LOGIN_SUCCESS(0, "登录成功"),
    UPDATE_SUCCESS(0, "修改成功"),
    REQUEST_SUCCESS(0, "请求成功"),
    UPLOAD_SUCCESS(0, "上传成功"),


    FILE_NOT_EXIST(100001,"文件不存在"),


    SIGN_AUTH_NOT_MATCH(200008, "签名不匹配"),


    UNKNOW_ERROR(100000, "未知异常"),
    ILLEGAL_ARGUMENT(100001, "参数无效"),
    PATH_ERROR(100001, "日志文件路径错误"),



    METHOD_NOT_ALLOW(405, "方法不允许访问"),
    PAGE_NOT_FOUND(404, "页面找不到"),
    BAD_REQUEST(400, "客户端请求的语法错误，服务器无法理解"),
    UNSUPPORTED_MEDIA_TYPE(415, "服务器无法处理请求附带的媒体格式"),

    USER_NOT_FOUND(200001, "用户不存在"),
    USER_PASSWORD_NOT_MATCH(200002, "账户或密码错误!"),
    ACCOUNT_EXIST(200003, "账户已存在"),
    CURRENT_PASSWORD_NOT_MATCH(200002, "当前密码不正确!"),
    MOBIILE_EXIST(200004, "该手机号已注册"),
    VERIFICATION_CODE_ERROR(200006, "验证码错误"),
    INVALID_ACCOUNT(200006, "无效手机号");

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
