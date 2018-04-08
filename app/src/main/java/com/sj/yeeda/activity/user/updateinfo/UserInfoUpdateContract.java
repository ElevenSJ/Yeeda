package com.sj.yeeda.activity.user.updateinfo;

import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface UserInfoUpdateContract {
    interface View extends BaseView {
        void upDataUserInfoView(UserInfoBean userInfoBean);
    }

    interface Presenter extends BasePresenter {
        void updateUserInfo(UserInfoBean userInfoBean);
    }
}
