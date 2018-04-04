package com.sj.yeeda.activity.user.usercenter;

import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.activity.user.usercenter.bean.UserCenterRyvItem;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface UserCenterContract {

    interface View extends BaseView {
        void upDataItemView(UserCenterRyvItem[] items);
        void upDataHeadView(UserInfoBean userInfoBean);
    }

    interface Presenter extends BasePresenter {

    }
}
