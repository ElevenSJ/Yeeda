package com.sj.yeeda.activity.solutions;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.solutions.bean.SolutionBean;
import com.sj.yeeda.base.TitleBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间: on 2018/4/4.
 * 创建人: 孙杰
 * 功能描述:
 */

public class SolutionsListActivity extends TitleBaseActivity<SolutionsPresenter> implements SolutionContract.View ,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    List<String> areaDates;
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;


    SolutionListAdapter mAdapter;


    @Override
    public SolutionsPresenter getPresenter() {
        if (presenter == null) {
            presenter = new SolutionsPresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_solution;
    }

    @Override
    public void initView() {
        setTitleTxt("方案商城");
        setTitleBg();
        getAreaData();


        rylView.setRefreshingColor(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, android.R.color.holo_blue_light),
                ContextCompat.getColor(this, android.R.color.holo_green_light)
        );
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_e0), 20, 20, 0);
        dividerDecoration.setDrawLastItem(true);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new SolutionListAdapter(this);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        rylView.setAdapterWithProgress(mAdapter);
        rylView.setRefreshListener(this);

    }

    private List<String> getAreaData() {
        areaDates = new ArrayList<>();
        areaDates.add("6-12㎡");
        areaDates.add("12-36㎡");
        areaDates.add("36-54㎡");
        areaDates.add("54-72㎡");
        areaDates.add("72-90㎡");
        return areaDates;
    }

    @Override
    public void showSolutionList(List<SolutionBean> solutionBeans) {
        if (solutionBeans != null) {
            mAdapter.clear();
            mAdapter.addAll(solutionBeans);
        }
    }

    @Override
    public void onRefresh() {
        presenter.getSolution((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""),"0",10);
    }
}
