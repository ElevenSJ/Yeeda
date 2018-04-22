package com.sj.yeeda.activity.user.updateinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.BuildConfig;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/4/8.
 * 创建人: 孙杰
 * 功能描述:
 */

public class UserInfoUpdateActivity extends TitleBaseActivity<UserInfoUpdatePresenter> implements UserInfoUpdateContract.View {
    @BindView(R.id.img_user_head)
    ImageView imgUserHead;
    @BindView(R.id.bt_modify)
    Button btModify;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_birthday)
    EditText edtBirthday;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.rdbt_male)
    RadioButton rdbtMale;
    @BindView(R.id.rdbt_female)
    RadioButton rdbtFemale;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    UserInfoBean userInfoBean;

    @Override
    public UserInfoUpdatePresenter getPresenter() {
        if (presenter == null) {
            presenter = new UserInfoUpdatePresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_user_info_update;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt(this.getString(R.string.txt_user_center_title));
        setImgTitleRight(true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BuildConfig.DEBUG) {
                    ToastUtils.showShortToast("联系客服");
                }

            }
        });
    }

    @OnClick(R.id.bt_modify)
    public void onClickView(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.bt_modify:
                userInfoBean.setBirthday(edtBirthday.getText().toString());
                userInfoBean.setEmail(edtEmail.getText().toString());
                userInfoBean.setPhone(edtPhone.getText().toString());
                userInfoBean.setUserName(edtName.getText().toString());
                userInfoBean.setSex(rdbtFemale.isChecked()?"0":"1");
                getPresenter().updateUserInfo(userInfoBean);
                break;
            default:
        }
    }
        @Override
        public void upDataUserInfoView (UserInfoBean userInfoBean){
            this.userInfoBean = userInfoBean;
            ImageUtils.loadImageWithError(userInfoBean.getIcon(), R.drawable.logo, imgUserHead);
            edtEmail.setText(userInfoBean.getEmail());
            edtPhone.setText(userInfoBean.getPhone());
            edtBirthday.setText(userInfoBean.getBirthday());
            edtName.setText(userInfoBean.getUserName());
            if (userInfoBean.getSex().equals("0")){
                rdbtFemale.setChecked(true);
            }else{
                rdbtMale.setChecked(true);
            }
        }
    }
