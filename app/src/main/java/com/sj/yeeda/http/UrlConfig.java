package com.sj.yeeda.http;

import android.support.annotation.Keep;

/**
 * 创建时间: on 2018/3/31.
 * 创建人: 孙杰
 * 功能描述:请求地址配置
 */
@Keep
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
    //方案面积类别
    public static final String QUERY_SOLUTION_AREAS_URL = "/appQuerySchemeArea";
    //查询方案详情
    public static final String QUERY_SOLUTION_DETAIL_URL = "/appSchemeDetail";

    //查询用户开票信息
    public static final String QUERY_INVOICE_URL = "/appQueryUserInvoice";
    //增加用户开票信息
    public static final String ADD_INVOICE_URL = "/appAddUserInvoice";
    //删除用户开票信息
    public static final String DEL_INVOICE_URL = "/appDeleteUserInvoice";
    //修改用户开票信息
    public static final String EDT_INVOICE_URL = "/appUpdateUserInvoice";


    //查询用户场馆信息
    public static final String QUERY_VENUE_URL = "/appQueryUserVenue";
    //增加用户场馆信息
    public static final String ADD_VENUE_URL = "/appAddUserVenue";
    //删除用户场馆信息
    public static final String DEL_VENUE_URL = "/appDeleteUserVenue";
    //修改用户场馆信息
    public static final String EDT_VENUE_URL = "/appUpdateUserVenue";
    //查询默认场馆
    public static final String QUERY_DEFAULT_VENUE_URL = "/appQueryUserDefaultVenue";

    //保存订单
    public static final String SAVE_ORDER_URL = "/appSaveOrder";
    //查询订单
    public static final String QUERY_ORDER_URL = "/appQueryOrderList";
    //查询订单详情
    public static final String QUERY_ORDER_DETAIL_URL = "/appQueryOrderDetail";
    //查询订单通知列表
    public static final String QUERY_ORDER_NEWS_URL = "/appQueryOrderNews";
    //支付宝支付获取sign
    public static final String GET_ALIPAY_SIGN_URL = "/getSignByAlipay";
    //微信统一下单
    public static final String GET_WECHAT_ORDER_URL = "/WXunifiedorder";


    //微信支付appid
    public static String WECHAT_APP_ID = "";

    //查询租赁设备
    public static final String QUERY_DEVICES_URL = "/appQueryRentEquipment";

    //查询IM账号
    public static final String QUERY_IM_ACCOUNT_URL = "/getIMCusotmerNumber";
    //查询IMToken
    public static final String QUERY_IM_TOKEN_URL = "/getMD5Token";
}
