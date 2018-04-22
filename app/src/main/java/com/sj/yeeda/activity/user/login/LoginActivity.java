package com.sj.yeeda.activity.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.DeviceUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.main.MainActivity;
import com.sj.yeeda.activity.user.register.RegisterActivity;
import com.sj.yeeda.base.BaseActivity;
import com.sj.yeeda.service.IMInitService;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/3/29.
 * 创建人: 孙杰
 * 功能描述:登录页
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
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
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndPermission.with(this)
                .permission(Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE,
                        Permission.READ_PHONE_STATE,
                        Permission.ACCESS_COARSE_LOCATION,
                        Permission.CAMERA,
                        Permission.RECORD_AUDIO)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        // TODO what to do.
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                // TODO what to do
            }
        }).start();
    }

    /**
     * 倒计时60秒，一次1秒
     */
    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btGetcode.setEnabled(false);
            btGetcode.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            btGetcode.setEnabled(true);
            btGetcode.setText(LoginActivity.this.getText(R.string.txt_get_code));
        }
    };

    @OnClick({R.id.bt_getcode, R.id.bt_login, R.id.tv_register_detail})
    public void onClick(View view) {
        Logger.i("onClick(View view):" + view.getId());
        switch (view.getId()) {
            case R.id.bt_getcode:
                presenter.getCode(edtPhoneValue.getText().toString().trim());
                break;
            case R.id.bt_login:
                presenter.doLogin(edtPhoneValue.getText().toString().trim(), edtCodeValue.getText().toString().trim(), DeviceUtils.getUniqueId(this));
                break;
            case R.id.tv_register_detail:
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
                break;
            default:
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && intent.getBooleanExtra("isLogined", false)) {
            toMainActivity();
        }
    }

    @Override
    public void toMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
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
