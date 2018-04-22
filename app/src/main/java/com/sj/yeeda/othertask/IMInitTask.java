package com.sj.yeeda.othertask;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.ArrayMap;

import com.jady.retrofitclient.HttpManager;
import com.sj.module_lib.utils.DateUtils;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.Utils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;
import com.sj.yeeda.im.SDKCoreHelper;
import com.yuntongxun.ecsdk.ECDevice;

import java.util.Map;

/**
 * 创建时间: on 2018/4/17.
 * 创建人: 孙杰
 * 功能描述:
 */
public class IMInitTask extends AsyncTask<String, String, Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {
        if(SDKCoreHelper.getConnectState()!= ECDevice.ECConnectState.CONNECT_SUCCESS) {
            SDKCoreHelper.getInstance().init(Utils.getContext());
        }
        return true;
    }

}
