package com.sj.yeeda.activity.invoice;

import android.util.ArrayMap;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.invoice.bean.InvoiceBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.List;
import java.util.Map;

public class InvoicePresenter implements InvoiceContract.Presenter {

    InvoiceContract.View mView;
    String userId;
    String token;
    public InvoicePresenter(InvoiceContract.View view){
        mView = view;
        userId = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.USER_ID, "");
        token = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, "");
    }
    @Override
    public void getBillInfo() {
        Map<String, Object> parameters = new ArrayMap<>(2);
        parameters.put("token",token);
        parameters.put("id", userId);
        HttpManager.get(UrlConfig.QUERY_INVOICE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {

            }

            @Override
            public void onSuccessData(String json) {
                List<InvoiceBean> billBeanList = new GsonResponsePasare<List<InvoiceBean>>() {
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
    public void addBillInfo(InvoiceBean billBean) {
        Logger.i("addBillInfo:token="+token+",uid="+userId);
        Map<String, Object> parameters = new ArrayMap<>(15);
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
        parameters.put("isDefault", billBean.getIsDefault());
        parameters.put("companyName", billBean.getCompanyName());
        parameters.put("contact", billBean.getContact());
        parameters.put("contactPhone", billBean.getContactPhone());
        Logger.i("parameters:size="+parameters.size());

        HttpManager.get(UrlConfig.ADD_INVOICE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                mView.queryBillData();
            }

            @Override
            public void onSuccessData(String json) {

            }

            @Override
            public void onFailure(String error_code, String error_message) {
            }

        });

    }

    @Override
    public void edtBillInfo(InvoiceBean billBean) {
        Map<String, Object> parameters = new ArrayMap<>(15);
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
        parameters.put("companyName", billBean.getCompanyName());
        parameters.put("contact", billBean.getContact());
        parameters.put("contactPhone", billBean.getContactPhone());

        HttpManager.get(UrlConfig.EDT_INVOICE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                mView.queryBillData();
            }

            @Override
            public void onSuccessData(String json) {

            }

            @Override
            public void onFailure(String error_code, String error_message) {
            }
        });
    }

    @Override
    public void delBillInfo(String id) {
        Map<String, Object> parameters = new ArrayMap<>(2);
        parameters.put("token", token);
        parameters.put("id", id);
        HttpManager.get(UrlConfig.DEL_INVOICE_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                mView.queryBillData();
            }

            @Override
            public void onSuccessData(String json) {

            }

            @Override
            public void onFailure(String error_code, String error_message) {
            }
        });
    }

    @Override
    public void start() {
        getBillInfo();
    }
}
