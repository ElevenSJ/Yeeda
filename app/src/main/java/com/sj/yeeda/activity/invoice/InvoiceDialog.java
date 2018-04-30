package com.sj.yeeda.activity.invoice;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sj.module_lib.utils.ToastUtils;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.invoice.bean.InvoiceBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:InvoiceDialog
 */
public class InvoiceDialog extends Dialog {

    @BindView(R.id.edt_bill_box_address)
    EditText edtBillBoxAddress;
    @BindView(R.id.edt_bill_email)
    EditText edtBillEmail;
    @BindView(R.id.edt_bill_bank_num)
    EditText edtBillBankNum;
    @BindView(R.id.edt_bill_bank_name)
    EditText edtBillBankName;
    @BindView(R.id.edt_bill_tel)
    EditText edtBillTel;
    @BindView(R.id.edt_bill_company_address)
    EditText edtBillCompanyAddress;
    @BindView(R.id.edt_bill_num)
    EditText edtBillNum;
    @BindView(R.id.rdbt_bill_type1)
    RadioButton rdbtBillType1;
    @BindView(R.id.rdbt_bill_type2)
    RadioButton rdbtBillType2;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.edt_bill_head)
    EditText edtBillHead;
    @BindView(R.id.edt_bill_company)
    EditText edtBillCompany;
    @BindView(R.id.edt_bill_connect_phone)
    EditText edtBillConnectPhone;
    @BindView(R.id.edt_bill_connect)
    EditText edtBillConnect;
    private boolean isCancelable = false;
    private boolean isCanceledOnTouchOutside = false;

    private InvoiceContract.Presenter present;
    private InvoiceBean billBean;

    public InvoiceDialog(Context context) {
        this(context, R.style.Transparentdialog);
    }

    public InvoiceDialog(Context context, InvoiceContract.Presenter basePresenter) {
        this(context, R.style.Transparentdialog);
        this.present = basePresenter;
    }

    public InvoiceDialog(Context context, int theme) {
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

        View layoutView = getLayoutInflater().inflate(R.layout.dialog_add_bill, null);
        setContentView(layoutView);
        ButterKnife.bind(this, layoutView);

//        txtAdd.setOnClickListener(this);
//        imgDelete.setOnClickListener(this);
    }

    public void show(InvoiceBean billBean) {
        this.show();
        this.billBean = billBean;
        edtBillCompany.setText(billBean.getCompanyName());
        edtBillConnectPhone.setText(billBean.getContactPhone());
        edtBillConnect.setText(billBean.getContact());
        edtBillBoxAddress.setText(billBean.getExpressAddress());
        edtBillEmail.setText(billBean.getEmail());
        edtBillBankNum.setText(billBean.getAccount());
        edtBillBankName.setText(billBean.getBank());
        edtBillTel.setText(billBean.getPhone());
        edtBillCompanyAddress.setText(billBean.getWorkAddress());
        edtBillNum.setText(billBean.getTariff());
        edtBillHead.setText(billBean.getTitle());
        if (TextUtils.isEmpty(billBean.getIsVatInvoice()) || billBean.getIsVatInvoice().equals("0")) {
            rdbtBillType1.setChecked(false);
        } else {
            rdbtBillType2.setChecked(true);
        }

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
                if (!checkNeedEdt()){
                    ToastUtils.showShortToast("有必填项未填写");
                    return;
                }
                if (billBean != null) {
                    present.edtBillInfo(new InvoiceBean.Builder().phone(edtBillTel.getText().toString())
                            .expressAddress(edtBillBoxAddress.getText().toString())
                            .account(edtBillBankNum.getText().toString())
                            .bank(edtBillBankName.getText().toString())
                            .email(edtBillEmail.getText().toString())
                            .id(billBean.getId())
                            .isVatInvoice(radioGroup.getCheckedRadioButtonId() == R.id.rdbt_bill_type1 ? "0" : "1")
                            .tariff(edtBillNum.getText().toString())
                            .title(edtBillHead.getText().toString())
                            .uid(billBean.getUid())
                            .workAddress(edtBillCompanyAddress.getText().toString())
                            .contact(edtBillConnect.getText().toString())
                            .contactPhone(edtBillConnectPhone.getText().toString())
                            .companyName(edtBillCompany.getText().toString())
                            .build());
                } else {
                    present.addBillInfo(new InvoiceBean.Builder().phone(edtBillTel.getText().toString())
                            .expressAddress(edtBillBoxAddress.getText().toString())
                            .account(edtBillBankNum.getText().toString())
                            .bank(edtBillBankName.getText().toString())
                            .email(edtBillEmail.getText().toString())
                            .isVatInvoice(radioGroup.getCheckedRadioButtonId() == R.id.rdbt_bill_type1 ? "0" : "1")
                            .tariff(edtBillNum.getText().toString())
                            .title(edtBillHead.getText().toString())
                            .workAddress(edtBillCompanyAddress.getText().toString())
                            .contact(edtBillConnect.getText().toString())
                            .contactPhone(edtBillConnectPhone.getText().toString())
                            .companyName(edtBillCompany.getText().toString())
                            .build());
                }
                break;
            case R.id.img_delete:
                dismiss();
                break;

        }
    }

    private boolean checkNeedEdt() {
        boolean allNeed = true;
        if (TextUtils.isEmpty(edtBillHead.getText().toString())){
            allNeed = false;
        }else if(TextUtils.isEmpty(edtBillNum.getText().toString())){
            allNeed = false;
        }else if(TextUtils.isEmpty(edtBillCompanyAddress.getText().toString())){
            allNeed = false;
        }else if(TextUtils.isEmpty(edtBillTel.getText().toString())){
            allNeed = false;
        }else if(TextUtils.isEmpty(edtBillBankName.getText().toString())){
            allNeed = false;
        }else if(TextUtils.isEmpty(edtBillBankNum.getText().toString())){
            allNeed = false;
        }else if(TextUtils.isEmpty(edtBillEmail.getText().toString())){
            allNeed = false;
        }else if(TextUtils.isEmpty(edtBillBoxAddress.getText().toString())){
            allNeed = false;
        }
        return allNeed;
    }

    @Override
    public void dismiss() {
        clear();
        super.dismiss();
    }

    private void clear() {
        billBean = null;
        edtBillCompany.setText("");
        edtBillConnectPhone.setText("");
        edtBillConnect.setText("");
        edtBillBoxAddress.setText("");
        edtBillEmail.setText("");
        edtBillBankNum.setText("");
        edtBillBankName.setText("");
        edtBillTel.setText("");
        edtBillCompanyAddress.setText("");
        edtBillNum.setText("");
        edtBillHead.setText("");
        rdbtBillType1.setChecked(true);
    }
}