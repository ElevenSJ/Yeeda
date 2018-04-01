package com.sj.yeeda.base;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.BuildConfig;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.utils.Utils;


/**
 * 创建时间: on 2018/3/29.
 * 创建人: 孙杰
 * 功能描述:应用入口基础类
 */

public class BaseApplication extends Application {

    private static BaseApplication sInstance;

    public static BaseApplication getApp() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        if (BuildConfig.DEBUG==true){
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
        Utils.init(this);
        ToastUtils.init(false);
        SPUtils.init(sInstance);
    }
}
