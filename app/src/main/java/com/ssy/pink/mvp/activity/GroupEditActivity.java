package com.ssy.pink.mvp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.common.Constants;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.MyUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class GroupEditActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.etName)
    EditText etName;
    GroupInfo groupInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_edit);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setText("编辑分组");
        tvRight.setText(R.string.done);
        groupInfo = (GroupInfo) getIntent().getSerializableExtra(Constants.INTENT_KEY_DATA);
        etName.setText(groupInfo.getCustomerGroupName());
    }

    @OnClick({R.id.aivBack, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aivBack:
                onBackPressed();
                break;
            case R.id.tvRight:
                updateGroup();
                break;
        }
    }

    private void updateGroup() {
        String str = etName.getText().toString().trim();
        if (TextUtils.isEmpty(str)) {
            showToast(R.string.hint_input_group_name);
        } else {
            PinkNet.updateGroup(UserManager.getInstance().userInfo.getCustomernum(), groupInfo.getCustomerGroupNum(),
                    str, new Subscriber<CommonResp<GroupInfo>>() {
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
                                showToast(R.string.update_success);
                                GroupInfo data = groupInfoCommonResp.getData();
                                List<GroupInfo> datas = GroupManager.getInstance().groupInfos;
                                int index = datas.indexOf(data);
                                if (index >= 0) {
                                    datas.remove(index);
                                    datas.add(index, data);
                                } else {
                                    datas.add(data);
                                }
                                EventBus.getDefault().post(EventCode.EDIT_GROUP);
                                finish();
                            }
                        }
                    });
        }

    }
}
