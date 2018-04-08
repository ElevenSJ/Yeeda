package com.sj.yeeda.activity.solutions.order;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.venue.bean.VenueBean;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class SolutionVenueRyvAdapter extends RecyclerArrayAdapter<VenueBean> {
    public SolutionVenueRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public SolutionVenueRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SolutionVenueRyvHolder(parent);
    }

    private static class SolutionVenueRyvHolder extends BaseViewHolder<VenueBean> {

        private TextView inVoiceTitleTxt;
        private TextView inVoiceInfoTxt;
        private CheckBox mCheckBox;

        public SolutionVenueRyvHolder(ViewGroup parent) {
            super(parent, R.layout.ryl_solution_invoice_item);
            inVoiceTitleTxt = $(R.id.invoice_title);
            inVoiceInfoTxt = $(R.id.invoice_info);
            mCheckBox = $(R.id.cb_invoce);
        }

        @Override
        public void setData(final VenueBean data) {
            super.setData(data);
            inVoiceTitleTxt.setText(data.getName());
            inVoiceInfoTxt.setText(data.getAddress()+"  "+data.getContact()+"  "+data.getContactPhone());
        }
    }
}
