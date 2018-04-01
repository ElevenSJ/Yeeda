package com.sj.yeeda.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.base.BasePresenter;
import com.sj.module_lib.base.BaseView;
import com.sj.module_lib.widgets.CustomDialog;


/**
 * Created by lenovo on 2018/3/29.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    public T presenter;
    private CustomDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new CustomDialog(this);
        presenter = getPresenter();
        try {
            presenter.start();
        }catch (Exception e){
            Logger.e("BasePresenter is null");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog!=null&&progressDialog.isShowing()){
            dismissProgress();
        }
    }

    @Override
    public void showProgress() {
        if (progressDialog!=null&&!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }

    public abstract T getPresenter();
}
