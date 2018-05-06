package com.sj.yeeda.im;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.http.UrlConfig;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.ECMessageBody;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;
import com.yuntongxun.ecsdk.im.ECUserStateMessageBody;
import com.yuntongxun.plugin.common.AppMgr;
import com.yuntongxun.plugin.common.common.utils.LogUtil;
import com.yuntongxun.plugin.im.dao.dbtools.DBECMessageTools;
import com.yuntongxun.plugin.im.manager.IMPluginManager;
import com.yuntongxun.plugin.im.manager.bean.RETURN_TYPE;
import com.yuntongxun.plugin.im.manager.port.OnIMBindViewListener;
import com.yuntongxun.plugin.im.manager.port.OnMessagePreproccessListener;
import com.yuntongxun.plugin.im.manager.port.OnNotificationClickListener;
import com.yuntongxun.plugin.im.manager.port.OnReturnIdsCallback;
import com.yuntongxun.plugin.im.manager.port.OnReturnIdsClickListener;
import com.yuntongxun.plugin.im.ui.chatting.model.IMChattingHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class IMManagerImpl implements OnReturnIdsClickListener, OnIMBindViewListener, OnNotificationClickListener, OnMessagePreproccessListener {
    private static List<String> HeadList = new ArrayList<>();

    private String orderId;
    private IMManagerImpl() {
    }

    private static IMManagerImpl instance;
    private OnReturnIdsCallback monReturnIdsCallback;

    public static IMManagerImpl getInstance() {
        if (instance == null) {
            synchronized (IMManagerImpl.class) {
                instance = new IMManagerImpl();
            }
            HeadList.add("R.drawable.img_personal_center_circle");
//            HeadList.add("http://new-img3.ol-img.com/moudlepic/199_module_images/201612/5861db8b4c8d3_630.jpg");
        }

        return instance;
    }

    public static void setResult(String... ids) {
        if (getInstance() == null) {
            return;
        }
        if (getInstance().monReturnIdsCallback == null) {
            return;
        }
        getInstance().monReturnIdsCallback.returnIds(ids);
        getInstance().monReturnIdsCallback = null;
    }

    /**
     * 配置头像、昵称和点击事件
     *
     * @param context
     * @param userId
     * @return
     */
    @Override
    public String onBindNickName(Context context, String userId) {
        return getNameById(userId);
    }

    private String getNameById(String userId) {
        String name = "";
        if (!TextUtils.isEmpty(userId)) {
            if (userId.equals((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.KEFU_ID, ""))) {
                name = "客户服务";
//                sendMsg(userId,name,false);
            } else if (userId.equals((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.TEZHAN_ID, ""))) {
                name = "特展设计";
                sendMsg(userId,name,true);
            } else if (userId.equals((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.DINGDAN_ID, ""))) {
                name = "订单客服";
                sendMsg(userId, UrlConfig.BASE_URL+"/webQueryOrderDetail?oid="+orderId,true);
            }
        }
        return name;
    }

    private void sendMsg(String contactId,String text,boolean insertDb) {
        if(text != null) {
            ECMessage msg = ECMessage.createECMessage(ECMessage.Type.TXT);
            msg.setTo(contactId);
            msg.setSessionId(contactId);
            Object msgBody = new ECTextMessageBody(text.toString());
            msg.setBody((ECMessageBody)msgBody);
            try {
                IMChattingHelper.getInstance().sendECMessage(msg, insertDb);
            } catch (Exception var9) {
                var9.printStackTrace();
            }
        }
    }

    @Override
    public void OnAvatarClickListener(Context context, String id) {
//        ToastUtil.showMessage("点击了" + getNameById(id) + "的头像");
    }

    @Override
    public String onBindAvatarByUrl(Context context, String userId) {
        return HeadList.get(0);
    }

    /**
     * 此方法为  设置IM通知消息点击事件监听，如果不配置默认跳转到聊天页面
     *
     * @param context
     * @param contactId
     * @param intent
     */
    @Override
    public void onNotificationClick(Context context, String contactId, Intent intent) {
        // 代码示例，用户只需要配置好intent。指定跳转的页面及传递的数据 不需要startActivity（）
        intent.setClassName(context, "com.sj.yeeda.activity.main.MainActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("connectedId", contactId);
    }

    /**
     * 配置添加成员和转发消息的加号
     *
     * @param context
     * @param return_type
     * @param callback
     */
    @Override
    public void onReturnIdsClick(Context context, RETURN_TYPE return_type, OnReturnIdsCallback callback, String... ids) {
        monReturnIdsCallback = callback;
        //当转发和增加群成员用于同个界面时，用当前枚举做判断
//        if (return_type == RETURN_TYPE.ADDMEMBER_USERID) {
//            //添加群成员
//            context.startActivity(new Intent(context, AddOthersIntoGroup.class));
//        } else if (return_type == RETURN_TYPE.TRANSMIT_CONTACTID) {
//            //转发
//            context.startActivity(new Intent(context, AddOthersIntoGroup.class));
//        }
    }


    @Override
    public boolean dispatchMessage(ECMessage ecMessage) {
        // 返回true意义是消费当前这条消息不交给插件内部处理,返回false意义是交给插件进行处理
        if (ecMessage.getForm().equals("10086")) {
//            LogUtil.d(TAG, "dispatchMessage 10086...");
            return true;
        }
        return false;
    }

    public static void startChatting(Context context, String userId) {
        int imStatus = (Integer) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_USER, SPFileUtils.IM_IS_CONNECTED, 1);
        switch (imStatus) {
            case 0:
                IMPluginManager.getManager().startChatting(context, userId);
                break;
            case 1:
                ToastUtils.showShortToast("正在连接，请稍后再试！");
                break;
            case -1:
                ToastUtils.showShortToast("连接失败，请稍后再试！");
                break;
            default:
        }
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
