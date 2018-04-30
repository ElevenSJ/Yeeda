package com.sj.yeeda.activity.order.detail;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sj.yeeda.R;
import com.sj.yeeda.Utils.FileUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:AttchmentDialog
 */
public class AttchmentWebDialog extends Dialog {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.layout_web)
    FrameLayout layoutWeb;
    private boolean isCancelable = false;
    private boolean isCanceledOnTouchOutside = false;

    Context context;
    String title;
    int drawableId;

    WebView webview;

    public AttchmentWebDialog(Context context, String title, int drawableId) {
        this(context, R.style.Transparentdialog);
        this.context = context;
        this.title = title;
        this.drawableId = drawableId;
    }

    public AttchmentWebDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(isCancelable);
        setCanceledOnTouchOutside(isCanceledOnTouchOutside);

        View layoutView = getLayoutInflater().inflate(R.layout.dialog_order_attchment_web, null);
        setContentView(layoutView);
        ButterKnife.bind(this, layoutView);


        Drawable drawableLeft = getContext().getResources().getDrawable(drawableId);// 找到资源图片
        drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());// 设置图片宽高
        txtTitle.setCompoundDrawables(drawableLeft, null, null, null);// 设置到控件中
        txtTitle.setText(title);

        webview = new WebView(context);
        layoutWeb.addView(webview);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);//启用js
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//js和android交互
        settings.setAppCachePath(FileUtils.CACHE_NET); //设置缓存的指定路径
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setAppCacheEnabled(true); //设置H5的缓存打开,默认关闭
        settings.setUseWideViewPort(true);//设置webview自适应屏幕大小
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//设置，可能的话使所有列的宽度不超过屏幕宽度
        settings.setLoadWithOverviewMode(true);//设置webview自适应屏幕大小
        settings.setDomStorageEnabled(true);//设置可以使用localStorage
        settings.setSupportZoom(false);//关闭zoom按钮
        settings.setBuiltInZoomControls(false);//关闭zoom
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }
        });
    }

    public void show(String url) {
        this.show();
        if (TextUtils.isEmpty(url)) {
            return;
        }
        webview.loadUrl(url);
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().getDecorView().setPadding(60, 60, 60, 60);
        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void setCancelable(boolean cancelable) {
        isCancelable = cancelable;
    }

    @Override
    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        isCanceledOnTouchOutside = canceledOnTouchOutside;
    }

    @OnClick(R.id.img_delete)
    public void onClickView(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_delete:
                dismiss();
                break;

        }
    }

    @Override
    public void dismiss() {
        clear();
        super.dismiss();
    }

    private void clear() {
        webview.destroy();
    }
}