package com.sj.yeeda.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.Utils;
import com.sj.yeeda.Utils.SPFileUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 创建时间: on 2018/4/16.
 * 创建人: 孙杰
 * 功能描述:
 */
public class JPushAliasService extends Service {

    int count = 0;
    final int maxCount = 5;
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            count++;
            if (count>=maxCount){
                JPushAliasService.this.stopSelf();
                return;
            }
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Logger.d( "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(Utils.getContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    Logger.i( "Unhandled msg - " + msg.what);
            }
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success "+alias;
                    SPUtils.getInstance().edit(SPFileUtils.FILE_USER).apply(SPFileUtils.IS_SET_JPUSH_ALIAS,true);
                    JPushAliasService.this.stopSelf();
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
            }
            Logger.e(logs);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String alias = intent.getStringExtra("alias");
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        Logger.e("JPushAliasService onDestroy");
    }
}
