package com.sj.yeeda.othertask;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.jady.retrofitclient.HttpManager;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.service.bean.IMAccountBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

/**
 * 创建时间: on 2018/4/17.
 * 创建人: 孙杰
 * 功能描述:
 */
public class IMAccountGetTask extends AsyncTask<String, String, Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {
        if (TextUtils.isEmpty((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.KEFU_ID, ""))) {
            HttpManager.get(UrlConfig.QUERY_IM_ACCOUNT_URL, null, new Callback() {
                @Override
                public void onSuccess(String message) {
                }

                @Override
                public void onSuccessData(String json) {
                    IMAccountBean imAccountBean = new GsonResponsePasare<IMAccountBean>() {
                    }.deal(json);
                    SPUtils.getInstance().edit(SPFileUtils.FILE_IM_ACCOUNT).apply(new String[]{SPFileUtils.KEFU_ID, SPFileUtils.TEZHAN_ID, SPFileUtils.DINGDAN_LOGIN}, new String[]{imAccountBean.getKefu(), imAccountBean.getTezhan(), imAccountBean.getKefu()});
                }

                @Override
                public void onFailure(String error_code, String error_message) {
                }
            });
        }
        return true;

    }


}
