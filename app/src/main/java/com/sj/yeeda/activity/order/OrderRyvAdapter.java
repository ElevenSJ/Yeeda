package com.sj.yeeda.activity.order;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.glide.ImageUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.order.bean.OrderBean;
import com.sj.yeeda.activity.order.bean.OrderList;

import butterknife.BindView;

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

        TextView txtOrderId;
        TextView txtOrderTime;
        TextView txtOrderState;
        ImageView imageOrderSolutionIcon;
        TextView txtOrderSolutionName;
        TextView txtOrderSolutionId;
        ImageView imgDesignerIcon;
        TextView txtDesignerName;
        TextView txtDesignerPhone;
        TextView txtOrderVenueInfo;
        TextView txtOrderDetail;
        TextView txtOrderPrice;
        Button btService;
        Button btToPay;

        public OrderRyvHolder(ViewGroup parent) {
            super(parent, R.layout.order_item);
            txtOrderId = $(R.id.txt_order_id);
            txtOrderTime = $(R.id.txt_order_time);
            txtOrderState = $(R.id.txt_order_state);
            imageOrderSolutionIcon = $(R.id.txt_order_solution_icon);
            txtOrderSolutionName = $(R.id.txt_order_solution_name);
            txtOrderSolutionId = $(R.id.txt_order_solution_id);
            imgDesignerIcon = $(R.id.img_designer_icon);
            txtDesignerName = $(R.id.txt_designer_name);
            txtDesignerPhone = $(R.id.txt_designer_phone);
            txtOrderVenueInfo = $(R.id.txt_order_venue_info);
            txtOrderDetail = $(R.id.txt_order_detail);
            txtOrderPrice = $(R.id.txt_order_price);
            btService = $(R.id.bt_service);
            btToPay = $(R.id.bt_to_pay);
        }

        @Override
        public void setData(final OrderBean data) {
            super.setData(data);
            txtOrderId.setText("No." + data.getId());
            txtOrderTime.setText(data.getCreateTime());
            txtOrderState.setText(data.getStatus().equals("1") ? "已支付" : "未支付");
            if (data.getScheme() != null) {
                ImageUtils.loadImageWithError(data.getScheme().getSchemeIcon(), R.mipmap.ic_launcher, imageOrderSolutionIcon);
                txtOrderSolutionName.setText(data.getScheme().getSchemeName());
                txtOrderSolutionId.setText("id:" + data.getScheme().getId());
                ImageUtils.loadImageWithError(data.getScheme().getIcon(), R.drawable.img_personal_center_circle, imgDesignerIcon);
                txtDesignerName.setText(data.getScheme().getCreateName());
            }
            txtDesignerPhone.setText(data.getPhone());
            if (data.getVenue() != null) {
                txtOrderVenueInfo.setText(data.getVenue().getName() + "\n" + data.getVenue().getAddress() + "\n" + data.getVenue().getContact() + "\n" + data.getVenue().getContactPhone());
            }
            txtOrderPrice.setText("¥" + data.getMoney());
        }
    }
}