package com.sj.yeeda.activity.user.supply.bean;

import java.io.Serializable;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */

public class UserInfoBean  implements Serializable{

    private static final long serialVersionUID=111L;
    /**
     * id : 922337051831838886717f129a0ee654742b5bd3f03239b193e
     * userName : 999
     * sex : 1
     * birthday : 1989-10-12
     * phone : 15985814330
     * email : 999999@qq.com
     * icon : null
     */

    private String id;
    private String userName;
    private String sex;
    private String birthday;
    private String phone;
    private String email;
    private String icon;

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
}
