package com.sj.module_lib.base;


import android.support.annotation.Keep;

/**
 * 创建时间: on 2018/3/29.
 * 创建人: 孙杰
 * 功能描述:View接口的基类
 */
@Keep
public interface BaseView{

        void showProgress();

        void dismissProgress();
}
