package com.sj.yeeda.activity.user.usercenter;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import com.sj.yeeda.activity.user.usercenter.bean.UserCenterRyvItem;
import com.sj.yeeda.base.BasePresenter;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;

/**
 * 创建时间: on 2018/4/2.
 * 创建人: 孙杰
 * 功能描述:个人中心
 */

public class UserCenterActivity extends TitleBaseActivity {
    @BindView(R.id.ryl_view)
    EasyRecyclerView ryvView;
    @BindView(R.id.img_user_head)
    ImageView imgUserHead;
    @BindView(R.id.txt_user_name)
    TextView txtUserName;

    UserRyvAdapter mAdapter;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_user_center;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {
        UserCenterRyvItem[] items = new UserCenterRyvItem[6];
        items[0] = new UserCenterRyvItem("个人信息",R.drawable.img_personal_center);
        items[1] = new UserCenterRyvItem("我的订单",R.drawable.img_personal_center);
        items[2] = new UserCenterRyvItem("我的开票信息",R.drawable.img_personal_center);
        items[3] = new UserCenterRyvItem("我的场馆",R.drawable.img_personal_center);
        items[4] = new UserCenterRyvItem("联系客服",R.drawable.img_personal_center);
        items[5] = new UserCenterRyvItem("设置",R.drawable.img_personal_center);

        mAdapter.addAll(items);

    }

    private void initView() {
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
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 30, 30);
        dividerDecoration.setDrawLastItem(true);
        ryvView.addItemDecoration(dividerDecoration);
        mAdapter = new UserRyvAdapter(this);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        ryvView.setAdapter(mAdapter);
    }

}
