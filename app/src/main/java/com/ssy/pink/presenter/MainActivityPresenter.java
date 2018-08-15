package com.ssy.pink.presenter;

import com.ssy.pink.iview.IMainActivityView;

/**
 * @author ssy
 * @date 2018/8/10
 */
public class MainActivityPresenter {
    private IMainActivityView iView;

    public MainActivityPresenter(IMainActivityView iView) {
        this.iView = iView;
    }
}
