package com.ssy.pink.mvp.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ssy.greendao.helper.HelperFactory;
import com.ssy.greendao.helper.LoopLogInfoDbHelper;
import com.ssy.pink.R;
import com.ssy.pink.base.BaseFragment;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.weibo.LoopLogInfo;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.manager.LoopManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.activity.BrowserActivity;
import com.ssy.pink.mvp.activity.GroupActivity;
import com.ssy.pink.mvp.iview.IWorkFragmentView;
import com.ssy.pink.mvp.presenter.WorkFragmentPresenter;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.utils.MyUtils;
import com.ssy.pink.view.ChooseGroupView;
import com.ssy.pink.view.dialog.ConfigIntroduceDialog;
import com.ssy.pink.view.dialog.InvalidDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author ssy
 * @date 2018/8/10
 */
public class WorkFragment extends BaseFragment implements IWorkFragmentView, CompoundButton.OnCheckedChangeListener {
    //    @BindView(R.id.cbDefaultGroup)
//    CheckBox cbDefaultGroup;
//    @BindView(R.id.tvDefalutCount)
//    TextView tvDefalutCount;
    @BindView(R.id.llGroupRoot)
    LinearLayout llGroupRoot;
    @BindView(R.id.etWeiboUrl)
    EditText etWeiboUrl;
    @BindView(R.id.rbRandomEmoticon)
    RadioButton rbRandomEmoticon;
    @BindView(R.id.rbCustom)
    RadioButton rbCustom;
    @BindView(R.id.rbBoth)
    RadioButton rbBoth;
    @BindView(R.id.etCustom)
    EditText etCustom;
    @BindView(R.id.rbContentKeep)
    RadioButton rbContentKeep;
    @BindView(R.id.rbContentDelete)
    RadioButton rbContentDelete;
    @BindView(R.id.rbSpeedFast)
    RadioButton rbSpeedFast;
    @BindView(R.id.rbSpeedStable)
    RadioButton rbSpeedStable;
    @BindView(R.id.rbSpeedSlow)
    RadioButton rbSpeedSlow;
    @BindView(R.id.rbCountMax)
    RadioButton rbCountMax;
    @BindView(R.id.rbCountCustom)
    RadioButton rbCountCustom;
    @BindView(R.id.etCountCustom)
    EditText etCountCustom;
    @BindView(R.id.tvWork)
    TextView tvWork;
    @BindView(R.id.tvWorkBg)
    TextView tvWorkBg;//单纯做开始抡博按钮的背景
    @BindView(R.id.tvLog)
    TextView tvLog;
    @BindView(R.id.parentScrollView)
    ScrollView parentScrollView;
    @BindView(R.id.logScrollView)
    ScrollView logScrollView;
    Unbinder unbinder;

    private WorkFragmentPresenter presenter;
    private boolean isWorking;
    private ConfigIntroduceDialog helpDialog;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_work;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initView();
        initListener();
        presenter = new WorkFragmentPresenter(this);
        // TODO: 2018/8/30 获取分组列表及展示
        presenter.listGroup();
    }


    @Override
    protected void DetoryViewAndThing() {
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onFirstUserVisible() {
        LoopManager.getInstance().setHistoryLog();
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    private void initView() {
        etWeiboUrl.setText(UserManager.getInstance().fansOrgInfo.getFansorginfourl());
        rbRandomEmoticon.setChecked(true);
        etCustom.setVisibility(View.GONE);
        rbContentKeep.setChecked(true);
        rbSpeedSlow.setChecked(true);
        rbCountCustom.setChecked(true);

        parentScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                logScrollView.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        logScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!TextUtils.isEmpty(tvLog.getText().toString())) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }

    private void initListener() {
        rbRandomEmoticon.setOnCheckedChangeListener(this);
        rbCustom.setOnCheckedChangeListener(this);
        rbBoth.setOnCheckedChangeListener(this);
        rbContentKeep.setOnCheckedChangeListener(this);
        rbContentDelete.setOnCheckedChangeListener(this);
        rbSpeedFast.setOnCheckedChangeListener(this);
        rbSpeedStable.setOnCheckedChangeListener(this);
        rbSpeedSlow.setOnCheckedChangeListener(this);
        rbCountMax.setOnCheckedChangeListener(this);
        rbCountCustom.setOnCheckedChangeListener(this);
    }

    @OnClick({R.id.llEnter, R.id.llHelpGetUrl, R.id.ivVisitUrl, R.id.ivHelpSendContent, R.id.ivHelpOtherContent,
            R.id.ivHelpSetSpeed, R.id.ivHelpSetCount, R.id.tvWork, R.id.ivHelpMonitor, R.id.ivCourse})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivCourse://教程

                break;
            case R.id.llEnter://进入分组
                startActivity(new Intent(mainActivity, GroupActivity.class));
                break;
            case R.id.llHelpGetUrl: //如何获取微博链接弹窗
                showHelpDialog(R.string.dialog_config_introduce);
                break;
            case R.id.ivVisitUrl://访问连接
                String url = etWeiboUrl.getText().toString().trim();
                if (TextUtils.isEmpty(url)) {
                    showToast("请输入微博链接");
                } else {
                    Intent i = new Intent(mainActivity, BrowserActivity.class);
                    i.putExtra("url", etWeiboUrl.getText().toString());
                    mainActivity.startActivity(i);
                }
                break;
            case R.id.ivHelpSendContent://发送内容
                showHelpDialog(R.string.dialog_config_send_content);
                break;
            case R.id.ivHelpOtherContent://其他人的转发内容
                showHelpDialog(R.string.dialog_config_others);
                break;
            case R.id.ivHelpSetSpeed://设置转发速度
                showHelpDialog(R.string.dialog_config_speed);
                break;
            case R.id.ivHelpSetCount://数量设置
                showHelpDialog(R.string.dialog_config_times_limit);
                break;
            case R.id.tvWork://开始抡博
                if (MyUtils.isAppValid()) {
                    setWorkStatus(!isWorking);
                } else {
                    new InvalidDialog(mainActivity).show();
                }
                break;
            case R.id.ivHelpMonitor://抡博监控的帮助信息
                showHelpDialog(R.string.dialog_config_introduce);
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rbRandomEmoticon:
                if (isChecked) {
                    etCustom.setVisibility(View.GONE);
                }
                break;
            case R.id.rbCustom:
                if (isChecked) {
                    etCustom.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rbBoth:
                if (isChecked) {
                    etCustom.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rbContentKeep:

                break;
            case R.id.rbContentDelete:

                break;
            case R.id.rbSpeedFast:
                if (isChecked) {
                    etCountCustom.setText("4");
                }

                break;
            case R.id.rbSpeedStable:
                if (isChecked) {
                    etCountCustom.setText("15");
                }

                break;
            case R.id.rbSpeedSlow:
                if (isChecked) {
                    etCountCustom.setText("28");
                }
                break;
            case R.id.rbCountMax:
                if (isChecked) {
                    etCountCustom.setVisibility(View.GONE);
                }
                break;
            case R.id.rbCountCustom:
                if (isChecked) {
                    etCountCustom.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void showHelpDialog(int strId) {
        if (helpDialog == null) {
            helpDialog = new ConfigIntroduceDialog(mainActivity);
        }
        helpDialog.setTips(strId).show();
    }

    @Override
    public void loadGroups() {
        llGroupRoot.removeAllViews();
        if (!ListUtils.isEmpty(GroupManager.getInstance().groupInfos)) {
            for (GroupInfo groupInfo : GroupManager.getInstance().groupInfos) {
                ChooseGroupView view = new ChooseGroupView(mainActivity).setData(groupInfo);
                llGroupRoot.addView(view);
            }
        }
    }

    private void setWorkStatus(boolean isWorking) {
        if (isWorking) {
            if (setWorkConfig()) {
                this.isWorking = isWorking;
                tvWork.setText("停止抡博");
                tvWorkBg.setEnabled(true);
                LoopManager.getInstance().startWork();
            }
        } else {
            this.isWorking = isWorking;
            tvWork.setText("开始抡博");
            tvWorkBg.setEnabled(false);
            LoopManager.getInstance().stopWork();
        }
        //工作状态，小号分组的checkbox不可选
//        for (int i = 0; i < llGroupRoot.getChildCount(); i++) {
//            ((ChooseGroupView) (llGroupRoot.getChildAt(i))).setCheckboxEnable(!this.isWorking);
//        }

    }

    /**
     * 设置抡博参数
     *
     * @return
     */
    private boolean setWorkConfig() {
        if (ListUtils.isEmpty(GroupManager.getInstance().smallInfos)) {
            showToast("没有抡博的小号");
            return false;
        }
        if (ListUtils.isEmpty(GroupManager.getInstance().validSmallInfos)) {
            showToast("没有有效的抡博小号");
            return false;
        }
        //设置抡博分组
//        if (cbDefaultGroup.isChecked()) {
//            LoopManager.getInstance().setSmalls(GroupManager.getInstance().validSmallInfos);
//        } else {
        List<SmallInfo> workSmallList = new ArrayList<>();
        for (GroupInfo groupInfo : GroupManager.getInstance().groupInfos) {
            if (groupInfo.isChecked()) {
                workSmallList.addAll(groupInfo.getValidSmallInfos());
            }
        }
        if (ListUtils.isEmpty(workSmallList)) {
            showToast("没有有效的抡博小号");
            return false;
        } else {
            LoopManager.getInstance().setSmalls(workSmallList);
        }


        if (TextUtils.isEmpty(etWeiboUrl.getText().toString())) {
            showToast("请设置微博链接");
            return false;
        }
        LoopManager.getInstance().url = etWeiboUrl.getText().toString();
        if (rbRandomEmoticon.isChecked()) {
            LoopManager.getInstance().customOn = false;
            LoopManager.getInstance().randomOn = true;
        } else if (rbCustom.isChecked()) {
            LoopManager.getInstance().customOn = true;
            LoopManager.getInstance().randomOn = false;
        } else {
            LoopManager.getInstance().customOn = true;
            LoopManager.getInstance().randomOn = true;
        }
        LoopManager.getInstance().customContent = TextUtils.isEmpty(etCustom.getText().toString()) ?
                "" : etCustom.getText().toString().trim();

        LoopManager.getInstance().keepOthers = rbContentKeep.isChecked();
        if (rbSpeedFast.isChecked()) {
            LoopManager.getInstance().setSpeed(2);
        } else if (rbSpeedStable.isChecked()) {
            LoopManager.getInstance().setSpeed(1);
        } else {
            LoopManager.getInstance().setSpeed(0);
        }
        if (rbCountMax.isChecked()) {
            LoopManager.getInstance().count = Integer.MAX_VALUE;
        } else {
            String countStr = etCountCustom.getText().toString();
            if (TextUtils.isEmpty(countStr)) {
                showToast("请设置微博转发数量");
                return false;
            }
            try {
                int count = Integer.valueOf(countStr);
                if (count <= 0) {
                    showToast("数量设置异常");
                    return false;
                } else {
                    LoopManager.getInstance().count = count;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                showToast("数量设置异常");
                return false;
            }
        }
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(Integer eventCode) {
        switch (eventCode) {
            case EventCode.UPDATE_GROUPS:
                loadGroups();
                break;
            case EventCode.WORK_FINISH:
                setWorkStatus(false);
                break;
            case EventCode.WORK_UPDATE_LOG:
                tvLog.setText(LoopManager.getInstance().logSb.toString());
                break;
            case EventCode.MODIFY_FANS_ORG:
                etWeiboUrl.setText(UserManager.getInstance().fansOrgInfo.getFansorginfourl());
                break;
        }
    }
}
