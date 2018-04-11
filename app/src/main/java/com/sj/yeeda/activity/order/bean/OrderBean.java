package com.sj.yeeda.activity.order.bean;

import android.support.annotation.Keep;

import com.sj.yeeda.activity.solutions.list.bean.SolutionBean;
import com.sj.yeeda.activity.venue.bean.VenueBean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */
@Keep
public class OrderBean implements Serializable{

    String area;
    String icon;
    VenueBean venueId;
    SolutionBean schemeId;
    String userName;
    String showTime;
    String constructorId;
    List<RentBean> rent;
    String money;
    String createTime;
    String id ;
    InvoiceBean invoiceId;
    String memberId;
    String status;
    String  phone;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public VenueBean getVenue() {
        return venueId;
    }

    public void setVenueId(VenueBean venueId) {
        this.venueId = venueId;
    }

    public SolutionBean getScheme() {
        return schemeId;
    }

    public void setSchemeId(SolutionBean schemeId) {
        this.schemeId = schemeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(String constructorId) {
        this.constructorId = constructorId;
    }

    public List<RentBean> getRent() {
        return rent;
    }

    public void setRent(List<RentBean> rent) {
        this.rent = rent;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InvoiceBean getInvoice() {
        return invoiceId;
    }

    public void setInvoiceId(InvoiceBean invoiceId) {
        this.invoiceId = invoiceId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private class RentBean {

        private String rentEquipmentId;
        private String money;
        private String orderId;
        private String num;
        private String id;

        public String getRentEquipmentId() {
            return rentEquipmentId;
        }

        public void setRentEquipmentId(String rentEquipmentId) {
            this.rentEquipmentId = rentEquipmentId;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    private class InvoiceBean {

        /**
         * uid : hz1522506079740hedu
         * isDefault : 1
         * address : 1
         * contact : 1
         * name : 1
         * id : 9223370514311474637a5e1b834a1a8489c842ca43480994fa7
         * contactPhone : 1
         */

        private String uid;
        private String isDefault;
        private String address;
        private String contact;
        private String name;
        private String id;
        private String contactPhone;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }
    }
}
