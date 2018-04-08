package com.sj.yeeda.activity.solutions.list;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.solutions.list.bean.SolutionBean;
import com.sj.yeeda.activity.solutions.order.SolutionOrderActivity;

public class SolutionListAdapter extends RecyclerArrayAdapter<SolutionBean> {
    SolutionContract.View mView;

    public SolutionListAdapter(Context context,SolutionContract.View View) {
        super(context);
        this.mView = View;
    }

    @Override
    public SolutionListAdapter.SolutionHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SolutionListAdapter.SolutionHolder(parent,mView);
    }

    private static class SolutionHolder extends BaseViewHolder<SolutionBean> {
        SolutionContract.View mView;

        ImageView imgIconSolution;
        TextView txtSolutianName;
        TextView txtInstallation;
        TextView txtSolutionInfo;
        ImageView imgDesignerIcon;
        TextView txtDesignerName;
        RatingBar ratingBar;
        Button btSolutionDetail;
        Button btChoose;
        TextView txtSolutionPrice;

        public SolutionHolder(ViewGroup parent, SolutionContract.View mView) {
            super(parent, R.layout.solution_item);
            imgIconSolution = $(R.id.img_icon_solution);
            txtSolutianName = $(R.id.txt_solutian_name);
            txtInstallation = $(R.id.txt_installation);
            txtSolutionInfo = $(R.id.txt_solution_info);
            imgDesignerIcon = $(R.id.img_designer_icon);
            txtDesignerName = $(R.id.txt_designer_name);
            ratingBar = $(R.id.ratingBar);
            btSolutionDetail = $(R.id.bt_solution_detail);
            btChoose = $(R.id.bt_choose);
            txtSolutionPrice = $(R.id.txt_solution_price);

            this.mView = mView;

        }

        @Override
        public void setData(final SolutionBean data) {
            super.setData(data);
            ImageUtils.loadImageView(data.getSchemeIcon(),imgIconSolution);
            txtSolutianName.setText(data.getSchemeName());
            txtInstallation.setText(data.getInstallation());
            txtSolutionInfo.setText("id:"+data.getId()+" | 面积："+data.getAreaCategory());
            ImageUtils.loadImageView(data.getIcon(),imgDesignerIcon);
            txtDesignerName.setText("设计师："+data.getUserName());
            ratingBar.setStepSize(3f);
            txtSolutionPrice.setText("¥"+data.getSchemePrice());
            btSolutionDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mView.toSolutionDetail(data);

                }
            });

            btChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mView.toSolutionOrder(data);
                }
            });

        }
    }
}