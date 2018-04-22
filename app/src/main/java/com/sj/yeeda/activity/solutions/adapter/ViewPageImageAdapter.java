package com.sj.yeeda.activity.solutions.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sj.module_lib.glide.ImageUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.solutions.detail.bean.SolutionDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间: on 2018/4/15.
 * 创建人: 孙杰
 * 功能描述:
 */
public class ViewPageImageAdapter extends PagerAdapter {
    ArrayList<SolutionDetailBean.ImageBena> images = new ArrayList<>();
    List<ImageView> mViews = new ArrayList<>();
    Context context;

    public ViewPageImageAdapter(Context ctx) {
        this.context = ctx;
    }

    public void setData(List<SolutionDetailBean.ImageBena> data) {
        mViews.clear();
        images.clear();
        images.addAll(data);
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mViews.add(imageView);
        }
        this.notifyDataSetChanged();
    }

    public SolutionDetailBean.ImageBena getItem(int position) {
        return images.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView view = mViews.get(position);
        final String res = getItem(position).getImgPath();
        ImageUtils.loadImageWithError(res, R.mipmap.ic_launcher, view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView imageView = mViews.get(position);
        if (imageView != null) {
            container.removeView(imageView);
        }
    }


}
