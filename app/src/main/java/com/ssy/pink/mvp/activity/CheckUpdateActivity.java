package com.ssy.pink.mvp.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.VersionInfo;
import com.ssy.pink.mvp.iview.ICheckUpdateActivityView;
import com.ssy.pink.mvp.presenter.CheckUpdateActivityPresenter;
import com.ssy.pink.utils.CommonUtils;
import com.ssy.pink.view.dialog.CommonDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckUpdateActivity extends BaseActivity implements ICheckUpdateActivityView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.aivBack)
    AppCompatImageView aivBack;
    @BindView(R.id.tvVersion)
    TextView tvVersion;
    @BindView(R.id.tvIntroduce)
    TextView tvIntroduce;

    CheckUpdateActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_update);
        ButterKnife.bind(this);
        init();
        presenter = new CheckUpdateActivityPresenter(this);
        presenter.checkVersion();
    }

    private void init() {
        tvTitle.setText("检查更新");
        tvVersion.setText(CommonUtils.getVersionName());
    }

    @OnClick({R.id.aivBack, R.id.tvVersion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.tvVersion:
                break;
        }
    }

    @Override
    public void loadVersion(VersionInfo versionInfo) {
        int vertionCode = CommonUtils.getVersionCode();
        if (vertionCode < versionInfo.getAppVersion()) {
            new CommonDialog.Builder(this)
                    .setTitle("发现新版本")
                    .setMessage(versionInfo.getAppDesc())
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton(R.string.download, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            showToast("开始下载");
                        }
                    })
                    .create()
                    .show();
        } else {
            showToast("当前版本已经是最新");
        }
    }
}
