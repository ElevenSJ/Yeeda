package com.sj.yeeda.activity.main;

import android.util.ArrayMap;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.Utils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.othertask.IMInitTask;
import com.sj.yeeda.othertask.UserInfoGetTask;
import com.sj.yeeda.othertask.UserInfoSaveTask;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.plugin.common.AppMgr;
import com.yuntongxun.plugin.common.ClientUser;
import com.yuntongxun.plugin.common.SDKCoreHelper;

import java.util.Map;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public class MainPresenter implements MainContract.Presenter {
    MainContract.View mView;
    String token;

    public MainPresenter(MainContract.View view) {
        mView = view;
        token = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, "");
    }

    @Override
    public void start() {
        getUserInfo();
    }

    @Override
    public void getUserInfo() {
        Map<String, Object> parameters = new ArrayMap<>(1);
        parameters.put("token", token);
        HttpManager.get(UrlConfig.QUERY_USER_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {

            }

            @Override
            public void onSuccessData(String json) {
                UserInfoBean userInfoBean = new GsonResponsePasare<UserInfoBean>() {
                }.deal(json);
                SPUtils.getInstance().edit(SPFileUtils.FILE_USER).apply(new String[]{SPFileUtils.USER_ID, SPFileUtils.USER_NAME}, new String[]{userInfoBean.getId(), userInfoBean.getUserName()});
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
                Logger.i("网络获取个人信息失败");
                new UserInfoGetTask() {
                    @Override
                    protected void onPostExecute(UserInfoBean userInfoBean) {
                        Logger.i(userInfoBean == null ? "读取本地序列化用户信息失败" : "读取本地序列化用户信息成功\n" + userInfoBean.toString());
                        mView.updateUserView(userInfoBean);
                        SPUtils.getInstance().edit(SPFileUtils.FILE_USER).apply(new String[]{SPFileUtils.USER_ID, SPFileUtils.USER_NAME}, new String[]{userInfoBean.getId(), userInfoBean.getUserName()});
                    }
                }.execute();
            }

            @Override
            public boolean enableShowToast() {
                return false;
            }
        });
    }

    @Override
    public void loginOut() {
        Map<String, Object> parameters = new ArrayMap<>(1);
        parameters.put("token",token);
        HttpManager.get(UrlConfig.LOGIN_OUT_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onSuccessData(String json) {

            }

            @Override
            public void onFailure(String error_code, String error_message) {

            }

            @Override
            public boolean enableShowToast() {
                return false;
            }
        });
        mView.loginOut();
    }

    @Override
    public void initIM() {
        mView.showProgress();
        String userPhone = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.USER_ACCOUNT, "");
        if (AppMgr.getClientUser() != null) {
            Logger.i("SDK auto connect...");
            SDKCoreHelper.init(Utils.getContext());
        } else {
            ClientUser.UserBuilder builder = new ClientUser.UserBuilder(userPhone, userPhone);
            builder.setAppKey("8aaf0708624670f201626d59d5361023");
            builder.setAppToken("0609ca9d38514caa9831247ad40f5603");
            SDKCoreHelper.login(builder.build());
        }
//        new IMInitTask(){
//            @Override
//            protected void onPostExecute(Boolean aBoolean) {
//                super.onPostExecute(aBoolean);
//                if(SDKCoreHelper.getConnectState()== ECDevice.ECConnectState.CONNECT_SUCCESS){
//                    Logger.i("MainActivity... IM已连接上");
//                }
//            }
//        }.execute("IMInitTask");
    }
}
