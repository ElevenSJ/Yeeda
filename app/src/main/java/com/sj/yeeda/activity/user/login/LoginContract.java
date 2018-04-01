package com.sj.yeeda.activity.user.login;


import com.sj.module_lib.base.BasePresenter;
import com.sj.module_lib.base.BaseView;

/**
 * <p>类说明</p>
 *
 * @author 张华洋 2017/2/22 20:33
 * @version V1.2.0
 * @name GirlsContract
 */
public interface LoginContract {

    interface View extends BaseView {
        void toMainActivity();
        void refreshCodeTxt(boolean refresh);
    }

    interface Presenter extends BasePresenter {
        void getCode(String phoneNum);
        void doLogin(String phoneNum,String codeNum,String deviceId);
    }

}
