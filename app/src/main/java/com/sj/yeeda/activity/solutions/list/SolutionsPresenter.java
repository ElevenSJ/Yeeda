package com.sj.yeeda.activity.solutions.list;

import android.util.ArrayMap;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.solutions.list.bean.SolutionArea;
import com.sj.yeeda.activity.solutions.list.bean.SolutionBean;
import com.sj.yeeda.activity.solutions.list.bean.SolutionList;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建时间: on 2018/4/4.
 * 创建人: 孙杰
 * 功能描述:
 */

public class SolutionsPresenter implements SolutionContract.Presenter {
    SolutionContract.View mView;

    public SolutionsPresenter(SolutionContract.View view) {
        mView = view;
    }

    String token;
    @Override
    public void start() {
        token = (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID,"");
        getSolution(token,"0",20);
        getAreas();
    }

    private void getAreas() {
        Map<String, Object> parameters = new ArrayMap<>(1);
        parameters.put("token", token);
        HttpManager.get(UrlConfig.QUERY_SOLUTION_AREAS_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
            }

            @Override
            public void onSuccessData(String json) {
                List<SolutionArea> solutionAreaList = new GsonResponsePasare< List<SolutionArea>>() {
                }.deal(json);
                mView.updateAreas(solutionAreaList);
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
    public void getSolution(String token, String firstIndex, int pageNum) {
        Map<String, Object> parameters = new ArrayMap<>(3);
        parameters.put("token", token);
        parameters.put("firstIndex", firstIndex);
        parameters.put("pageNum", pageNum);
        HttpManager.get(UrlConfig.QUERY_SOLUTIONS_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                ToastUtils.showShortToast(json);
            }

            @Override
            public void onSuccessData(String json) {
                SolutionList solutionList = new GsonResponsePasare<SolutionList>() {
                }.deal(json);
                Logger.i("solutionList size() "+solutionList.getDataList().size());
                for (SolutionBean bean:solutionList.getDataList() ){
                    Logger.i("SolutionBean = "+bean.toString());
                }
                mView.showSolutionList(solutionList);
            }

            @Override
            public void onFailure(String error_code, String error_message) {
                mView.showSolutionList(null);
            }

            @Override
            public boolean enableShowToast() {
                return false;
            }
        });

    }
}
