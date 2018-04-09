package com.sj.yeeda.activity.pay.bean;

import android.support.annotation.Keep;

@Keep
public class PayListItemBean {

    String name;
    int type;
    int drawableLeftId;
    int drawableRightId;

    public PayListItemBean(String name, int type, int drawableLeftId, int img_right_arrow) {
        this.name = name;
        this.type = type;
        this.drawableLeftId = drawableLeftId;
        this.drawableRightId = img_right_arrow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
