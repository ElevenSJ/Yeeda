package com.sj.yeeda.activity.order.list;

import com.sj.yeeda.activity.order.bean.OrderList;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface OrderContract {
    interface View extends BaseView {
        void updateOrderList(OrderList orderList);
    }

    interface Presenter extends BasePresenter {
        void getOrders(String fistIndex,int pageNum);
    }
}
