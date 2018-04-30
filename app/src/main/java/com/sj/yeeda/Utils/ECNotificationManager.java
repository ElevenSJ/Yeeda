/*
 *  Copyright (c) 2013 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.yuntongxun.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.sj.yeeda.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.sj.module_lib.utils.Utils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.service.ServiceCustomActivity;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.im.ECFileMessageBody;
import com.yuntongxun.ecsdk.im.ECImageMessageBody;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;


/**
 * 状态栏通知
 * @author Jorstin Chan@容联•云通讯
 * @date 2015-1-4
 * @version 4.0
 */
public class ECNotificationManager {

    public static final int NOTIFY_ID_PUSHCONTENT = 35;

    private Context mContext;

    private static NotificationManager mNotificationManager;

    public static ECNotificationManager mInstance;
    public static ECNotificationManager getInstance() {
        if(mInstance == null) {
            mInstance = new ECNotificationManager(Utils.getContext());
        }

        return mInstance;
    }

    private ECNotificationManager(Context context){
        mContext = context;
    }

    public final void showNotification(Context context , ECMessage message) {
        Intent intent = new Intent(mContext, ServiceCustomActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 35, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        String tickerText = getTickerText(mContext, message.getNickName(), message.getType().ordinal());
        String contentTitle = message.getNickName();
        String contentText = getContentText(context, message ,message.getType().ordinal());

        Notification notification = NotificationUtil.buildNotification(context, R.mipmap.ic_launcher, Notification.DEFAULT_VIBRATE, true, tickerText, contentTitle, contentText, null, pendingIntent);
        notification.flags = (Notification.FLAG_AUTO_CANCEL | notification.flags);
        ((NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE)).notify(NOTIFY_ID_PUSHCONTENT, notification);
    }


    /**
     *
     * @param contex
     * @param fromUserName
     * @param msgType
     * @return
     */
    public final String getTickerText(Context contex ,String fromUserName ,int msgType) {
        if(msgType == ECMessage.Type.TXT.ordinal()) {
            return contex.getResources().getString(R.string.notification_fmt_one_txttype, fromUserName);
        } else if (msgType == ECMessage.Type.IMAGE.ordinal()) {
            return contex.getResources().getString(R.string.notification_fmt_one_imgtype, fromUserName);
        } else if (msgType == ECMessage.Type.VOICE.ordinal()) {
            return contex.getResources().getString(R.string.notification_fmt_one_voicetype, fromUserName);
        } else if (msgType == ECMessage.Type.FILE.ordinal()) {
            return contex.getResources().getString(R.string.notification_fmt_one_filetype, fromUserName);
        }  else {
            return contex.getPackageManager().getApplicationLabel(contex.getApplicationInfo()).toString();
        }

    }

    /**
     *
     * @param context
     * @return
     */
    public final String getContentText(Context context , ECMessage message ,int lastMsgType) {

        if(lastMsgType == ECMessage.Type.TXT.ordinal()) {
            return ((ECTextMessageBody)message.getBody()).getMessage();
        } else if (lastMsgType == ECMessage.Type.FILE.ordinal()) {
            return "文件："+((ECFileMessageBody)message.getBody()).getFileName();
        }  else if (lastMsgType == ECMessage.Type.IMAGE.ordinal()) {
            return "图片："+((ECImageMessageBody)message.getBody()).getFileName();
        } else {
            return "有新消息";
        }

    }

    private void cancel() {
        NotificationManager notificationManager = (NotificationManager) Utils
                .getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null) {
            return;
        }
        notificationManager.cancel(0);
    }

    /**
     * 取消所有的状态栏通知
     */
    public final void forceCancelNotification() {
        cancel();
        NotificationManager notificationManager = (NotificationManager) Utils
                .getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null) {
            return;
        }
        notificationManager.cancel(NOTIFY_ID_PUSHCONTENT);

    }

    private void checkNotification() {
        if(mNotificationManager == null) {
            mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        
    }
}
