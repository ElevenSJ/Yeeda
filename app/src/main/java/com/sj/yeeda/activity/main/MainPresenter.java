package com.sj.yeeda.activity.main;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public class MainPresenter implements MainContract.Presenter{
    MainContract.View mView;
    public MainPresenter(MainContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }
}
