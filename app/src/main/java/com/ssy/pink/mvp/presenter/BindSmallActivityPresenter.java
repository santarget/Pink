package com.ssy.pink.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.BindLogInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.manager.WeiboManager;
import com.ssy.pink.mvp.activity.LoginActivity;
import com.ssy.pink.mvp.iview.IBindSmallActivityView;
import com.ssy.pink.network.api.PinkNet;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BindSmallActivityPresenter extends BasePresenter {
    private Activity activity;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private IBindSmallActivityView iView;
    private List<SmallInfo> successList = new ArrayList<>();
    private List<SmallInfo> failList = new ArrayList<>();

    public BindSmallActivityPresenter(IBindSmallActivityView iView, Activity activity) {
        this.iView = iView;
        this.activity = activity;
    }

    public void bindSmall() {
        List<BindLogInfo> logInfos = iView.getAdapter().getDatas();
        for (SmallInfo info : BindManager.getInstance().smallInfos) {
            BindLogInfo logInfo = new BindLogInfo();
            logInfo.setSmallInfo(info);
            logInfo.setStatus(2);
            logInfos.add(0, logInfo);
            iView.getAdapter().notifyItemChanged(0);
            bindSmallSingle(logInfo);
        }
    }

    private void bindSmallSingle(final BindLogInfo bindLogInfo) {
        final SmallInfo info = bindLogInfo.getSmallInfo();

        Subscription subscription = PinkNet.bindSmall(UserManager.getInstance().userInfo.getCustomernum(), info.getWeibosmallNumId(), info.getSmallWeiboNum(),
                info.getUsepwd(), info.getSmallWeiboName(), BindManager.getInstance().groupInfo.getCustomergroupnum(), new Subscriber<CommonResp<NoBodyEntity>>() {
                    @Override
                    public void onCompleted() {
                        iView.setCurrentProgress(getFinishCount());
                        iView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.setCurrentProgress(getFinishCount());
                        iView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onNext(CommonResp<NoBodyEntity> noBodyEntityCommonResp) {
                        if (noBodyEntityCommonResp.getCode().equalsIgnoreCase(ResponseCode.CODE_SUCCESS)) {
                            SmallInfo smallInfo = new SmallInfo(UserManager.getInstance().userInfo.getCustomernum(),
                                    BindManager.getInstance().groupInfo.getCustomergroupnum(),
                                    BindManager.getInstance().groupInfo.getCustomergroupname(),
                                    info.getSmallWeiboNum(), info.getUsepwd(), info.getSmallWeiboName(),
                                    info.getWeibosmallNumId(), UserManager.getInstance().userInfo.getFansorginfoname(),
                                    UserManager.getInstance().userInfo.getFansorginfonum(), info.getSmallNumStatus());
                            successList.add(smallInfo);
                            bindLogInfo.setSmallInfo(smallInfo);
                            bindLogInfo.setStatus(1);
                        } else {
                            failList.add(info);
                            bindLogInfo.setStatus(0);
                            // TODO: 2018/9/10  
                            bindLogInfo.setMsg("账号密码不正确");
                        }
                    }

                });
        mSubscriptions.add(subscription);
    }

    private int getFinishCount() {
        return successList.size() + failList.size();
    }

    public List<SmallInfo> getFailList() {
        return failList;
    }


    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    WeiboManager.getInstance().mAccessToken = token;
                    if (WeiboManager.getInstance().mAccessToken.isSessionValid()) {
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(activity, token);
//                        presenter.getWeiboUserInfo(etAccout.getText().toString(), UserManager.getInstance().fansOrgInfo.getFansorginfonum());
                    }
                }
            });
        }

        @Override
        public void cancel() {
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
        }
    }

    public void onDestroy() {
        mSubscriptions.clear();
    }

}
