package com.sj.yeeda.activity.invoice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.invoice.bean.InvoiceBean;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class InvoiceRyvAdapter extends RecyclerArrayAdapter<InvoiceBean> {
    Context context;

    public InvoiceRyvAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public UserCenterRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserCenterRyvHolder(parent, context);
    }

    private static class UserCenterRyvHolder extends BaseViewHolder<InvoiceBean> {
        TextView txtBillType;
        TextView txtBillInfo;
        CheckBox rdtBillHead;
        ImageView imgEdit;
        ImageView imgDel;

        InvoiceActivity activity;

        public UserCenterRyvHolder(ViewGroup parent, Context context) {
            super(parent, R.layout.bill_item);
            rdtBillHead = $(R.id.rdbt_select);
            txtBillType = $(R.id.txt_bill_type);
            txtBillInfo = $(R.id.txt_info_value);
            imgEdit = $(R.id.img_edit);
            imgDel = $(R.id.img_delete);
            this.activity = (InvoiceActivity) context;
        }

        @Override
        public void setData(final InvoiceBean data) {
            super.setData(data);
            rdtBillHead.setClickable(activity.getChooseType());
            rdtBillHead.setChecked(rdtBillHead != null && rdtBillHead.equals("1"));
            rdtBillHead.setText("抬头：" + data.getTitle());
            txtBillType.setText(data.getIsVatInvoice().equals("0") ? "普通发票" : "增值税专用发票");
            txtBillInfo.setText(data.getTariff() + "\n" + data.getWorkAddress() + "\n" + data.getPhone() + "\n" + data.getBank() + "\n" + data.getAccount() + "\n" + data.getEmail() + "\n" + data.getExpressAddress()+ "\n" + data.getContact()+ "\n" + data.getContactPhone()+ "\n" + data.getCompanyName());
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.editInvoice(data);
                }
            });
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.delInvoice(data);
                }
            });
            if (activity.getChooseType()){
                rdtBillHead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            activity.setResult(data);
                        }
                    }
                });
            }
        }
    }
}
