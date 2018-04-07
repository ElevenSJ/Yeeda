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
    //注销
    public static final String LOGIN_OUT_URL = "/appLoginOut";
    //查询个人信息
    public static final String QUERY_USER_URL = "/appQueryUserInfo";
    //完善个人信息
    public static final String PARFIT_USER_URL = "/appParfaitUserInfo";
    //修改个人信息
    public static final String UPDATE_USER_URL = "/appUpdateUserInfo";
    //查询方案列表
    public static final String QUERY_SOLUTIONS_URL = "/appSchemeList";

    //查询用户开票信息
    public static final String QUERY_INVOICE_URL = "/appQueryUserInvoice";
    //增加用户开票信息
    public static final String ADD_INVOICE_URL = "/appAddUserInvoice";
    //删除用户开票信息
    public static final String DEL_INVOICE_URL = "/appDeleteUserInvoice";
    //修改用户开票信息
    public static final String EDT_INVOICE_URL = "/appUpdateUserInvoice";

}
