package com.sj.yeeda.activity.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
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
import com.sj.yeeda.im.IMManagerImpl;
import com.sj.yeeda.service.JPushAliasService;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yuntongxun.ecsdk.SdkErrorCode;
import com.yuntongxun.plugin.common.AppMgr;
import com.yuntongxun.plugin.common.ClientUser;
import com.yuntongxun.plugin.common.SDKCoreHelper;
import com.yuntongxun.plugin.common.common.utils.RongXInUtils;
import com.yuntongxun.plugin.greendao3.helper.DaoHelper;
import com.yuntongxun.plugin.im.dao.helper.IMDao;
import com.yuntongxun.plugin.im.manager.IMPluginManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    ImageView imgUserIcon;
    TextView txtUserName;

    UserInfoBean userBean;


    private BroadcastReceiver mSDKNotifyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (RongXInUtils.ACTION_KICK_OFF.equals(intent.getAction())) {
                //代码示例
                ToastUtils.showShortToast("您的账号被他人登陆，请确定您的账号安全");
                getPresenter().loginOut();
            } else if (SDKCoreHelper.ACTION_SDK_CONNECT.equals(intent.getAction())) {
                if (SDKCoreHelper.isLoginSuccess(intent)) {
                    Logger.e("登入成功");
                    SPUtils.getInstance().edit(SPFileUtils.FILE_USER).apply(SPFileUtils.IM_IS_CONNECTED, 0);
                    // 初始化IM数据库
                    DaoHelper.init(MainActivity.this, new IMDao());
                } else {
                    int error = intent.getIntExtra("error", 0);
                    if (error == SdkErrorCode.CONNECTING) {
                        return;
                    }
                    SPUtils.getInstance().edit(SPFileUtils.FILE_USER).apply(SPFileUtils.IM_IS_CONNECTED,-1);
                    Logger.e("登入失败[" + error + "]");
                }
            }
        }
    };

    @Override
    public void initView() {
        navView.setItemIconTintList(null);
        navView.setNavigationItemSelectedListener(this);

        imgUserIcon = navView.getHeaderView(0).findViewById(R.id.img_user_icon);
        txtUserName = navView.getHeaderView(0).findViewById(R.id.txt_user_name);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SDKCoreHelper.ACTION_SDK_CONNECT);// SDK状态广播
        intentFilter.addAction(RongXInUtils.ACTION_KICK_OFF);// 账号异地登入广播
        registerReceiver(mSDKNotifyReceiver, intentFilter);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndPermission.with(this)
                    .permission(Permission.WRITE_EXTERNAL_STORAGE,
                            Permission.READ_PHONE_STATE, Permission.ACCESS_COARSE_LOCATION)
                    .onGranted(new Action() {
                        @Override
                        public void onAction(List<String> permissions) {
                            getPresenter().initIM();
                        }
                    }).onDenied(new Action() {
                @Override
                public void onAction(List<String> permissions) {
//                            ToastUtils.showShortToast("未授权权限，可能导致某些功能无法使用");
                }
            }).start();
        } else {
            getPresenter().initIM();
        }
        if (getIntent().getBooleanExtra("LoginOut", false)) {
            getPresenter().loginOut();
        } else {
            String contactId = getIntent().getStringExtra("contactId");
            if (!TextUtils.isEmpty(contactId)) {
                /**
                 * 获取到contactID后调用该方法，方法为自动判断是单聊还是群聊，然后打开页面
                 */
                IMManagerImpl.startChatting(this,contactId);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //互斥登录，退出当前设备
        if (intent != null) {
            if (intent.getBooleanExtra("LoginOut", false)) {
                getPresenter().loginOut();
            }
            String contactId = getIntent().getStringExtra("contactId");
            if (!TextUtils.isEmpty(contactId)) {
                /**
                 * 获取到contactID后调用该方法，方法为自动判断是单聊还是群聊，然后打开页面
                 */
                IMManagerImpl.startChatting(this,contactId);
            }
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
    protected void onDestroy() {
        super.onDestroy();
        // 注销广播
        try{
            unregisterReceiver(mSDKNotifyReceiver);
        }catch ( Exception e){
            e.printStackTrace();
        }

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
                if (userBean != null && userBean.getType().equals("2")) {
                    ToastUtils.showShortToast("当前用户无法进入方案商城");
                    break;
                }
                intent.setClass(MainActivity.this, SolutionListActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_design:
//                intent.setClass(MainActivity.this, ServiceCustomActivity.class);
//                intent.putExtra("connectedId", (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.TEZHAN_ID, ""));
//                intent.putExtra("user", userBean);
//                startActivity(intent);
                IMManagerImpl.startChatting(this,(String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.TEZHAN_ID, ""));
                break;
            case R.id.bt_order:
                intent.setClass(MainActivity.this, OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_service:
//                intent.setClass(MainActivity.this, ServiceCustomActivity.class);
//                intent.putExtra("connectedId", (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.KEFU_ID, ""));
//                intent.putExtra("user", userBean);
//                startActivity(intent);
                IMManagerImpl.startChatting(this,(String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.KEFU_ID, ""));
                break;
            case R.id.bt_user_center:
                intent.setClass(MainActivity.this, UserCenterActivity.class);
                startActivity(intent);
                break;
            default:
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();
                Intent intent = new Intent();
                switch (id) {
                    case R.id.nav_plan_shop:
                        if (userBean != null && userBean.getType().equals("2")) {
                            ToastUtils.showShortToast("当前用户无法进入方案商城");
                            break;
                        }
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
                        //                intent.setClass(MainActivity.this, ServiceCustomActivity.class);
                        //                intent.putExtra("connectedId", (String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.KEFU_ID, ""));
                        //                intent.putExtra("user", userBean);
                        //                startActivity(intent);
                        IMManagerImpl.startChatting(MainActivity.this,(String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.KEFU_ID, ""));
                        break;
                    case R.id.nav_exit:
                        getPresenter().loginOut();
                        break;
                    default:
                }
            }
        }, 200);
        return true;
    }

    @Override
    public void loginOut() {
        JPushInterface.deleteAlias(this, 0);
        SDKCoreHelper.logout();
        SPUtils.getInstance().clear(SPFileUtils.FILE_USER);
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
            intent.putExtra("alias", userInfoBean.getPhone());
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
