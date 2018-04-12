package com.sj.yeeda.activity.solutions.detail;

import com.sj.yeeda.activity.solutions.detail.bean.SolutionDetailBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface SolutionDetailContract {
    interface View extends BaseView {

        void updateSolutionDetail(SolutionDetailBean solutionDetailBean);
    }

    interface Presenter extends BasePresenter {
        void getSolutionDetail(String id);

    }
}
