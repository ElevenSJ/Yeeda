package com.sj.yeeda.activity.user.updateinfo;


import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.user.othertask.UserInfoGetTask;
import com.sj.yeeda.activity.user.othertask.UserInfoSaveTask;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */

public class UserInfoUpdatePresenter implements  UserInfoUpdateContract.Presenter{
    UserInfoUpdateContract.View mView;

    UserInfoGetTask userInfoGetTask;

    public UserInfoUpdatePresenter(UserInfoUpdateContract.View view){
        mView = view;
    }
    @Override
    public void start() {
        userInfoGetTask = new UserInfoGetTask() {
            @Override
            protected void onPostExecute(UserInfoBean userInfoBean) {
                Logger.i(userInfoBean == null ? "读取本地序列化用户信息失败" : "读取本地序列化用户信息成功\n" + userInfoBean.toString());
                mView.upDataUserInfoView(userInfoBean);
            }
        };
        userInfoGetTask.execute();
    }

    @Override
    public void updateUserInfo(final UserInfoBean userInfoBean) {

        mView.showProgress();
        Map<String, Object> parameters = new HashMap<>(7);
        parameters.put("token", SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""));
        parameters.put("id", userInfoBean.getId());
        parameters.put("userName", userInfoBean.getUserName());
        parameters.put("sex", userInfoBean.getSex());
        parameters.put("phone", userInfoBean.getPhone());
        parameters.put("email", userInfoBean.getEmail());
        parameters.put("birthday", userInfoBean.getBirthday());

        HttpManager.get(UrlConfig.UPDATE_USER_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                new UserInfoSaveTask() {
                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);
                        Logger.i(aBoolean ? "用户信息本地序列化成功" : "用户信息本地序列化失败");
                    }
                }.execute(userInfoBean);
            }

            @Override
            public void onSuccessData(String json) {

            }

            @Override
            public void onFailure(String error_code, String error_message) {

            }

            @Override
            public void onFinish() {
                mView.dismissProgress();
            }
        });

    }
}
