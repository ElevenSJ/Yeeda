package com.sj.yeeda.activity.order;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.widgets.AmountView;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.order.bean.OrderBean;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class OrderRyvAdapter extends RecyclerArrayAdapter<OrderBean> {
    Context context;
    public OrderRyvAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public OrderRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderRyvHolder(parent);
    }

    private static class OrderRyvHolder extends BaseViewHolder<OrderBean> {

        public OrderRyvHolder(ViewGroup parent) {
            super(parent, R.layout.device_item);
        }

        @Override
        public void setData(final OrderBean data) {
            super.setData(data);

        }
    }
}
