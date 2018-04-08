package com.sj.yeeda.activity.device.bean;

import java.io.Serializable;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */

public class DeviceBean implements Serializable{

    /**
     * thumbnail : 123123123
     * price : 21313
     * name : 123
     * rentCompanyId : 23
     * id : 92233705168484328137cc40545aaae4a6a9973d0b8df073f0c
     * category : 123qwe
     * introduction : 3123123
     * picture :  https://public.app-storage-node.com/FkddI3oBwyvsLpQF65Q3fO6ZBsvz?attname=icon-phone-s.png
     */

    private String thumbnail;
    private String price;
    private String name;
    private String rentCompanyId;
    private String id;
    private String category;
    private String introduction;
    private String picture;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRentCompanyId() {
        return rentCompanyId;
    }

    public void setRentCompanyId(String rentCompanyId) {
        this.rentCompanyId = rentCompanyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
