package com.sj.yeeda.activity.user.supply.bean;

import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */
@Keep
public class UserInfoBean  implements Serializable{

    private static final long serialVersionUID=111L;


    /**
     * id : hz1522502127400fChs
     * userName : 15605198042
     * sex : 1
     * companyName :
     * phone : 15605198042
     * email : 123456
     * icon :
     * type : 0（普通会员） 1（设计师）  2（加盟方）
     */

    private String id;
    private String userName;
    private String sex;
    private String companyName;
    private String phone;
    private String email;
    private String icon;
    private String type;
    private String birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
