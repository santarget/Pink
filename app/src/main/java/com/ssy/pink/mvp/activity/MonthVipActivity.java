package com.ssy.pink.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.ProductInfo;
import com.ssy.pink.bean.UserProductInfo;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.utils.ListUtils;

import java.util.List;

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

    ProductInfo monthProduct;//包月产品

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_vip);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("包月会员");
        rlMonth.setSelected(true);
        tvPay.setText(tvPriceMonth.getText().toString());
        if (ListUtils.isEmpty(UserManager.getInstance().productInfos)) {
            showToast("未获取到商品列表");
        } else {
            for (ProductInfo productInfo : UserManager.getInstance().productInfos) {
                if (productInfo.getProductstate().equalsIgnoreCase("1") &&
                        productInfo.getProducttype().equalsIgnoreCase("1")) {
                    monthProduct = productInfo;
                    break;
                }
            }
            if (monthProduct != null) {
                int price = monthProduct.getPrice();
                tvPriceMonth.setText(String.valueOf(price));
                tvPay.setText(String.valueOf(price));
                tvPriceSeason.setText(String.valueOf(price * 3));
                tvPriceHalfYear.setText(String.valueOf(price * 6));
            } else {
                showToast("未获取到包月产品");
            }
        }

        //当前用户已订购的产品
        if (ListUtils.isEmpty(UserManager.getInstance().orderedInfos)) {
            tvTopTips.setVisibility(View.GONE);
        } else {
            UserProductInfo monthOrdered = null;
            for (UserProductInfo orderedInfo : UserManager.getInstance().orderedInfos) {
                if (orderedInfo.getProductstate().equalsIgnoreCase("1") &&
                        orderedInfo.getProducttype().equalsIgnoreCase("1")) {
                    //已订购的包月产品
                    monthOrdered = orderedInfo;
                    break;
                }
            }
            if (monthOrdered == null) {
                tvTopTips.setVisibility(View.GONE);
            } else {
                tvTopTips.setVisibility(View.VISIBLE);
                tvTopTips.setText(String.format("已开通月度会员，有效期至%s", monthOrdered.getDeadlinetime()));
            }
        }
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
                if (TextUtils.isEmpty(tvPay.getText().toString())) {
                    showToast("请选择产品");
                } else {
                    startActivity(new Intent(MonthVipActivity.this, WeixinPayActivity.class));
                }
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
