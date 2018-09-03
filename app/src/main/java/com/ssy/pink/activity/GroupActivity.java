package com.ssy.pink.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.FrameLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.ssy.pink.R;
import com.ssy.pink.adapter.GroupAdapter;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.base.BaseRecycleViewAdapter;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.iview.IGroupActivityView;
import com.ssy.pink.view.recyclerViewBase.SpaceItemDecoration;
import com.ssy.pink.view.recyclerViewBase.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupActivity extends BaseActivity implements IGroupActivityView {

    @BindView(R.id.recyclerView)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.flContent)
    FrameLayout flContent;

    private List<GroupInfo> datas = new ArrayList<>();
    private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        ButterKnife.bind(this);
        init();
        initListener();
    }

    private void init() {
        //设置RecyclerView垂直布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        //设置分割线
//        recyclerView.addItemDecoration(new LinerRecyclerItemDecoration(this, OrientationHelper.VERTICAL));
        recyclerView.addItemDecoration(new SpaceItemDecoration());
        datas.add(new GroupInfo());
        datas.add(new GroupInfo());
        adapter = new GroupAdapter(this, datas);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
        adapter.setItemClickListen(new BaseRecycleViewAdapter.OnItemClickListen() {
            @Override
            public void onItemClick(View view, int position) {
                showToast(position + "");
                startActivity(new Intent(GroupActivity.this, GroupDetailActivity.class));
            }
        });
        adapter.setMenuListener(new GroupAdapter.OnSlideMenuListener() {
            @Override
            public void onEdit(int position) {
                showToast(position + " onEdit");
                Intent intent = new Intent(GroupActivity.this, GroupEditActivity.class);
                startActivity(intent);
            }

            @Override
            public void onDelete(int position) {
                showToast(position + " onDelete");
            }
        });
    }


    @OnClick({R.id.aivBack, R.id.aivAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.aivAdd:
                startActivity(new Intent(GroupActivity.this, GroupAddActivity.class));
                break;
        }
    }

}
