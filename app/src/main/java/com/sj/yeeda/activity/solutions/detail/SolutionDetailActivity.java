package com.sj.yeeda.activity.solutions.detail;

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
import com.sj.module_lib.widgets.SlideDetailsLayout;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.solutions.detail.bean.SolutionDetailBean;
import com.sj.yeeda.base.TitleBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SolutionDetailActivity extends TitleBaseActivity<SolutionDetailContract.Presenter> implements SolutionDetailContract.View {
    @BindView(R.id.slidedetails_front)
    FrameLayout slidedetailsFront;
    @BindView(R.id.slidedetails_behind)
    FrameLayout slidedetailsBehind;
    @BindView(R.id.slidedetails)
    SlideDetailsLayout slidedetails;
    @BindView(R.id.bt_sure)
    Button btSure;

    String id;

    WebView webView;

//    @BindView(R.id.viewPager)
//    ViewPager viewPager;
//    @BindView(R.id.txt_img_num)
//    TextView txtImgNum;
//    @BindView(R.id.txt_designer_value)
//    TextView txtDesignerValue;
//    @BindView(R.id.txt_solutian_name)
//    TextView txtSolutianName;
//    @BindView(R.id.txt_installation)
//    TextView txtInstallation;
//    @BindView(R.id.txt_solution_info)
//    TextView txtSolutionInfo;
//    @BindView(R.id.img_designer_icon)
//    ImageView imgDesignerIcon;
//    @BindView(R.id.ratingBar)
//    RatingBar ratingBar;
//    @BindView(R.id.txt_solution_price)
//    TextView txtSolutionPrice;

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

        View detailView = getLayoutInflater().inflate(R.layout.solution_detail_info_layout, null);
        ButterKnife.bind(this, detailView);
        slidedetailsFront.addView(detailView);


        webView = new WebView(this);
        slidedetailsBehind.addView(webView);
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1) {
            new Object() {
                public void setLoadWithOverviewMode(boolean overview) {
                    settings.setLoadWithOverviewMode(overview);
                }
            }.setLoadWithOverviewMode(true);
        }
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
    }


    @Override
    public void updateSolutionDetail(final SolutionDetailBean solutionDetailBean) {
        if (solutionDetailBean != null) {
//            txtImgNum.setText(solutionDetailBean.getImgs().size());
//            if (solutionDetailBean.getScheme() != null) {
//                txtDesignerValue.setText(solutionDetailBean.getScheme().getUserName());
//                txtSolutianName.setText(solutionDetailBean.getScheme().getSchemeName());
//                txtSolutionInfo.setText("id:" + solutionDetailBean.getScheme().getId() + " | " + "面积:" + solutionDetailBean.getScheme().getAreaCategory());
//                txtInstallation.setText(solutionDetailBean.getScheme().getInstallation());
//                ImageUtils.loadImageWithError(solutionDetailBean.getScheme().getIcon(), R.drawable.img_personal_center_circle, imgDesignerIcon);
//                txtSolutionPrice.setText(solutionDetailBean.getScheme().getSchemePrice());
//            }
//            getWindow().getDecorView().post(new Runnable() {
//                @Override
//                public void run() {
//                    webView.loadUrl(solutionDetailBean.getScheme().getDetailed());
//
//                }
//            });
        }
    }
}
