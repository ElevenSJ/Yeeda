package com.sj.yeeda.activity.pay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.pay.alipay.PayResult;
import com.sj.yeeda.activity.pay.bean.PayListItemBean;
import com.sj.yeeda.wxapi.WechatOrderBean;
import com.sj.yeeda.base.TitleBaseActivity;
import com.sj.yeeda.http.UrlConfig;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.lang.ref.WeakReference;
import java.util.Map;

import butterknife.BindView;

public class PayActivity extends TitleBaseActivity<PayContract.Presenter> implements PayContract.View {

    public static final String PAY_SUCCESS = "com.sj.yeeda.activity.pay.PAY_SUCCESS";
    public static final String PAY_FAIL = "com.sj.yeeda.activity.pay.PAY_FAIL";
    public static final String PAY_CANCLE = "com.sj.yeeda.activity.pay.PAY_CANCLE";
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    @BindView(R.id.txt_price)
    TextView txtPrice;

    PayRyvAdapter mAdapter;

    String orderId;
    String allPrice;

    int payPrice=0;

    Handler mHandler = new ResultHandler(this);

    private BroadcastReceiver mPayReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (PAY_SUCCESS.equals(intent.getAction())) {
                //支付成功
                onSuccessBack();
            } else if (PAY_FAIL.equals(intent.getAction())) {
                //支付失败
                ToastUtils.showShortToast("支付失败");
            } else if (PAY_CANCLE.equals(intent.getAction())) {
                //支付取消
                ToastUtils.showShortToast("取消支付");
            }
        }
    };

    //支付宝支付flag
    private static final int SDK_PAY_FLAG = 1;

    @Override
    public PayContract.Presenter getPresenter() {
        if (presenter == null) {
            presenter = new PayPresent(this);
        }
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
        allPrice = getIntent().getStringExtra("allPrice");
        txtPrice.setText("¥ "+allPrice);
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
                int type = mAdapter.getItem(position).getType();
                switch (type) {
                    case 0:
                        //微信支付
                        getPresenter().getWechatOrder(orderId, payPrice+"", "商品订单号：" + orderId);
                        break;
                    case 1:
                        //支付宝支付
                        getPresenter().getAlipayOrder(orderId, payPrice+"");
                        break;
                    default:
                }

            }
        });
        rylView.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PAY_SUCCESS);
        intentFilter.addAction(PAY_FAIL);
        intentFilter.addAction(PAY_CANCLE);
        registerReceiver(mPayReceiver, intentFilter);
    }

    @Override
    public void upDataItemView(PayListItemBean[] items) {
        mAdapter.addAll(items);
        try {
            payPrice = (int)(Double.valueOf(allPrice)*100);
        }catch(Exception e){
            e.printStackTrace();
            ToastUtils.showShortToast("价格不正确");
            finish();
//            return;
        }
        if ("0".equals(allPrice)){
            onSuccessBack();
        }
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
//        request.packageValue = wechatOrderBean.getPackageX();
        request.packageValue = "Sign=WXPay";
        request.nonceStr = wechatOrderBean.getNoncestr();
        request.timeStamp = wechatOrderBean.getTimestamp();
        request.sign = wechatOrderBean.getSign();
        if (request.checkArgs()){
            msgApi.sendReq(request);
        }else{
            ToastUtils.showShortToast("参数异常");
        }
//        Intent intent = new Intent(this, WXPayEntryActivity.class);
//        intent.putExtra("data", wechatOrderBean);
//        startActivity(intent);

    }

    class ResultHandler extends Handler {
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
                        onSuccessBack();
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

    private void onSuccessBack() {
        setResult(RESULT_OK);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销广播
        unregisterReceiver(mPayReceiver);
    }

}
