package com.sj.yeeda.activity.setting;

import com.sj.yeeda.activity.order.bean.OrderDetailBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/15.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface SettingContract {
    interface View extends BaseView {
        void loginOut();
    }

    interface Presenter extends BasePresenter {
        void loginOut();
    }
}
