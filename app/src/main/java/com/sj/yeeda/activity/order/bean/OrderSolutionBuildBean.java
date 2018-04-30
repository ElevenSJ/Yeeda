package com.sj.yeeda.activity.order.bean;

/**
 * 创建时间: on 2018/4/27.
 * 创建人: 孙杰
 * 功能描述:
 */
public class OrderSolutionBuildBean {
    String buildId;
    String nums;
    String moneys;
    Build build;

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getMoneys() {
        return moneys;
    }

    public void setMoneys(String moneys) {
        this.moneys = moneys;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public class Build {
        /**
         * buildName : 12
         * buildParam : 1
         * createTime : 2018-04-15 14:31:40
         * createId : 92233705163348884313b5df741de814575bea20bc1eb4d76a4
         * buildImgs : https://public.app-storage-node.com/FvMlbWwsK_ecQalaUEShRmNe9Sle?attname=02.jpg
         * scale : 1
         * buildPrice : 1
         * id : 92233705130808757915ac7641e558e436eb9c030889e80f9a0
         * type : 1
         * buildInventory : 1
         * status : 0
         */

        private String buildName;
        private String buildParam;
        private String createTime;
        private String createId;
        private String buildImgs;
        private String scale;
        private String buildPrice;
        private String id;
        private String type;
        private String buildInventory;
        private String status;

        public String getBuildName() {
            return buildName;
        }

        public void setBuildName(String buildName) {
            this.buildName = buildName;
        }

        public String getBuildParam() {
            return buildParam;
        }

        public void setBuildParam(String buildParam) {
            this.buildParam = buildParam;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateId() {
            return createId;
        }

        public void setCreateId(String createId) {
            this.createId = createId;
        }

        public String getBuildImgs() {
            return buildImgs;
        }

        public void setBuildImgs(String buildImgs) {
            this.buildImgs = buildImgs;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getBuildPrice() {
            return buildPrice;
        }

        public void setBuildPrice(String buildPrice) {
            this.buildPrice = buildPrice;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBuildInventory() {
            return buildInventory;
        }

        public void setBuildInventory(String buildInventory) {
            this.buildInventory = buildInventory;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
