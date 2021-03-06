package com.sj.yeeda.activity.user.register;

import android.text.TextUtils;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.user.register.bean.RegisterBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: on 2018/3/31.
 * 创建人: 孙杰
 * 功能描述:RegisterPresenter
 */
public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mView;

    public RegisterPresenter(RegisterContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void getCode(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum) || phoneNum.length() != 11) {
            ToastUtils.showShortToast("手机号码不正确");
            return;
        }
        mView.refreshCodeTxt(true);
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("phone", phoneNum);
        HttpManager.get(UrlConfig.GET_SMSCODE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String s) {
                ToastUtils.showShortToast(s);
            }

            @Override
            public void onSuccessData(String json) {

            }

            @Override
            public void onFailure(String error_code, String error_message) {
                mView.refreshCodeTxt(false);
            }
        });
    }

    @Override
    public void doRegister(String phoneNum, String codeNum) {
        Logger.d("phoneNum:" + phoneNum + ",codeNum:" + codeNum);
        if (TextUtils.isEmpty(phoneNum) || phoneNum.length() != 11) {
            ToastUtils.showShortToast("手机号码不正确");
            return;
        }
        if (TextUtils.isEmpty(codeNum)) {
            ToastUtils.showShortToast("请输入验证码");
            return;
        }
        mView.showProgress();
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("phone", phoneNum);
        parameters.put("msmcode", codeNum);
        HttpManager.get(UrlConfig.REGISTER_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                ToastUtils.showShortToast(json);
            }

            @Override
            public void onSuccessData(String json) {
                RegisterBean registerBean = new GsonResponsePasare<RegisterBean>() {
                }.deal(json);
                String tokenId = registerBean.getTokenId();
                SPUtils.getInstance().edit(SPFileUtils.FILE_USER).apply(SPFileUtils.TOKEN_ID, tokenId);
                mView.toSupplyUserInfoActivity();
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
