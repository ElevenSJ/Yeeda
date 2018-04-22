package com.sj.yeeda.base;

import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sj.yeeda.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/4/2.
 * 创建人: 孙杰
 * 功能描述:
 */
@Keep
public abstract class TitleBaseActivity<T> extends BaseActivity<T> {

    @Nullable
    @BindView(R.id.img_title_right)
    ImageView imgTitleRight;
    @Nullable
    @BindView(R.id.txt_message_title)
    TextView txtTitle;
    @Nullable
    @BindView(R.id.layout_title)
    ConstraintLayout layoutTopTitle;

    public void setImgTitleRight(boolean isVisiable, View.OnClickListener clickListener) {
        if (imgTitleRight != null) {
            imgTitleRight.setVisibility(isVisiable ? View.VISIBLE : View.INVISIBLE);
            imgTitleRight.setOnClickListener(clickListener);
        }
    }

    public void setTitleTxt(String titleTxt) {
        if (txtTitle != null) {
            txtTitle.setVisibility(titleTxt == null || TextUtils.isEmpty(titleTxt) ? View.INVISIBLE : View.VISIBLE);
            if (titleTxt != null && !TextUtils.isEmpty(titleTxt)) {
                txtTitle.setText(titleTxt);
            }
        }
    }

    public void setTitleBg() {
        if (layoutTopTitle != null) {
            layoutTopTitle.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            Logger.e("layoutTitle == null");
        }
    }

    @OnClick(R.id.img_title_back)
    public void onTitleBackClick() {
        finish();
    }
}
