package com.sj.yeeda.activity.solutions.list;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.solutions.detail.SolutionDetailActivity;
import com.sj.yeeda.activity.solutions.list.bean.SolutionBean;
import com.sj.yeeda.activity.solutions.list.bean.SolutionList;
import com.sj.yeeda.activity.solutions.order.SolutionOrderActivity;
import com.sj.yeeda.base.TitleBaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * 创建时间: on 2018/4/4.
 * 创建人: 孙杰
 * 功能描述:
 */

public class SolutionListActivity extends TitleBaseActivity<SolutionsPresenter> implements SolutionContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnMoreListener, RecyclerArrayAdapter.OnNoMoreListener {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;

    SolutionListAdapter mAdapter;


    //所有的面积数据
    List<String> areaDatas = new ArrayList<>();
    //所有筛选中选中的面积数据
    Set<Integer> areaChooseIndex = new HashSet<>();
    //所有方案数据
    List<SolutionBean> allSolutions = new ArrayList<>();

    boolean isFistGetData = true;

    //默认选中tab
    int itemSelected = 0;


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
        super.initView();
        setTitleTxt("方案商城");
        setTitleBg();

        rylView.setRefreshingColor(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, android.R.color.holo_blue_light),
                ContextCompat.getColor(this, android.R.color.holo_red_light)
        );
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_e0), 1, 0, 0);
        dividerDecoration.setDrawLastItem(false);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new SolutionListAdapter(this,this);
//        mAdapter.setMore(R.layout.layout_load_more, this);
//        mAdapter.setNoMore(R.layout.layout_load_no_more);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        rylView.setAdapterWithProgress(mAdapter);
        rylView.setRefreshListener(this);
        areaDatas.add(0, "全部");
    }

    private void getAreaData(SolutionList solutionList) {
        areaDatas.remove(0);
        for (SolutionBean solutionBean : solutionList.getDataList()) {
            if (!areaDatas.contains(solutionBean.getAreaCategory())) {
                areaDatas.add(solutionBean.getAreaCategory());
            }
        }
        //排序
        Collections.sort(areaDatas, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                Logger.i("s1:"+o1.substring(0, o1.indexOf("-"))+"s2:"+o2.substring(0, o2.indexOf("-")));
                return Integer.valueOf(o1.substring(0, o1.indexOf("-")))-Integer.valueOf(o2.substring(0, o2.indexOf("-")));
            }
        });
        areaDatas.add(0, "全部");
        addTabLayout();
    }

    public void addTabLayout() {
        tabLayout.removeAllTabs();
        /**动态添加值**/
        for (int i = 0; i < areaDatas.size(); i++) {
            tabLayout.addTab(tabLayout.newTab());
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.tab_item_layout);
                View view = tab.getCustomView();
                TextView textView = (TextView) view.findViewById(R.id.tv_txt);
                textView.setText(areaDatas.get(i));
            }
        }
        /**默认选择第一项itemSelected = 0 **/
        TabLayout.Tab tab = tabLayout.getTabAt(itemSelected);
        tab.select();

        /**计算滑动的偏移量**/
        final int width = (int) (getOffsetWidth(itemSelected) * getResources().getDisplayMetrics().density);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.scrollTo(width, 0);
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                itemSelected = tab.getPosition();
                mAdapter.clear();
                checkTabSelect();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    /**
     * 根据字符个数计算偏移量
     */
    private int getOffsetWidth(int index) {
        String str = "";
        for (int i = 0; i < index; i++) {
            str += areaDatas.get(i);
        }
        return str.length() * 14 + index * 12;
    }

    void checkTabSelect() {
        if (itemSelected == 0) {
            mAdapter.addAll(allSolutions);
        } else {
            //筛选临时数据
            List<SolutionBean> tmpSolutions = new ArrayList<>();
            for (SolutionBean bean : allSolutions) {
                if (bean.getAreaCategory().equals(areaDatas.get(itemSelected))) {
                    tmpSolutions.add(bean);
                }
            }
            mAdapter.addAll(tmpSolutions);
        }
    }

    @Override
    public void showSolutionList(SolutionList solutionList) {
        rylView.setRefreshing(false);
        if (solutionList != null && solutionList.getDataList() != null) {
            if (isFistGetData) {
                allSolutions.clear();
                mAdapter.clear();
            }
            getAreaData(solutionList);
            allSolutions.addAll(solutionList.getDataList());
            checkTabSelect();
        } else {
            mAdapter.clear();
        }
        isFistGetData = false;
    }

    @Override
    public void toSolutionDetail() {
        Intent intent = new Intent(this, SolutionDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void toSolutionOrder() {
        Intent intent = new Intent(this, SolutionOrderActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        isFistGetData = true;
        presenter.getSolution((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""), "0", 10);

    }

    @Override
    public void onMoreShow() {

    }

    @Override
    public void onMoreClick() {
        isFistGetData = false;
        presenter.getSolution((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""), allSolutions.size() > 0 ? allSolutions.get(allSolutions.size() - 1).getId() : "0", 10);
    }

    @Override
    public void onNoMoreShow() {

    }

    @Override
    public void onNoMoreClick() {
        isFistGetData = false;
        presenter.getSolution((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.TOKEN_ID, ""), allSolutions.size() > 0 ? allSolutions.get(allSolutions.size() - 1).getId() : "0", 10);
    }
}
