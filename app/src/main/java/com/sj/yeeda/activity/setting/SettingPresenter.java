package com.sj.yeeda.activity.setting;

import com.jady.retrofitclient.HttpManager;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: on 2018/4/15.
 * 创建人: 孙杰
 * 功能描述:
 */
public class SettingPresenter  implements SettingContract.Presenter{
    SettingContract.View mView;

    public SettingPresenter(SettingContract.View view){
        this.mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void loginOut() {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("token", SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""));
        HttpManager.get(UrlConfig.LOGIN_OUT_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onSuccessData(String json) {

            }

            @Override
            public void onFailure(String error_code, String error_message) {

            }
            @Override
            public boolean enableShowToast() {
                return false;
            }
        });
        mView.loginOut();
    }
}
