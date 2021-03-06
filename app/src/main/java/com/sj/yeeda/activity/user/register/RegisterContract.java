package com.sj.yeeda.activity.user.register;


import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * <p>类说明</p>
 *
 * @author 张华洋 2017/2/22 20:33
 * @version V1.2.0
 * @name GirlsContract
 */
public interface RegisterContract {

    interface View extends BaseView {
        void toSupplyUserInfoActivity();
        void refreshCodeTxt(boolean refresh);
    }

    interface Presenter extends BasePresenter {
        void getCode(String phoneNum);
        void doRegister(String phoneNum, String codeNum);
    }

}
