package com.sj.yeeda.activity.main;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.user.othertask.UserInfoSaveTask;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public class MainPresenter implements MainContract.Presenter {
    MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        getUserInfo();
    }

    @Override
    public void getUserInfo() {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("token", SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""));
        HttpManager.get(UrlConfig.QUERY_USER_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                UserInfoBean userInfoBean = new GsonResponsePasare<UserInfoBean>() {
                }.deal(json);
                SPUtils.getInstance().edit(SPFileUtils.FILE_USER).apply(SPFileUtils.USER_ID, userInfoBean.getId());
                new UserInfoSaveTask() {
                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);
                        Logger.i(aBoolean ? "用户信息本地序列化成功" : "用户信息本地序列化失败");
                    }
                }.execute(userInfoBean);
                mView.updateUserView(userInfoBean);
            }

            @Override
            public void onFailure(String error_code, String error_message) {
            }

            @Override
            public boolean enableShowToast() {
                return false;
            }
        });
    }

    @Override
    public void loginOut() {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("token", SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""));
        HttpManager.get(UrlConfig.LOGIN_OUT_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String s) {

            }
            @Override
            public void onFailure(String error_code, String error_message) {

            }
            @Override
            public boolean enableShowToast() {
                return false;
            }
        });
        SPUtils.getInstance().edit(SPFileUtils.FILE_USER).apply(new String[]{SPFileUtils.TOKEN_ID, SPFileUtils.IS_LOGIN,SPFileUtils.USER_ID}, new Object[]{"", false,""});
        mView.loginOut();
    }
}
