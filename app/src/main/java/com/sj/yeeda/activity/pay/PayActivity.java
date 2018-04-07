package com.sj.yeeda.activity.pay;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.bill.BillActivity;
import com.sj.yeeda.activity.pay.bean.PayBean;
import com.sj.yeeda.activity.user.usercenter.UserCenterActivity;
import com.sj.yeeda.activity.user.usercenter.UserRyvAdapter;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;

public class PayActivity extends TitleBaseActivity<PayPresent> implements PayContract.View{
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;

    PayRyvAdapter mAdapter;

    @Override
    public PayPresent getPresenter() {
        presenter = new PayPresent(this);
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_pay_type;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("支付");
        setTitleBg();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 16, 16);
        dividerDecoration.setDrawLastItem(false);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new PayRyvAdapter(this);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtils.showShortToast(mAdapter.getItem(position).getName());
                int type = mAdapter.getItem(position).getType();
                Intent intent = new Intent();
                switch (type){
                    case 0:
                        //微信支付
                        break;
                    case 1:
                        //支付宝支付
                        break;
                    default:
                }
                startActivity(intent);
            }
        });
        rylView.setAdapter(mAdapter);
    }

    @Override
    public void upDataItemView(PayBean[] items) {
        mAdapter.addAll(items);
    }
}
