package com.sj.yeeda.activity.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.BuildConfig;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.main.MainActivity;
import com.sj.yeeda.activity.user.login.LoginActivity;

import cn.jpush.android.api.JPushInterface;
import com.sj.yeeda.othertask.IMAccountGetTask;

/**
 * 创建时间: on 2018/3/30.
 * 创建人: 孙杰
 * 功能描述:
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toGoNext();
    }

    private void initTools() {
        if (BuildConfig.DEBUG == true) {
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
        JPushInterface.setDebugMode(BuildConfig.DEBUG); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);

        ToastUtils.init(false);
        SPUtils.init(this);
        SPUtils.getInstance().edit(SPFileUtils.FILE_USER);
        SPUtils.getInstance().edit(SPFileUtils.FILE_IM_ACCOUNT);
        new IMAccountGetTask().execute("getImAccount");
    }


    private void toGoNext() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                if ((Boolean) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.IS_LOGIN, false)) {
                    intent.setClass(SplashActivity.this, MainActivity.class);
                } else {
                    intent.setClass(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
            }
        }, 2000);
        initTools();
    }
}
