package com.sj.yeeda.activity.pay;

import com.sj.yeeda.activity.pay.bean.PayListItemBean;
import com.sj.yeeda.wxapi.WechatOrderBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface PayContract {
    interface View extends BaseView {

        void upDataItemView(PayListItemBean[] items);
        void doAlipay(String orderStr);

        void doWechatPay(WechatOrderBean wechatOrderBean);
    }

    interface Presenter extends BasePresenter {
        void getAlipayOrder(String orderId,String totalFee);
        void getWechatOrder(String orderId,String totalFee,String body);

    }
}
