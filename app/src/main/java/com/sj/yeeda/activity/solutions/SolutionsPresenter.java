package com.sj.yeeda.activity.solutions;

import com.jady.retrofitclient.HttpManager;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.solutions.bean.SolutionBean;
import com.sj.yeeda.activity.solutions.bean.SolutionList;
import com.sj.yeeda.activity.user.othertask.UserInfoSaveTask;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.http.Callback;
import com.sj.yeeda.http.GsonResponsePasare;
import com.sj.yeeda.http.UrlConfig;

import java.util.HashMap;
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

    @Override
    public void start() {
        getSolution((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""),"0",10);
    }

    @Override
    public void getSolution(String token, String firstIndex, int pageNum) {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("token", token);
        parameters.put("firstIndex", firstIndex);
        parameters.put("pageNum", pageNum);
        HttpManager.get(UrlConfig.QUERY_SOLUTIONS_URL, parameters, new Callback() {
            @Override
            public void onSuccess(String json) {
                SolutionList solutionList = new GsonResponsePasare<SolutionList>() {
                }.deal(json);
                Logger.i("solutionList size() "+solutionList.getDataList().size());
                for (SolutionBean bean:solutionList.getDataList() ){
                    Logger.i("SolutionBean = "+bean.toString());
                }
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
}
