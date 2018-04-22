package com.sj.yeeda.im;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.SPUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.base.BaseApplication;
import com.yuntongxun.ecsdk.ECChatManager;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.ECNotifyOptions;
import com.yuntongxun.ecsdk.SdkErrorCode;

import java.util.HashMap;

public class SDKCoreHelper implements ECDevice.InitListener, ECDevice.OnECDeviceConnectListener,ECDevice.OnLogoutResultListener {

    public static final String TAG = "SDKCoreHelper";
    private static SDKCoreHelper sInstance;
    private Context mContext;
    private ECDevice.ECConnectState mConnect = ECDevice.ECConnectState.CONNECT_FAILED;
    private ECInitParams mInitParams;
    private ECInitParams.LoginMode mMode = ECInitParams.LoginMode.FORCE_LOGIN;
    /**
     * 初始化错误
     */
    public static final int ERROR_CODE_INIT = -3;

    private ECNotifyOptions mOptions;

    private SDKCoreHelper() {
        initNotifyOptions();
    }

    public synchronized static SDKCoreHelper getInstance() {
        if (sInstance == null) {
            sInstance = new SDKCoreHelper();
        }
        return sInstance;
    }

    public synchronized static void init(Context ctx) {
        init(ctx, ECInitParams.LoginMode.FORCE_LOGIN);
    }

    public static void init(Context ctx, ECInitParams.LoginMode mode) {
        Logger.d(TAG + "容联IM开始初始化。。。");
        ctx = BaseApplication.getApp().getApplicationContext();
        getInstance().mMode = mode;
        getInstance().mContext = ctx;
        // 判断SDK是否已经初始化，没有初始化则先初始化SDK
        if (!ECDevice.isInitialized()) {
            getInstance().mConnect = ECDevice.ECConnectState.CONNECTING;
            ECDevice.initial(ctx, getInstance());
            return;
        }
        // 已经初始化成功，直接进行注册
        getInstance().onInitialized();
    }

    private void initNotifyOptions() {
        if (mOptions == null) {
            mOptions = new ECNotifyOptions();
        }
        // 设置新消息是否提醒
        mOptions.setNewMsgNotify(true);
        // 设置状态栏通知图标
        mOptions.setIcon(R.mipmap.ic_launcher);
        // 设置是否启用勿扰模式（不会声音/震动提醒）
        mOptions.setSilenceEnable(false);
        // 设置勿扰模式时间段（开始小时/开始分钟-结束小时/结束分钟）
        // 小时采用24小时制
        // 如果设置勿扰模式不启用，则设置勿扰时间段无效
        // 当前设置晚上11点到第二天早上8点之间不提醒
        mOptions.setSilenceTime(23, 0, 8, 0);
        // 设置是否震动提醒(如果处于免打扰模式则设置无效，没有震动)
        mOptions.enableShake(true);
        // 设置是否声音提醒(如果处于免打扰模式则设置无效，没有声音)
        mOptions.enableSound(true);
    }

    @Override
    public void onInitialized() {
        ECDevice.setNotifyOptions(mOptions);
        IMChattingHelper.getInstance().init();
        ECDevice.setOnDeviceConnectListener(this);

        Logger.d(TAG + " 容联IM初始化成功");
        if (getConnectState() == ECDevice.ECConnectState.CONNECT_SUCCESS){
            Logger.d(TAG + " 容联IM已连接");
            return;
        }
        Logger.d(TAG + " 容联IM开始设置登录参数");
        if (mInitParams == null) {
            //创建登录参数对象
            mInitParams = ECInitParams.createParams();
        }
        mInitParams.reset();
        //设置用户登录账号
        mInitParams.setUserid((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.USER_ACCOUNT, ""));
        //设置AppId
        mInitParams.setAppKey("8aaf0708624670f201626d59d5361023");
        //设置AppToken
        Logger.i(TAG+"AppToken:"+(String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.IM_TOKEN_ID, ""));
//        mInitParams.setToken((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.IM_TOKEN_ID, ""));
        mInitParams.setToken("0609ca9d38514caa9831247ad40f5603");
        //设置登陆验证模式：自定义登录方式
        mInitParams.setAuthType(ECInitParams.LoginAuthType.NORMAL_AUTH);
        //LoginMode（强制上线：FORCE_LOGIN  默认登录：AUTO。使用方式详见注意事项）
        mInitParams.setMode(ECInitParams.LoginMode.FORCE_LOGIN);
        //验证参数是否正确
        if (mInitParams.validate()) {
            Logger.d(TAG + "容联IM登录参数验证成功，开始登录");
            // 登录函数
            ECDevice.login(mInitParams);
        } else {
            Logger.d(TAG + "容联IM登录参数验证失败");
        }
    }

    @Override
    public void onError(Exception exception) {
        Logger.d(TAG + "容联IM初始化失败：" + exception.getMessage());
        ECDevice.unInitial();
    }

    @Override
    public void onConnect() {
        // Deprecated
        Logger.d(TAG + "容联IM已连接");
    }

    @Override
    public void onDisconnect(ECError error) {
        Logger.d(TAG + "容联IM断开连接");
    }

    @Override
    public void onConnectState(ECDevice.ECConnectState state, ECError error) {
        Logger.d(error.toString());
        if (state == ECDevice.ECConnectState.CONNECT_FAILED) {
            if (error.errorCode == SdkErrorCode.SDK_KICKED_OFF) {
                Logger.d(TAG + "容联IM帐号异地登陆");
            } else {
                Logger.d(TAG + "容联IM帐号其他登录失败,错误码：" + error.errorCode);
            }
            return;
        } else if (state == ECDevice.ECConnectState.CONNECT_SUCCESS) {
            Logger.d(TAG + "容联IM帐号登陆成功");
        }
        getInstance().mConnect = state;
    }

    /**
     * 当前SDK注册状态
     *
     * @return
     */
    public static ECDevice.ECConnectState getConnectState() {
        return getInstance().mConnect;
    }

    /**
     * 判断服务是否自动重启
     * @return 是否自动重启
     */
    public static boolean isUIShowing() {
        return ECDevice.isInitialized();
    }

    @Override
    public void onLogout(int i, String s) {
        Logger.d(TAG + "容联IM帐号已注销，释放资源");
        ECDevice.unInitial();
        getInstance().mConnect = ECDevice.ECConnectState.CONNECT_FAILED;
        if(mInitParams != null && mInitParams.getInitParams() != null) {
            mInitParams.getInitParams().clear();
        }
        mInitParams = null;
    }

    public static void logout(boolean isNotice){
        Logger.d(TAG + "容联IM帐号开始注销");
        IMChattingHelper.getInstance().destroy();
        ECDevice.NotifyMode notifyMode = (isNotice) ? ECDevice.NotifyMode.IN_NOTIFY : ECDevice.NotifyMode.NOT_NOTIFY;
        ECDevice.logoutForResult(notifyMode,getInstance());

    }

    public static void release() {
    }

    /**
     * IM聊天功能接口
     *
     * @return
     */
    public static ECChatManager getECChatManager() {
        ECChatManager ecChatManager = ECDevice.getECChatManager();
        Logger.d(TAG, "ecChatManager :" + ecChatManager);
        return ecChatManager;
    }

    /**
     * 缓存对讲号
     */

    public HashMap<String, String> cacheMap = new HashMap<String, String>();

    public static void putCacheCreat(String interMeetingNo, String creator) {
        getInstance().cacheMap.put(interMeetingNo, creator);
    }

    public static String getCreator(String interMeetingNo) {
        if (getInstance().cacheMap.containsKey(interMeetingNo)) {
            return getInstance().cacheMap.get(interMeetingNo);
        } else {
            return "";
        }

    }


}
