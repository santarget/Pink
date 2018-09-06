package com.ssy.pink.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ssy.pink.R;
import com.ssy.pink.adapter.SmallAdapter;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.common.Constants;
import com.ssy.pink.mvp.iview.IGroupDetailActivityView;
import com.ssy.pink.mvp.presenter.GroupDetailActivityPresenter;
import com.ssy.pink.view.dialog.DeletaDialog;
import com.ssy.pink.view.recyclerViewBase.LinerRecyclerItemDecoration;
import com.ssy.pink.view.recyclerViewBase.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupDetailActivity extends BaseActivity implements IGroupDetailActivityView, SmallAdapter.OnSlideMenuListener {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.cbSelectAll)
    CheckBox cbSelectAll;
    @BindView(R.id.cbAllAbnormal)
    CheckBox cbAllAbnormal;
    @BindView(R.id.recyclerView)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.flContent)
    FrameLayout flContent;
    @BindView(R.id.tvAccoutNumber)
    TextView tvAccoutNumber;
    @BindView(R.id.ivIp)
    ImageView ivIp;
    @BindView(R.id.llMulti)
    LinearLayout llMulti;

    GroupDetailActivityPresenter presenter;
    GroupInfo groupInfo;
    SmallAdapter adapter;
    DeletaDialog deleteDialog;
    boolean isMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        ButterKnife.bind(this);
        init();
        presenter = new GroupDetailActivityPresenter(this);
    }

    private void init() {
        groupInfo = (GroupInfo) getIntent().getSerializableExtra(Constants.INTENT_KEY_DATA);
        tvTitle.setText(groupInfo.getCustomerGroupName());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.addItemDecoration(new LinerRecyclerItemDecoration(this, OrientationHelper.VERTICAL));
        List<SmallInfo> datas = new ArrayList<>();
        datas.add(new SmallInfo("180222222", "哈哈哈"));
        datas.add(new SmallInfo("18033333", "用户123893723"));
        adapter = new SmallAdapter(this, datas);
        recyclerView.setAdapter(adapter);
        adapter.setMenuListener(this);
        setMultiMode(false);
    }

    @OnClick({R.id.aivBack, R.id.tvRight, R.id.tvDelete, R.id.tvMove, R.id.llAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.tvRight:
                setMultiMode(!isMulti);
                break;
            case R.id.tvDelete:
                break;
            case R.id.tvMove:
                break;
            case R.id.llAdd:
                Intent i = new Intent(this, AddSmallActivity.class);
                i.putExtra(Constants.INTENT_KEY_DATA, groupInfo);
                startActivity(i);
                break;
        }
    }

    private void showDeleteDialog(final SmallInfo info) {
        deleteDialog = new DeletaDialog.Builder(this)
                .setMessage("删除后不可恢复，需要重新添加绑定。确定要删除该账号吗？")
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
                        presenter.deleteSmall(info);
                    }
                })
                .create();
        deleteDialog.show();
    }

    @Override
    public void onDelete(int position) {
        showDeleteDialog(adapter.getData(position));
    }

    public void setMultiMode(boolean isMulti) {
        this.isMulti = isMulti;
        adapter.setMultiMode(isMulti);
        if (isMulti) {
            tvRight.setText(R.string.done);
            llMulti.setVisibility(View.VISIBLE);
        } else {
            tvRight.setText(R.string.manage);
            llMulti.setVisibility(View.GONE);
        }
    }
}
