package com.sj.yeeda.activity.order.detail;

import android.util.ArrayMap;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.order.bean.OrderDetailBean;
import com.sj.yeeda.activity.order.bean.OrderList;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.base.BaseView;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;
import com.sj.yeeda.othertask.UserInfoGetTask;

import java.util.Map;

/**
 * 创建时间: on 2018/4/14.
 * 创建人: 孙杰
 * 功能描述:
 */
public class OrderDetailPresenter implements OrderDetailContract.Presenter {
    OrderDetailContract.View mView;

    String token;

    public OrderDetailPresenter(OrderDetailContract.View view) {
        mView = view;
        token = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, "");
    }

    @Override
    public void start() {

    }

    @Override
    public void getOrderDetail(String id) {
        mView.showProgress();
        Map<String, Object> parameters = new ArrayMap<>(2);
        parameters.put("token", token);
        parameters.put("id", id);
        HttpManager.get(UrlConfig.QUERY_ORDER_DETAIL_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {

            }

            @Override
            public void onSuccessData(String json) {
                final OrderDetailBean orderDetailBean = new GsonResponsePasare<OrderDetailBean>() {
                }.deal(json);
                new UserInfoGetTask() {
                    @Override
                    protected void onPostExecute(UserInfoBean userInfoBean) {
                        super.onPostExecute(userInfoBean);
                        mView.updateOrderDetailView(userInfoBean==null?"":userInfoBean.getType(), orderDetailBean);
                    }
                }.execute();
            }

            @Override
            public void onFailure(String error_code, String error_message) {
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mView.dismissProgress();
            }
        });
    }
}
