package com.sj.yeeda.activity.message;

import android.util.ArrayMap;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.message.bean.MessageBean;
import com.sj.yeeda.activity.order.bean.OrderList;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.List;
import java.util.Map;

/**
 * 创建时间: on 2018/4/15.
 * 创建人: 孙杰
 * 功能描述:
 */
public class MessagePresenter implements MessageContract.Presenter{

    MessageContract.View mView;
    String token;
    public MessagePresenter(MessageContract.View view){
        mView = view;
    }
    @Override
    public void start() {
        token = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, "");
    }

    @Override
    public void getMessage() {
        Map<String, Object> parameters = new ArrayMap<>(3);
        parameters.put("token", token);
        HttpManager.get(UrlConfig.QUERY_ORDER_NEWS_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {

            }

            @Override
            public void onSuccessData(String json) {
                List<MessageBean> messageBeanList = new GsonResponsePasare<List<MessageBean>>(){}.deal(json);
                mView.updateMessageView(messageBeanList);
            }

            @Override
            public void onFailure(String error_code, String error_message) {
            }

        });
    }
}
