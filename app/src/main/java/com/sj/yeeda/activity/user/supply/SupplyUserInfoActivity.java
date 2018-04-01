package com.sj.yeeda.activity.user.supply;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sj.module_lib.base.BasePresenter;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.user.login.LoginActivity;
import com.sj.yeeda.base.BaseActivity;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public class SupplyUserInfoActivity extends BaseActivity<SupplyUserInfoPresenter> implements SupplyUserInfoContract.View{
    @Override
    public SupplyUserInfoPresenter getPresenter() {
        presenter = new SupplyUserInfoPresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_userinfo);
    }

    @Override
    public void toMainActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.putExtra("isLogined",true);
        startActivity(loginIntent);
    }
}
