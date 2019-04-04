package com.dennis.common.constants;

/**
 * Created by Dennis on 2019/1/8.
 */


public class ApiConstant {

    // Header 中 token 的 key
    public final static String AUTHORIZATION = "Authorization";

    // JWTUtil Redis 数据库 用户 key 前缀
    public static String USER_JWT_PREFIX = "api:jwt:";

    // Header 中 sign 的 key
    public static final String HEADER_ENCRYPTE_SIGN = "Encrypt-Sign";

    // 该项目签名 sign-key
    public static final String SIGN_KEY = "FB8EDB1A4ABB4F43A3721B06518112A9";


}
