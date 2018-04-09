package com.sj.yeeda.activity.pay;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.utils.Utils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.pay.alipay.PayResult;
import com.sj.yeeda.activity.pay.bean.PayListItemBean;
import com.sj.yeeda.activity.pay.wechat.WechatOrderBean;
import com.sj.yeeda.base.TitleBaseActivity;
import com.sj.yeeda.http.UrlConfig;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.lang.ref.WeakReference;
import java.util.Map;

import butterknife.BindView;

public class PayActivity extends TitleBaseActivity<PayPresent> implements PayContract.View {
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;

    PayRyvAdapter mAdapter;

    String orderId;

    Handler mHandler = new ResultHandler(this);

    //支付宝支付flag
    private static final int SDK_PAY_FLAG = 1;

    @Override
    public PayPresent getPresenter() {
        presenter = new PayPresent(this);
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_pay_type;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("支付");
        setTitleBg();
        orderId = getIntent().getStringExtra("orderId");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 16, 16);
        dividerDecoration.setDrawLastItem(false);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new PayRyvAdapter(this);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (TextUtils.isEmpty(orderId)) {
                    ToastUtils.showShortToast("未获取到订单id");
                    return;
                }
                ToastUtils.showShortToast(mAdapter.getItem(position).getName());
                int type = mAdapter.getItem(position).getType();
                switch (type) {
                    case 0:
                        //微信支付
                        getPresenter().getWechatOrder("orderId", "1", "测试商品支付");
                        break;
                    case 1:
                        //支付宝支付
                        getPresenter().getAlipayOrder("orderId", "0.01");
                        break;
                    default:
                }

            }
        });
        rylView.setAdapter(mAdapter);
    }

    @Override
    public void upDataItemView(PayListItemBean[] items) {
        mAdapter.addAll(items);
    }

    @Override
    public void doAlipay(final String orderStr) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(orderStr, true);
                Logger.i("msp" + result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void doWechatPay(WechatOrderBean wechatOrderBean) {
        UrlConfig.WECHAT_APP_ID = wechatOrderBean.getAppId();
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp(UrlConfig.WECHAT_APP_ID);

        PayReq request = new PayReq();
        request.appId = wechatOrderBean.getAppId();
        request.partnerId = wechatOrderBean.getPartnerid();
        request.prepayId = wechatOrderBean.getPrepayid();
        request.packageValue = wechatOrderBean.getPackageX();
        request.nonceStr = wechatOrderBean.getNoncestr();
        request.timeStamp = wechatOrderBean.getTimestamp();
        request.sign = wechatOrderBean.getSign();
        msgApi.sendReq(request);

    }

    static class ResultHandler extends Handler {
        WeakReference<PayActivity> mActivity;

        public ResultHandler(PayActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    Logger.i("支付宝支付返回结果:" + payResult.toString());
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShortToast("支付成功");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShortToast("支付失败");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
