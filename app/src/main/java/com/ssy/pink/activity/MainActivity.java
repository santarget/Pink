package com.ssy.pink.activity;

import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ssy.pink.MyApplication;
import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.base.BaseFragment;
import com.ssy.pink.fragment.MyFragment;
import com.ssy.pink.fragment.SuperFragment;
import com.ssy.pink.fragment.WorkFragment;
import com.ssy.pink.iview.IMainActivityView;
import com.ssy.pink.presenter.MainActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ssy
 * @date 2018/8/9
 */
public class MainActivity extends BaseActivity implements IMainActivityView {
    private static final int FRAGMENT_WORK = 0;
    private static final int FRAGMENT_SUPER = 1;
    private static final int FRAGMENT_MY = 2;
    @BindView(R.id.tvWork)
    TextView tvWork;
    @BindView(R.id.tvSuper)
    TextView tvSuper;
    @BindView(R.id.tvMy)
    TextView tvMy;
    @BindView(R.id.flBody)
    FrameLayout flBody;
    @BindView(R.id.aivWork)
    AppCompatImageView aivWork;
    @BindView(R.id.aivSuper)
    AppCompatImageView aivSuper;
    @BindView(R.id.aivMy)
    AppCompatImageView aivMy;
    private WorkFragment workFragment;
    private MyFragment myFragment;
    private SuperFragment superFragment;
    private BaseFragment currentFragment;
    private MainActivityPresenter presenter;
    private long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainActivityPresenter(this);
        selectFragment(FRAGMENT_WORK);
    }


    @OnClick({R.id.tvWork, R.id.tvMy, R.id.tvSuper, R.id.aivWork, R.id.aivMy, R.id.aivSuper})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvWork:
            case R.id.aivWork:
                selectFragment(FRAGMENT_WORK);
                break;
            case R.id.tvSuper:
            case R.id.aivSuper:
                selectFragment(FRAGMENT_SUPER);
                break;
            case R.id.tvMy:
            case R.id.aivMy:
                selectFragment(FRAGMENT_MY);
                break;
        }
    }

    private void transFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentFragment == fragment) {
            return;
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        this.currentFragment = fragment;
        fragment.setUserVisibleHint(true);
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.flBody, fragment);
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        long thisTime = System.currentTimeMillis();
        if (lastTime == 0) {
            lastTime = thisTime;
        }
        if (lastTime != thisTime && (thisTime - lastTime) < 1000) {
            super.onBackPressed();
                /*Process.killProcess(Process.myPid());
                System.exit(0);*/
        } else {
            lastTime = thisTime;
            showToast(R.string.back_exit);
        }
    }

    private void selectFragment(int fragment) {
        if (FRAGMENT_WORK == fragment) {
            tvWork.setSelected(true);
            tvMy.setSelected(false);
            tvSuper.setSelected(false);
            aivWork.setSelected(true);
            aivMy.setSelected(false);
            aivSuper.setSelected(false);
            if (workFragment == null) {
                workFragment = new WorkFragment();
            }
            transFragment(workFragment);
        } else if (FRAGMENT_SUPER == fragment) {
            tvWork.setSelected(false);
            tvMy.setSelected(false);
            tvSuper.setSelected(true);
            aivWork.setSelected(false);
            aivMy.setSelected(false);
            aivSuper.setSelected(true);
            if (superFragment == null) {
                superFragment = new SuperFragment();
            }
            transFragment(superFragment);
        } else {
            tvWork.setSelected(false);
            tvMy.setSelected(true);
            tvSuper.setSelected(false);
            aivWork.setSelected(false);
            aivMy.setSelected(true);
            aivSuper.setSelected(false);
            if (myFragment == null) {
                myFragment = new MyFragment();
            }
            transFragment(myFragment);
        }
    }
}
