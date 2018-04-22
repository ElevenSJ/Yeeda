package com.sj.yeeda.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.widgets.CustomDialog;
import com.sj.yeeda.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 创建时间: on 2018/3/29.
 * 创建人: 孙杰
 * 功能描述:activity 基类
 */
public abstract class BaseActivity<T> extends AppCompatActivity implements BaseView {
    public T presenter;
    private CustomDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        presenter = getPresenter();
        initView();
        try {
            ((BasePresenter)presenter).start();
        } catch (Exception e) {
            Logger.e("BasePresenter is null");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing()) {
            dismissProgress();
        }
        progressDialog = null;
    }

    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new CustomDialog(this);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public boolean isProgressShowing() {
        if (progressDialog != null) {
            return progressDialog.isShowing();
        }else{
            return false;
        }
    }
    public abstract T getPresenter();

    public abstract int getContentView();

    public void initView() {

    }
}
