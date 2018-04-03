package com.sj.yeeda.activity.main;

import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface MainContract {
    interface View extends BaseView {
        void loginOut();
        void updateUserView(UserInfoBean userInfoBean);

    }

    interface Presenter extends BasePresenter {
        void getUserInfo();
        void loginOut();

    }
}
