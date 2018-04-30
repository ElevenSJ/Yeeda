package com.sj.yeeda.othertask;

import android.content.Context;
import android.os.AsyncTask;

import com.orhanobut.logger.Logger;
import com.sj.yeeda.Utils.FileUtils;
import com.sj.yeeda.activity.service.bean.IMAccountBean;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.base.BaseApplication;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:序列化客服信息到本地
 */
public class IMAccountInfoSaveTask extends AsyncTask<IMAccountBean, Integer, Boolean> {

    @Override
    protected Boolean doInBackground(IMAccountBean... imAccountBeans) {
        //序列化到本地
        try {
            FileOutputStream outStream = BaseApplication.getApp().openFileOutput(FileUtils.FILE_NAME_IMACCOUNT,
                    Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(outStream);
            out.writeObject(imAccountBeans[0]);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            return false;
        }
        return true;
    }
}
