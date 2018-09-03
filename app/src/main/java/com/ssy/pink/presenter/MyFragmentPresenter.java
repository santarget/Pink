package com.ssy.pink.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.iview.IMyFragmentView;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.MyUtils;

import rx.Subscriber;

public class MyFragmentPresenter extends BasePresenter {
    private IMyFragmentView iView;

    public MyFragmentPresenter(IMyFragmentView iView) {
        this.iView = iView;
    }

    public void listFansOrg() {
        PinkNet.listFansOrg(new Subscriber<CommonListResp<FansOrgInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
                iView.showToast("组织获取失败");
                iView.hasGotOrgs(false);
            }

            @Override
            public void onNext(CommonListResp<FansOrgInfo> fansOrgInfoCommonListResp) {
                iView.hasGotOrgs(true);
                iView.setFansOrgList(fansOrgInfoCommonListResp.getData());
            }
        });

    }
}
