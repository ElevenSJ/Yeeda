package com.sj.yeeda.activity.solutions.list.bean;

import android.support.annotation.Keep;

/**
 * 创建时间: on 2018/4/9.
 * 创建人: 孙杰
 * 功能描述:
 */
@Keep
public class SolutionArea {

    String id;
    String areaCategory;

    private SolutionArea(){

    }

    public SolutionArea(String id, String areaCategory) {
        this.id = id;
        this.areaCategory = areaCategory;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaCategory() {
        return areaCategory;
    }

    public void setAreaCategory(String areaCategory) {
        this.areaCategory = areaCategory;
    }
}
