package com.sj.yeeda.activity.user.usercenter;

import com.orhanobut.logger.Logger;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.user.othertask.UserInfoGetTask;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.activity.user.usercenter.bean.UserCenterRyvItem;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:
 */
public class UserCenterPresenter implements UserCenterContract.Presenter {
    UserCenterContract.View mView;

    UserInfoGetTask userInfoGetTask;
    UserCenterRyvItem[] items = new UserCenterRyvItem[6];

    public UserCenterPresenter(UserCenterContract.View view) {
        mView = view;
    }


    @Override
    public void start() {
        items[0] = new UserCenterRyvItem("个人信息", R.drawable.img_personal_center, R.drawable.img_right_arrow);
        items[1] = new UserCenterRyvItem("我的订单", R.drawable.img_my_order, R.drawable.img_right_arrow);
        items[2] = new UserCenterRyvItem("我的开票信息", R.drawable.img_plan_shop, R.drawable.img_right_arrow);
        items[3] = new UserCenterRyvItem("我的场馆", R.drawable.img_my_venue, R.drawable.img_right_arrow);
        items[4] = new UserCenterRyvItem("联系客服", R.drawable.img_customer_service, R.drawable.img_right_arrow);
        items[5] = new UserCenterRyvItem("设置", R.drawable.img_exit, R.drawable.img_right_arrow);
        mView.upDataItemView( items);
        userInfoGetTask = new UserInfoGetTask() {
            @Override
            protected void onPostExecute(UserInfoBean userInfoBean) {
                Logger.i(userInfoBean == null ? "读取本地序列化用户信息失败" : "读取本地序列化用户信息成功\n" + userInfoBean.toString());
                mView.upDataHeadView(userInfoBean);
            }
        };
        userInfoGetTask.execute();
    }
}
