package com.sj.yeeda.activity.device;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */

public class DevicePresenter implements  DeviceContract.Presenter{
    DeviceContract.View mView;
    public DevicePresenter(DeviceContract.View view){
        mView = view;
    }
    @Override
    public void start() {

    }
}
