package com.sj.yeeda.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.base.BasePresenter;
import com.sj.module_lib.base.BaseView;


/**
 * Created by lenovo on 2018/3/29.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    public T presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        try {
            presenter.start();
        }catch (NullPointerException e){
            Logger.e("BasePresenter",e);
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    public abstract T getPresenter();
}