package com.sj.yeeda.activity.order.bean;

/**
 * 创建时间: on 2018/4/14.
 * 创建人: 孙杰
 * 功能描述: 租赁方+设备
 */
public class OrderRentBean {
    private String rentEquipmentId;
    private String rentEquipmentName;
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

    public String getRentEquipmentName() {
        return rentEquipmentName;
    }

    public void setRentEquipmentName(String rentEquipmentName) {
        this.rentEquipmentName = rentEquipmentName;
    }
}