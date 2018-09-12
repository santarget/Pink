package com.ssy.pink.mvp.presenter;

import com.ssy.pink.MyApplication;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.WeiboCustomerInfo;
import com.ssy.pink.bean.WeiboUserInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.manager.WeiboManager;
import com.ssy.pink.mvp.iview.ILoginActivityView;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.network.api.WeiboNet;
import com.ssy.pink.utils.MyUtils;

import rx.Subscriber;

public class LoginActivityPresenter extends BasePresenter {
    private ILoginActivityView iView;

    public LoginActivityPresenter(ILoginActivityView iView) {
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
            public void onNext(CommonListResp commonListResp) {
                iView.hasGotOrgs(true);
                iView.setOrgsList(commonListResp.getData());

            }
        });

    }

    public void getWeiboUserInfo(final String weiboNum, final String fansOrgNum) {
        WeiboNet.getUserInfo(new Subscriber<WeiboUserInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WeiboUserInfo weiboUserInfo) {
                WeiboManager.getInstance().userInfo = weiboUserInfo;
                syncCustomer(WeiboManager.getInstance().mAccessToken.getUid(), weiboNum, weiboUserInfo.getName(), fansOrgNum);
            }
        });
    }

    /**
     * 同步主账号信息
     */
    public void syncCustomer(String weiboId, String weiboNum, String weiboName, String fansOrgNum) {
        PinkNet.syncCustomer(weiboId, weiboNum, weiboName, fansOrgNum, new Subscriber<CommonResp<WeiboCustomerInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonResp<WeiboCustomerInfo> resp) {
                if (resp.getCode().equalsIgnoreCase(ResponseCode.CODE_SUCCESS)) {
                    UserManager.getInstance().userInfo = resp.getData();
                    MyApplication.token = resp.getData().getSessionid();
                    iView.toMainActivity();
                } else {
                    iView.showToast(resp.getMsg());
                }

            }
        });
    }
}
