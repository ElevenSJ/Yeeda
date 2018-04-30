package com.sj.yeeda.activity.user.register;

import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.sj.yeeda.R;
import com.sj.yeeda.Utils.FileUtils;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;

/**
 * 创建时间: on 2018/4/26.
 * 创建人: 孙杰
 * 功能描述:
 */
public class RegisterHtmlActivity extends TitleBaseActivity {
    @BindView(R.id.webview_layout)
    FrameLayout webviewLayout;
    WebView webview;
    @Override
    public Object getPresenter() {
        return null;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_register_html;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("注册协议");
        setTitleBg();

        String url = getIntent().getStringExtra("url");

        webview = new WebView(this);
        webviewLayout.addView(webview);
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
        webview.loadUrl(url);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.destroy();
    }
}
