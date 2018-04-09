package com.sj.yeeda.activity.solutions.list.bean;

import android.support.annotation.Keep;

import java.util.List;
@Keep
public class SolutionList {
    List<SolutionBean> dataList;

    public List<SolutionBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<SolutionBean> dataList) {
        this.dataList = dataList;
    }
}
