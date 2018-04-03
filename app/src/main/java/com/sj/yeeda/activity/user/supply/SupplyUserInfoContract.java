package com.sj.yeeda.activity.user.supply;

import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface SupplyUserInfoContract {
    interface View extends BaseView{
        void toMainActivity();

    }

    interface Presenter extends BasePresenter{
        void  supplyInfo(String phoneNum,String userName,String userSex);
    }
}
