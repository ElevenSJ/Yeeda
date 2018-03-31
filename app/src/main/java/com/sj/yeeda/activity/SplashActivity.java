package com.sj.yeeda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.jady.retrofitclient.HttpManager;
import com.sj.module_lib.base.BasePresenter;
import com.sj.yeeda.activity.http.UrlConfig;
import com.sj.yeeda.activity.user.login.LoginActivity;
import com.sj.yeeda.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: on 2018/3/30.
 * 创建人: 孙杰
 * 功能描述:
 */

public class SplashActivity  extends BaseActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toLogin();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void toLogin() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HttpManager.init(SplashActivity.this.getApplicationContext(), UrlConfig.BASE_URL);
//                HttpManager.getInstance().setOnGetHeadersListener(new HttpManager.OnGetHeadersListener() {
//                    @Override
//                    public Map<String, String> getHeaders() {
//                        Map<String, String> headers = new HashMap<>();
//                        headers.put("access_token", "1234");
//                        return headers;
//                    }
//                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
        },2000);

    }
}
