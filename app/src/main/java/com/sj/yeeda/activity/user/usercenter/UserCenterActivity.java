package com.sj.yeeda.activity.user.usercenter;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.BuildConfig;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.activity.user.usercenter.bean.UserCenterRyvItem;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;

/**
 * 创建时间: on 2018/4/2.
 * 创建人: 孙杰
 * 功能描述:个人中心
 */

public class UserCenterActivity extends TitleBaseActivity<UserCenterPresenter> implements UserCenterContract.View {
    @BindView(R.id.ryl_view)
    EasyRecyclerView ryvView;
    @BindView(R.id.img_user_head)
    ImageView imgUserHead;
    @BindView(R.id.txt_user_name)
    TextView txtUserName;

    UserRyvAdapter mAdapter;


    @Override
    public UserCenterPresenter getPresenter() {
        presenter = new UserCenterPresenter(this);
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
                ToastUtils.showShortToast(mAdapter.getItem(position).getName());
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
        if (userInfoBean!=null){
            imgUserHead.setImageResource(R.mipmap.ic_launcher_round);
            txtUserName.setText(userInfoBean.getUserName());
        }
    }
}
