package com.sj.yeeda.activity.venue;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sj.yeeda.R;
import com.sj.yeeda.activity.venue.bean.VenueBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:InvoiceDialog
 */
public class VenueDialog extends Dialog {

    @BindView(R.id.edt_bill_tel)
    EditText edtVenuePhone;
    @BindView(R.id.edt_bill_company_address)
    EditText edtVenueContact;
    @BindView(R.id.edt_bill_num)
    EditText edtVenueAddress;
    @BindView(R.id.edt_bill_head)
    EditText edtVenueName;
    @BindView(R.id.cb_default)
    CheckBox cbDefault;
    private boolean isCancelable = false;
    private boolean isCanceledOnTouchOutside = false;

    private VenuePresenter present;
    private VenueBean venueBean;

    public VenueDialog(Context context) {
        this(context, R.style.Transparentdialog);
    }

    public VenueDialog(Context context, VenuePresenter basePresenter) {
        this(context, R.style.Transparentdialog);
        this.present = basePresenter;
    }

    public VenueDialog(Context context, int theme) {
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

        View layoutView = getLayoutInflater().inflate(R.layout.dialog_add_venue, null);
        setContentView(layoutView);
        ButterKnife.bind(this, layoutView);

    }

    public void show(VenueBean venueBean) {
        this.show();
        this.venueBean = venueBean;
        if (TextUtils.isEmpty(venueBean.getIsDefault()) || venueBean.getIsDefault().equals("1")) {
            cbDefault.setChecked(true);
        } else {
            cbDefault.setChecked(false);
        }
        edtVenueName.setText(venueBean.getName());
        edtVenueAddress.setText(venueBean.getAddress());
        edtVenueContact.setText(venueBean.getContact());
        edtVenuePhone.setText(venueBean.getContactPhone());

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

    @OnClick({R.id.txt_add, R.id.img_delete})
    public void onClickView(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.txt_add:
                if (venueBean != null) {
                    present.edtVenueInfo(new VenueBean.Builder()
                            .id(venueBean.getId())
                            .uid(venueBean.getUid())
                            .name(edtVenueName.getText().toString())
                            .address(edtVenueAddress.getText().toString())
                            .contact(edtVenueContact.getText().toString())
                            .contactPhone(edtVenuePhone.getText().toString())
                            .isDefault(cbDefault.isChecked()?"1":"0")
                            .build());
                } else {
                    present.addVenueInfo(new VenueBean.Builder()
                            .name(edtVenueName.getText().toString())
                            .address(edtVenueAddress.getText().toString())
                            .contact(edtVenueContact.getText().toString())
                            .contactPhone(edtVenuePhone.getText().toString())
                            .isDefault(cbDefault.isChecked()?"1":"0")
                            .build());
                }
                break;
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
        venueBean = null;
        edtVenueName.setText("");
        edtVenueAddress.setText("");
        edtVenueContact.setText("");
        edtVenuePhone.setText("");
    }
}