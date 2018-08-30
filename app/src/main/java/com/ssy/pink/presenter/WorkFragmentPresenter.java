package com.ssy.pink.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.iview.IWorkFragmentView;
import com.ssy.pink.network.api.PinkNet;

import rx.Subscriber;

public class WorkFragmentPresenter extends BasePresenter {
    private IWorkFragmentView iView;

    public WorkFragmentPresenter(IWorkFragmentView iView) {
        this.iView = iView;
    }

    public void test() {

    }


}
