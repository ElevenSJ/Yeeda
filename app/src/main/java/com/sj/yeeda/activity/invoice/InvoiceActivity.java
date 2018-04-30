package com.sj.yeeda.activity.invoice;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.invoice.bean.InvoiceBean;
import com.sj.yeeda.base.TitleBaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InvoiceActivity extends TitleBaseActivity<InvoiceContract.Presenter> implements InvoiceContract.View {
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    @BindView(R.id.txt_add_bill)
    TextView txtAddBill;

    InvoiceRyvAdapter mAdapter;

    InvoiceDialog addBillDialog;

    @Override
    public void queryBillData() {
        if (addBillDialog!=null&&addBillDialog.isShowing()){
            addBillDialog.dismiss();
        }
        mAdapter.clear();
        presenter.getBillInfo();
    }

    @Override
    public void upDateBillData(List<InvoiceBean> billBeanList) {
        if (billBeanList == null||billBeanList.size()==0){
            mAdapter.clear();
        }else{
            mAdapter.addAll(billBeanList);
        }
    }

    @Override
    public InvoiceContract.Presenter getPresenter() {
        if (presenter==null){
            presenter = new InvoicePresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_invoice;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("开票信息");
        setTitleBg();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
//        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.bg_gray), 20, 16, 16);
//        dividerDecoration.setDrawLastItem(false);
//        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new InvoiceRyvAdapter(this);
        rylView.setAdapter(mAdapter);
    }

    @OnClick(R.id.txt_add_bill)
    void addBillClick(View view){
        if (addBillDialog == null){
            addBillDialog = new InvoiceDialog(this,getPresenter());
        }
        addBillDialog.show();
    }
    void delInvoice(InvoiceBean invoiceBean){
        getPresenter().delBillInfo(invoiceBean.getId());
    }
    void editInvoice(InvoiceBean invoiceBean){
        if (addBillDialog == null){
            addBillDialog = new InvoiceDialog(this,getPresenter());
        }
        addBillDialog.show(invoiceBean);
    }

    public boolean getChooseType() {
        return getIntent().getBooleanExtra("choose",false);
    }

    public void setResult(InvoiceBean invoiceBean) {
        Intent intent = new Intent();
        intent.putExtra("data",invoiceBean);
        setResult(RESULT_OK,intent);
        finish();
    }
}
