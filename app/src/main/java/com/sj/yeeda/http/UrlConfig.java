package com.sj.yeeda.http;

/**
 * 创建时间: on 2018/3/31.
 * 创建人: 孙杰
 * 功能描述:请求地址配置
 */
public class UrlConfig {
    //baseUrl
    public static final String BASE_URL = "http://prj-huizhan.app-service-node.com";


    //获取验证码
    public static final String GET_SMSCODE_URL = "/appSendSmsValidCodeToPhone";
    //注册
    public static final String REGISTER_URL = "/appRegister";
    //登录
    public static final String LOGIN_URL = "/appLogin";
}