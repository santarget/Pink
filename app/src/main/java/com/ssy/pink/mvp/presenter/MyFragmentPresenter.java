package com.ssy.pink.mvp.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.CustomerInfo;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.SmallStatusInfo;
import com.ssy.pink.bean.weibo.WeiboUserInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.manager.WeiboManager;
import com.ssy.pink.mvp.iview.IMyFragmentView;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.network.api.WeiboNet;
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

    public void getSmallStutas() {
        PinkNet.getSmallStutas(UserManager.getInstance().userInfo.getCustomernum(), new Subscriber<CommonListResp<SmallStatusInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonListResp<SmallStatusInfo> smallStatusInfoCommonListResp) {
                iView.loadSmallCount(smallStatusInfoCommonListResp.getData());
            }
        });
    }

    public void getWeiboUserInfo() {
        if (WeiboManager.getInstance().mAccessToken == null) {
            iView.showToast("未获取到当前微博授权");
            return;
        }
        WeiboNet.getUserInfo(WeiboManager.getInstance().mAccessToken.getUid(), WeiboManager.getInstance().mAccessToken.getToken(),
                new Subscriber<WeiboUserInfo>() {
                    @Override
                    public void onCompleted() {
                        iView.finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.finishRefresh();
                        MyUtils.handleExcep(e);
                    }

                    @Override
                    public void onNext(WeiboUserInfo weiboUserInfo) {
                        WeiboManager.getInstance().userInfo = weiboUserInfo;
                    }
                });
    }

    /**
     * 同步主账号信息
     */
    public void syncCustomer(String fansOrgNum) {
        CustomerInfo userInfo = UserManager.getInstance().userInfo;
        PinkNet.syncCustomer(userInfo.getWeiboid(), userInfo.getWeibonum(), userInfo.getWeiboname(), fansOrgNum, new Subscriber<CommonResp<CustomerInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonResp<CustomerInfo> resp) {
                if (resp.getCode().equalsIgnoreCase(ResponseCode.CODE_SUCCESS)) {
                    UserManager.getInstance().userInfo = resp.getData();
                } else {
                    iView.showToast(resp.getMsg());
                }

            }
        });
    }
}
