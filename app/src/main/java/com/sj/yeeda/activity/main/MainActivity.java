package com.sj.yeeda.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.BuildConfig;
import com.sj.yeeda.R;
import com.sj.yeeda.base.BaseActivity;
import com.sj.yeeda.activity.user.usercenter.UserCenterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.img_home_user_center)
    ImageView imgHomeUserCenter;
    @BindView(R.id.img_home_service)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        navView.setItemIconTintList(null);
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public MainPresenter getPresenter() {
        presenter = new MainPresenter(this);
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @OnClick({R.id.img_home_user_center, R.id.img_home_service, R.id.bt_solutions, R.id.bt_design, R.id.bt_order, R.id.bt_service, R.id.bt_user_center})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.img_home_user_center:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.img_home_service:
                break;
            case R.id.bt_solutions:
                break;
            case R.id.bt_design:
                break;
            case R.id.bt_order:
                break;
            case R.id.bt_service:
                break;
            case R.id.bt_user_center:
                intent.setClass(MainActivity.this, UserCenterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (BuildConfig.DEBUG){
            ToastUtils.showShortToast(item.getTitle().toString());
        }
        switch (id) {
            case R.id.nav_plan_shop:
                break;
            case R.id.nav_my_order:
                break;
            case R.id.nav_personal_center:
                break;
            case R.id.nav_my_venue:
                break;
            case R.id.nav_customer_service:
                break;
            case R.id.nav_exit:
                presenter.loginOut();
                break;
            default:
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void loginOut() {
        finish();
    }
}
