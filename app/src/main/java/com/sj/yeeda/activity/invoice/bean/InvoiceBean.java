package com.sj.yeeda.activity.invoice.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class InvoiceBean implements Parcelable{

    /**
     * uid : 922337051831838886717f129a0ee654742b5bd3f03239b193e
     * bank : 123
     * isVatInvoice : 0
     * phone : 123
     * tariff : 123
     * id : 9223370516598591217945ec3f84d3f4d5abe42eaf27a57a576
     * title : 123
     * workAddress : 213
     * account : 123
     * email : 213
     * expressAddress : 213
     */

    private String uid;
    private String bank;
    private String isVatInvoice;
    private String phone;
    private String tariff;
    private String id;
    private String title;
    private String workAddress;
    private String account;
    private String email;
    private String expressAddress;

    private String isDefault;

    private InvoiceBean(){

    }
    private InvoiceBean(Builder builder){
        this.uid = builder.uid;
        this.bank = builder.bank;
        this.isVatInvoice = builder.isVatInvoice;
        this.phone = builder.phone;
        this.tariff = builder.tariff;
        this.id = builder.id;
        this.title = builder.title;
        this.workAddress = builder.workAddress;
        this.account = builder.account;
        this.email = builder.email;
        this.expressAddress = builder.expressAddress;
        this.isDefault = builder.isDefault;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getIsVatInvoice() {
        return isVatInvoice;
    }

    public void setIsVatInvoice(String isVatInvoice) {
        this.isVatInvoice = isVatInvoice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 这里默认返回0即可
     */
    @Override
    public int describeContents() {
        return 0;
    }
    /**
     * 把值写入Parcel中
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(bank);
        dest.writeString(isVatInvoice);
        dest.writeString(phone);
        dest.writeString(tariff);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(workAddress);
        dest.writeString(account);
        dest.writeString(email);
        dest.writeString(expressAddress);
        dest.writeString(isDefault);

    }
    public InvoiceBean(Parcel source) {
        uid = source.readString();
        bank=source.readString();
        isVatInvoice=source.readString();
        phone=source.readString();
        tariff=source.readString();
        id=source.readString();
        title=source.readString();
        workAddress=source.readString();
        account=source.readString();
        email=source.readString();
        expressAddress=source.readString();
        isDefault=source.readString();
    }
    public static final Creator<InvoiceBean> CREATOR = new Creator<InvoiceBean>() {

        @Override
        public InvoiceBean[] newArray(int size) {
            return new InvoiceBean[size];
        }

        /**
         * 从Parcel中读取数据
         */
        @Override
        public InvoiceBean createFromParcel(Parcel source) {
            return new InvoiceBean(source);
        }
    };

    public static class Builder {
        private String uid;
        private String bank;
        private String isVatInvoice;
        private String phone;
        private String tariff;
        private String id;
        private String title;
        private String workAddress;
        private String account;
        private String email;
        private String expressAddress;
        private String isDefault = "0";

        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder bank(String bank) {
            this.bank = bank;
            return this;
        }

        public Builder isVatInvoice(String isVatInvoice) {
            this.isVatInvoice = isVatInvoice;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder tariff(String tariff) {
            this.tariff = tariff;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder workAddress(String workAddress) {
            this.workAddress = workAddress;
            return this;
        }

        public Builder account(String account) {
            this.account = account;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder expressAddress(String expressAddress) {
            this.expressAddress = expressAddress;
            return this;
        }

        public Builder isDefault(String isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public InvoiceBean build() {
            return new InvoiceBean(this);
        }


    }
}
