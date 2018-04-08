package com.sj.yeeda.activity.solutions.order;

import com.sj.yeeda.activity.invoice.bean.InvoiceBean;
import com.sj.yeeda.activity.venue.bean.VenueBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface SolutionOrderContract {
    interface View extends BaseView {
        void updateVenue(VenueBean venueBean);
        void updateInVoice(InvoiceBean invoiceBean);

    }

    interface Presenter extends BasePresenter {
        void queryDefaultVenue();

    }
}
