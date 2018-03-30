package com.sj.module_lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 创建时间: on 2018/3/29.
 * 创建人: 孙杰
 * 功能描述:Activity基础类
 */
public class BaseActivity<T extends BasePresenter> extends AppCompatActivity{
    T basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenter = null;
    }
}
