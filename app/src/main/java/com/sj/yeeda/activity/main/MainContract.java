package com.sj.yeeda.activity.main;

import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public class MainContract {
    interface View extends BaseView {
        void loginOut();

    }

    interface Presenter extends BasePresenter {
        void loginOut();

    }
}
