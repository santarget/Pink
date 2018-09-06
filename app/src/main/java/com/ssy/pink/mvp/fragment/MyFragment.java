package com.ssy.pink.mvp.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.mvp.activity.GroupActivity;
import com.ssy.pink.mvp.activity.MonthVipActivity;
import com.ssy.pink.mvp.activity.MyIdolActivity;
import com.ssy.pink.mvp.activity.SettingActivity;
import com.ssy.pink.base.BaseFragment;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.SmallStatusInfo;
import com.ssy.pink.bean.WeiboCustomerInfo;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.EventWithObj;
import com.ssy.pink.mvp.iview.IMyFragmentView;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.presenter.MyFragmentPresenter;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.view.CircleImageView;
import com.ssy.pink.view.dialog.LoginChooseDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author ssy
 * @date 2018/8/10
 */
public class MyFragment extends BaseFragment implements IMyFragmentView {
    @BindView(R.id.tvFans)
    TextView tvFans;
    @BindView(R.id.tvFollow)
    TextView tvFollow;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.idGender)
    ImageView idGender;
    @BindView(R.id.tvOrg)
    TextView tvOrg;
    @BindView(R.id.tvAltCurrent)
    TextView tvAltCurrent;
    @BindView(R.id.tvAltBlack)
    TextView tvAltBlack;
    @BindView(R.id.tvAltNormal)
    TextView tvAltNormal;
    @BindView(R.id.tvMyIdolNumber)
    TextView tvMyIdolNumber;
    @BindView(R.id.civIcon)
    CircleImageView civIcon;
    Unbinder unbinder;

    private MyFragmentPresenter presenter;
    private LoginChooseDialog chooseDialog;
    private List<FansOrgInfo> orgsList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        presenter = new MyFragmentPresenter(this);
    }

    @Override
    protected void DetoryViewAndThing() {
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onFirstUserVisible() {
        WeiboCustomerInfo userInfo = UserManager.getInstance().userInfo;
        if (userInfo != null) {
            tvName.setText(userInfo.getWeiboname());
            tvOrg.setText(userInfo.getFansorginfoname());
        }
        MoneyInfo moneyInfo = UserManager.getInstance().moneyInfo;
        if (moneyInfo != null) {
            tvMyIdolNumber.setText(String.valueOf(moneyInfo.getRestBeanNum()));
            tvAltCurrent.setText(String.valueOf(moneyInfo.getAllSmallNum()));
            tvAltBlack.setText(String.valueOf(moneyInfo.getAllInValidSmallNum()));
            tvAltNormal.setText(String.valueOf(moneyInfo.getAllValidSmallNum()));
        }else{
            presenter.getSmallStutas();
        }
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }


    @OnClick({R.id.tvModify, R.id.rlMyIdol, R.id.llAddAccout, R.id.llSettings, R.id.rlMonthVip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvModify:
                showLoginChooseDialog();
                break;
            case R.id.rlMyIdol:
                Intent i = new Intent(mainActivity, MyIdolActivity.class);
                mainActivity.startActivity(i);
                break;
            case R.id.llAddAccout:
                mainActivity.startActivity(new Intent(mainActivity, GroupActivity.class));
                break;
            case R.id.llSettings:
                mainActivity.startActivity(new Intent(mainActivity, SettingActivity.class));
                break;
            case R.id.rlMonthVip:
                mainActivity.startActivity(new Intent(mainActivity, MonthVipActivity.class));
                break;
        }
    }

    private void showLoginChooseDialog() {
        if (chooseDialog == null) {
            chooseDialog = new LoginChooseDialog(mainActivity);
        }
        if (!hasGotOrgs) {
            presenter.listFansOrg();
        }
        chooseDialog.show();
        if (orgsList != null) {
            chooseDialog.setDatas(orgsList);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(EventWithObj event) {
        if (EventCode.LOGIN_CHOOSE_ORG == event.eventCode) {
            FansOrgInfo fansOrgInfo = (FansOrgInfo) event.obj;
            if (fansOrgInfo != null) {
                tvOrg.setText(fansOrgInfo.getFansorginfoname());
            }
        }
    }

    @Override
    public void setFansOrgList(List<FansOrgInfo> list) {
        orgsList = list;
        if (chooseDialog != null && chooseDialog.isShowing()) {
            chooseDialog.setDatas(orgsList);
        }
    }

    private boolean hasGotOrgs;

    @Override
    public void hasGotOrgs(boolean hasGot) {
        hasGotOrgs = hasGot;
        if (!hasGot && chooseDialog != null && chooseDialog.isShowing()) {
            chooseDialog.dismiss();
        }
    }

    @Override
    public void loadSmallCount(List<SmallStatusInfo> smallStatusInfos) {
        if (!ListUtils.isEmpty(smallStatusInfos)) {
            int valid = 0;
            int invalid = 0;
            for (SmallStatusInfo info : smallStatusInfos) {
                if (info.getSmallnumstatus().equalsIgnoreCase("0")) {
                    invalid = info.getCountnum();
                } else if (info.getSmallnumstatus().equalsIgnoreCase("1")) {
                    valid = info.getCountnum();
                }
            }
            tvAltBlack.setText(String.valueOf(invalid));
            tvAltNormal.setText(String.valueOf(valid));
            tvAltCurrent.setText(String.valueOf(invalid + valid));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(Integer eventCode) {
        switch (eventCode) {
            case EventCode.GET_MONEY_INFO:
                tvAltBlack.setText(String.valueOf(UserManager.getInstance().moneyInfo.getAllInValidSmallNum()));
                tvAltNormal.setText(String.valueOf(UserManager.getInstance().moneyInfo.getAllValidSmallNum()));
                tvAltCurrent.setText(String.valueOf(UserManager.getInstance().moneyInfo.getAllSmallNum()));
                break;
        }
    }
}
