package com.ssy.pink.mvp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.ssy.pink.R;
import com.ssy.pink.adapter.GroupAdapter;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.base.BaseRecycleViewAdapter;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.common.Constants;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.iview.IGroupActivityView;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.mvp.presenter.GroupActivityPresenter;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.view.dialog.DeletaDialog;
import com.ssy.pink.view.recyclerViewBase.SpaceItemDecoration;
import com.ssy.pink.view.recyclerViewBase.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupActivity extends BaseActivity implements IGroupActivityView {
    public final int REQUEST_CODE_GROUP_DETAIL = 100;
    @BindView(R.id.recyclerView)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
//    @BindView(R.id.tvTotalCount)
//    TextView tvTotalCount;
//    @BindView(R.id.tvNormalCount)
//    TextView tvNormalCount;
//    @BindView(R.id.llDefault)
//    LinearLayout llDefault;

    private GroupActivityPresenter presenter;
    private GroupAdapter adapter;
    private DeletaDialog deleteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        ButterKnife.bind(this);
        init();
        initListener();
        presenter = new GroupActivityPresenter(this);
    }

    private void init() {
//        tvTotalCount.setText(String.valueOf(UserManager.getInstance().moneyInfo.getAllSmallNum()));
//        tvNormalCount.setText(String.valueOf(UserManager.getInstance().moneyInfo.getAllValidSmallNum()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.addItemDecoration(new SpaceItemDecoration());
        adapter = new GroupAdapter(this, GroupManager.getInstance().groupInfos);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.listGroup();
            }
        });
        adapter.setItemClickListen(new BaseRecycleViewAdapter.OnItemClickListen() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(GroupActivity.this, GroupDetailActivity.class);
                intent.putExtra(Constants.INTENT_KEY_DATA, adapter.getData(position));
                startActivityForResult(intent, REQUEST_CODE_GROUP_DETAIL);
            }
        });
        adapter.setMenuListener(new GroupAdapter.OnSlideMenuListener() {
            @Override
            public void onEdit(int position) {
                Intent intent = new Intent(GroupActivity.this, GroupEditActivity.class);
                intent.putExtra(Constants.INTENT_KEY_DATA, adapter.getData(position));
                startActivity(intent);
            }

            @Override
            public void onDelete(int position) {
                showDeleteDialog(adapter.getData(position));
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
//            case R.id.llDefault:
//                Intent intent = new Intent(GroupActivity.this, GroupDetailActivity.class);
//                startActivityForResult(intent, REQUEST_CODE_GROUP_DETAIL);
//                break;
        }
    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(EventCode.UPDATE_GROUPS);
        super.onBackPressed();
    }

    @Override
    public void loadGroups() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void finishRefresh() {
        refreshLayout.finishRefresh();
    }

    private void showDeleteDialog(final GroupInfo info) {
        deleteDialog = new DeletaDialog.Builder(this)
                .setMessage("删除分组后，组内的小号也会一并删除，确定继续吗？")
                .setNegativeButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        presenter.deleteGroup(info);
                    }
                })
                .create();
        deleteDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GROUP_DETAIL) {
            presenter.listSmall();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(Integer eventCode) {
        switch (eventCode) {
            case EventCode.ADD_GROUP:
                adapter.notifyDataSetChanged();
                break;
            case EventCode.EDIT_GROUP:
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
