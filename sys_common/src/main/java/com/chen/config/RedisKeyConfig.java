package com.chen.config;

/**
 * redis配置
 */
public class RedisKeyConfig {

    /** HTTP请求头的名称 */
    public static final String token = "token";

    /** 登录token失效时间2小时，单位：秒 */
    public static final long loginToken_expiredTime = 2L*60*60;

// TODO 屏蔽RedisKey

//    /** 平台登录session的key */
//    public static final String loginKey_platform = "pxj:platform:token:backerId:";
//
//
//    /** 平台登录session的key */
//    public static final String device_keepalive = "pxj:platform:device:keepalive:";
//
//    /** 平台登录权限的key */
//    public static final String menuKey_platform = "pxj:platform:menu:backerId:";
//
//    /** 平台登录验证码的key */
//    public static final String verifyCodeKey_login = "pxj:platform:login:verifycodeid:";

}
