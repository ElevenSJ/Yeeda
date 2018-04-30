package com.sj.yeeda.activity.order.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.Utils.SPFileUtils;
import com.sj.yeeda.activity.invoice.InvoiceDialog;
import com.sj.yeeda.activity.order.bean.OrderAccessoryBean;
import com.sj.yeeda.activity.order.bean.OrderDetailBean;
import com.sj.yeeda.activity.order.bean.OrderSolutionBuildBean;
import com.sj.yeeda.activity.order.detail.bean.AttchmentRyvItem;
import com.sj.yeeda.activity.pay.PayActivity;
import com.sj.yeeda.base.TitleBaseActivity;
import com.sj.yeeda.im.IMManagerImpl;
import com.yuntongxun.plugin.im.manager.IMPluginManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/4/14.
 * 创建人: 孙杰
 * 功能描述:
 */
public class OrderDetailActivity extends TitleBaseActivity<OrderDetailContract.Presenter> implements OrderDetailContract.View {

    String id;
    @BindView(R.id.txt_invoice_info)
    TextView txtInvoiceInfo;
    @BindView(R.id.txt_rent_company)
    TextView txtRentCompany;
    @BindView(R.id.txt_rent_num)
    TextView txtRentNum;
    @BindView(R.id.txt_rent_price)
    TextView txtRentPrice;
    @BindView(R.id.txt_rent_names)
    TextView txtRentNames;
    @BindView(R.id.txt_order_id)
    TextView txtOrderId;
    @BindView(R.id.txt_order_time)
    TextView txtOrderTime;
    @BindView(R.id.txt_order_state)
    TextView txtOrderState;
    @BindView(R.id.txt_order_solution_icon)
    ImageView txtOrderSolutionIcon;
    @BindView(R.id.txt_order_solution_name)
    TextView txtOrderSolutionName;
    @BindView(R.id.txt_order_solution_id)
    TextView txtOrderSolutionId;
    @BindView(R.id.img_designer_icon)
    ImageView imgDesignerIcon;
    @BindView(R.id.txt_designer_name)
    TextView txtDesignerName;
    @BindView(R.id.txt_designer_phone)
    TextView txtDesignerPhone;
    @BindView(R.id.txt_venue_info)
    TextView txtVenueInfo;
    @BindView(R.id.bt_service)
    Button btService;
    @BindView(R.id.bt_to_pay)
    Button btToPay;
    @BindView(R.id.txt_order_price)
    TextView txtOrderPrice;


    OrderDetailBean orderDetailBean;
    @BindView(R.id.layout_attachment)
    LinearLayout layoutAttachment;

    AttchmentDialog attchmentDialog;
    AttchmentWebDialog attchmentWebDialog;

    @Override
    public OrderDetailContract.Presenter getPresenter() {
        if (presenter == null) {
            presenter = new OrderDetailPresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        super.initView();
        id = getIntent().getStringExtra("id");
        setTitleBg();
        setTitleTxt("订单详情");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().getOrderDetail(id);
    }

    @OnClick({R.id.bt_to_pay, R.id.bt_service, R.id.img_goujian, R.id.img_attchment, R.id.img_install})
    public void onViewClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bt_to_pay:
                if (orderDetailBean != null && orderDetailBean.getOrder() != null) {
                    Intent intent = new Intent(this, PayActivity.class);
                    intent.putExtra("orderId", orderDetailBean.getOrder().getId());
                    intent.putExtra("allPrice", orderDetailBean.getOrder().getMoney());
                    startActivityForResult(intent, 101);
                } else {
                    ToastUtils.showShortToast("未查询到订单信息");
                }
                break;
            case R.id.bt_service:
//                Intent intent = new Intent();
//                intent.setClass( view.getContext(), ServiceCustomActivity.class);
//                intent.putExtra("connectedId",(String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT,SPFileUtils.KEFU_ID,""));
//                view.getContext(). startActivity(intent);
                IMManagerImpl.getInstance().setOrderId(orderDetailBean.getOrder().getId());
                IMManagerImpl.startChatting(view.getContext(),(String) SPUtils.getInstance().getSharedPreference(SPFileUtils.FILE_IM_ACCOUNT, SPFileUtils.DINGDAN_ID, ""));
                break;
            case R.id.img_goujian:
                showProgress();
                final List<AttchmentRyvItem> attchmentRyvItems = new ArrayList<>();
                if (orderDetailBean != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (orderDetailBean.getBuild().size() > 0) {
                                for (OrderSolutionBuildBean build : orderDetailBean.getBuild()) {
                                    attchmentRyvItems.add(new AttchmentRyvItem(build.getBuildId(), build.getBuild().getBuildName(), build.getNums()));
                                }
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dismissProgress();
                                    if (attchmentDialog == null) {
                                        attchmentDialog = new AttchmentDialog(OrderDetailActivity.this, "构件", R.drawable.img_install);
                                    }
                                    attchmentDialog.show(attchmentRyvItems);
                                }
                            });
                        }
                    }).start();
                } else {
                    ToastUtils.showShortToast("未获取到订单详情");
                }
                break;
            case R.id.img_attchment:
                showProgress();
                final List<AttchmentRyvItem> attchmentRyvList = new ArrayList<>();
                if (orderDetailBean != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (orderDetailBean.getAccessory().size() > 0) {
                                for (OrderAccessoryBean accessoryBean : orderDetailBean.getAccessory()) {
                                    attchmentRyvList.add(new AttchmentRyvItem(accessoryBean.getAccessoryStrId(), accessoryBean.getAccessory() == null ? "附件" : accessoryBean.getAccessory().getName(), accessoryBean.getNums()));
                                }
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dismissProgress();
                                    if (attchmentDialog == null) {
                                        attchmentDialog = new AttchmentDialog(OrderDetailActivity.this, "附件", R.drawable.img_install);
                                    }
                                    attchmentDialog.show(attchmentRyvList);
                                }
                            });
                        }
                    }).start();
                } else {
                    ToastUtils.showShortToast("未获取到订单详情");
                }
                break;
            case R.id.img_install:
                if (orderDetailBean != null) {
                    if (attchmentWebDialog == null) {
                        attchmentWebDialog = new AttchmentWebDialog(OrderDetailActivity.this, "安装说明", R.drawable.img_install);
                    }
                    attchmentWebDialog.show(orderDetailBean.getScheme()==null?null:orderDetailBean.getScheme().getInstallation());
                }else{
                    ToastUtils.showShortToast("未获取到订单详情");
                }
                break;
            default:
        }

    }

    @Override
    public void updateOrderDetailView(String type, OrderDetailBean orderDetailBean) {
        if (!TextUtils.isEmpty(type) && type.equals("2")) {
            layoutAttachment.setVisibility(View.VISIBLE);
        }else{
            layoutAttachment.setVisibility(View.GONE);
        }
        this.orderDetailBean = orderDetailBean;
        if (orderDetailBean == null) {
            ToastUtils.showShortToast("未获取到订单详情");
            btToPay.setEnabled(false);
            return;
        }
        if (orderDetailBean != null) {
            if (orderDetailBean.getScheme() != null) {
                ImageUtils.loadImageWithError(orderDetailBean.getScheme().getSchemeIcon(), R.mipmap.ic_launcher, txtOrderSolutionIcon);
                txtOrderSolutionName.setText(orderDetailBean.getScheme().getSchemeName());
                txtOrderSolutionId.setText("id:" + orderDetailBean.getScheme().getId() + " | 面积:" + orderDetailBean.getScheme().getAreaCategory());
            }
            if (orderDetailBean.getOrder() != null) {
                txtOrderPrice.setText("¥" + orderDetailBean.getOrder().getMoney());
                txtOrderId.setText("No." + orderDetailBean.getOrder().getId());
                txtOrderTime.setText(orderDetailBean.getOrder().getCreateTime());
                txtOrderState.setText(orderDetailBean.getOrder().getStatus().equals("0") ? "未支付" : "已支付");
                btToPay.setEnabled(orderDetailBean.getOrder().getStatus().equals("0"));
            }
            if (orderDetailBean.getDesigner() != null) {
                ImageUtils.loadImageWithError(orderDetailBean.getDesigner().getIcon(), R.drawable.img_personal_center_circle, imgDesignerIcon);
                txtDesignerName.setText(orderDetailBean.getDesigner().getUserName());
                txtDesignerPhone.setText(orderDetailBean.getDesigner().getPhone());
            }
            String venueInfo = "";
            if (orderDetailBean.getVenue() != null) {
                venueInfo += orderDetailBean.getVenue().getName();
                venueInfo += "\n" + orderDetailBean.getVenue().getAddress();
                venueInfo += "\n" + orderDetailBean.getVenue().getContact();
                venueInfo += "\n" + orderDetailBean.getVenue().getContactPhone();
            }
            if (orderDetailBean.getConstructor() != null) {
                venueInfo += "\n" + orderDetailBean.getConstructor().getUserName();
                venueInfo += "\n" + orderDetailBean.getConstructor().getPhone();
            }
            txtVenueInfo.setText(venueInfo);
            if (orderDetailBean.getRent() != null && orderDetailBean.getRent().size() > 0) {
                String rentNames = "";
                String rentPrices = "";
                String rentNums = "";
                String rentCompanys = "";
                for (int i = 0; i < orderDetailBean.getRent().size(); i++) {
                    if (i == 0) {
                        rentNames = orderDetailBean.getRent().get(i).getRentEquipmentName();
                        rentPrices = "¥" + orderDetailBean.getRent().get(i).getMoney();
                        rentNums = orderDetailBean.getRent().get(i).getNum() + "";
                        rentCompanys = orderDetailBean.getRent().get(i).getRentEquipmentId();
                    } else {
                        rentNames += "\n" + orderDetailBean.getRent().get(i).getId();
                        rentPrices += "\n" + "¥" + orderDetailBean.getRent().get(i).getMoney();
                        rentNums += "\n" + orderDetailBean.getRent().get(i).getNum() + "";
                        rentCompanys += "\n" + orderDetailBean.getRent().get(i).getRentEquipmentId();
                    }
                }
                txtRentNames.setText(rentNames);
                txtRentPrice.setText(rentPrices);
                txtRentNum.setText(rentNums);
                txtRentCompany.setText(rentCompanys);
            }

            if (orderDetailBean.getInvoice() != null) {
                txtInvoiceInfo.setText((orderDetailBean.getInvoice().getIsVatInvoice().equals("1") ? "增值税发票" : "普通发票") + "\n" + orderDetailBean.getInvoice().getAccount() + "\n" + orderDetailBean.getInvoice().getPhone() + "\n" + orderDetailBean.getInvoice().getExpressAddress());
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 101:
                    getPresenter().getOrderDetail(id);
                    break;
                default:
            }
        }
    }
}
