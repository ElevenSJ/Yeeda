package com.sj.yeeda.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.sj.yeeda.R;
import com.sj.yeeda.activity.message.bean.MessageBean;
import com.sj.yeeda.activity.order.detail.OrderDetailActivity;
import com.sj.yeeda.base.TitleBaseActivity;

import java.util.List;

import butterknife.BindView;

/**
 * 创建时间: on 2018/4/15.
 * 创建人: 孙杰
 * 功能描述:
 */
public class MessageActivity extends TitleBaseActivity<MessageContract.Presenter> implements MessageContract.View {
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;

    MessageRyvAdapter mAdapter;

    @Override
    public MessageContract.Presenter getPresenter() {
        if (presenter == null) {
            presenter = new MessagePresenter(this);
        }
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_message;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleTxt("提醒通知");
        setTitleBg();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_AD), 1, 16, 16);
        dividerDecoration.setDrawLastItem(true);
        rylView.addItemDecoration(dividerDecoration);
        mAdapter = new MessageRyvAdapter(this);
        rylView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MessageActivity.this,OrderDetailActivity.class);
                intent.putExtra("id",mAdapter.getItem(position).getOrderId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().getMessage();
    }

    @Override
    public void updateMessageView(List<MessageBean> messageBeanList) {
        mAdapter.addAll(messageBeanList);
    }
}
