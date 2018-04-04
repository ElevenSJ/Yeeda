package com.sj.yeeda.activity.solutions;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.solutions.bean.SolutionBean;

public class SolutionListAdapter extends RecyclerArrayAdapter<SolutionBean> {
    public SolutionListAdapter(Context context) {
        super(context);
    }

    @Override
    public SolutionListAdapter.SolutionHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SolutionListAdapter.SolutionHolder(parent);
    }

    private static class SolutionHolder extends BaseViewHolder<SolutionBean> {


        public SolutionHolder(ViewGroup parent) {
            super(parent, R.layout.ryl_user_center_item);
        }

        public void setData(final SolutionBean data) {
            super.setData(data);
        }
    }
}