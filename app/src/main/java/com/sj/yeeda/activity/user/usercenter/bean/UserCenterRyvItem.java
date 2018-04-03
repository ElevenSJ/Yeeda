package com.sj.yeeda.activity.user.usercenter.bean;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class UserCenterRyvItem {
    String name;
    int drawableId;

    public UserCenterRyvItem(String name, int drawableId) {
        this.name = name;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
