package com.sj.yeeda.activity.order.list;

import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.order.bean.OrderList;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;

public class OrderActivity extends TitleBaseActivity<OrderContract.Presenter> implements OrderContract.View {


    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    OrderRyvAdapter mAdapter;

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
        rylView.setAdapter(mAdapter);

    }

    @Override
    public void updateOrderList(OrderList orderList) {
        mAdapter.addAll(orderList.getDataList());
    }
}
