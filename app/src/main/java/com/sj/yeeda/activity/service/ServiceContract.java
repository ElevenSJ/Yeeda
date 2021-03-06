package com.sj.yeeda.activity.service;

import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/17.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface ServiceContract {

    interface View extends BaseView {
        void initMessageAdapter();
    }

    interface Presenter extends BasePresenter {
    }
}
