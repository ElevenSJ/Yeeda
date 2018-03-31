package com.sj.yeeda.activity.user.login;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.activity.http.BaseResponse;
import com.sj.yeeda.activity.http.Callback;
import com.sj.yeeda.activity.http.UrlConfig;
import com.sj.yeeda.activity.user.login.bean.LoginBean;
import com.sj.yeeda.activity.user.register.bean.RegisterBean;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>类说明</p>
 *
 * @author 张华洋 2017/2/22 20:33
 * @version V1.2.0
 * @name LoginPresenter
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    /**
     *
     * @param phoneNum
     */
    @Override
    public void getCode(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)||phoneNum.length()!=11){
            ToastUtils.showShortToast("手机号码不正确");
            return;
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("phone", phoneNum);
        HttpManager.get(UrlConfig.GET_SMSCODE_URL, parameters, new Callback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse data) {
                ToastUtils.showShortToast(data.getMessage());
                mView.refreshCodeTxt();
                Logger.v(data.toString());
            }

            @Override
            public void onFailure(String error_code, String error_message) {
                Logger.e("onFailure   err_code:"+error_code+",message:"+error_message);
            }
        });
    }

    /**
     * @param phoneNum
     * @param codeNum
     * @param deviceId
     */
    @Override
    public void doLogin(String phoneNum, String codeNum, String deviceId) {
        Logger.d("phoneNum:"+phoneNum+",codeNum:"+codeNum+",deviceId"+deviceId);
        if (TextUtils.isEmpty(phoneNum)||phoneNum.length()!=11){
            ToastUtils.showShortToast("手机号码不正确");
            return;
        }
        if (TextUtils.isEmpty(codeNum)){
            ToastUtils.showShortToast("请输入验证码");
            return;
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("phone", phoneNum);
        parameters.put("checkcode", codeNum);
        parameters.put("checkcode", deviceId);
        HttpManager.get(UrlConfig.LOGIN_URL, parameters, new Callback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse data) {
                LoginBean loginBean = new Gson().fromJson(data.getData().toString(),LoginBean.class);
                String token = loginBean.getTokenId();
                Logger.v(token);
                mView.toMainActivity();
            }

            @Override
            public void onFailure(String error_code, String error_message) {
                Logger.e("onFailure   err_code:"+error_code+",message:"+error_message);
            }
        });
    }

}
