package com.sj.yeeda.activity.order.detail;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.order.detail.bean.AttchmentRyvItem;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class AttchmentRyvAdapter extends RecyclerArrayAdapter<AttchmentRyvItem> {
    public AttchmentRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public AttchmentRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new AttchmentRyvHolder(parent);
    }

    private static class AttchmentRyvHolder extends BaseViewHolder<AttchmentRyvItem> {

        private TextView mTextNameView;
        private TextView mTextNumView;

        public AttchmentRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_order_attchment);
            mTextNameView = $(R.id.txt_name);
            mTextNumView = $(R.id.txt_num);
        }

        @Override
        public void setData(final AttchmentRyvItem data) {
            super.setData(data);
            mTextNameView.setText(data.getName());
            mTextNumView.setText(data.getNum());
        }
    }
}
