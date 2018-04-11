package com.sj.yeeda.widgets;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;

/**
 * 创建时间: on 2018/4/11.
 * 创建人: 孙杰
 * 功能描述:
 */

public class NoScroolLinearLayoutManager extends LinearLayoutManager {

    public NoScroolLinearLayoutManager(Context context) {
        super(context);
    }

    public NoScroolLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public NoScroolLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
        View view = recycler.getViewForPosition(0);
        if (view != null) {
            measureChild(view, widthSpec, heightSpec);
            int measuredWidth = View.MeasureSpec.getSize(widthSpec);
            int measuredHeight = view.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);
        }
    }
}