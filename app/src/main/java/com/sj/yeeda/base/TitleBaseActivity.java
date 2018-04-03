package com.sj.yeeda.base;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sj.yeeda.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/4/2.
 * 创建人: 孙杰
 * 功能描述:
 */

public abstract class TitleBaseActivity<T extends BasePresenter> extends BaseActivity<T> {

    @Nullable
    @BindView(R.id.img_title_right)
    ImageView imgTitleRight;
    @Nullable
    @BindView(R.id.txt_title)
    TextView txtTitle;

    public void setImgTitleRight(boolean isVisiable, View.OnClickListener clickListener) {
        if (imgTitleRight != null) {
            imgTitleRight.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
            imgTitleRight.setOnClickListener(clickListener);
        }
    }

    public void setTitleTxt(String titleTxt) {
        if (txtTitle != null) {
            txtTitle.setVisibility(titleTxt == null || TextUtils.isEmpty(titleTxt) ? View.GONE : View.VISIBLE);
            if (titleTxt != null && !TextUtils.isEmpty(titleTxt)) {
                txtTitle.setText(titleTxt);
            }
        }
    }

    @OnClick(R.id.img_title_back)
    public void onTitleBackClick() {
        finish();
    }
}
