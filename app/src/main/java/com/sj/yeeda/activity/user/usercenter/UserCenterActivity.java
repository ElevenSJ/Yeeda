package com.sj.yeeda.activity.user.usercenter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.BuildConfig;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.invoice.InvoiceActivity;
import com.sj.yeeda.activity.order.list.OrderActivity;
import com.sj.yeeda.activity.setting.SettingActivity;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.activity.user.updateinfo.UserInfoUpdateActivity;
import com.sj.yeeda.activity.user.usercenter.bean.UserCenterRyvItem;
import com.sj.yeeda.activity.venue.VenueActivity;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;

/**
 * 创建时间: on 2018/4/2.
 * 创建人: 孙杰
 * 功能描述:个人中心
 */

public class UserCenterActivity extends TitleBaseActivity<UserCenterContract.Presenter> implements UserCenterContract.View {
    @BindView(R.id.ryl_view)
    EasyRecyclerView ryvView;
    @BindView(R.id.img_user_head)
    ImageView imgUserHead;
    @BindView(R.id.txt_user_name)
    TextView txtUserName;

    UserRyvAdapter mAdapter;

    UserInfoBean userInfoBean;
    @Override
    public UserCenterContract.Presenter getPresenter() {
        if (presenter==null){
            presenter = new UserCenterPresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_user_center;
    }

    @Override
    public void initView() {
        setTitleTxt(this.getString(R.string.txt_user_center_title));
        setImgTitleRight(true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BuildConfig.DEBUG){
                    ToastUtils.showShortToast("联系客服");
                }

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ryvView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 16, 16);
        dividerDecoration.setDrawLastItem(false);
        ryvView.addItemDecoration(dividerDecoration);
        mAdapter = new UserRyvAdapter(this);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
                        //个人信息
                        intent.setClass(UserCenterActivity.this, UserInfoUpdateActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //我的订单
                        intent.setClass(UserCenterActivity.this, OrderActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        //我的开票信息
                        intent.setClass(UserCenterActivity.this, InvoiceActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        //我的场馆
                        intent.setClass(UserCenterActivity.this, VenueActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        //联系客服
                        break;
                    case 5:
                        //设置
                        intent.setClass(UserCenterActivity.this, SettingActivity.class);
                        intent.putExtra("data",userInfoBean);
                        startActivity(intent);
                        break;
                        default:
                }

            }
        });
        ryvView.setAdapter(mAdapter);
    }

    @Override
    public void upDataItemView(UserCenterRyvItem[] items) {
            mAdapter.addAll(items);
    }

    @Override
    public void upDataHeadView(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
        if (userInfoBean!=null){
            ImageUtils.loadImageWithError(userInfoBean.getIcon(),R.drawable.logo,imgUserHead);
            txtUserName.setText(userInfoBean.getUserName());
        }
    }
}
