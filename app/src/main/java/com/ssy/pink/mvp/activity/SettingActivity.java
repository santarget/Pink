package com.ssy.pink.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ssy.pink.MyApplication;
import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.VersionInfo;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.manager.LoopManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.iview.ISettingActivityView;
import com.ssy.pink.mvp.presenter.SettingActivityPresenter;
import com.ssy.pink.utils.CommonUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements ISettingActivityView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvVersion)
    TextView tvVersion;

    SettingActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
        presenter = new SettingActivityPresenter(this);
    }

    private void initView() {
        tvTitle.setText("我的设置");
        tvVersion.setText(CommonUtils.getVersionName());
    }

    @OnClick({R.id.aivBack, R.id.rlWeibo, R.id.rlShare, R.id.rlUpdate, R.id.rlContact, R.id.rlSwitch, R.id.tvLoginOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.rlWeibo:
                break;
            case R.id.rlShare:
                startActivity(new Intent(SettingActivity.this, ShareAppActivity.class));
                break;
            case R.id.rlUpdate:
                startActivity(new Intent(SettingActivity.this, CheckUpdateActivity.class));
                break;
            case R.id.rlContact:
                break;
            case R.id.rlSwitch:
                startActivity(new Intent(SettingActivity.this, SwitchAccoutActivity.class));
                break;
            case R.id.tvLoginOut:
                //退出登录，重置数据
                MyApplication.getInstance().setToken("");
                UserManager.getInstance().reset();
                GroupManager.getInstance().reset();
                LoopManager.getInstance().reset();

                Intent i = new Intent(this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                break;
        }
    }


}
