package com.sj.yeeda.activity.device;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.device.bean.DeviceBean;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class DeviceRyvAdapter extends RecyclerArrayAdapter<DeviceBean> {

    public DeviceRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public UserCenterRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserCenterRyvHolder(parent);
    }

    private static class UserCenterRyvHolder extends BaseViewHolder<DeviceBean> {

        public UserCenterRyvHolder(ViewGroup parent) {
            super(parent, R.layout.venue_item);
        }

        @Override
        public void setData(final DeviceBean data) {
            super.setData(data);
        }
    }
}
