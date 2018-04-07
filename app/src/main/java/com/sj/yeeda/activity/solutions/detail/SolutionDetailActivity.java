package com.sj.yeeda.activity.solutions.detail;

import com.sj.yeeda.R;
import com.sj.yeeda.base.TitleBaseActivity;

public class SolutionDetailActivity extends TitleBaseActivity implements SolutionDetailContract.View{
    @Override
    public Object getPresenter() {
        return null;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_solution_detail;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("方案详情");
        setTitleBg();
    }
}
