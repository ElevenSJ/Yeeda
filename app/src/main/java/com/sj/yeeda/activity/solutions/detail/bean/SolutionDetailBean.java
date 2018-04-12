package com.sj.yeeda.activity.solutions.detail.bean;

import android.support.annotation.Keep;

import com.sj.yeeda.activity.solutions.list.bean.SolutionBean;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;

import java.util.List;

/**
 * 创建时间: on 2018/4/12.
 * 创建人: 孙杰
 * 功能描述:
 */

@Keep
public class SolutionDetailBean {
    List<ImageBena> imgs;
    SolutionBean scheme;
    UserInfoBean user;

    public List<ImageBena> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImageBena> imgs) {
        this.imgs = imgs;
    }

    public SolutionBean getScheme() {
        return scheme;
    }

    public void setScheme(SolutionBean scheme) {
        this.scheme = scheme;
    }

    public UserInfoBean getUser() {
        return user;
    }

    public void setUser(UserInfoBean user) {
        this.user = user;
    }

    @Keep
    private class ImageBena {
        /**
         * createtime : 2018-04-11 20:12:13
         * imgPath : https://public.app-storage-node.com/Fn97dfMsyanmS25S0qL_TDP77aot?attname=6.png
         * id : 9223370513406042369d7eae568082b4e2db18ab1dcaa8b5ddc
         * foreignKeyId : 100000002420180411201213
         */

        private String createtime;
        private String imgPath;
        private String id;
        private String foreignKeyId;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getForeignKeyId() {
            return foreignKeyId;
        }

        public void setForeignKeyId(String foreignKeyId) {
            this.foreignKeyId = foreignKeyId;
        }
    }
}
