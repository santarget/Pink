package com.ssy.pink.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.common.Constants;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.mvp.iview.IAddSmallActivityView;
import com.ssy.pink.mvp.presenter.AddSmallActivityPresenter;
import com.ssy.pink.view.dialog.CheckDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ssy
 * @date 2018/9/6
 */
public class AddSmallActivity extends BaseActivity implements IAddSmallActivityView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etAccout)
    EditText etAccout;

    AddSmallActivityPresenter presenter;
    CheckDialog checkDialog;
    int checkStatus;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_add);
        ButterKnife.bind(this);
        init();
        presenter = new AddSmallActivityPresenter(this);

    }

    private void init() {
        BindManager.getInstance().groupInfo = (GroupInfo) getIntent().getSerializableExtra(Constants.INTENT_KEY_DATA);
        tvTitle.setText("绑号");
    }

    @OnClick({R.id.aivBack, R.id.tvCheck, R.id.tvBind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.tvCheck:
                content = etAccout.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    showToast("请添加账号");
                } else {
                    presenter.checkAccout(content);
                }
                break;
            case R.id.tvBind:
                if (TextUtils.isEmpty(content) || !content.equals(etAccout.getText().toString().trim())) {
                    showToast("请先检测格式");
                } else if (checkStatus == CheckDialog.STATUS_CHECKING) {
                    showToast("请先检测格式");
                } else if (checkStatus == CheckDialog.STATUS_ERROR) {
                    showToast("账号格式存在异常");
                } else {
                    startActivity(new Intent(this, BindSmallActivity.class));
                }
                break;
        }
    }

    @Override
    public void setCheckDialog(int status) {
        checkStatus = status;
        if (checkDialog == null) {
            checkDialog = new CheckDialog(this);
        }
        checkDialog.setStatus(status);
        if (status == CheckDialog.STATUS_ERROR) {
            checkDialog.setErrorWord(presenter.errorWord);
        }
        checkDialog.show();
    }
}
