package com.sj.yeeda.activity.device;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.orhanobut.logger.Logger;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.device.bean.DeviceBean;
import com.sj.yeeda.base.TitleBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceActivity extends TitleBaseActivity<DevicePresenter> implements DeviceContract.View {

    List<DeviceBean> deviceBeanList;

    List<DeviceBean> alreadyChooseDevice = new ArrayList<>();

    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    @BindView(R.id.txt_device_num)
    TextView txtDeviceNum;
    @BindView(R.id.spinner_type)
    Spinner spinnerType;

    DeviceRyvAdapter mAdapter;

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

        txtDeviceNum.setText(String.valueOf(alreadyChooseDevice.size()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 16, 16);
        dividerDecoration.setDrawLastItem(false);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new DeviceRyvAdapter(this);
        rylView.setAdapter(mAdapter);


        /**选项选择监听*/
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateDeviceListView((String) parent.getAdapter().getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick(R.id.bt_sure)
    public void onClickView(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bt_sure:
                new Thread(new Runnable() {
                    List<String> rentIdList = new ArrayList<>();
                    List<String> numList = new ArrayList<>();
                    List<String> rentMoneyList = new ArrayList<>();

                    int allNum = 0;

                    @Override
                    public void run() {
                        for (DeviceBean deviceBean : alreadyChooseDevice) {
                            allNum += deviceBean.getNum();
                            rentIdList.add(deviceBean.getId());
                            numList.add(deviceBean.getNum() + "");
                            rentMoneyList.add(deviceBean.getPrice());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Logger.i("rentId:" + TextUtils.join(",", rentIdList.toArray()) + "\n" + "nums" + TextUtils.join(",", numList.toArray()) + "\n" + "rentMoneys" + TextUtils.join(",", rentMoneyList.toArray()));
                                Intent intent = new Intent();
                                intent.putExtra("allNum", allNum);
                                intent.putExtra("rentId", TextUtils.join(",", rentIdList.toArray()));
                                intent.putExtra("nums", TextUtils.join(",", numList.toArray()));
                                intent.putExtra("rentMoneys", TextUtils.join(",", rentMoneyList.toArray()));
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        });
                    }
                }).start();
                break;
            default:
        }

    }

    public void updateDeviceListView(String type) {
        if (mAdapter.getCount() > 0) {
            mAdapter.removeAll();
        }
        if (type.equals("全部")) {
            mAdapter.addAll(deviceBeanList);
        } else {
            List<DeviceBean> tmpDeviceBeans = new ArrayList<>();
            for (DeviceBean deviceBean : deviceBeanList) {
                if (deviceBean.getCategory().equals(type)) {
                    tmpDeviceBeans.add(deviceBean);
                }
            }
            mAdapter.addAll(tmpDeviceBeans);
        }
    }

    @Override
    public void updateDeviceListView(List<DeviceBean> deviceBeanList) {
        this.deviceBeanList = new ArrayList<>(deviceBeanList.size());
        this.deviceBeanList.addAll(deviceBeanList);
        updateDeviceTypes(deviceBeanList);
    }

    private void updateDeviceTypes(List<DeviceBean> deviceBeanList) {
        List<String> stringList = new ArrayList<>();
        stringList.add("全部");
        for (DeviceBean deviceBean : deviceBeanList) {
            if (!stringList.contains(deviceBean.getCategory())) {
                stringList.add(deviceBean.getCategory());
            }
        }
        ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringList);
        spinnerType.setAdapter(spinnerAdapter);

    }

    public void updatePrice(DeviceBean deviceBean) {
        if (deviceBean.getNum() == 0) {
            if (alreadyChooseDevice.contains(deviceBean)) {
                alreadyChooseDevice.remove(deviceBean);
            }
        } else {
            if (alreadyChooseDevice.contains(deviceBean)) {
                alreadyChooseDevice.remove(deviceBean);
            }
            alreadyChooseDevice.add(deviceBean);
        }
        txtDeviceNum.setText(String.valueOf(alreadyChooseDevice.size()));
    }

}
