package com.sj.yeeda.activity.order;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.order.bean.OrderBean;
import com.sj.yeeda.activity.order.bean.OrderList;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */

public class OrderPresenter implements OrderContract.Presenter {
    OrderContract.View mView;

    String token;

    public OrderPresenter(OrderContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        token = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, "");
        getOrders("",10);
    }

    private void getOrders(String firstIndex,int pageNum) {
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("token", token);
        parameters.put("firstIndex", firstIndex);
        parameters.put("pageNum", pageNum);
        HttpManager.get(UrlConfig.QUERY_ORDER_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {

            }

            @Override
            public void onSuccessData(String json) {
                OrderList orderList = new GsonResponsePasare<OrderList>(){}.deal(json);
                Logger.i("订单信息数:"+orderList.getDataList().size());
                mView.updateOrderList(orderList);
            }

            @Override
            public void onFailure(String error_code, String error_message) {
            }

        });

    }
}
