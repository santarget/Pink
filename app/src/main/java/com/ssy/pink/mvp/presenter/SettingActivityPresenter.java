package com.ssy.pink.mvp.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.VersionInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.VersionResp;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.mvp.iview.ISettingActivityView;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.JsonUtils;

import rx.Subscriber;

/**
 * @author ssy
 * @date 2018/9/14
 */
public class SettingActivityPresenter extends BasePresenter {
    ISettingActivityView iView;

    public SettingActivityPresenter(ISettingActivityView iView) {
        this.iView = iView;
    }
}
