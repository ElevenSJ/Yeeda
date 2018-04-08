package com.sj.yeeda.activity.venue;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.invoice.InvoiceContract;
import com.sj.yeeda.activity.invoice.InvoiceDialog;
import com.sj.yeeda.activity.invoice.InvoicePresenter;
import com.sj.yeeda.activity.invoice.InvoiceRyvAdapter;
import com.sj.yeeda.activity.invoice.bean.InvoiceBean;
import com.sj.yeeda.activity.venue.bean.VenueBean;
import com.sj.yeeda.base.TitleBaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VenueActivity extends TitleBaseActivity<VenuePresenter> implements VenueContract.View {
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    @BindView(R.id.txt_add_bill)
    TextView txtAddVenue;

    VenueRyvAdapter mAdapter;

    VenueDialog addVenueDialog;

    @Override
    public void queryVenueData() {
        if (addVenueDialog!=null&&addVenueDialog.isShowing()){
            addVenueDialog.dismiss();
        }
        mAdapter.clear();
        presenter.getVenueInfo();
    }

    @Override
    public void upDateVenueData(List<VenueBean> venueBeanList) {
        if (venueBeanList == null||venueBeanList.size()==0){
            mAdapter.clear();
        }else{
            mAdapter.addAll(venueBeanList);
        }
    }

    @Override
    public VenuePresenter getPresenter() {
        if (presenter==null){
            presenter = new VenuePresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_venue;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("我的场馆");
        setTitleBg();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 16, 16);
        dividerDecoration.setDrawLastItem(false);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new VenueRyvAdapter(this);
        rylView.setAdapter(mAdapter);
    }

    @OnClick(R.id.txt_add_bill)
    void addVenueClick(View view){
        if (addVenueDialog == null){
            addVenueDialog = new VenueDialog(this,getPresenter());
        }
        addVenueDialog.show();
    }
    public boolean getChooseType() {
        return getIntent().getBooleanExtra("choose",false);
    }

    public void setResult(VenueBean venueBean) {
        Intent intent = new Intent();
        intent.putExtra("data",venueBean);
        setResult(RESULT_OK,intent);
        finish();
    }

    void delVenue(VenueBean venueBean){
        getPresenter().delVenueInfo(venueBean.getId());
    }
    void editVenue(VenueBean venueBean){
        if (addVenueDialog == null){
            addVenueDialog = new VenueDialog(this,getPresenter());
        }
        addVenueDialog.show(venueBean);
    }

}
