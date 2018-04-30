package com.sj.yeeda.activity.order.bean;

import android.support.annotation.Keep;

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
    OrderDesignerBean Designer;
    OrderSolutionBean scheme;
    List<OrderSolutionBuildBean> build;
    Constructor Constructor;
    OrderInvoiceBean invoice;
    List<OrderRentBean> rent;
    List<OrderAccessoryBean> accessory;
    Order order;

    public VenueBean getVenue() {
        return venue;
    }

    public void setVenue(VenueBean venue) {
        this.venue = venue;
    }

    public OrderDesignerBean getDesigner() {
        return Designer;
    }

    public void setDesigner(OrderDesignerBean designer) {
        Designer = designer;
    }

    public OrderSolutionBean getScheme() {
        return scheme;
    }

    public void setScheme(OrderSolutionBean scheme) {
        this.scheme = scheme;
    }

    public Constructor getConstructor() {
        return Constructor;
    }

    public void setConstructor(Constructor constructor) {
        Constructor = constructor;
    }

    public OrderInvoiceBean getInvoice() {
        return invoice;
    }

    public void setInvoice(OrderInvoiceBean invoice) {
        this.invoice = invoice;
    }

    public List<OrderRentBean> getRent() {
        return rent;
    }

    public void setRent(List<OrderRentBean> rent) {
        this.rent = rent;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderSolutionBuildBean> getBuild() {
        return build;
    }

    public void setBuild(List<OrderSolutionBuildBean> build) {
        this.build = build;
    }

    public List<OrderAccessoryBean> getAccessory() {
        return accessory;
    }

    public void setAccessory(List<OrderAccessoryBean> accessory) {
        this.accessory = accessory;
    }

    @Keep
    public class Order {
        String area;
        String money;
        String createTime;
        String showTime;
        String venueId;
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
