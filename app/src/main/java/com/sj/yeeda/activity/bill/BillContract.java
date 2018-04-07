package com.sj.yeeda.activity.bill;

import com.sj.yeeda.activity.bill.bean.BillBean;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

import java.util.List;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface BillContract {
    interface View extends BaseView {
        void queryBillData();
        void upDateBillData(List<BillBean> billBeanList);
    }

    interface Presenter extends BasePresenter {
        void getBillInfo();
        void addBillInfo(BillBean billBean);
        void edtBillInfo(BillBean billBean);
        void delBillInfo(String id);
    }
}
