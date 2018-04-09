package com.sj.yeeda.activity.device;

import com.sj.yeeda.activity.device.bean.DeviceBean;
import com.sj.yeeda.activity.invoice.bean.InvoiceBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

import java.util.List;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface DeviceContract {
    interface View extends BaseView {
        void updateDeviceListView(List<DeviceBean> deviceBeanList);
    }

    interface Presenter extends BasePresenter {
    }
}
