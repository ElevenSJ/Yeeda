package com.sj.yeeda.activity.pay;

import com.sj.yeeda.R;
import com.sj.yeeda.activity.pay.bean.PayBean;

public class PayPresent implements PayContract.Presenter{
    PayContract.View mView;
    PayBean[] items = new PayBean[2];
    public PayPresent( PayContract.View  view) {
        mView = view;
    }
    @Override
    public void start() {
        items[0] = new PayBean("微信支付",0 ,R.drawable.img_pay_wechat, R.drawable.img_right_arrow);
        items[1] = new PayBean("支付宝支付",1, R.drawable.img_pay_alipay, R.drawable.img_right_arrow);

        mView.upDataItemView( items);
    }
}
