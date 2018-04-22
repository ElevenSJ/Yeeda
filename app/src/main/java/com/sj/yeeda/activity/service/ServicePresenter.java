package com.sj.yeeda.activity.service;

import com.orhanobut.logger.Logger;
import com.sj.yeeda.im.SDKCoreHelper;
import com.sj.yeeda.othertask.IMInitTask;
import com.yuntongxun.ecsdk.ECDevice;

/**
 * 创建时间: on 2018/4/17.
 * 创建人: 孙杰
 * 功能描述:
 */
public class ServicePresenter implements ServiceContract.Presenter {
    ServiceContract.View mView;
    public ServicePresenter(ServiceContract.View view){
        this.mView = view;
    }
    @Override
    public void start() {
        mView.showProgress();
        initIM();
    }

    public void initIM() {
        new IMInitTask(){
            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if(SDKCoreHelper.getConnectState()== ECDevice.ECConnectState.CONNECT_SUCCESS){
                    Logger.i("ServiceCustomActivity... IM已连接上");
                    mView.dismissProgress();
                    mView.initMessageAdapter();
                }
            }
        }.execute("IMInitTask");
    }
}
