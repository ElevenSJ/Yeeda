package com.sj.yeeda.activity.order.bean;

/**
 * 创建时间: on 2018/4/27.
 * 创建人: 孙杰
 * 功能描述:
 */
public class OrderAccessoryBean {
    String accessoryStrId;
    String nums;
    Accessory accessory;

    public String getAccessoryStrId() {
        return accessoryStrId;
    }

    public void setAccessoryStrId(String accessoryStrId) {
        this.accessoryStrId = accessoryStrId;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }

    public class Accessory{
        String name;
        String id;

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
    }
}
