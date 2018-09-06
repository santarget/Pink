package com.ssy.pink.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonthVipActivity extends BaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTopTips)
    TextView tvTopTips;
    @BindView(R.id.tvPriceMonth)
    TextView tvPriceMonth;
    @BindView(R.id.rlMonth)
    RelativeLayout rlMonth;
    @BindView(R.id.tvPriceSeason)
    TextView tvPriceSeason;
    @BindView(R.id.rlSeason)
    RelativeLayout rlSeason;
    @BindView(R.id.tvPriceHalfYear)
    TextView tvPriceHalfYear;
    @BindView(R.id.rlHalfYear)
    RelativeLayout rlHalfYear;
    @BindView(R.id.tvYuan)
    TextView tvYuan;
    @BindView(R.id.tvPay)
    TextView tvPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_vip);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("包月会员");
    }

    @OnClick({R.id.aivBack, R.id.rlMonth, R.id.rlSeason, R.id.rlHalfYear, R.id.tvOK})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.rlMonth:
                changeSelectState(rlMonth);
                tvPay.setText(tvPriceMonth.getText().toString());
                break;
            case R.id.rlSeason:
                changeSelectState(rlSeason);
                tvPay.setText(tvPriceSeason.getText().toString());
                break;
            case R.id.rlHalfYear:
                changeSelectState(rlHalfYear);
                tvPay.setText(tvPriceHalfYear.getText().toString());
                break;
            case R.id.tvOK:

                break;
        }
    }

    private void changeSelectState(View v) {
        rlMonth.setSelected(false);
        rlSeason.setSelected(false);
        rlHalfYear.setSelected(false);
        v.setSelected(true);
    }
}
