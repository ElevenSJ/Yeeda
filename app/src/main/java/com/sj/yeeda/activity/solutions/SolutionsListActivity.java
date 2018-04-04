package com.sj.yeeda.activity.solutions;

import com.sj.yeeda.R;
import com.sj.yeeda.base.TitleBaseActivity;

/**
 * 创建时间: on 2018/4/4.
 * 创建人: 孙杰
 * 功能描述:
 */

public class SolutionsListActivity extends TitleBaseActivity<SolutionsPresenter> implements SolutionContract.View{

    @Override
    public SolutionsPresenter getPresenter() {
        presenter = new SolutionsPresenter(this);
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_solution;
    }
}
