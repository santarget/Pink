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

public class HandleAbnormalActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_abnormal);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("处理异常");
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
                break;
        }
    }
}
