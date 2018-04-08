package com.sj.yeeda.activity.solutions.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.invoice.bean.InvoiceBean;
import com.sj.yeeda.activity.user.usercenter.bean.UserCenterRyvItem;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class SolutionInvoiceRyvAdapter extends RecyclerArrayAdapter<InvoiceBean> {
    public SolutionInvoiceRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public SolutionInvoiceRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SolutionInvoiceRyvHolder(parent);
    }

    private static class SolutionInvoiceRyvHolder extends BaseViewHolder<InvoiceBean> {

        private TextView inVoiceTitleTxt;
        private TextView inVoiceInfoTxt;
        private CheckBox mCheckBox;

        public SolutionInvoiceRyvHolder(ViewGroup parent) {
            super(parent, R.layout.ryl_solution_invoice_item);
            inVoiceTitleTxt = $(R.id.invoice_title);
            inVoiceInfoTxt = $(R.id.invoice_info);
            mCheckBox = $(R.id.cb_invoce);
        }

        @Override
        public void setData(final InvoiceBean data) {
            super.setData(data);
            mCheckBox.setChecked(true);
            inVoiceTitleTxt.setText(data.getTitle());
            inVoiceInfoTxt.setText(data.getIsVatInvoice().equals("1")?"增值税专用发票":"普通发票");
        }
    }
}
