package com.ssy.pink.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckUpdateActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.aivBack)
    AppCompatImageView aivBack;
    @BindView(R.id.tvVersion)
    TextView tvVersion;
    @BindView(R.id.tvIntroduce)
    TextView tvIntroduce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_update);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setText("检查更新");
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
}
