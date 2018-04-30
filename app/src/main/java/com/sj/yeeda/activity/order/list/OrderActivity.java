package com.sj.yeeda.activity.order.list;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.order.bean.OrderList;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;

public class OrderActivity extends TitleBaseActivity<OrderContract.Presenter> implements OrderContract.View , SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnMoreListener{


    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    OrderRyvAdapter mAdapter;

    boolean isRefresh = true;

    @Override
    public OrderContract.Presenter getPresenter() {
        if (presenter == null) {
            presenter = new OrderPresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("我的订单");
        setTitleBg();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 16, 16);
        dividerDecoration.setDrawLastItem(false);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new OrderRyvAdapter(this);
        mAdapter.setMore(R.layout.layout_load_more, this);
        mAdapter.setNoMore(R.layout.layout_load_no_more);
        rylView.setAdapter(mAdapter);

    }

    @Override
    public void updateOrderList(OrderList orderList) {
        if (orderList!=null){
            if (isRefresh){
                mAdapter.clear();
                mAdapter.addAll(orderList.getDataList());
            }else{
                if (orderList.getDataList().isEmpty()){
                    mAdapter.stopMore();
                }else{
                    mAdapter.addAll(orderList.getDataList());
                }
            }
            isRefresh = false;
        }else{
            mAdapter.pauseMore();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 101:
                    onRefresh();
                    break;
                default:
            }
        }
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        getPresenter().getOrders("",10);
    }

    @Override
    public void onMoreShow() {

    }

    @Override
    public void onMoreClick() {
        getPresenter().getOrders(mAdapter.getCount()>0?mAdapter.getItem(mAdapter.getCount()-1).getId():"",10);
    }
}
