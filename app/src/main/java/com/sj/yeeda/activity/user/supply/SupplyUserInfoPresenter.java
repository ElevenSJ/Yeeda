package com.sj.yeeda.activity.user.supply;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public class SupplyUserInfoPresenter implements SupplyUserInfoContract.Presenter{
    private SupplyUserInfoContract.View mView;
    public SupplyUserInfoPresenter(SupplyUserInfoContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }
}
