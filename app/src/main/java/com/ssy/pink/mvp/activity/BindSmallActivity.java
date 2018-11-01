package com.ssy.pink.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.adapter.BindLogAdapter;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.BindLogInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.common.Constants;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.mvp.iview.IBindSmallActivityView;
import com.ssy.pink.mvp.presenter.BindSmallActivityPresenter;
import com.ssy.pink.view.dialog.BindAbnormalDialog;
import com.ssy.pink.view.dialog.BindFinishDialog;
import com.ssy.pink.view.recyclerViewBase.DashlineItemDivider;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    BindLogAdapter adapter;
    List<BindLogInfo> logInfos = new ArrayList<>();
    BindFinishDialog bindFinishDialog;
    BindAbnormalDialog bindAbnormalDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_small);
        ButterKnife.bind(this);
        init();
        presenter = new BindSmallActivityPresenter(this, this);
        presenter.bindSmall();
    }

    private void init() {
        tvTitle.setText("正在绑号");
        tvRight.setText("取消绑号");
        tvTotal.setText(String.valueOf(BindManager.getInstance().smallInfos.size()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.addItemDecoration(new DashlineItemDivider());
        adapter = new BindLogAdapter(this, logInfos);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.aivBack, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.tvRight:
                onBackPressed();
                break;
        }
    }

    @Override
    public void setCurrentProgress(final int finished) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvFinish.setText(String.valueOf(finished));
                progressBar.setProgress(finished * 100 / BindManager.getInstance().smallInfos.size());
                if (finished == BindManager.getInstance().smallInfos.size()) {
                    //绑定结束
                    if (presenter.getFailList().size() == 0) {
                        showBindFinishDialog();
                    } else {
                        showBindAbnormalDialog();
                    }
                }
            }
        });
    }

    @Override
    public BindLogAdapter getAdapter() {
        return adapter;
    }

    private void showBindFinishDialog() {
        if (bindFinishDialog == null) {
            bindFinishDialog = new BindFinishDialog(this, new BindFinishDialog.BindFinishListener() {
                @Override
                public void onOK(BindFinishDialog dialog) {
                    dialog.dismiss();
//                    finish();
                }
            });
        }
        bindFinishDialog.show();
    }

    private void showBindAbnormalDialog() {
        if (bindAbnormalDialog == null) {
            bindAbnormalDialog = new BindAbnormalDialog(this, new BindAbnormalDialog.BindAbnormalListener() {
                @Override
                public void onEnd(BindAbnormalDialog dialog) {
                    dialog.dismiss();
//                    finish();
                }

                @Override
                public void onHandle(BindAbnormalDialog dialog) {
                    dialog.dismiss();
                    Intent i = new Intent(BindSmallActivity.this, HandleAbnormalActivity.class);
                    i.putExtra(Constants.INTENT_KEY_DATA, (Serializable) presenter.getFailList());
                    startActivity(i);
                    finish();
                }
            });
        }

        if (bindAbnormalDialog.isShowing()) {
            bindAbnormalDialog.hide();
        }
        bindAbnormalDialog.setTips(presenter.getFailList().size()).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        if (progressBar.getProgress()>1){
            EventBus.getDefault().post(EventCode.ADD_SMALL);
        }
    }
}
