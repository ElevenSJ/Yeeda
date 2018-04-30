package com.sj.yeeda.activity.service.bean;

import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * 创建时间: on 2018/4/17.
 * 创建人: 孙杰
 * 功能描述:
 */
@Keep
public class IMAccountBean implements Serializable {

    private static final long serialVersionUID=112L;

    private String kefu;
    private String tezhan;
    private String dingdan;

    public String getKefu() {
        return kefu;
    }

    public void setKefu(String kefu) {
        this.kefu = kefu;
    }

    public String getTezhan() {
        return tezhan;
    }

    public void setTezhan(String tezhan) {
        this.tezhan = tezhan;
    }

    public String getDingdan() {
        return dingdan;
    }

    public void setDingdan(String dingdan) {
        this.dingdan = dingdan;
    }

    @Override
    public String toString() {
        return "IMAccountBean{" +
                "kefu='" + kefu + '\'' +
                ", tezhan='" + tezhan + '\'' +
                ", dingdan='" + dingdan + '\'' +
                '}';
    }
}
