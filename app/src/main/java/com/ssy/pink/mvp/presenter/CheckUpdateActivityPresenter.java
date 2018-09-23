package com.ssy.pink.mvp.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.VersionInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.VersionResp;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.mvp.iview.ICheckUpdateActivityView;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.JsonUtils;

import rx.Subscriber;

/**
 * @author ssy
 * @date 2018/9/14
 */
public class CheckUpdateActivityPresenter extends BasePresenter {
    ICheckUpdateActivityView iView;

    public CheckUpdateActivityPresenter(ICheckUpdateActivityView iView) {
        this.iView = iView;
    }

    public void checkVersion() {
        PinkNet.getVerison(new Subscriber<CommonResp<VersionResp>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommonResp<VersionResp> versionRespCommonResp) {
                if (versionRespCommonResp.getCode().equalsIgnoreCase(ResponseCode.CODE_SUCCESS)) {
                    VersionInfo versionInfo = JsonUtils.toObject(versionRespCommonResp.getData().getContent(), VersionInfo.class);
                    iView.loadVersion(versionInfo);
                }
            }
        });

    }
}
