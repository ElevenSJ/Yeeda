package com.sj.yeeda.activity.device.bean;

import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */
@Keep
public class DeviceBean implements Serializable{


    /**
     * thumbnail : https://public.app-storage-node.com/Fhk4SLfbibPNperGlFwWyJCrGe6Q?attname=11.jpg
     * price : 0.01
     * name : 123123
     * rentCompanyId : 请问恶趣味2
     * id : 922337051661744925882262c2300314f7d98548d7b01bd1f26
     * category : 我企鹅我
     * introduction : <p>213213213</p>

     * picture : https://public.app-storage-node.com/FupIfczEXdHrRoCKlHYpuxoyyt-G?attname=7.jpg,https://public.app-storage-node.com/Fh4x2Ssezdt16lUVGR3k0Yz7QL0W?attname=3.jpg
     */

    private String thumbnail;
    private String price;
    private String name;
    private String rentCompanyId;
    private String id;
    private String category;
    private String introduction;
    private String picture;
    private int num = 0;

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
