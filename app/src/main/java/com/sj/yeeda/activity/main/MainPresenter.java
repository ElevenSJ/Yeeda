package com.sj.yeeda.activity.main;

import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public class MainPresenter implements MainContract.Presenter{
    MainContract.View mView;
    public MainPresenter(MainContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void loginOut() {
        SPUtils.getInstance().edit(SPFileUtils.FILE_USER).apply(new String[]{SPFileUtils.TOKEN_ID,SPFileUtils.IS_LOGIN},new Object[]{"",false});
        mView.loginOut();
    }
}
