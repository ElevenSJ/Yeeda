package com.sj.yeeda.activity.order.detail;

import com.sj.yeeda.activity.order.bean.OrderDetailBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/14.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface OrderDetailContract {

    interface View extends BaseView {
        void updateOrderDetailView(OrderDetailBean orderDetailBean);
    }

    interface Presenter extends BasePresenter {
        void  getOrderDetail(String id);
    }
}
