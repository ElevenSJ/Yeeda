package com.sj.yeeda.activity.bill;

import com.jady.retrofitclient.HttpManager;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.bill.bean.BillBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillPresenter  implements BillContract.Presenter {

    BillContract.View mView;
    String userId;
    String token;
    public BillPresenter(BillContract.View view){
        mView = view;
    }
    @Override
    public void getBillInfo() {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("token",token);
        parameters.put("id", userId);
        HttpManager.get(UrlConfig.QUERY_INVOICE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                    List<BillBean> billBeanList = new GsonResponsePasare<List<BillBean>>() {
                    }.deal(json);
                    mView.upDateBillData(billBeanList);
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
    public void addBillInfo(BillBean billBean) {
        Map<String, Object> parameters = new HashMap<>(11);
        parameters.put("token", token);
        parameters.put("uid", userId);
        parameters.put("isVatInvoice", billBean.getIsVatInvoice());
        parameters.put("title", billBean.getTitle());
        parameters.put("tariff", billBean.getTariff());
        parameters.put("workAddress", billBean.getWorkAddress());
        parameters.put("phone", billBean.getPhone());
        parameters.put("bank", billBean.getBank());
        parameters.put("account", billBean.getAccount());
        parameters.put("email", billBean.getEmail());
        parameters.put("expressAddress", billBean.getExpressAddress());

        HttpManager.postByBody(UrlConfig.ADD_INVOICE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                mView.queryBillData();
            }
            @Override
            public void onFailure(String error_code, String error_message) {
            }

        });

    }

    @Override
    public void edtBillInfo(BillBean billBean) {
        Map<String, Object> parameters = new HashMap<>(12);
        parameters.put("id", billBean.getId());
        parameters.put("token", token);
        parameters.put("uid", billBean.getUid());
        parameters.put("isVatInvoice", billBean.getIsVatInvoice());
        parameters.put("title", billBean.getTitle());
        parameters.put("tariff", billBean.getTariff());
        parameters.put("workAddress", billBean.getWorkAddress());
        parameters.put("phone", billBean.getPhone());
        parameters.put("bank", billBean.getBank());
        parameters.put("account", billBean.getAccount());
        parameters.put("email", billBean.getEmail());
        parameters.put("expressAddress", billBean.getExpressAddress());

        HttpManager.postByBody(UrlConfig.EDT_INVOICE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                mView.queryBillData();
            }
            @Override
            public void onFailure(String error_code, String error_message) {
            }
        });
    }

    @Override
    public void delBillInfo(String id) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("token", token);
        parameters.put("id", id);
        HttpManager.get(UrlConfig.DEL_INVOICE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                mView.queryBillData();
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
        getBillInfo();
    }
}
