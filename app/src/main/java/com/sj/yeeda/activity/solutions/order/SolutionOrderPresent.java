package com.sj.yeeda.activity.solutions.order;

import com.jady.retrofitclient.HttpManager;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.solutions.order.bean.OrderResultBean;
import com.sj.yeeda.activity.venue.bean.VenueBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionOrderPresent implements SolutionOrderContract.Presenter {

    SolutionOrderContract.View mView;

    String userId;
    String token;


    public SolutionOrderPresent(SolutionOrderContract.View view) {
        mView = view;
        userId = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.USER_ID, "");
        token = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, "");
    }

    @Override
    public void start() {
        queryDefaultVenue();
    }

    @Override
    public void queryDefaultVenue() {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("token", token);
        parameters.put("uid", userId);
        HttpManager.get(UrlConfig.QUERY_DEFAULT_VENUE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
            }

            @Override
            public void onSuccessData(String json) {
                List<VenueBean> venueBeanList = new GsonResponsePasare<List<VenueBean>>() {
                }.deal(json);
                if (venueBeanList != null && venueBeanList.size() > 0) {
                    mView.updateVenue(venueBeanList.get(0));
                }
            }

            @Override
            public void onFailure(String error_code, String error_message) {
            }
        });
    }

    @Override
    public void saveOrder(String schemeId, String venueId, String rentId, String nums, String rentMoneys, String showTime, String area,String invoiceId) {
        mView.showProgress();
        Map<String, Object> parameters = new HashMap<>(9);
        parameters.put("token", token);
        parameters.put("schemeId", schemeId);
        parameters.put("venueId", venueId);
        parameters.put("rentId", rentId);
        parameters.put("nums", nums);
        parameters.put("rentMoneys", rentMoneys);
        parameters.put("showTime", showTime);
        parameters.put("area", area);
        parameters.put("invoiceId", invoiceId);
        HttpManager.get(UrlConfig.SAVE_ORDER_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                ToastUtils.showShortToast(json);

            }

            @Override
            public void onSuccessData(String json) {
                OrderResultBean orderResultBean = new GsonResponsePasare<OrderResultBean>() {
                }.deal(json);
                mView.toPay(orderResultBean.getId(),orderResultBean.getMoney());
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
