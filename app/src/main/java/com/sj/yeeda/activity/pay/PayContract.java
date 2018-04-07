package com.sj.yeeda.activity.pay;

import com.sj.yeeda.activity.pay.bean.PayBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface PayContract {
    interface View extends BaseView {

        void upDataItemView(PayBean[] items);
    }

    interface Presenter extends BasePresenter {

    }
}
