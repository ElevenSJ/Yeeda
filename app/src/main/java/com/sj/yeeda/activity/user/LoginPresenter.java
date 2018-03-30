package com.sj.yeeda.activity.user;

import com.sj.module_lib.utils.ToastUtils;

/**
 * <p>类说明</p>
 *
 * @author 张华洋 2017/2/22 20:33
 * @version V1.2.0
 * @name LoginPresenter
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    /**
     *
     * @param phoneNum
     */
    @Override
    public void getCode(String phoneNum) {
        ToastUtils.showShortToast("获取验证码");
    }

    /**
     * @param phoneNum
     * @param codeNum
     * @param deviceId
     */
    @Override
    public void doLogin(String phoneNum, String codeNum, String deviceId) {
        ToastUtils.showShortToast("登录");
    }

    @Override
    public void toRegister() {
        ToastUtils.showShortToast("注册");
    }
}
