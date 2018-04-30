package com.sj.yeeda.activity.order.detail;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.order.detail.bean.AttchmentRyvItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:AttchmentDialog
 */
public class AttchmentDialog extends Dialog {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    private boolean isCancelable = false;
    private boolean isCanceledOnTouchOutside = false;

    Context context;
    String title;
    int drawableId;
    AttchmentRyvAdapter mAdapter;

    public AttchmentDialog(Context context,String title,int drawableId) {
        this(context, R.style.Transparentdialog);
        this.context = context;
        this.title = title;
        this.drawableId = drawableId;
    }

    public AttchmentDialog(Context context, int theme) {
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

        View layoutView = getLayoutInflater().inflate(R.layout.dialog_order_attchment, null);
        setContentView(layoutView);
        ButterKnife.bind(this, layoutView);


        Drawable drawableLeft = getContext().getResources().getDrawable(drawableId);// 找到资源图片
        drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());// 设置图片宽高
        txtTitle.setCompoundDrawables(drawableLeft, null, null, null);// 设置到控件中
        txtTitle.setText(title+"表");
        name.setText(title+"名称");

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(context.getResources().getColor(R.color.gray_AD), 1, 16, 16);
        dividerDecoration.setDrawLastItem(false);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new AttchmentRyvAdapter(context);
        rylView.setAdapter(mAdapter);
    }

    public void show(List<AttchmentRyvItem> items) {
        this.show();
        if (items == null||items.isEmpty()){
            return;
        }
        mAdapter.addAll(items);
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
        mAdapter.removeAll();
    }
}