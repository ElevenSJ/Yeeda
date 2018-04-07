package com.sj.yeeda.activity.bill;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.bill.bean.BillBean;
import com.sj.yeeda.base.TitleBaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BillActivity extends TitleBaseActivity<BillPresenter> implements BillContract.View {
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    @BindView(R.id.txt_add_bill)
    TextView txtAddBill;

    BillRyvAdapter mAdapter;

    BillDialog addBillDialog;

    @Override
    public void queryBillData() {
        if (addBillDialog!=null&&addBillDialog.isShowing()){
            addBillDialog.dismiss();
        }
        mAdapter.clear();
        presenter.getBillInfo();
    }

    @Override
    public void upDateBillData(List<BillBean> billBeanList) {
        if (billBeanList == null||billBeanList.size()==0){
            mAdapter.clear();
        }else{
            mAdapter.addAll(billBeanList);
        }
    }

    @Override
    public BillPresenter getPresenter() {
        presenter = new BillPresenter(this);
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_bill;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("开票信息");
        setTitleBg();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 16, 16);
        dividerDecoration.setDrawLastItem(false);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new BillRyvAdapter(this,presenter);
        rylView.setAdapter(mAdapter);
    }

    @OnClick(R.id.txt_add_bill)
    void addBillClick(View view){
        if (addBillDialog == null){
            addBillDialog = new BillDialog(this,getPresenter());
        }
        addBillDialog.show();
    }
}
