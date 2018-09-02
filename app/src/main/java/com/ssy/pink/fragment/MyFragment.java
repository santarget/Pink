package com.ssy.pink.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.activity.GroupActivity;
import com.ssy.pink.activity.MonthVipActivity;
import com.ssy.pink.activity.MyIdolActivity;
import com.ssy.pink.activity.SettingActivity;
import com.ssy.pink.base.BaseFragment;
import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.WeiboCustomerInfo;
import com.ssy.pink.iview.IMyFragmentView;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.presenter.MyFragmentPresenter;
import com.ssy.pink.view.CircleImageView;
import com.ssy.pink.view.dialog.LoginChooseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author ssy
 * @date 2018/8/10
 */
public class MyFragment extends BaseFragment implements IMyFragmentView{
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

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void DetoryViewAndThing() {
        unbinder.unbind();
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

        chooseDialog.show();
       /* if (orgsList != null) {
            chooseDialog.setDatas(orgsList);
        }*/
    }
}
