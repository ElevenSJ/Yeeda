package com.sj.yeeda.activity.user.usercenter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.user.usercenter.bean.UserCenterRyvItem;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class UserRyvAdapter extends RecyclerArrayAdapter<UserCenterRyvItem> {
    public UserRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public UserCenterRyvHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserCenterRyvHolder(parent);
    }

    private class UserCenterRyvHolder extends BaseViewHolder{

        private TextView mTextView;

        public UserCenterRyvHolder(ViewGroup parent) {
            super(parent, R.layout.ryl_user_center_item);
            mTextView = (TextView) $(R.id.textView);
        }

        public void setData(final UserCenterRyvItem data) {
            super.setData(data);
            mTextView.setText(data.getName());
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.showShortToast(data.getName());
                }
            });
        }
    }
}
