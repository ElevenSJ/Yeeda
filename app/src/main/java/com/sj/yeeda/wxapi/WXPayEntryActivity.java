package com.sj.yeeda.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.sj.yeeda.activity.pay.PayActivity;
import com.sj.yeeda.http.UrlConfig;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.wechat_pay_result);

        api = WXAPIFactory.createWXAPI(this, UrlConfig.WECHAT_APP_ID);
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Logger.d("onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == 0) {
            //支付成功
            sendBroadcast(new Intent(PayActivity.PAY_SUCCESS));
        } else if (resp.getType() == -1) {
            //支付失败
            sendBroadcast(new Intent(PayActivity.PAY_FAIL));
        } else {
            sendBroadcast(new Intent(PayActivity.PAY_CANCLE));
        }
        finish();
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.app_name);
//        builder.setMessage("微信支付结果：" + String.valueOf(resp.errCode));
//        builder.show();
    }
}