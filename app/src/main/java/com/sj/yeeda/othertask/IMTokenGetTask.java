package com.sj.yeeda.othertask;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.ArrayMap;

import com.jady.retrofitclient.HttpManager;
import com.sj.module_lib.utils.DateUtils;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.service.bean.IMAccountBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.Map;

/**
 * 创建时间: on 2018/4/17.
 * 创建人: 孙杰
 * 功能描述:
 */
public class IMTokenGetTask extends AsyncTask<String, String, Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {
        if (TextUtils.isEmpty((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.IM_TOKEN_ID, ""))) {
            Map<String,Object> parameters = new ArrayMap<>(2);
            parameters.put("tokenid", SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""));
            parameters.put("timestamp", DateUtils.getCurrentTime("yyyyMMddHHmmss"));
            HttpManager.get(UrlConfig.QUERY_IM_TOKEN_URL, parameters, new Callback() {
                @Override
                public void onSuccess(String message) {
                }

                @Override
                public void onSuccessData(String json) {
                    String token = new GsonResponsePasare<String>(){}.deal(json);
                    SPUtils.getInstance().edit(SPFileUtils.FILE_IM_ACCOUNT).apply(SPFileUtils.IM_TOKEN_ID,token);
                }

                @Override
                public void onFailure(String error_code, String error_message) {
                }
            });
        }
        return true;

    }


}
