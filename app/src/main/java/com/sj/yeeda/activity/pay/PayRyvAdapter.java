package com.sj.yeeda.activity.pay;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.pay.bean.PayBean;
import com.sj.yeeda.activity.user.usercenter.bean.UserCenterRyvItem;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class PayRyvAdapter extends RecyclerArrayAdapter<PayBean> {
    public PayRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public UserCenterRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserCenterRyvHolder(parent);
    }

    private static class UserCenterRyvHolder extends BaseViewHolder<PayBean> {

        private TextView mTextView;

        public UserCenterRyvHolder(ViewGroup parent) {
            super(parent, R.layout.ryl_user_center_item);
            mTextView = $(R.id.textView);
        }

        public void setData(final PayBean data) {
            super.setData(data);
            mTextView.setText(data.getName());
            Drawable drawableLeft = getContext().getResources().getDrawable(data.getDrawableLeftId());// 找到资源图片
            Drawable drawableRight = getContext().getResources().getDrawable(data.getDrawableRightId());// 找到资源图片
            drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());// 设置图片宽高
            drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());// 设置图片宽高
            mTextView.setCompoundDrawables(drawableLeft, null, drawableRight, null);// 设置到控件中
        }
    }
}
