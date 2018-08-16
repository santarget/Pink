package com.ssy.pink.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("交易记录");
    }

    @OnClick(R.id.aivBack)
    public void onViewClicked() {
        onBackPressed();
    }
}
