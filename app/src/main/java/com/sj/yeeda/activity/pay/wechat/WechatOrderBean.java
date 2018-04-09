package com.sj.yeeda.activity.pay.wechat;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * 创建时间: on 2018/4/9.
 * 创建人: 孙杰
 * 功能描述:
 */
@Keep
public class WechatOrderBean {

    /**
     * appId : wx201114d2a2410919
     * partnerid : 1500152722
     * prepayid : wx09141251399173fe54ff52c20298551499
     * package : Sign=WXPay
     * noncestr : 6dZSnQ7dabJcaBDGDfzQnbYPKiiRtcjM
     * timestamp : 1523254182
     * sign : C16068B91FE0DD5AA1870BBD20654D97
     */

    private String appId;
    private String partnerid;
    private String prepayid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private String timestamp;
    private String sign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
