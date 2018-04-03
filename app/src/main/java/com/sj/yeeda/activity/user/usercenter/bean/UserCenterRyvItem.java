package com.sj.yeeda.activity.user.usercenter.bean;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class UserCenterRyvItem {
    String name;
    int drawableLeftId;
    int drawableRightId;

    public UserCenterRyvItem(String name, int drawableLeftId,int drawableRightId) {
        this.name = name;
        this.drawableLeftId = drawableLeftId;
        this.drawableRightId = drawableRightId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableLeftId() {
        return drawableLeftId;
    }

    public void setDrawableLeftId(int drawableLeftId) {
        this.drawableLeftId = drawableLeftId;
    }

    public int getDrawableRightId() {
        return drawableRightId;
    }

    public void setDrawableRightId(int drawableRightId) {
        this.drawableRightId = drawableRightId;
    }
}
