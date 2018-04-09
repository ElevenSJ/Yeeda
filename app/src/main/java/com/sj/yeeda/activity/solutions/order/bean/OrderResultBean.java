package com.sj.yeeda.activity.solutions.order.bean;

import android.support.annotation.Keep;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */
@Keep
public class OrderResultBean {
    String id;
    String money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
