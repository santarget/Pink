package com.ssy.pink.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.mvp.iview.IBindSmallActivityView;
import com.ssy.pink.mvp.presenter.BindSmallActivityPresenter;
import com.ssy.pink.view.recyclerViewBase.DashgapLineRecyclerItemDecoration;
import com.ssy.pink.view.recyclerViewBase.SpaceItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindSmallActivity extends BaseActivity implements IBindSmallActivityView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tvFinish)
    TextView tvFinish;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    BindSmallActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_small);
        ButterKnife.bind(this);
        init();
        presenter = new BindSmallActivityPresenter(this);
        presenter.bindSmall();
    }

    private void init() {
        tvTitle.setText("正在绑号");
        tvRight.setText("取消绑号");
        tvTotal.setText(String.valueOf(BindManager.getInstance().smallInfos.size()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.addItemDecoration(new DashgapLineRecyclerItemDecoration(this, OrientationHelper.HORIZONTAL));
    }

    @OnClick({R.id.aivBack, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.tvRight:
                break;
        }
    }

    @Override
    public void setCurrentProgress(int finished) {
        tvFinish.setText(String.valueOf(finished));
        progressBar.setProgress(finished / BindManager.getInstance().smallInfos.size() * 100);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
