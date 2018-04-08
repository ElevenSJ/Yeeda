package com.sj.yeeda.activity.invoice;

import com.sj.yeeda.activity.invoice.bean.InvoiceBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

import java.util.List;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface InvoiceContract {
    interface View extends BaseView {
        void queryBillData();
        void upDateBillData(List<InvoiceBean> BillBeanList);
    }

    interface Presenter extends BasePresenter {
        void getBillInfo();
        void addBillInfo(InvoiceBean BillBean);
        void edtBillInfo(InvoiceBean BillBean);
        void delBillInfo(String id);
    }
}
