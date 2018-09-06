package com.ssy.pink.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareAppActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_app);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setText("应用分享");
    }

    @OnClick({R.id.tvTitle, R.id.aivBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvTitle:
                break;
            case R.id.aivBack:
                onBackPressed();
                break;
        }
    }
}
