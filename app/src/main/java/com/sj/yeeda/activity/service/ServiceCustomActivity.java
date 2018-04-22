package com.sj.yeeda.activity.service;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.photo.BrowserImageActivity;
import com.sj.yeeda.activity.service.models.DefaultUser;
import com.sj.yeeda.activity.service.models.MyMessage;
import com.sj.yeeda.activity.user.supply.bean.UserInfoBean;
import com.sj.yeeda.base.TitleBaseActivity;
import com.sj.yeeda.im.IMChattingHelper;
import com.sj.yeeda.widgets.ChatView;
import com.yuntongxun.ecsdk.ECChatManager;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.im.ECImageMessageBody;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.OnCameraCallbackListener;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.model.FileItem;
import cn.jiguang.imui.commons.ImageLoader;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.messages.MsgListAdapter;
import cn.jiguang.imui.messages.ptr.PtrHandler;
import cn.jiguang.imui.messages.ptr.PullToRefreshLayout;

/**
 * 创建时间: on 2018/4/17.
 * 创建人: 孙杰
 * 功能描述:
 */
public class ServiceCustomActivity extends TitleBaseActivity<ServiceContract.Presenter> implements ServiceContract.View, View.OnTouchListener {

    private final static String TAG = "ServiceCustomActivity";
    private ChatView mChatView;
    private MsgListAdapter<MyMessage> mAdapter;
    private List<MyMessage> mData;

    private InputMethodManager mImm;
    private Window mWindow;
    private ArrayList<String> mPathList = new ArrayList<>();
    private ArrayList<String> mMsgIdList = new ArrayList<>();


    private UserInfoBean userBean;

    @Override
    public ServiceContract.Presenter getPresenter() {
        if (presenter == null) {
            presenter = new ServicePresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_service;
    }

    @Override
    public void initView() {
        super.initView();
        userBean = (UserInfoBean) getIntent().getSerializableExtra("user");
        setTitleTxt("客户服务");
        setTitleBg();
        this.mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mWindow = getWindow();
        mChatView = (ChatView) findViewById(R.id.chat_view);
        mChatView.initModule();
        mChatView.getChatInputView().getRecordVoiceButton().setVisibility(View.GONE);
        mChatView.setOnTouchListener(this);
        mChatView.setMenuClickListener(new OnMenuClickListener() {
            @Override
            public boolean onSendTextMessage(CharSequence input) {
                if (input.length() == 0) {
                    return false;
                }
                final MyMessage message = new MyMessage(input.toString(), IMessage.MessageType.SEND_TEXT.ordinal());
                message.setUserInfo(new DefaultUser(userBean.getId(), userBean.getUserName(), "R.drawable.img_personal_center_circle"));
                message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
                message.setMessageStatus(IMessage.MessageStatus.SEND_GOING);
                mAdapter.addToStart(message, true);
                try {
                    //创建一个待发送的消息ECmessage消息体
                    ECMessage msg = ECMessage.createECMessage(ECMessage.Type.TXT);
                    //设置消息接收者,如果是发送群组消息，则接收者设置群组ID
                    msg.setTo((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.KEFU_ID, ""));
                    //创建一个文本消息体，并添加到消息对象中
                    ECTextMessageBody msgBody = new ECTextMessageBody(input.toString());
                    //将消息体存放到ECMessage中
                    msg.setBody(msgBody);
                    msg.setMsgStatus(ECMessage.MessageStatus.SENDING);
                    //调用SDK发送接口发送消息到服务器
                    ECChatManager manager = IMChattingHelper.getInstance().getChatManager();
                    manager.sendMessage(msg, new ECChatManager.OnSendMessageListener() {
                        @Override
                        public void onSendMessageComplete(ECError error, ECMessage ecMessage) {
                            // 处理消息发送结果
                            if (ecMessage == null) {
                                return;
                            }
                            ecMessage.setMsgStatus(ECMessage.MessageStatus.SUCCESS);
                            Logger.e("send message  e=" + error.errorCode + error.errorMsg);
                            // 将发送的消息更新到本地数据库并刷新UI
                            message.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                        }

                        @Override
                        public void onProgress(String msgId, int totalByte, int progressByte) {
                            // 处理文件发送上传进度（尽上传文件、图片时候SDK回调该方法）
                        }
                    });
                } catch (Exception e) {
                    // 处理发送异常
                    Logger.e("send message fail , e=" + e.getMessage());
                    message.setMessageStatus(IMessage.MessageStatus.SEND_FAILED);
                }
                return true;
            }

            @Override
            public void onSendFiles(List<FileItem> list) {
                if (list == null || list.isEmpty()) {
                    return;
                }

                MyMessage message;
                for (FileItem item : list) {
                    if (item.getType() == FileItem.Type.Image) {
                        message = new MyMessage(null, IMessage.MessageType.SEND_IMAGE.ordinal());
                        mPathList.add(item.getFilePath());
                        mMsgIdList.add(message.getMsgId());
                        message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
                        message.setMediaFilePath(item.getFilePath());
                        message.setUserInfo(new DefaultUser(userBean.getId(), userBean.getUserName(), "R.drawable.img_personal_center_circle"));
                        message.setMessageStatus(IMessage.MessageStatus.SEND_GOING);
                        final MyMessage fMsg = message;
                        mAdapter.addToStart(fMsg, true);
                        try {
                            //创建一个待发送的消息ECmessage消息体
                            ECMessage msg = ECMessage.createECMessage(ECMessage.Type.IMAGE);
                            //或者创建一个图片消息体 并且设置附件包体（其实图片也是相当于附件）比如我们发送SD卡里面的一张Tony_2015.jpg图片。
                            ECImageMessageBody msgBody = new ECImageMessageBody();
                            //设置附件名
                            msgBody.setFileName(item.getFileName());
                            // 设置附件扩展名
                            msgBody.setFileExt(item.getFileName().substring(item.getFileName().indexOf(".")));
                            //设置附件本地路径
                            msgBody.setLocalUrl(item.getFilePath());
                            //设置消息接收者，如果是发送群组消息，则接收者设置群组ID
                            msg.setTo((String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.KEFU_ID, ""));
                            //将消息体存放到ECMessage中
                            msg.setBody(msgBody);
                            //调用SDK发送接口发送消息到服务器
                            ECChatManager manager = IMChattingHelper.getInstance().getChatManager();
                            manager.sendMessage(msg, new ECChatManager.OnSendMessageListener() {
                                @Override
                                public void onSendMessageComplete(ECError error, ECMessage message) {
                                    // 处理消息发送结果
                                    if (message == null) {
                                        return;
                                    }
                                    // 将发送的消息更新到本地数据库并刷新UI
                                    Logger.e("send imge message success , e=" + error.errorCode + error.errorMsg);
                                    fMsg.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                                }

                                @Override
                                public void onProgress(String msgId, int totalByte, int progressByte) {
                                    // 处理文件发送上传进度（尽上传文件、图片时候SDK回调该方法）
                                    fMsg.setProgress(progressByte + "/" + totalByte);
                                }
                            });
                        } catch (Exception e) {
                            // 处理发送异常
                            Logger.e("send imge message fail , e=" + e.getMessage());
                            fMsg.setMessageStatus(IMessage.MessageStatus.SEND_FAILED);
                        }
                    } else if (item.getType() == FileItem.Type.Video) {

                    } else {
                        throw new RuntimeException("Invalid FileItem type. Must be Type.Image or Type.Video");
                    }

                }
            }

            @Override
            public boolean switchToMicrophoneMode() {
                scrollToBottom();
                return true;
            }

            @Override
            public boolean switchToGalleryMode() {
                scrollToBottom();
//                mChatView.getChatInputView().getSelectPhotoView().updateData();
                return true;
            }

            @Override
            public boolean switchToCameraMode() {
                scrollToBottom();
                File rootDir = getFilesDir();
                String fileDir = rootDir.getAbsolutePath() + "/photo";
                mChatView.setCameraCaptureFile(fileDir, new SimpleDateFormat("yyyy-MM-dd-hhmmss",
                        Locale.getDefault()).format(new Date()));
                return true;
            }

            @Override
            public boolean switchToEmojiMode() {
                scrollToBottom();
                return true;
            }
        });

        mChatView.setOnCameraCallbackListener(new OnCameraCallbackListener() {
            @Override
            public void onTakePictureCompleted(String photoPath) {
                final MyMessage message = new MyMessage(null, IMessage.MessageType.SEND_IMAGE.ordinal());
                message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
                message.setMediaFilePath(photoPath);
                mPathList.add(photoPath);
                mMsgIdList.add(message.getMsgId());
                message.setUserInfo(new DefaultUser(userBean.getId(), userBean.getUserName(), "R.drawable.img_personal_center_circle"));
                ServiceCustomActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addToStart(message, true);
                    }
                });
            }

            @Override
            public void onStartVideoRecord() {
                ToastUtils.showShortToast("暂不支持");
            }

            @Override
            public void onFinishVideoRecord(String videoPath) {

            }

            @Override
            public void onCancelVideoRecord() {

            }
        });

        mChatView.getChatInputView().getInputView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scrollToBottom();
                return false;
            }
        });
        mChatView.getSelectAlbumBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("去选择图片");
            }
        });
    }


    //加载更多
    private void loadNextPage() {
    }

    private void scrollToBottom() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mChatView.getMessageListView().smoothScrollToPosition(0);
            }
        }, 200);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!isProgressShowing()) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ChatInputView chatInputView = mChatView.getChatInputView();
                    if (chatInputView.getMenuState() == View.VISIBLE) {
                        chatInputView.dismissMenuLayout();
                    }
                    try {
                        View v = getCurrentFocus();
                        if (mImm != null && v != null) {
                            mImm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                            mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                            view.clearFocus();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view.performClick();
                    break;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initMessageAdapter() {
        final float density = getResources().getDisplayMetrics().density;
        final float MIN_WIDTH = 60 * density;
        final float MAX_WIDTH = 200 * density;
        final float MIN_HEIGHT = 60 * density;
        final float MAX_HEIGHT = 200 * density;
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadAvatarImage(ImageView avatarImageView, String string) {
                // You can use other image load libraries.
                if (string.contains("R.drawable")) {
                    Integer resId = getResources().getIdentifier(string.replace("R.drawable.", ""),
                            "drawable", getPackageName());

                    avatarImageView.setImageResource(resId);
                } else {
                    ImageUtils.loadImageWithError(string, R.drawable.img_personal_center_circle, avatarImageView);
                }
            }

            /**
             * Load image message
             * @param imageView Image message's ImageView.
             * @param string A file path, or a uri or url.
             */
            @Override
            public void loadImage(final ImageView imageView, String string) {
                // You can use other image load libraries.
                ImageUtils.loadImageWithError(string, R.mipmap.ic_launcher, imageView);
            }

            @Override
            public void loadVideo(ImageView imageCover, String uri) {
            }
        };

        MsgListAdapter.HoldersConfig holdersConfig = new MsgListAdapter.HoldersConfig();
        mAdapter = new MsgListAdapter<>(userBean.getPhone(), holdersConfig, imageLoader);
        mAdapter.setOnMsgClickListener(new MsgListAdapter.OnMsgClickListener<MyMessage>() {
            @Override
            public void onMessageClick(MyMessage message) {
                // do something
                if (message.getType() == IMessage.MessageType.RECEIVE_VIDEO.ordinal()
                        || message.getType() == IMessage.MessageType.SEND_VIDEO.ordinal()) {
                    if (!TextUtils.isEmpty(message.getMediaFilePath())) {
                    }
                } else if (message.getType() == IMessage.MessageType.RECEIVE_IMAGE.ordinal()
                        || message.getType() == IMessage.MessageType.SEND_IMAGE.ordinal()) {
                    Intent intent = new Intent(ServiceCustomActivity.this, BrowserImageActivity.class);
                    intent.putExtra("msgId", message.getMsgId());
                    intent.putStringArrayListExtra("pathList", mPathList);
                    intent.putStringArrayListExtra("idList", mMsgIdList);
                    startActivity(intent);
                } else {
                }
            }
        });

        mAdapter.setMsgLongClickListener(new MsgListAdapter.OnMsgLongClickListener<MyMessage>()

        {
            @Override
            public void onMessageLongClick(View view, MyMessage message) {
                // do something
            }
        });

        mAdapter.setOnAvatarClickListener(new MsgListAdapter.OnAvatarClickListener<MyMessage>()

        {
            @Override
            public void onAvatarClick(MyMessage message) {
                // do something
            }
        });

        mAdapter.setMsgStatusViewClickListener(new MsgListAdapter.OnMsgStatusViewClickListener<MyMessage>()

        {
            @Override
            public void onStatusViewClick(MyMessage message) {
                // message status view click, resend or download here
            }
        });
        PullToRefreshLayout layout = mChatView.getPtrLayout();
        layout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PullToRefreshLayout layout) {
                loadNextPage();
            }
        });
        mAdapter.setOnLoadMoreListener(new MsgListAdapter.OnLoadMoreListener()

        {
            @Override
            public void onLoadMore(int page, int totalCount) {
//                loadNextPage();
            }
        });
        mChatView.setAdapter(mAdapter);
        mAdapter.getLayoutManager().scrollToPosition(0);
    }
}
