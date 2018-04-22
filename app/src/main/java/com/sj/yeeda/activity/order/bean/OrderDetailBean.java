package com.sj.yeeda.activity.order.bean;

import android.support.annotation.Keep;

import com.sj.yeeda.activity.device.bean.DeviceBean;
import com.sj.yeeda.activity.invoice.bean.InvoiceBean;
import com.sj.yeeda.activity.solutions.list.bean.SolutionBean;
import com.sj.yeeda.activity.venue.bean.VenueBean;

import java.util.List;

/**
 * 创建时间: on 2018/4/14.
 * 创建人: 孙杰
 * 功能描述:
 */
@Keep
public class OrderDetailBean {
    VenueBean venue;
    DesignerBean Designer;
    SolutionBean scheme;
    Constructor Constructor;
    InvoiceBean invoice;
    List<RentBean> rent;
    Order order;

    public VenueBean getVenue() {
        return venue;
    }

    public void setVenue(VenueBean venue) {
        this.venue = venue;
    }

    public DesignerBean getDesigner() {
        return Designer;
    }

    public void setDesigner(DesignerBean designer) {
        Designer = designer;
    }

    public SolutionBean getScheme() {
        return scheme;
    }

    public void setScheme(SolutionBean scheme) {
        this.scheme = scheme;
    }

    public com.sj.yeeda.activity.order.bean.Constructor getConstructor() {
        return Constructor;
    }

    public void setConstructor(com.sj.yeeda.activity.order.bean.Constructor constructor) {
        Constructor = constructor;
    }

    public InvoiceBean getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceBean invoice) {
        this.invoice = invoice;
    }

    public List<RentBean> getRent() {
        return rent;
    }

    public void setRent(List<RentBean> rent) {
        this.rent = rent;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Keep
    public class Order {
        String area;
        String money;
        String createTime;
        String showTime;
        String venueId;
        String constructorId;
        String invoiceId;
        String schemeId;
        String id;
        String memberId;
        String status;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getShowTime() {
            return showTime;
        }

        public void setShowTime(String showTime) {
            this.showTime = showTime;
        }

        public String getVenueId() {
            return venueId;
        }

        public void setVenueId(String venueId) {
            this.venueId = venueId;
        }

        public String getConstructorId() {
            return constructorId;
        }

        public void setConstructorId(String constructorId) {
            this.constructorId = constructorId;
        }

        public String getInvoiceId() {
            return invoiceId;
        }

        public void setInvoiceId(String invoiceId) {
            this.invoiceId = invoiceId;
        }

        public String getSchemeId() {
            return schemeId;
        }

        public void setSchemeId(String schemeId) {
            this.schemeId = schemeId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
