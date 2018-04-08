package com.sj.yeeda.activity.venue;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.venue.bean.VenueBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VenuePresenter implements VenueContract.Presenter {

    VenueContract.View mView;
    String userId;
    String token;
    public VenuePresenter(VenueContract.View view){
        mView = view;
    }
    @Override
    public void getVenueInfo() {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("token",token);
        parameters.put("id", userId);
        HttpManager.get(UrlConfig.QUERY_VENUE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                    List<VenueBean> venueBeanList = new GsonResponsePasare<List<VenueBean>>() {
                    }.deal(json);
                    mView.upDateVenueData(venueBeanList);
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
    public void addVenueInfo(VenueBean venueBean) {
        Logger.i("addVenueInfo:token="+token+",uid="+userId);
        Map<String, Object> parameters = new HashMap<>(7);
        parameters.put("token", token);
        parameters.put("uid", userId);
        parameters.put("name", venueBean.getName());
        parameters.put("address", venueBean.getAddress());
        parameters.put("contact", venueBean.getContact());
        parameters.put("contactPhone", venueBean.getContactPhone());
        parameters.put("isDefault", venueBean.getIsDefault());

        HttpManager.get(UrlConfig.ADD_VENUE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                mView.queryVenueData();
            }
            @Override
            public void onFailure(String error_code, String error_message) {
            }

        });

    }

    @Override
    public void edtVenueInfo(VenueBean venueBean) {
        Map<String, Object> parameters = new HashMap<>(12);
        parameters.put("id", venueBean.getId());
        parameters.put("token", token);
        parameters.put("uid", venueBean.getUid());
        parameters.put("name", venueBean.getName());
        parameters.put("address", venueBean.getAddress());
        parameters.put("contact", venueBean.getContact());
        parameters.put("contactPhone", venueBean.getContactPhone());
        parameters.put("isDefault", venueBean.getIsDefault());

        HttpManager.get(UrlConfig.EDT_VENUE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                mView.queryVenueData();
            }
            @Override
            public void onFailure(String error_code, String error_message) {
            }
        });
    }

    @Override
    public void delVenueInfo(String id) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("token", token);
        parameters.put("id", id);
        HttpManager.get(UrlConfig.DEL_VENUE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                mView.queryVenueData();
            }

            @Override
            public void onFailure(String error_code, String error_message) {
            }
        });
    }

    @Override
    public void start() {
        userId = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.USER_ID, "");
        token = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, "");
        getVenueInfo();
    }
}
