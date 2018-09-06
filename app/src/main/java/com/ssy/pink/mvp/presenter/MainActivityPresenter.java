package com.ssy.pink.mvp.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.mvp.iview.IMainActivityView;

/**
 * @author ssy
 * @date 2018/8/10
 */
public class MainActivityPresenter extends BasePresenter {
    private IMainActivityView iView;

    public MainActivityPresenter(IMainActivityView iView) {
        this.iView = iView;
    }
}
