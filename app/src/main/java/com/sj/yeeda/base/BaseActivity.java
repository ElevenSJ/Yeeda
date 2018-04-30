package com.sj.yeeda.base;

import android.content.Intent;
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
import com.sj.yeeda.activity.welcome.SplashActivity;

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
        //不走恢复流程
        if (savedInstanceState != null) {
            Intent intent = new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            setContentView(getContentView());
            ButterKnife.bind(this);
            presenter = getPresenter();
            initView();
            if (presenter != null) {
                ((BasePresenter) presenter).start();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing()) {
            dismissProgress();
        }
        progressDialog = null;
        presenter = null;
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
        } else {
            return false;
        }
    }

    public abstract T getPresenter();

    public abstract int getContentView();

    public void initView() {

    }
}
