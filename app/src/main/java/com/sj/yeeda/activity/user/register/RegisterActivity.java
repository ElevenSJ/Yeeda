package com.sj.yeeda.activity.user.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sj.module_lib.utils.DeviceUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.user.login.LoginActivity;
import com.sj.yeeda.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/31.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {
    @BindView(R.id.edt_phone_value)
    EditText edtPhoneValue;
    @BindView(R.id.edt_code_value)
    EditText edtCodeValue;
    @BindView(R.id.bt_getcode)
    Button btGetcode;
    @BindView(R.id.bt_register)
    Button btRegister;
    @BindView(R.id.tv_toLogin)
    TextView tvToLogin;

    /** 倒计时60秒，一次1秒 */
    CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btGetcode.setText(millisUntilFinished/1000+"秒");
        }

        @Override
        public void onFinish() {
            btGetcode.setText(RegisterActivity.this.getText(R.string.txt_get_code));
            btGetcode.setEnabled(true);
        }
    };

    @Override
    public RegisterPresenter getPresenter() {
        presenter = new RegisterPresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_getcode, R.id.bt_register, R.id.tv_toLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_getcode:
                presenter.getCode(edtPhoneValue.getText().toString().trim());
                break;
            case R.id.bt_register:
                presenter.doRegister(edtPhoneValue.getText().toString().trim(), edtCodeValue.getText().toString().trim());
                break;
            case R.id.tv_toLogin:
                Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(toLogin);
                finish();
                break;
            default:
        }
    }

    @Override
    public void toMainActivity() {

    }

    @Override
    public void toSupplyUserInfoActivity() {

    }

    @Override
    public void refreshCodeTxt() {
        btGetcode.setEnabled(false);
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
