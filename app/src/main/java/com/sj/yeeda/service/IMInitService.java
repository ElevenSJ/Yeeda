package com.sj.yeeda.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.sj.module_lib.utils.Utils;

/**
 * 创建时间: on 2018/4/18.
 * 创建人: 孙杰
 * 功能描述:
 */
public class IMInitService extends IntentService{
    public IMInitService() {
        super("IMInitService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//        new IMTokenGetTask(){
//            @Override
//            protected void onPostExecute(Boolean aBoolean) {
//                super.onPostExecute(aBoolean);
//                SDKCoreHelper.getInstance().init(Utils.getContext());
//            }
//        }.execute("IMTokenGetTask");
    }
}
