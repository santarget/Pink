package com.ssy.pink.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ssy.pink.R;
import com.ssy.pink.adapter.GroupAdapter;
import com.ssy.pink.adapter.SmallAdapter;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.common.Constants;
import com.ssy.pink.iview.IGroupDetailActivityView;
import com.ssy.pink.presenter.GroupDetailActivityPresenter;
import com.ssy.pink.view.recyclerViewBase.LinerRecyclerItemDecoration;
import com.ssy.pink.view.recyclerViewBase.SpaceItemDecoration;
import com.ssy.pink.view.recyclerViewBase.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupDetailActivity extends BaseActivity implements IGroupDetailActivityView {

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

    GroupDetailActivityPresenter presenter;
    GroupInfo groupInfo;
    SmallAdapter adapter;

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
        tvRight.setText(R.string.done);
        //设置RecyclerView垂直布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        //设置分割线
        recyclerView.addItemDecoration(new LinerRecyclerItemDecoration(this, OrientationHelper.VERTICAL));
//        adapter = new SmallAdapter(this, groupInfo.getAllSmallInfos());
        List<SmallInfo> datas = new ArrayList<>();
        datas.add(new SmallInfo("180222222", "哈哈哈"));
        datas.add(new SmallInfo("18033333", "用户123893723"));
        adapter = new SmallAdapter(this, datas);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.aivBack, R.id.tvRight, R.id.tvDelete, R.id.tvMove, R.id.llAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.tvRight:
                break;
            case R.id.tvDelete:
                break;
            case R.id.tvMove:
                break;
            case R.id.llAdd:
                break;
        }
    }
}
