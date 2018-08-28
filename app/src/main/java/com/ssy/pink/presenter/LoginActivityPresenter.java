package com.ssy.pink.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.iview.ILoginActivityView;
import com.ssy.pink.network.api.PinkNet;

import java.util.List;

import rx.Subscriber;

public class LoginActivityPresenter extends BasePresenter {
    private ILoginActivityView iView;

    public LoginActivityPresenter(ILoginActivityView iView) {
        this.iView = iView;
    }

    public void listFansOrg() {

        PinkNet.listFansOrg(new Subscriber<CommonResp<FansOrgInfo>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(CommonResp commonResp) {
                List<FansOrgInfo> infos = commonResp.getData();
                iView.showToast(infos.toString());
            }
        });

    }
}
