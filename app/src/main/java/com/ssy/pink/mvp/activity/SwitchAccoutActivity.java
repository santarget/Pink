package com.ssy.pink.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SwitchAccoutActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_accout);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setText("切换账号");
    }

    @OnClick({R.id.aivBack, R.id.llAddAccout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.llAddAccout:
                break;
        }
    }
}
