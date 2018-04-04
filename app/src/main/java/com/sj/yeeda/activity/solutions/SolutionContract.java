package com.sj.yeeda.activity.solutions;

import com.sj.yeeda.activity.solutions.bean.SolutionBean;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

import java.util.List;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface SolutionContract {
    interface View extends BaseView {
        void showSolutionList(List<SolutionBean> solutionBeans);
    }

    interface Presenter extends BasePresenter {

        void getSolution(String token,String firstIndex,int pageNum);

    }
}
