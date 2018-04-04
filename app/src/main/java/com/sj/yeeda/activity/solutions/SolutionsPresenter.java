package com.sj.yeeda.activity.solutions;

/**
 * 创建时间: on 2018/4/4.
 * 创建人: 孙杰
 * 功能描述:
 */

public class SolutionsPresenter implements SolutionContract.Presenter {
    SolutionContract.View mView;

    public SolutionsPresenter(SolutionContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }
}
