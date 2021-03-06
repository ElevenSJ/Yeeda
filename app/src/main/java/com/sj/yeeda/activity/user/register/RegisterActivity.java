package com.sj.yeeda.activity.user.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.user.supply.SupplyUserInfoActivity;
import com.sj.yeeda.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/3/29.
 * 创建人: 孙杰
 * 功能描述:注册页
 */
public class RegisterActivity extends BaseActivity<RegisterContract.Presenter> implements RegisterContract.View {
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
    @BindView(R.id.rdbt_agree)
    CheckBox rdbtAgree;
    @BindView(R.id.txt_register_html)
    TextView txtRegisterHtml;
    /**
     * 倒计时60秒，一次1秒
     */
    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btGetcode.setText(millisUntilFinished / 1000 + "秒");
            btGetcode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            btGetcode.setText(RegisterActivity.this.getText(R.string.txt_get_code));
            btGetcode.setEnabled(true);
        }
    };



    @Override
    public RegisterContract.Presenter getPresenter() {
        if (presenter == null){
            presenter = new RegisterPresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.bt_getcode, R.id.bt_register, R.id.tv_toLogin,R.id.txt_register_html})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_getcode:
                getPresenter().getCode(edtPhoneValue.getText().toString().trim());
                break;
            case R.id.bt_register:
                if (rdbtAgree.isChecked()) {
                    getPresenter().doRegister(edtPhoneValue.getText().toString().trim(), edtCodeValue.getText().toString().trim());
                } else {
                    ToastUtils.showShortToast("请查看艺搭环保软件注册协议，并同意");
                }
                break;
            case R.id.tv_toLogin:
                finish();
                break;
            case R.id.txt_register_html:
                Intent intent = new Intent(this,RegisterHtmlActivity.class);
                intent.putExtra("url","https://prj-huizhan.app-service-node.com/appRegistrationAgreement");
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    @Override
    public void toSupplyUserInfoActivity() {
        Intent supplyUserInfo = new Intent(RegisterActivity.this, SupplyUserInfoActivity.class);
        supplyUserInfo.putExtra("phoneNum", edtPhoneValue.getText().toString().trim());
        startActivity(supplyUserInfo);
    }

    @Override
    public void refreshCodeTxt(boolean refresh) {
        if (refresh) {
            timer.start();
        } else {
            timer.onFinish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

}
