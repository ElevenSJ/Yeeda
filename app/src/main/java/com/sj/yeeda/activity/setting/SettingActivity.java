package com.sj.yeeda.activity.setting;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.sj.module_lib.glide.ImageUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.main.MainActivity;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/4/15.
 * 创建人: 孙杰
 * 功能描述:
 */
public class SettingActivity extends TitleBaseActivity<SettingContract.Presenter> implements SettingContract.View {
    @BindView(R.id.img_user_head)
    ImageView imgUserHead;

    UserInfoBean userInfoBean;
    @Override
    public SettingContract.Presenter getPresenter() {
        if (presenter == null) {
            presenter = new SettingPresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("设置");

        userInfoBean = (UserInfoBean) getIntent().getSerializableExtra("data");
        if (userInfoBean !=null){
            ImageUtils.loadImageWithError(userInfoBean.getIcon(),R.drawable.logo,imgUserHead);
        }
    }

    @OnClick(R.id.bt_loginout)
    public void loginOut(View view) {
        getPresenter().loginOut();

    }
    @Override
    public void loginOut() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("LoginOut",true);
        startActivity(intent);
        finish();
    }

}
