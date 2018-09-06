package com.ssy.pink.mvp.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.mvp.iview.IAddSmallActivityView;

/**
 * @author ssy
 * @date 2018/9/6
 */
public class AddSmallActivityPresenter extends BasePresenter {
    private IAddSmallActivityView iView;

    public AddSmallActivityPresenter(IAddSmallActivityView iView) {
        this.iView = iView;
    }

    public void checkAccout(String str) {
        String[] accouts = str.split("\n");

    }
}
