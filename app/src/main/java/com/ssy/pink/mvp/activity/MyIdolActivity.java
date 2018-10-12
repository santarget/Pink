package com.ssy.pink.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ssy
 * @date 2018/8/16
 */
public class MyIdolActivity extends BaseActivity {
    @BindView(R.id.tvIdolNumber)
    TextView tvIdolNumber;
    @BindView(R.id.etMoney)
    EditText etMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_idol);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
//        tvIdolNumber.setText(String.valueOf(UserManager.getInstance().moneyInfo.getRestBeanNum()));
    }

    @OnClick({R.id.ivBack, R.id.tvRecord, R.id.tvRecharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.tvRecord:
                startActivity(new Intent(this, RecordActivity.class));
                break;
            case R.id.tvRecharge:
                MyUtils.pay(this);
                break;
        }
    }
}
