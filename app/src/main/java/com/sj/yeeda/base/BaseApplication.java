package com.sj.yeeda.base;

import android.app.Application;

import com.jady.retrofitclient.HttpManager;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.utils.Utils;
import com.sj.yeeda.BuildConfig;
import com.sj.yeeda.R;
import com.sj.yeeda.http.UrlConfig;
import com.sj.yeeda.im.IMManagerImpl;
import com.yuntongxun.plugin.common.SDKCoreHelper;
import com.yuntongxun.plugin.common.common.utils.LogUtil;
import com.yuntongxun.plugin.im.manager.IMPluginHelper;
import com.yuntongxun.plugin.im.manager.IMPluginManager;
import com.yuntongxun.plugin.im.manager.bean.IMConfiguration;


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
        //容联IM
        if (!IMPluginHelper.shouldInit(this)) {
            //防止多进程初始化多次
            return;
        }
        SDKCoreHelper.setContext(this);
        // 插件日志开启(放在setContext之后)
        LogUtil.setDebugMode(BuildConfig.DEBUG);
        initIMPlugin();

        ToastUtils.init(false);
        sInstance = this;
        Utils.init(sInstance);
        HttpManager.init(this.getApplicationContext(), UrlConfig.BASE_URL);

    }

    private void initIMPlugin() {
        /**
         * 推荐配置样式2
         */
        IMConfiguration imConfiguration1 = new IMConfiguration.IMConfigBuilder(this)
                .setOnIMBindViewListener(IMManagerImpl.getInstance())
                .setOnNotificationClickListener(IMManagerImpl.getInstance())
                .setOnReturnIdsClickListener(IMManagerImpl.getInstance())
                .setOnMessagePreproccessListener(IMManagerImpl.getInstance())
                .notifyIcon(R.mipmap.ic_launcher)
                .topBarBackgroundColorRecource(R.color.colorPrimaryDark)
                .topBarTitleColorResource(R.color.white)
//                .showMsgState(true)
                .topBarLeftImageDrawableResource(R.drawable.img_title_back)
                .topBarSingleImageDrawableResource(R.color.colorPrimaryDark)
                .build();
        IMPluginManager.getManager().init(imConfiguration1);
    }

}
