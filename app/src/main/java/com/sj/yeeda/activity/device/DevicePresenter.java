package com.sj.yeeda.activity.device;

import android.util.ArrayMap;

import com.jady.retrofitclient.HttpManager;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.device.bean.DeviceBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.List;
import java.util.Map;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */

public class DevicePresenter implements DeviceContract.Presenter {
    DeviceContract.View mView;

    String token;

    public DevicePresenter(DeviceContract.View view) {
        mView = view;
        token = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, "");
    }

    @Override
    public void start() {
        mView.showProgress();
        getDevices();
    }

    private void getDevices() {
        Map<String, Object> parameters = new ArrayMap<>(1);
        parameters.put("token", token);
        HttpManager.get(UrlConfig.QUERY_DEVICES_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
            }

            @Override
            public void onSuccessData(String json) {
                List<DeviceBean> deviceBeanList = new GsonResponsePasare<List<DeviceBean>>() {
                }.deal(json);
                mView.updateDeviceListView(deviceBeanList);
            }

            @Override
            public void onFailure(String error_code, String error_message) {
            }
            @Override
            public boolean enableShowToast() {
                return false;
            }
        });

    }
}
