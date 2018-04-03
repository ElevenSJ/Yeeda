package com.sj.yeeda.activity.user.supply;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.sj.yeeda.R;
import com.sj.yeeda.activity.user.login.LoginActivity;
import com.sj.yeeda.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public class SupplyUserInfoActivity extends BaseActivity<SupplyUserInfoPresenter> implements SupplyUserInfoContract.View {
    @BindView(R.id.edt_name_value)
    EditText edtNameValue;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    String phoneNun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phoneNun = getIntent().getStringExtra("phoneNum");
    }

    @Override
    public SupplyUserInfoPresenter getPresenter() {
        presenter = new SupplyUserInfoPresenter(this);
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_supply_userinfo;
    }

    @Override
    public void toMainActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.putExtra("isLogined", true);
        startActivity(loginIntent);
    }


    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        String sex = radioGroup.getCheckedRadioButtonId()==R.id.rdbt_male?"1":"2";
        presenter.supplyInfo(phoneNun,edtNameValue.getText().toString().trim(),sex);
    }
}
