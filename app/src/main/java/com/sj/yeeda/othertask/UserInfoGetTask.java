package com.sj.yeeda.othertask;

import android.os.AsyncTask;

import com.orhanobut.logger.Logger;
import com.sj.yeeda.Utils.FileUtils;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.base.BaseApplication;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:序列化用户信息到本地
 */
public class UserInfoGetTask extends AsyncTask<Integer, Integer, UserInfoBean> {

    @Override
    protected UserInfoBean doInBackground(Integer... params) {
        //读取本地序列化
        UserInfoBean user;
        try {
            FileInputStream inputStream = BaseApplication.getApp().openFileInput(FileUtils.FILE_NAME_USERINFO);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            user = (UserInfoBean) in.readObject();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            return null;
        }
        return user;
    }
}
