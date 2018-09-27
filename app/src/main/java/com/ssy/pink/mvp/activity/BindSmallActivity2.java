package com.ssy.pink.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.adapter.BindLogAdapter;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.BindLogInfo;
import com.ssy.pink.mvp.iview.IBindSmallActivityView2;
import com.ssy.pink.mvp.presenter.BindSmallActivityPresenter2;
import com.ssy.pink.view.dialog.BindingDialog;
import com.ssy.pink.view.recyclerViewBase.DashlineItemDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindSmallActivity2 extends BaseActivity implements IBindSmallActivityView2 {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    BindSmallActivityPresenter2 presenter;
    BindLogAdapter adapter;
    List<BindLogInfo> logInfos = new ArrayList<>();
    BindingDialog bindingDialog;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_small2);
        ButterKnife.bind(this);
        init();
        presenter = new BindSmallActivityPresenter2(this, this);
        presenter.bindWeiboSingle();
    }

    private void init() {
        tvTitle.setText("正在绑号");
        setCount();
//        tvTotal.setText(String.valueOf(BindManager.getInstance().smallInfos.size()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.addItemDecoration(new DashlineItemDivider());
        adapter = new BindLogAdapter(this, logInfos);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.aivBack, R.id.tvContinue, R.id.tvEnd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.tvContinue:
                setCount();
                presenter.bindWeiboSingle();
                break;
            case R.id.tvEnd:
                onBackPressed();
                break;
        }
    }


    @Override
    public BindLogAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void showBindingDialog(boolean show) {
        if (bindingDialog == null) {
            bindingDialog = new BindingDialog(this);
        }
        if (show) {
            bindingDialog.show();
        } else {
            bindingDialog.hide();
        }
    }

    private void setCount() {
        tvTotal.setText(String.format("已尝试绑定%d个账号", ++count));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
