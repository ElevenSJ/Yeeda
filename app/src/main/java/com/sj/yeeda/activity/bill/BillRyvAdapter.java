package com.sj.yeeda.activity.bill;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.bill.bean.BillBean;
import com.sj.yeeda.activity.user.usercenter.bean.UserCenterRyvItem;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class BillRyvAdapter extends RecyclerArrayAdapter<BillBean> {
    BillPresenter presenter;
    public BillRyvAdapter(Context context,BillPresenter presenter) {
        super(context);
        this.presenter = presenter;
    }

    @Override
    public UserCenterRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserCenterRyvHolder(parent,presenter);
    }

    private static class UserCenterRyvHolder extends BaseViewHolder<BillBean> {
        TextView txtBillType;
        TextView txtBillInfo;
        RadioButton rdtBillHead;
        ImageView imgEdit;
        ImageView imgDel;

        BillPresenter presenter;

        public UserCenterRyvHolder(ViewGroup parent, BillPresenter presenter) {
            super(parent, R.layout.bill_item);
            rdtBillHead = $(R.id.rdbt_select);
            txtBillType = $(R.id.txt_bill_type);
            txtBillInfo = $(R.id.txt_info_value);
            imgEdit = $(R.id.img_edit);
            imgDel = $(R.id.img_delete);
        }

        public void setData(final BillBean data) {
            super.setData(data);
            rdtBillHead.setText("抬头：" + data.getTitle());
            txtBillType.setText(data.getIsVatInvoice().equals("0") ? "普通发票" : "增值税专用发票");
            txtBillInfo.setText(data.getTariff() + "\n" + data.getWorkAddress() + "\n" + data.getPhone() + "\n" + data.getBank() + "\n" + data.getAccount() + "\n" + data.getEmail() + "\n" + data.getExpressAddress());
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (presenter!=null){
                        presenter.edtBillInfo(data);
                    }
                }
            });
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (presenter!=null) {
                        presenter.delBillInfo(data.getId());
                    }
                }
            });
        }
    }
}
