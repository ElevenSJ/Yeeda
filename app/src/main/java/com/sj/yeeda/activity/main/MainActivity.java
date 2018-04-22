package com.sj.yeeda.activity.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.message.MessageActivity;
import com.sj.yeeda.activity.order.list.OrderActivity;
import com.sj.yeeda.activity.service.ServiceCustomActivity;
import com.sj.yeeda.activity.solutions.list.SolutionListActivity;
import com.sj.yeeda.activity.user.login.LoginActivity;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.activity.user.usercenter.UserCenterActivity;
import com.sj.yeeda.activity.venue.VenueActivity;
import com.sj.yeeda.base.BaseActivity;
import com.sj.yeeda.im.SDKCoreHelper;
import com.sj.yeeda.othertask.IMInitTask;
import com.sj.yeeda.service.JPushAliasService;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yuntongxun.ecsdk.ECDevice;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.img_home_user_center)
    ImageView imgHomeUserCenter;
    @BindView(R.id.img_home_message)
    ImageView imgHomeService;
    @BindView(R.id.bt_solutions)
    TextView btSolutions;
    @BindView(R.id.bt_design)
    TextView btDesign;
    @BindView(R.id.bt_order)
    TextView btOrder;
    @BindView(R.id.bt_service)
    TextView btService;
    @BindView(R.id.bt_user_center)
    TextView btUserCenter;
    ImageView imgUserIcon;
    TextView txtUserName;

    UserInfoBean userBean;

    @Override
    public void initView() {
        navView.setItemIconTintList(null);
        navView.setNavigationItemSelectedListener(this);

        imgUserIcon = navView.getHeaderView(0).findViewById(R.id.img_user_icon);
        txtUserName = navView.getHeaderView(0).findViewById(R.id.txt_user_name);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null && getIntent().getBooleanExtra("LoginOut", false)) {
            getPresenter().loginOut();
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndPermission.with(this)
                    .permission( Permission.WRITE_EXTERNAL_STORAGE,
                            Permission.READ_PHONE_STATE,Permission.ACCESS_COARSE_LOCATION)
                    .onGranted(new Action() {
                        @Override
                        public void onAction(List<String> permissions) {
                            getPresenter().initIM();
//                            startService(new Intent(MainActivity.this, IMInitService.class));
                        }
                    }).onDenied(new Action() {
                        @Override
                        public void onAction(List<String> permissions) {
//                            ToastUtils.showShortToast("未授权权限，可能导致某些功能无法使用");
                        }
            }).start();
        } else {
//            startService(new Intent(this, IMInitService.class));
            getPresenter().initIM();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //互斥登录，退出当前设备
        if (intent != null && intent.getBooleanExtra("LoginOut", false)) {
            getPresenter().loginOut();
        }
    }

    @Override
    public MainContract.Presenter getPresenter() {
        if (presenter == null) {
            presenter = new MainPresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @OnClick({R.id.img_home_user_center, R.id.img_home_message, R.id.bt_solutions, R.id.bt_design, R.id.bt_order, R.id.bt_service, R.id.bt_user_center})
    public void onViewClicked(View view) {
        int id = view.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.img_home_user_center:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.img_home_message:
                intent.setClass(MainActivity.this, MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_solutions:
                intent.setClass(MainActivity.this, SolutionListActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_design:
                break;
            case R.id.bt_order:
                intent.setClass(MainActivity.this, OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_service:
                intent.setClass(MainActivity.this, ServiceCustomActivity.class);
                intent.putExtra("user",userBean);
                startActivity(intent);
                break;
            case R.id.bt_user_center:
                intent.setClass(MainActivity.this, UserCenterActivity.class);
                startActivity(intent);
                break;
            default:
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.nav_plan_shop:
                intent.setClass(MainActivity.this, SolutionListActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_my_order:
                intent.setClass(MainActivity.this, OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_personal_center:
                intent.setClass(MainActivity.this, UserCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_my_venue:
                intent.setClass(MainActivity.this, VenueActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_customer_service:
                intent.setClass(MainActivity.this, ServiceCustomActivity.class);
                intent.putExtra("user",userBean);
                startActivity(intent);
                break;
            case R.id.nav_exit:
                getPresenter().loginOut();
                break;
            default:
        }
        return true;
    }

    @Override
    public void loginOut() {
        SDKCoreHelper.logout(false);
        SPUtils.getInstance().clear();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void updateUserView(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return;
        }
        this.userBean = userInfoBean;
        ImageUtils.loadImageWithError(userInfoBean.getIcon(), R.drawable.logo, imgUserIcon);
        txtUserName.setText(userInfoBean.getUserName());
        if (!(Boolean) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.IS_SET_JPUSH_ALIAS, false)) {
            Intent intent = new Intent();
            intent.putExtra("alias", userInfoBean.getUserName());
            intent.setClass(MainActivity.this, JPushAliasService.class);
            startService(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    long exitTime = 0;

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.showShortToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

}
