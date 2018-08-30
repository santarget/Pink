package com.ssy.pink.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseFragment;
import com.ssy.pink.iview.IWorkFragmentView;
import com.ssy.pink.view.dialog.ConfigIntroduceDialog;
import com.ssy.pink.view.recyclerViewBase.SpaceItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author ssy
 * @date 2018/8/10
 */
public class WorkFragment extends BaseFragment implements IWorkFragmentView, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
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
    @BindView(R.id.llMonitor)
    LinearLayout llMonitor;
    Unbinder unbinder;

    private boolean isWorking;
    private ConfigIntroduceDialog helpDialog;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_work;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        unbinder = ButterKnife.bind(this, view);
        initView();
        initListener();
        // TODO: 2018/8/30 获取分组列表及展示
    }


    @Override
    protected void DetoryViewAndThing() {
        unbinder.unbind();
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    private void initView() {
        //设置RecyclerView垂直布局
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity, OrientationHelper.VERTICAL, false));
        //设置分割线
        recyclerView.addItemDecoration(new SpaceItemDecoration(15));
        rbRandomEmoticon.setChecked(true);
        etCustom.setVisibility(View.GONE);
        rbContentKeep.setChecked(true);
        rbSpeedSlow.setChecked(true);
        rbCountMax.setChecked(true);
        etCountCustom.setVisibility(View.GONE);
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

    @OnClick({R.id.llEnter, R.id.llHelpGetUrl, R.id.ivVisitUrl, R.id.ivHelpSendContent, R.id.ivHelpOtherContent, R.id.ivHelpSetSpeed, R.id.ivHelpSetCount, R.id.tvWork, R.id.ivHelpMonitor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llEnter://进入分组

                break;
            case R.id.llHelpGetUrl: //如何获取微博链接弹窗
                showHelpDialog(R.string.dialog_config_introduce);
                break;
            case R.id.ivVisitUrl://访问连接
                break;
            case R.id.ivHelpSendContent://发送内容
                showHelpDialog(R.string.dialog_config_introduce);
                break;
            case R.id.ivHelpOtherContent://其他人的转发内容
                showHelpDialog(R.string.dialog_config_introduce);
                break;
            case R.id.ivHelpSetSpeed://设置转发速度
                showHelpDialog(R.string.dialog_config_introduce);
                break;
            case R.id.ivHelpSetCount://数量设置
                showHelpDialog(R.string.dialog_config_introduce);
                break;
            case R.id.tvWork://开始抡博
                if (isWorking) {
                    isWorking = false;
                    tvWork.setText("开始抡博");
                    tvWorkBg.setEnabled(false);
                } else {
                    isWorking = true;
                    tvWork.setText("停止抡博");
                    tvWorkBg.setEnabled(true);
                }
                break;
            case R.id.ivHelpMonitor://抡博监控的帮助信息
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

                break;
            case R.id.rbSpeedStable:

                break;
            case R.id.rbSpeedSlow:

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
}
