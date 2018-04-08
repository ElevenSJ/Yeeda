package com.sj.yeeda.activity.venue;

import com.sj.yeeda.activity.venue.bean.VenueBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

import java.util.List;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */

public interface VenueContract {

    interface View extends BaseView {
        void queryVenueData();
        void upDateVenueData(List<VenueBean> VenueBeanList);
    }

    interface Presenter extends BasePresenter {
        void getVenueInfo();
        void addVenueInfo(VenueBean VenueBean);
        void edtVenueInfo(VenueBean VenueBean);
        void delVenueInfo(String id);
    }
}
