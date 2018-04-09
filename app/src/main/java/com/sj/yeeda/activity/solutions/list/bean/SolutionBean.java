package com.sj.yeeda.activity.solutions.list.bean;

import android.support.annotation.Keep;

import java.io.Serializable;
@Keep
public class SolutionBean implements Serializable{


    /**
     * schemePrice : 0.01
     * schemeType : 1
     * areaCategory : 36-54㎡
     * schemeIcon : https://public.app-storage-node.com/Fnom2oDYHkdoQvbic4UP-9w8NgX9?attname=icon-exchange.png
     * schemeName : 测试
     * icon : https://public.app-storage-node.com/FsZttjtmvQ6ut77zJdRXszlMWGN0?attname=2.jpg
     * updateTime : 2018-04-03 13:15:10
     * userName : 111111111
     * createTime : 2018-03-27 13:02:35
     * detailed : null
     * createId : 92233705163348884313b5df741de814575bea20bc1eb4d76a4
     * installation : <p>1</p>

     * buildStr : 1
     * id : S20180327130235
     * accessoryStr : 1
     * createName : admin
     */

    private String schemePrice;
    private String schemeType;
    private String areaCategory;
    private String schemeIcon;
    private String schemeName;
    private String icon;
    private String updateTime;
    private String userName;
    private String createTime;
    private String detailed;
    private String createId;
    private String installation;
    private String buildStr;
    private String id;
    private String accessoryStr;
    private String createName;

    public String getSchemePrice() {
        return schemePrice;
    }

    public void setSchemePrice(String schemePrice) {
        this.schemePrice = schemePrice;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public String getAreaCategory() {
        return areaCategory;
    }

    public void setAreaCategory(String areaCategory) {
        this.areaCategory = areaCategory;
    }

    public String getSchemeIcon() {
        return schemeIcon;
    }

    public void setSchemeIcon(String schemeIcon) {
        this.schemeIcon = schemeIcon;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getInstallation() {
        return installation;
    }

    public void setInstallation(String installation) {
        this.installation = installation;
    }

    public String getBuildStr() {
        return buildStr;
    }

    public void setBuildStr(String buildStr) {
        this.buildStr = buildStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessoryStr() {
        return accessoryStr;
    }

    public void setAccessoryStr(String accessoryStr) {
        this.accessoryStr = accessoryStr;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Override
    public String toString() {
        return "SolutionBean{" +
                "schemePrice='" + schemePrice + '\'' +
                ", schemeType='" + schemeType + '\'' +
                ", areaCategory='" + areaCategory + '\'' +
                ", schemeIcon='" + schemeIcon + '\'' +
                ", schemeName='" + schemeName + '\'' +
                ", icon='" + icon + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userName='" + userName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", detailed='" + detailed + '\'' +
                ", createId='" + createId + '\'' +
                ", installation='" + installation + '\'' +
                ", buildStr='" + buildStr + '\'' +
                ", id='" + id + '\'' +
                ", accessoryStr='" + accessoryStr + '\'' +
                ", createName='" + createName + '\'' +
                '}';
    }
}
