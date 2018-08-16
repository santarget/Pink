package com.ssy.pink.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.activity.MyIdolActivity;
import com.ssy.pink.activity.SettingActivity;
import com.ssy.pink.base.BaseFragment;
import com.ssy.pink.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author ssy
 * @date 2018/8/10
 */
public class MyFragment extends BaseFragment {
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

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }


    @OnClick({R.id.tvModify, R.id.rlMyIdol, R.id.llAddAccout, R.id.llSettings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvModify:
                break;
            case R.id.rlMyIdol:
                Intent i = new Intent(mainActivity, MyIdolActivity.class);
                mainActivity.startActivity(i);
                break;
            case R.id.llAddAccout:
                break;
            case R.id.llSettings:
                mainActivity.startActivity(new Intent(mainActivity, SettingActivity.class));
                break;
        }
    }
}
