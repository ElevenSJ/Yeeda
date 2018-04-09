package com.sj.yeeda.activity.device;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.widgets.AmountView;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.device.bean.DeviceBean;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class DeviceRyvAdapter extends RecyclerArrayAdapter<DeviceBean> {
    Context context;
    public DeviceRyvAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public DeviceRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeviceRyvHolder(parent,context);
    }

    private static class DeviceRyvHolder extends BaseViewHolder<DeviceBean> {
        DeviceActivity mActivity;

        TextView txtName;
        TextView txtCompany;
        TextView txtPrice;
        AmountView amountView;
        ImageView imgIcon;

        public DeviceRyvHolder(ViewGroup parent, Context context) {
            super(parent, R.layout.device_item);
            txtName = $(R.id.txt_device_name);
            txtCompany = $(R.id.txt_device_company);
            txtPrice = $(R.id.txt_device_price);
            amountView = $(R.id.amount_view);
            imgIcon = $(R.id.img_device_icon);
            mActivity = (DeviceActivity) context;
        }

        @Override
        public void setData(final DeviceBean data) {
            super.setData(data);
            txtName.setText(data.getName());
            txtCompany.setText(data.getRentCompanyId());
            txtPrice.setText("¥" + data.getPrice());
            ImageUtils.loadImageWithError(data.getThumbnail(), R.drawable.logo, imgIcon);

            amountView.setGoods_storage(5000);
            amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    data.setNum(amount);
                    mActivity.updatePrice(data);
                }
            });

        }
    }
}
