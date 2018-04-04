package com.sj.yeeda.http;

import com.google.gson.Gson;
import com.jady.retrofitclient.callback.HttpCallback;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.activity.user.login.bean.LoginBean;

import java.lang.reflect.Type;


/**
 * 创建时间: on 2018/3/31.
 * 创建人: 孙杰
 * 功能描述:请求回调基类
 */
public abstract class Callback extends HttpCallback<String> {
    @Override
    public void onResolve(String json) {
        Logger.i("返回报文" + json);
        BaseResponse baseResponse = new Gson().fromJson(json, BaseResponse.class);
        Logger.i("返回报文" + baseResponse.getData().toString() +",length:"+baseResponse.getData().toString().length());
        onFinish();
        if (baseResponse.success) {
            if (baseResponse.getData().toString().equals("\"\"")||baseResponse.getData().toString().length()<=2){
                onSuccess(baseResponse.message);
            }else{
                onSuccess(json);
            }
        } else {
            onFailed(baseResponse.code, baseResponse.message);
        }
    }
    @Override
    public void onFailed(String error_code, String error_message) {
        onFinish();
        if (enableShowToast()) {
            ToastUtils.showShortToast(error_message);
        }
        onFailure(error_code, error_message);
    }

    public abstract void onSuccess(String json);

    public abstract void onFailure(String error_code, String error_message);

    public boolean enableShowToast() {
        return true;
    }

    public void onFinish() {
    }

}
