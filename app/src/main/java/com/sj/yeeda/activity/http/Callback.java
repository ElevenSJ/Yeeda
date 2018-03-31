package com.sj.yeeda.activity.http;

import com.google.gson.Gson;
import com.jady.retrofitclient.callback.HttpCallback;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.ToastUtils;

import java.lang.reflect.Type;


/**
 * 创建时间: on 2018/3/31.
 * 创建人: 孙杰
 * 功能描述:请求回调基类
 */
public abstract class Callback<T extends BaseResponse> extends HttpCallback<T> {
    @Override
    public void onResolve(T t) {
        Logger.i(t.toString());
        if (t.isSuccess()){
            onSuccess(t);
        }else{
            onFailed(t.getCode(), t.getMessage());
        }
    }

    @Override
    public void onFailed(String error_code, String error_message) {
        if (enableShowToast()) {
            ToastUtils.showShortToast(error_message);
        }
        onFailure(error_code, error_message);
    }

    public abstract void onSuccess(T v);

    public abstract void onFailure(String error_code, String error_message);

    public boolean enableShowToast() {
        return true;
    }

}
