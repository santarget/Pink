package com.ssy.pink.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.adapter.HandleAbnormalAdapter;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.common.Constants;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.view.recyclerViewBase.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandleAbnormalActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<SmallInfo> smallInfoList;
    HandleAbnormalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_abnormal);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setText("处理异常");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpaceItemDecoration(true));
        smallInfoList = (List<SmallInfo>) getIntent().getSerializableExtra(Constants.INTENT_KEY_DATA);
        if (!ListUtils.isEmpty(smallInfoList)) {
            adapter = new HandleAbnormalAdapter(this, smallInfoList);
            recyclerView.setAdapter(adapter);
        }


    }

    @OnClick({R.id.aivBack, R.id.tvEnd, R.id.tvRebind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                finish();
                break;
            case R.id.tvEnd:
                finish();
                break;
            case R.id.tvRebind:
                if (!ListUtils.isEmpty(smallInfoList)) {
                    BindManager.getInstance().smallInfos.clear();
                    BindManager.getInstance().smallInfos.addAll(smallInfoList);
                    startActivity(new Intent(this, BindSmallActivity.class));
                    finish();
                }
                break;
        }
    }
}
