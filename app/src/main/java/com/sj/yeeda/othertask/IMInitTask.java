package com.sj.yeeda.othertask;

import android.os.AsyncTask;

/**
 * 创建时间: on 2018/4/17.
 * 创建人: 孙杰
 * 功能描述:
 */
public class IMInitTask extends AsyncTask<String, String, Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {
//        if(SDKCoreHelper.getConnectState()!= ECDevice.ECConnectState.CONNECT_SUCCESS) {
//            SDKCoreHelper.getInstance().init(Utils.getContext());
//        }
        return true;
    }

}
