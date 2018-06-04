package com.sj.yeeda.activity.solutions.detail;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.Utils.FileUtils;
import com.sj.yeeda.activity.solutions.adapter.ViewPageImageAdapter;
import com.sj.yeeda.activity.solutions.detail.bean.SolutionDetailBean;
import com.sj.yeeda.activity.solutions.order.SolutionOrderActivity;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SolutionDetailActivity extends TitleBaseActivity<SolutionDetailContract.Presenter> implements SolutionDetailContract.View {
    @BindView(R.id.bt_sure)
    Button btSure;

    String id;

    WebView webview;
    @BindView(R.id.slidedetails_behind)
    FrameLayout slidedetailsBehind;

    SolutionDetailBean solutionDetailBean;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.txt_img_num)
    TextView txtImgNum;
    @BindView(R.id.txt_designer_value)
    TextView txtDesignerValue;
    @BindView(R.id.txt_solutian_name)
    TextView txtSolutianName;
    @BindView(R.id.txt_installation)
    TextView txtInstallation;
    @BindView(R.id.txt_solution_info)
    TextView txtSolutionInfo;
    @BindView(R.id.img_designer_icon)
    ImageView imgDesignerIcon;
    @BindView(R.id.txt_designer_name)
    TextView txtDesignerName;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.txt_solution_price_name)
    TextView txtSolutionPriceName;
    @BindView(R.id.txt_solution_price)
    TextView txtSolutionPrice;

    ViewPageImageAdapter imageAdapter;

    @Override
    public SolutionDetailContract.Presenter getPresenter() {
        if (presenter == null) {
            presenter = new SolutionDetailPresent(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_solution_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        getPresenter().getSolutionDetail(id);
    }


    @Override
    public void initView() {
        super.initView();
        setTitleTxt("方案详情");
        setTitleBg();

        imageAdapter = new ViewPageImageAdapter(this);
        // 更新下标
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                txtImgNum.setText((arg0 + 1) + "/" + solutionDetailBean.getImgs().size() + "");
            }

        });
        viewPager.setAdapter(imageAdapter);


        webview = new WebView(this);
        slidedetailsBehind.addView(webview);
//        final WebSettings settings = webView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setSupportZoom(true);
//        settings.setUseWideViewPort(true);
//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
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
        //设置webView里字体大小
        settings.setTextZoom(100);
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

    @OnClick(R.id.bt_sure)
    public void onViewClick(View view) {
        if (solutionDetailBean != null) {
            Intent intent = new Intent(this, SolutionOrderActivity.class);
            intent.putExtra("data", solutionDetailBean.getScheme());
            startActivity(intent);
        } else {
            ToastUtils.showShortToast("未获取到方案详情");
        }
    }


    @Override
    public void updateSolutionDetail(final SolutionDetailBean solutionDetailBean) {
        this.solutionDetailBean = solutionDetailBean;
        if (solutionDetailBean != null) {
            btSure.setEnabled(true);
            getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {
                    webview.loadUrl(solutionDetailBean.getScheme().getDetailed());

                }
            });
            imageAdapter.setData(solutionDetailBean.getImgs());
            if (solutionDetailBean.getImgs().size() == 0) {
                txtImgNum.setVisibility(View.GONE);
            } else {
                txtImgNum.setVisibility(View.VISIBLE);
                txtImgNum.setText("1/" + solutionDetailBean.getImgs().size());
            }

            if (solutionDetailBean.getScheme() != null) {
                Drawable drawableLeft = getResources().getDrawable(solutionDetailBean.getUser().getSex().equals("0") ? R.drawable.img_female : R.drawable.img_male);// 找到资源图片
                drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
                txtDesignerValue.setCompoundDrawables(drawableLeft, null, null, null);// 设置到控件中
                txtDesignerValue.setText(solutionDetailBean.getUser().getUserName());
                txtSolutianName.setText(solutionDetailBean.getScheme().getSchemeName());
                txtSolutionInfo.setText("id:" + solutionDetailBean.getScheme().getId() + " | " + "面积:" + solutionDetailBean.getScheme().getAreaCategory());
                txtInstallation.setText(solutionDetailBean.getScheme().getInstallation());
                ImageUtils.loadImageWithError(solutionDetailBean.getScheme().getIcon(), R.drawable.img_personal_center_circle, imgDesignerIcon);
//                txtSolutionPrice.setText("¥" + solutionDetailBean.getScheme().getSchemePrice());
            }
            ratingBar.setRating(30);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.destroy();
    }
}
