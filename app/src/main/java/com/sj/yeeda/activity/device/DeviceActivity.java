package com.sj.yeeda.activity.device;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class DeviceActivity extends TitleBaseActivity<DeviceContract.Presenter> implements DeviceContract.View {

    List<DeviceBean> deviceBeanList;

    List<DeviceBean> alreadyChooseDevice = new ArrayList<>();

    int allNum = 0;
    Double totalPrice = 0d;

    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    @BindView(R.id.txt_device_num)
    TextView txtDeviceNum;
    @BindView(R.id.spinner_type)
    Spinner spinnerType;

    DeviceRyvAdapter mAdapter;

    List<String> rentIdList = new ArrayList<>();
    List<String> numList = new ArrayList<>();
    List<String> rentMoneyList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();

    String rentId ="";
    String nums="";

    @Override
    public DeviceContract.Presenter getPresenter() {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rentId = getIntent().getStringExtra("rentId");
        nums = getIntent().getStringExtra("nums");
    }

    @OnClick(R.id.bt_sure)
    public void onClickView(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bt_sure:
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        for (DeviceBean deviceBean : alreadyChooseDevice) {
                            totalPrice+=Double.valueOf(deviceBean.getPrice())*deviceBean.getNum();
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
                                intent.putExtra("alltotalPriceNum", totalPrice);
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
    public void updateDeviceListView(final List<DeviceBean> deviceList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DeviceActivity.this.deviceBeanList = new ArrayList<>(deviceList.size());
                DeviceActivity.this.deviceBeanList.addAll(deviceList);
                stringList.clear();
                stringList.add("全部");
                allNum = 0;
                totalPrice = 0d;
                for (DeviceBean deviceBean : deviceBeanList) {
                    if (!TextUtils.isEmpty(rentId)){
                        String [] rentArray = rentId.split(",");
                        String [] numArray = nums.split(",");
                        for (int i = 0,length = rentArray.length;i<length;i++){
                            if (deviceBean.getId().equals(rentArray[i])){
                                deviceBean.setNum(Integer.valueOf(numArray[i]));
                                rentIdList.add(rentArray[i]);
                                numList.add(numArray[i]);
                                rentMoneyList.add(deviceBean.getPrice());
                                alreadyChooseDevice.add(deviceBean);

                                allNum+=deviceBean.getNum();
                                totalPrice+=Double.valueOf(deviceBean.getPrice())*deviceBean.getNum();
                            }
                        }
                    }
                    if (!stringList.contains(deviceBean.getCategory())) {
                        stringList.add(deviceBean.getCategory());
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgress();
                        txtDeviceNum.setText(String.valueOf(allNum));
                        ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(DeviceActivity.this, android.R.layout.simple_spinner_dropdown_item, stringList);
                        spinnerType.setAdapter(spinnerAdapter);
                    }
                });
            }
        }).start();

    }

    public void updateDevice(DeviceBean deviceBean) {
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
        allNum = 0;
        totalPrice = 0d;
        for (DeviceBean dvBean :alreadyChooseDevice){
            allNum+=dvBean.getNum();
            totalPrice+=Double.valueOf(dvBean.getPrice())*dvBean.getNum();
        }
        txtDeviceNum.setText(String.valueOf(allNum));
    }

}
