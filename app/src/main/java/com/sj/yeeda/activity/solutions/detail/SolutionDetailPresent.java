package com.sj.yeeda.activity.solutions.detail;

import android.text.TextUtils;
import android.util.ArrayMap;

import com.jady.retrofitclient.HttpManager;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.solutions.detail.bean.SolutionDetailBean;
import com.sj.yeeda.activity.solutions.list.bean.SolutionArea;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.List;
import java.util.Map;

public class SolutionDetailPresent implements SolutionDetailContract.Presenter {
    SolutionDetailContract.View mView;

    public SolutionDetailPresent(SolutionDetailContract.View view) {
        this.mView = view;
        token = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID,"");
    }

    String token;
    @Override
    public void start() {
    }

    @Override
    public void getSolutionDetail(String id) {
        if (TextUtils.isEmpty(id)){
            ToastUtils.showShortToast("方案id为空");
            return;
        }
        Map<String, Object> parameters = new ArrayMap<>(2);
        parameters.put("token", token);
        parameters.put("id", id);
        HttpManager.get(UrlConfig.QUERY_SOLUTION_DETAIL_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
            }
            @Override
            public void onSuccessData(String json) {
                SolutionDetailBean solutionDetailBean = new GsonResponsePasare<SolutionDetailBean>(){}.deal(json);
                mView.updateSolutionDetail(solutionDetailBean);

            }
            @Override
            public void onFailure(String error_code, String error_message) {

            }
        });

    }
}
