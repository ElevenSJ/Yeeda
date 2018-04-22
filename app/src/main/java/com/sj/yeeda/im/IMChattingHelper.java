package com.sj.yeeda.im;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.yuntongxun.ecsdk.ECChatManager;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.OnChatReceiveListener;
import com.yuntongxun.ecsdk.im.ECFileMessageBody;
import com.yuntongxun.ecsdk.im.ECImageMessageBody;
import com.yuntongxun.ecsdk.im.ECLocationMessageBody;
import com.yuntongxun.ecsdk.im.ECMessageNotify;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;
import com.yuntongxun.ecsdk.im.ECVideoMessageBody;
import com.yuntongxun.ecsdk.im.ECVoiceMessageBody;
import com.yuntongxun.ecsdk.im.group.ECGroupNoticeMessage;

import java.util.HashMap;
import java.util.List;


public class IMChattingHelper implements OnChatReceiveListener, ECChatManager.OnDownloadMessageListener {

    private static IMChattingHelper sInstance;

    private static HashMap<String, ECMessage> syncMessage = new HashMap<String, ECMessage>();
    /**
     * 是否是同步消息
     */
    private boolean isFirstSync = false;

    private OnMessageReportCallback mOnMessageReportCallback;

    public static void setOnMessageReportCallback(
            OnMessageReportCallback callback) {
        getInstance().mOnMessageReportCallback = callback;
    }

    public interface OnMessageReportCallback {
        void onMessageReport(ECError error, ECMessage message);

        void onPushMessage(String sessionId, List<ECMessage> msgs);

    }

    public synchronized static IMChattingHelper getInstance() {
        if (sInstance == null) {
            synchronized (IMChattingHelper.class) {
                sInstance = new IMChattingHelper();
            }
        }
        return sInstance;
    }

    /**
     * 云通讯SDK聊天功能接口
     */
    private ECChatManager mChatManager;

    public void init() {
        ECDevice.setOnChatReceiveListener(this);
    }

    public ECChatManager getChatManager() {
        if (mChatManager == null) {
            mChatManager = SDKCoreHelper.getECChatManager();
        }
        return mChatManager;
    }

    public void destroy() {
        if (syncMessage != null) {
            syncMessage.clear();
        }
        mChatManager = null;
        isFirstSync = false;
        sInstance = null;
    }

    @Override
    public void onDownloadMessageComplete(ECError ecError, ECMessage ecMessage) {

    }

    @Override
    public void onProgress(String s, int i, int i1) {

    }

    @Override
    public void OnReceivedMessage(ECMessage msg) {
        Logger.i("收到新消息");
        if (msg == null) {
            Logger.i("消息为null");
            return;
        }
        // 接收到的IM消息，根据IM消息类型做不同的处理(IM消息类型：ECMessage.Type)
        ECMessage.Type type = msg.getType();
        if (type == ECMessage.Type.TXT) {
            // 在这里处理文本消息
            ECTextMessageBody textMessageBody = (ECTextMessageBody) msg.getBody();
            Logger.i("文本消息：" + textMessageBody.getMessage());
        } else {
            String thumbnailFileUrl = null;
            String remoteUrl = null;
            if (type == ECMessage.Type.FILE) {
                // 在这里处理附件消息
                ECFileMessageBody fileMsgBody = (ECFileMessageBody) msg.getBody();
                Logger.i("附件消息：" + fileMsgBody.getFileName());
                // 获得下载地址
                remoteUrl = fileMsgBody.getRemoteUrl();
            } else if (type == ECMessage.Type.IMAGE) {
                // 在这里处理图片消息
                ECImageMessageBody imageMsgBody = (ECImageMessageBody) msg.getBody();
                Logger.i("图片消息：" + imageMsgBody.getFileName() + imageMsgBody.getOriginFileLength());
                // 获得缩略图地址
                thumbnailFileUrl = imageMsgBody.getThumbnailFileUrl();
                // 获得原图地址
                remoteUrl = imageMsgBody.getRemoteUrl();
            } else if (type == ECMessage.Type.VOICE) {
                // 在这里处理语音消息
                ECVoiceMessageBody voiceMsgBody = (ECVoiceMessageBody) msg.getBody();
                // 获得下载地址
                remoteUrl = voiceMsgBody.getRemoteUrl();
            } else if (type == ECMessage.Type.VIDEO) {
                // 在这里处理视频消息
                ECVideoMessageBody videoMessageBody = (ECVideoMessageBody) msg.getBody();
                // 获得下载地址
                remoteUrl = videoMessageBody.getRemoteUrl();
            } else if (type == ECMessage.Type.LOCATION) {
                // 在这里处理地理位置消息
                ECLocationMessageBody locationMessageBody = (ECLocationMessageBody) msg.getBody();
                // 获得下载地址
                locationMessageBody.getLatitude(); // 纬度信息
                locationMessageBody.getLongitude();// 经度信息
            } else {
                Logger.i("未知消息类型");
                // 后续还会支持（自定义等消息类型）
            }
            if (TextUtils.isEmpty(remoteUrl)) {
                return;
            }
            if (!TextUtils.isEmpty(thumbnailFileUrl)) {
                // 先下载缩略图
            } else {
                // 下载附件
            }
        }
        // 根据不同类型处理完消息之后，将消息序列化到本地存储（sqlite）
        // 通知UI有新消息到达
    }

    @Override
    public void onReceiveMessageNotify(ECMessageNotify ecMessageNotify) {

    }

    @Override
    public void OnReceiveGroupNoticeMessage(ECGroupNoticeMessage notice) {
        //收到群组通知消息,可以根据ECGroupNoticeMessage.ECGroupMessageType类型区分不同消息类型
        Logger.i("收到群组通知消息（有人加入、退出...）");
    }

    @Override
    public void onOfflineMessageCount(int count) {
        // 登陆成功之后SDK回调该接口通知帐号离线消息数
        Logger.i("离线消息总共条数:" + count);
    }

    @Override
    public int onGetOfflineMessage() {
        return ECDevice.SYNC_OFFLINE_MSG_ALL;
    }

    @Override
    public void onReceiveOfflineMessage(List<ECMessage> msgs) {
        // SDK根据应用设置的离线消息拉取规则通知应用离线消息
        for (ECMessage msg : msgs) {
            syncMessage.put(msg.getForm(),msg);
            // 接收到的IM消息，根据IM消息类型做不同的处理(IM消息类型：ECMessage.Type)
            ECMessage.Type type = msg.getType();
            if (type == ECMessage.Type.TXT) {
                // 在这里处理文本消息
                ECTextMessageBody textMessageBody = (ECTextMessageBody) msg.getBody();
                Logger.i("文本消息：" + textMessageBody.getMessage());
            } else {
                String thumbnailFileUrl = null;
                String remoteUrl = null;
                if (type == ECMessage.Type.FILE) {
                    // 在这里处理附件消息
                    ECFileMessageBody fileMsgBody = (ECFileMessageBody) msg.getBody();
                    Logger.i("附件消息：" + fileMsgBody.getFileName());
                    // 获得下载地址
                    remoteUrl = fileMsgBody.getRemoteUrl();
                } else if (type == ECMessage.Type.IMAGE) {
                    // 在这里处理图片消息
                    ECImageMessageBody imageMsgBody = (ECImageMessageBody) msg.getBody();
                    Logger.i("图片消息：" + imageMsgBody.getFileName() + imageMsgBody.getOriginFileLength());
                    // 获得缩略图地址
                    thumbnailFileUrl = imageMsgBody.getThumbnailFileUrl();
                    // 获得原图地址
                    remoteUrl = imageMsgBody.getRemoteUrl();
                } else if (type == ECMessage.Type.VOICE) {
                    // 在这里处理语音消息
                    ECVoiceMessageBody voiceMsgBody = (ECVoiceMessageBody) msg.getBody();
                    // 获得下载地址
                    remoteUrl = voiceMsgBody.getRemoteUrl();
                } else if (type == ECMessage.Type.VIDEO) {
                    // 在这里处理视频消息
                    ECVideoMessageBody videoMessageBody = (ECVideoMessageBody) msg.getBody();
                    // 获得下载地址
                    remoteUrl = videoMessageBody.getRemoteUrl();
                } else if (type == ECMessage.Type.LOCATION) {
                    // 在这里处理地理位置消息
                    ECLocationMessageBody locationMessageBody = (ECLocationMessageBody) msg.getBody();
                    // 获得下载地址
                    locationMessageBody.getLatitude(); // 纬度信息
                    locationMessageBody.getLongitude();// 经度信息
                } else {
                    Logger.i("未知消息类型");
                    // 后续还会支持（自定义等消息类型）
                }
                if (TextUtils.isEmpty(remoteUrl)) {
                    return;
                }
                if (!TextUtils.isEmpty(thumbnailFileUrl)) {
                    // 先下载缩略图
                } else {
                    // 下载附件
                }
            }
        }
    }

    @Override
    public void onReceiveOfflineMessageCompletion() {
        // SDK通知应用离线消息拉取完成
        Logger.i("离线消息拉取完成");

    }

    @Override
    public void onServicePersonVersion(int version) {
        // SDK通知应用当前帐号的个人信息版本号
    }

    @Override
    public void onReceiveDeskMessage(ECMessage ecMessage) {

    }

    @Override
    public void onSoftVersion(String s, int i) {

    }


}
