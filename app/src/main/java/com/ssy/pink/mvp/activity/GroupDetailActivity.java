package com.ssy.pink.mvp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ssy.pink.R;
import com.ssy.pink.adapter.SmallAdapter;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.common.Constants;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.EventWithObj;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.mvp.iview.IGroupDetailActivityView;
import com.ssy.pink.mvp.presenter.GroupDetailActivityPresenter;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.view.dialog.DeletaDialog;
import com.ssy.pink.view.dialog.GroupDialog;
import com.ssy.pink.view.recyclerViewBase.LinerRecyclerItemDecoration;
import com.ssy.pink.view.recyclerViewBase.SwipeRecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupDetailActivity extends BaseActivity implements IGroupDetailActivityView, SmallAdapter.OnSlideMenuListener,
        CompoundButton.OnCheckedChangeListener, OnRefreshListener {

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
    @BindView(R.id.tvMove)
    TextView tvMove;

    GroupDetailActivityPresenter presenter;
    GroupInfo groupInfo;
    SmallAdapter adapter;
    DeletaDialog deleteDialog;
    GroupDialog groupDialog;
    boolean isMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        ButterKnife.bind(this);
        init();
        presenter = new GroupDetailActivityPresenter(this).setGroupInfo(groupInfo);
        setMultiMode(false);
    }

    private void init() {
        groupInfo = (GroupInfo) getIntent().getSerializableExtra(Constants.INTENT_KEY_DATA);
        tvTitle.setText(groupInfo.getCustomergroupname());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.addItemDecoration(new LinerRecyclerItemDecoration(this, OrientationHelper.VERTICAL));
        adapter = new SmallAdapter(this, groupInfo.getAllSmallInfos());
        recyclerView.setAdapter(adapter);
        adapter.setMenuListener(this);
        tvAccoutNumber.setText(ListUtils.isEmpty(groupInfo.getAllSmallInfos()) ? "0" : String.valueOf(groupInfo.getAllSmallInfos().size()));
        cbSelectAll.setOnCheckedChangeListener(this);
        cbAllAbnormal.setOnCheckedChangeListener(this);
        refreshLayout.setOnRefreshListener(this);
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
                if (ListUtils.isEmpty(getSelectedList())) {
                    showToast(R.string.select_one);
                } else {
                    showMultiDeleteDialog();
                }
                break;
            case R.id.tvMove:
                if (ListUtils.isEmpty(getSelectedList())) {
                    showToast(R.string.select_one);
                } else {
                    showGroupDialog();
                }
                break;
            case R.id.llAdd:
//                Intent i = new Intent(this, AddSmallActivity.class);
                BindManager.getInstance().groupInfo = (GroupInfo) getIntent().getSerializableExtra(Constants.INTENT_KEY_DATA);
                Intent i = new Intent(this, BindSmallActivity2.class);
//                i.putExtra(Constants.INTENT_KEY_DATA, groupInfo);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.equals(cbSelectAll)) {
            if (isChecked) {
                cbAllAbnormal.setChecked(false);
            }
            presenter.selectAll(isChecked);
        } else if (buttonView.equals(cbAllAbnormal)) {
            if (isChecked) {
                cbSelectAll.setChecked(false);
                presenter.selectAbnormal();
            }

        }
    }

    @Override
    public SmallAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void updateData() {
//        if (TextUtils.isEmpty(groupInfo.getCustomergroupnum())) {
//            //默认分组
//            groupInfo = new GroupInfo();
//            groupInfo.setCustomergroupname("默认分组");
//            groupInfo.setAllSmallInfos(GroupManager.getInstance().smallInfos);
//        } else {
        for (GroupInfo group : GroupManager.getInstance().groupInfos) {
            if (group.equals(groupInfo)) {
                groupInfo = group;
                break;
            }
        }
//        }
        adapter = new SmallAdapter(this, groupInfo.getAllSmallInfos());
        adapter.setMenuListener(this);
        recyclerView.setAdapter(adapter);
        presenter.setGroupInfo(groupInfo);
        tvAccoutNumber.setText(String.valueOf(groupInfo.getAllSmallInfos().size()));
    }

    @Override
    public void finishRefresh() {
        refreshLayout.finishRefresh();
    }

    private void showDeleteDialog(final SmallInfo info) {
        deleteDialog = new DeletaDialog.Builder(this)
                .setMessage("删除账号后不可恢复，需要重新添加绑定。确定要删除吗？")
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
                        presenter.deleteSmall(info.getWeibosmallNumId());
                    }
                })
                .create();
        deleteDialog.show();
    }

    private void showMultiDeleteDialog() {
        deleteDialog = new DeletaDialog.Builder(this)
                .setMessage("删除账号后不可恢复，需要重新添加绑定。确定要删除吗？")
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
                        StringBuffer sb = new StringBuffer();
                        for (SmallInfo smallInfo : getSelectedList()) {
                            sb.append(smallInfo.getWeibosmallNumId()).append(";");
                        }
                        presenter.deleteSmall(sb.toString());
                        setMultiMode(false);
                    }
                })
                .create();
        deleteDialog.show();
    }

    private void showGroupDialog() {
        if (groupDialog == null) {
            groupDialog = new GroupDialog(this);
        }
        List<GroupInfo> groupInfos = new ArrayList<>();
        groupInfos.addAll(GroupManager.getInstance().groupInfos);
        groupInfos.remove(groupInfo);
        groupDialog.setDatas(groupInfos);
        groupDialog.show();

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
            presenter.selectAll(false);
        }
    }

    private List<SmallInfo> getSelectedList() {
        List<SmallInfo> selectedList = new ArrayList<>();
        for (SmallInfo smallInfo : groupInfo.getAllSmallInfos()) {
            if (smallInfo.isChecked()) {
                selectedList.add(smallInfo);
            }
        }
        return selectedList;
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        presenter.listSmall();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(EventWithObj event) {
        if (EventCode.MOVE_SMALL == event.eventCode) {
            GroupInfo targetGroup = (GroupInfo) event.obj;
            StringBuffer sb = new StringBuffer();
            for (SmallInfo smallInfo : getSelectedList()) {
                sb.append(smallInfo.getWeibosmallNumId()).append(";");
            }
            presenter.moveSmall(sb.toString(), targetGroup.getCustomergroupnum());
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(Integer eventCode) {
        switch (eventCode) {
            case EventCode.ADD_SMALL:
                presenter.listSmall();
                break;
        }
    }
}
