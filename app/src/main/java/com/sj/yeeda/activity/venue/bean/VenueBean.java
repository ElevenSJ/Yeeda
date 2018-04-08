package com.sj.yeeda.activity.venue.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.sj.yeeda.activity.invoice.bean.InvoiceBean;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */

public class VenueBean implements Parcelable {
    /**
     * uid : 922337051831838886717f129a0ee654742b5bd3f03239b193e
     * isDefault : 1
     * address : 1w
     * contact : 112222
     * name : 23
     * id : 92233705169325478336624e9fa9d964599a5e932b99c68ac2d
     * contactPhone : wq
     */

    private String uid;
    private String isDefault;
    private String address;
    private String contact;
    private String name;
    private String id;
    private String contactPhone;

    private VenueBean(){

    }
    private VenueBean(VenueBean.Builder builder){
        this.uid = builder.uid;
        this.isDefault = builder.isDefault;
        this.address = builder.address;
        this.contact = builder.contact;
        this.name = builder.name;
        this.id = builder.id;
        this.contactPhone = builder.contactPhone;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(uid);
        dest.writeString(isDefault);
        dest.writeString(address);
        dest.writeString(contact);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(contactPhone);
    }

    public VenueBean(Parcel source) {
        uid = source.readString();
        isDefault=source.readString();
        address=source.readString();
        contact=source.readString();
        name=source.readString();
        id=source.readString();
        contactPhone=source.readString();
    }
    public static final Creator<VenueBean> CREATOR = new Creator<VenueBean>() {

        @Override
        public VenueBean[] newArray(int size) {
            return new VenueBean[size];
        }

        /**
         * 从Parcel中读取数据
         */
        @Override
        public VenueBean createFromParcel(Parcel source) {
            return new VenueBean(source);
        }
    };

    public static class Builder {
        private String uid;
        private String isDefault;
        private String address;
        private String contact;
        private String name;
        private String id;
        private String contactPhone;

        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder isDefault(String isDefault) {
            this.isDefault = isDefault;
            return this;
        }
        public Builder address(String address) {
            this.address = address;
            return this;
        }
        public Builder contact(String contact) {
            this.contact = contact;
            return this;
        }
        public Builder id(String id) {
            this.id = id;
            return this;
        }
        public Builder contactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
            return this;
        }

        public VenueBean build() {
            return new VenueBean(this);
        }

    }
}
