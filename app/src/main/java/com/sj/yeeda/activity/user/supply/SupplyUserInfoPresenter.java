package com.sj.yeeda.activity.user.supply;

import android.text.TextUtils;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.user.othertask.UserInfoSaveTask;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public class SupplyUserInfoPresenter implements SupplyUserInfoContract.Presenter {
    private SupplyUserInfoContract.View mView;

    public SupplyUserInfoPresenter(SupplyUserInfoContract.View view) {
        mView = view;
    }

    private UserInfoSaveTask userInfoSaveTask;

    @Override
    public void start() {

    }

    @Override
    public void supplyInfo(String phoneNum, String userName, String userSex) {
        Logger.d("phoneNum:" + phoneNum + ",userName:" + userName + ",userSex:" + userSex);
        if (TextUtils.isEmpty(userName)) {
            ToastUtils.showShortToast("请输入您的姓名");
            return;
        }
        mView.showProgress();
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("token", SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""));
        parameters.put("userName", userName);
        parameters.put("sex", userSex);

        HttpManager.get(UrlConfig.PARFIT_USER_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                mView.toMainActivity();
            }

            @Override
            public void onFailure(String error_code, String error_message) {

            }

            @Override
            public void onFinish() {
                mView.dismissProgress();
            }
        });
    }
}
