package com.sj.yeeda.activity.pay;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.pay.bean.PayListItemBean;
import com.sj.yeeda.wxapi.WechatOrderBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
import java.util.Map;

public class PayPresent implements PayContract.Presenter{
    PayContract.View mView;
    PayListItemBean[] items = new PayListItemBean[2];
    public PayPresent( PayContract.View  view) {
        mView = view;
        items[0] = new PayListItemBean("微信支付",0 ,R.drawable.img_pay_wechat, R.drawable.img_right_arrow);
        items[1] = new PayListItemBean("支付宝支付",1, R.drawable.img_pay_alipay, R.drawable.img_right_arrow);
    }
    @Override
    public void start() {
        mView.upDataItemView( items);
    }
    /**
     * 微信下单统一
     * @param orderId
     * @param totalFee
     */
    @Override
    public void getWechatOrder(String orderId, String totalFee,String body) {
        mView.showProgress();
        Map<String,Object> map = new HashMap<>(3);
        map.put("orderId",orderId);
        map.put("totalFee",totalFee);
        map.put("body",body);
        map.put("trade_type","APP");
        HttpManager.get(UrlConfig.GET_WECHAT_ORDER_URL, map, new Callback() {
            @Override
            public void onSuccess(String json) {
                ToastUtils.showShortToast(json);
            }

            @Override
            public void onSuccessData(String json) {
                WechatOrderBean wechatOrderBean =  new GsonResponsePasare<WechatOrderBean>(){}.deal(json);
                mView.doWechatPay(wechatOrderBean);
            }

            @Override
            public void onFailure(String error_code, String error_message) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                mView.dismissProgress();
            }
        });

    }

    /**
     * 支付宝统一下单
     * @param orderId
     * @param totalFee
     */
    @Override
    public void getAlipayOrder(String orderId, String totalFee) {
        mView.showProgress();
        Map<String,Object> map = new HashMap<>(2);
        map.put("orderId",orderId);
        map.put("totalFee",totalFee);
        HttpManager.get(UrlConfig.GET_ALIPAY_SIGN_URL, map, new Callback() {
            @Override
            public void onSuccess(String json) {
                ToastUtils.showShortToast(json);
            }

            @Override
            public void onSuccessData(String json) {
                String sign = new GsonResponsePasare<String>(){}.deal(json);
                Logger.i("Alipay Sign = "+sign);
                mView.doAlipay(sign);
            }

            @Override
            public void onFailure(String error_code, String error_message) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                mView.dismissProgress();
            }
        });

    }

}
