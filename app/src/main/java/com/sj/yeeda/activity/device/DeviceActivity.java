package com.sj.yeeda.activity.device;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.yeeda.R;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceActivity extends TitleBaseActivity<DevicePresenter> implements DeviceContract.View {
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    DeviceRyvAdapter mAdapter;
    @BindView(R.id.txt_device_num)
    TextView txtDeviceNum;
    @BindView(R.id.bt_sure)
    Button btSure;
    @BindView(R.id.spinner_type)
    Spinner spinnerType;

    @Override
    public DevicePresenter getPresenter() {
        if (presenter == null) {
            presenter = new DevicePresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_device;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("租赁设备");
        setTitleBg();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 16, 16);
        dividerDecoration.setDrawLastItem(false);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new DeviceRyvAdapter(this);
        rylView.setAdapter(mAdapter);
    }

}
