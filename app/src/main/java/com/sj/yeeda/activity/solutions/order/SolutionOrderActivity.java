package com.sj.yeeda.activity.solutions.order;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.module_lib.utils.DateUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.device.DeviceActivity;
import com.sj.yeeda.activity.invoice.InvoiceActivity;
import com.sj.yeeda.activity.invoice.bean.InvoiceBean;
import com.sj.yeeda.activity.pay.PayActivity;
import com.sj.yeeda.activity.solutions.list.bean.SolutionBean;
import com.sj.yeeda.activity.venue.VenueActivity;
import com.sj.yeeda.activity.venue.bean.VenueBean;
import com.sj.yeeda.base.TitleBaseActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

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
    TextView edtTimeEnd;
    @BindView(R.id.edt_time_begain)
    TextView edtTimeBegain;
    @BindView(R.id.edt_area)
    EditText edtArea;
    @BindView(R.id.txt_device_num)
    TextView txtDeviceNum;
    @BindView(R.id.bt_to_pay)
    Button btToPay;
    @BindView(R.id.txt_price)
    TextView txtPrice;


    SolutionBean data;

    SolutionInvoiceRyvAdapter invoiceRyvAdapter;
    SolutionVenueRyvAdapter venueRyvAdapter;


    String  rentId = "";
    String  nums = "";
    String  rentMoneys = "";

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
        data = (SolutionBean) getIntent().getSerializableExtra("data");
        txtPrice.setText("¥"+data.getSchemePrice());
        setTitleTxt("输入订单信息");
        setTitleBg();

        LinearLayoutManager invoiceLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerDecoration invoiceDividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 0, 0);
        invoiceDividerDecoration.setDrawLastItem(false);


        rylViewInvoice.setLayoutManager(invoiceLayoutManager);
        rylViewInvoice.addItemDecoration(invoiceDividerDecoration);
        invoiceRyvAdapter = new SolutionInvoiceRyvAdapter(this);
        rylViewInvoice.setAdapter(invoiceRyvAdapter);


        LinearLayoutManager venueLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerDecoration venueDividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 0, 0);
        venueDividerDecoration.setDrawLastItem(false);
        rylViewVenue.setLayoutManager(venueLayoutManager);
        rylViewVenue.addItemDecoration(venueDividerDecoration);
        venueRyvAdapter = new SolutionVenueRyvAdapter(this);
        rylViewVenue.setAdapter(venueRyvAdapter);

    }

    TimePickerView pvStartTimePicker;
    TimePickerView pvEndTimePicker;

    @OnClick({R.id.bt_to_pay,R.id.bt_choose_device,R.id.txt_choose_invoice,R.id.txt_choose_venue,R.id.edt_time_begain,R.id.edt_time_end})
    void onClickView(View view){
        int id  = view.getId();
        Intent intent = new Intent();
        switch (id){
            case R.id.bt_to_pay:
                if (venueRyvAdapter.getCount()==0){
                    ToastUtils.showShortToast("请选择场馆");
                    return;
                }
                if (TextUtils.isEmpty(edtTimeBegain.getText().toString())){
                    ToastUtils.showShortToast("请选择展览开始时间");
                    return;
                }
                if (TextUtils.isEmpty(edtTimeEnd.getText().toString())){
                    ToastUtils.showShortToast("请选择展览结束时间");
                    return;
                }
                if (TextUtils.isEmpty(edtArea.getText().toString())){
                    ToastUtils.showShortToast("请输入展台面积");
                    return;
                }
                if (TextUtils.isEmpty(rentId)){
                    ToastUtils.showShortToast("请选择租赁设备");
                    return;
                }
                if (invoiceRyvAdapter.getCount()==0){
                    ToastUtils.showShortToast("请选择开票信息");
                    return;
                }
                //支付
                presenter.saveOrder(data.getId(),venueRyvAdapter.getItem(0).getId(),rentId,nums,rentMoneys,edtTimeBegain.getText().toString()+"至"+edtTimeEnd.getText().toString(),edtArea.getText().toString(),invoiceRyvAdapter.getItem(0).getId());
                break;
            case R.id.bt_choose_device:
                //选择租赁设备
                intent.setClass(this, DeviceActivity.class);
                startActivityForResult(intent,101);
                break;
            case R.id.txt_choose_invoice:
                //选择开票信息
                intent.setClass(this, InvoiceActivity.class);
                intent.putExtra("choose",true);
                startActivityForResult(intent,102);
                break;
            case R.id.txt_choose_venue:
                //选择场馆
                intent.setClass(this, VenueActivity.class);
                intent.putExtra("choose",true);
                startActivityForResult(intent,100);
                break;
            case R.id.edt_time_begain:
                //选择开始时间
                if (pvStartTimePicker == null){
                    pvStartTimePicker = new TimePickerBuilder(SolutionOrderActivity.this, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            edtTimeBegain.setText(DateUtils.getTime(date));
                        }
                    }).build();
                }
                pvStartTimePicker.show();
                break;
            case R.id.edt_time_end:
                //选择结束时间
                if (pvEndTimePicker == null){
                    pvEndTimePicker = new TimePickerBuilder(SolutionOrderActivity.this, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            edtTimeEnd.setText(DateUtils.getTime(date));
                        }
                    }).build();
                }
                pvEndTimePicker.show();
                break;
                default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case 100:
                    //场馆信息
                    if (data.getParcelableExtra("data")!=null){
                        VenueBean venueBean =   data.getParcelableExtra("data");
                        updateVenue(venueBean);
                    }
                    break;
                case 101:
                    //设备信息
                    if (data.getStringExtra("rentId")!=null){
                        rentId = data.getStringExtra("rentId");
                        nums = data.getStringExtra("nums");
                        rentMoneys = data.getStringExtra("rentMoneys");
                        txtDeviceNum.setText("已选设备"+data.getIntExtra("allNum",0));
                    }
                    break;
                case 102:
                    //开票信息
                    if (data.getParcelableExtra("data")!=null){
                        InvoiceBean invoiceBean =   data.getParcelableExtra("data");
                        updateInVoice(invoiceBean);
                    }
                    break;
                    default:
            }
        }
    }

    @Override
    public void toPay(String orderId) {
        Intent intent = new Intent();
        intent.setClass(this, PayActivity.class);
        intent.putExtra("orderId",orderId);
        startActivity(intent);
    }

    @Override
    public void updateVenue(VenueBean venueBean) {
        if (venueRyvAdapter.getAllData().size()>0){
            venueRyvAdapter.remove(0);
        }
        venueRyvAdapter.add(venueBean);
    }
    @Override
    public void updateInVoice(InvoiceBean invoiceBean) {
        if (invoiceRyvAdapter.getAllData().size()>0){
            invoiceRyvAdapter.remove(0);
        }
        invoiceRyvAdapter.add(invoiceBean);
    }
}
