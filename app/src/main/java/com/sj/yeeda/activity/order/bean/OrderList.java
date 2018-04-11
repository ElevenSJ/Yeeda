package com.sj.yeeda.activity.order.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * 创建时间: on 2018/4/11.
 * 创建人: 孙杰
 * 功能描述:
 */
@Keep
public class OrderList {
    List<OrderBean> dataList;

    public List<OrderBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<OrderBean> dataList) {
        this.dataList = dataList;
    }
}
