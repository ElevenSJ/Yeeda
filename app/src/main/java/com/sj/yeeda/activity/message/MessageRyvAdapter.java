package com.sj.yeeda.activity.message;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.message.bean.MessageBean;
import com.sj.yeeda.activity.order.bean.OrderBean;

/**
 * 创建时间: on 2018/4/15.
 * 创建人: 孙杰
 * 功能描述:
 */
public class MessageRyvAdapter extends RecyclerArrayAdapter<MessageBean> {
    Context context;

    public MessageRyvAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public MessageRyvAdapter.MessageRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageRyvAdapter.MessageRyvHolder(parent);
    }

    private static class MessageRyvHolder extends BaseViewHolder<MessageBean> {
        TextView txtTitle;
        TextView txtdetail;
        TextView txtTime;

        public MessageRyvHolder(ViewGroup parent) {
            super(parent, R.layout.message_item);
            txtTitle = $(R.id.txt_message_title);
            txtdetail = $(R.id.txt_message_detail);
            txtTime = $(R.id.txt_message_time);
        }

        @Override
        public void setData(final MessageBean data) {
            super.setData(data);
            txtTitle.setText("订单号:"+data.getOrderId());
            txtdetail.setText(data.getNews());
            txtTime.setText(data.getCreatetime());
        }
    }
}
