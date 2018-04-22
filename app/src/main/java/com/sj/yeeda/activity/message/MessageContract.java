package com.sj.yeeda.activity.message;

import com.sj.yeeda.activity.message.bean.MessageBean;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.BaseView;

import java.util.List;

/**
 * 创建时间: on 2018/4/15.
 * 创建人: 孙杰
 * 功能描述:
 */
public interface MessageContract {
    interface View extends BaseView {
        void updateMessageView(List<MessageBean> messageBeanList);
    }

    interface Presenter extends BasePresenter {
        void getMessage();
    }
}
