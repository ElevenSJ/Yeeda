package com.sj.yeeda.activity.venue;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.invoice.InvoiceActivity;
import com.sj.yeeda.activity.invoice.bean.InvoiceBean;
import com.sj.yeeda.activity.venue.bean.VenueBean;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class VenueRyvAdapter extends RecyclerArrayAdapter<VenueBean> {
    Context context;

    public VenueRyvAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public UserCenterRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserCenterRyvHolder(parent, context);
    }

    private static class UserCenterRyvHolder extends BaseViewHolder<VenueBean> {
        TextView txtVenueInfo;
        CheckBox rdtVenueHead;
        ImageView imgEdit;
        ImageView imgDel;

        VenueActivity activity;

        public UserCenterRyvHolder(ViewGroup parent, Context context) {
            super(parent, R.layout.venue_item);
            rdtVenueHead = $(R.id.rdbt_select);
            txtVenueInfo = $(R.id.txt_info_value);
            imgEdit = $(R.id.img_edit);
            imgDel = $(R.id.img_delete);
            this.activity = (VenueActivity) context;
        }

        @Override
        public void setData(final VenueBean data) {
            super.setData(data);
            rdtVenueHead.setText(data.getName());
            rdtVenueHead.setChecked(data.getIsDefault()!=null&&data.getIsDefault().equals("1"));
            rdtVenueHead.setClickable(activity.getChooseType());
            txtVenueInfo.setText(data.getAddress()+"\n"+data.getContact()+"\n"+data.getContactPhone());
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.showShortToast(view.getId() + "");
                    activity.delVenue(data);
                }
            });
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.showShortToast(view.getId() + "");
                    activity.editVenue(data);
                }
            });
            if (activity.getChooseType()){
                rdtVenueHead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
