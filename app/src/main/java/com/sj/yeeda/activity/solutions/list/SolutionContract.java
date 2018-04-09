package com.sj.yeeda.activity.solutions.list;

import com.sj.yeeda.activity.solutions.list.bean.SolutionArea;
import com.sj.yeeda.activity.solutions.list.bean.SolutionBean;
import com.sj.yeeda.activity.solutions.list.bean.SolutionList;
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
        void showSolutionList(SolutionList solutionList);

        void toSolutionDetail(SolutionBean data);

        void toSolutionOrder(SolutionBean data);

        void updateAreas(List<SolutionArea> solutionAreaList);
    }

    interface Presenter extends BasePresenter {

        void getSolution(String token,String firstIndex,int pageNum);

    }
}
