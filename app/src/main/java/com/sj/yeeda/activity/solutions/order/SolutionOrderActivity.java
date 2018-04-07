package com.sj.yeeda.activity.solutions.order;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.sj.yeeda.R;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SolutionOrderActivity extends TitleBaseActivity<SolutionOrderPresent> implements SolutionOrderContract.View {
    @BindView(R.id.rdt_invoice_et)
    RadioButton rdtInvoiceEt;
    @BindView(R.id.rdbt_invoice_paper)
    RadioButton rdbtInvoicePaper;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.ryl_view_invoice)
    EasyRecyclerView rylViewInvoice;
    @BindView(R.id.line_invoice)
    View lineInvoice;
    @BindView(R.id.txt_choose_invoice)
    TextView txtChooseInvoice;
    @BindView(R.id.ryl_view_venue)
    EasyRecyclerView rylViewVenue;
    @BindView(R.id.line_venue)
    View lineVenue;
    @BindView(R.id.txt_choose_venue)
    TextView txtChooseVenue;
    @BindView(R.id.bt_choose_device)
    Button btChooseDevice;
    @BindView(R.id.edt_time_end)
    EditText edtTimeEnd;
    @BindView(R.id.edt_time_begain)
    EditText edtTimeBegain;
    @BindView(R.id.edt_area)
    EditText edtArea;
    @BindView(R.id.txt_device_num)
    TextView txtDeviceNum;
    @BindView(R.id.bt_to_pay)
    Button btToPay;

    @Override
    public SolutionOrderPresent getPresenter() {
        presenter = new SolutionOrderPresent(this);
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_solution_order;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("输入订单信息");
        setTitleBg();
    }

}
