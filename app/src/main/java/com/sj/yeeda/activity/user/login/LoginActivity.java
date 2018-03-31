package com.sj.yeeda.activity.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sj.module_lib.utils.DeviceUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.user.register.RegisterActivity;
import com.sj.yeeda.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/3/29.
 * 创建人: 孙杰
 * 功能描述:登录页
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{
    @BindView(R.id.edt_phone_value)
    EditText edtPhoneValue;
    @BindView(R.id.edt_code_value)
    EditText edtCodeValue;
    @BindView(R.id.bt_getcode)
    Button btGetcode;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_register_detail)
    TextView tvRegisterDetail;

    @Override
    public LoginPresenter getPresenter() {
        presenter = new LoginPresenter(this);
        return presenter ;
    }

    /** 倒计时60秒，一次1秒 */
    CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btGetcode.setText(millisUntilFinished/1000+"秒");
        }

        @Override
        public void onFinish() {
            btGetcode.setText(LoginActivity.this.getText(R.string.txt_get_code));
            btGetcode.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_getcode,R.id.bt_login,R.id.tv_register_detail})
    public void onClick(View view){
            switch (view.getId()){
                case R.id.bt_getcode:
                    presenter.getCode(edtPhoneValue.getText().toString().trim());
                    break;
                case R.id.bt_login:
                    presenter.doLogin(edtPhoneValue.getText().toString().trim(),edtCodeValue.getText().toString().trim(), DeviceUtils.getUniqueId(this));
                    break;
                case R.id.tv_register_detail:
                    Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivityForResult(register,921);
                    break;
                    default:
            }
    }

    @Override
    public void toMainActivity() {

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
