package com.ssy.pink.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.common.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ssy
 * @date 2018/9/6
 */
public class AddSmallActivity extends BaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etAccout)
    EditText etAccout;
    GroupInfo groupInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_add);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        groupInfo = (GroupInfo) getIntent().getSerializableExtra(Constants.INTENT_KEY_DATA);
        tvTitle.setText("绑号");
    }

    @OnClick({R.id.aivBack, R.id.tvCheck, R.id.tvBind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.tvCheck:
                break;
            case R.id.tvBind:
                break;
        }
    }
}
