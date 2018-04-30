package com.sj.yeeda.activity.order.detail.bean;

/**
 * 创建时间: on 2018/4/27.
 * 创建人: 孙杰
 * 功能描述:
 */
public class AttchmentRyvItem {
    String id ;
    String name;
    String num;

    public AttchmentRyvItem(String buildId, String buildName, String nums) {
        setId(buildId);
        setName(buildName);
        setNum(nums);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
