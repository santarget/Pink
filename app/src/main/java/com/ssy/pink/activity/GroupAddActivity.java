package com.ssy.pink.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.MyUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class GroupAddActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.etName)
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setText("添加分组");
        tvRight.setText(R.string.done);
    }

    @OnClick({R.id.aivBack, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.tvRight:
                addGroup();
                break;
        }
    }

    private void addGroup() {
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showToast(R.string.hint_input_group_name);
            return;
        }
        PinkNet.addGroup(UserManager.getInstance().userInfo.getCustomernum(), name, new Subscriber<CommonResp<GroupInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonResp<GroupInfo> groupInfoCommonResp) {
                if (groupInfoCommonResp.getCode().equalsIgnoreCase(ResponseCode.CODE_SUCCESS)) {
                    GroupManager.getInstance().groupInfos.add(groupInfoCommonResp.getData());
                    EventBus.getDefault().post(EventCode.ADD_GROUP);
                    showToast("分组添加成功");
                    onBackPressed();
                } else if (groupInfoCommonResp.getCode().equalsIgnoreCase(ResponseCode.CODE_0041)) {
                    showToast("分组添加失败，小组名称存在重名");
                }
            }
        });
    }
}
