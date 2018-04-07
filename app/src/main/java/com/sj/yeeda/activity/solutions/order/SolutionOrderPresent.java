package com.sj.yeeda.activity.solutions.order;

public class SolutionOrderPresent implements SolutionOrderContract.Presenter {

    SolutionOrderContract.View mView ;
    public SolutionOrderPresent(SolutionOrderContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }
}
