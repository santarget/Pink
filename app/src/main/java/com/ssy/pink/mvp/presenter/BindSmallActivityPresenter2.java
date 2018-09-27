package com.ssy.pink.mvp.presenter;

import android.app.Activity;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.ssy.greendao.helper.HelperFactory;
import com.ssy.greendao.helper.SmallInfoDbHelper;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.BindLogInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.bean.weibo.WeiboTokenInfo;
import com.ssy.pink.bean.weibo.WeiboUserInfo;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.iview.IBindSmallActivityView2;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.network.api.WeiboNet;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BindSmallActivityPresenter2 extends BasePresenter {
    private Activity activity;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private IBindSmallActivityView2 iView;
    private List<SmallInfo> successList = new ArrayList<>();
    private List<SmallInfo> failList = new ArrayList<>();
    private SsoHandler mSsoHandler;
    private BindLogInfo bindingLogInfo;//正在绑定的对象
    private SmallInfoDbHelper dbHelper;


    public BindSmallActivityPresenter2(IBindSmallActivityView2 iView, Activity activity) {
        this.iView = iView;
        this.activity = activity;
        mSsoHandler = new SsoHandler(activity);
        dbHelper = HelperFactory.getSmallInfoDbHelper();
    }

    public void bindWeiboSingle() {
        iView.showBindingDialog(true);
        SmallInfo smallInfo = new SmallInfo(UserManager.getInstance().userInfo.getCustomernum(), BindManager.getInstance().groupInfo.getCustomergroupnum(),
                BindManager.getInstance().groupInfo.getCustomergroupname(), null, null,
                "未知微博名称", null, UserManager.getInstance().userInfo.getFansorginfoname(),
                UserManager.getInstance().userInfo.getFansorginfonum(), "1");
        BindLogInfo logInfo = new BindLogInfo();
        logInfo.setSmallInfo(smallInfo);
        logInfo.setStatus(2);
        bindingLogInfo = logInfo;
        iView.getAdapter().getDatas().add(0, bindingLogInfo);
        iView.getAdapter().notifyDataSetChanged();
        mSsoHandler.authorizeWeb(new SelfWbAuthListener());
    }

    private void bindSmallSingle(final BindLogInfo bindLogInfo) {
        final SmallInfo info = bindLogInfo.getSmallInfo();

        Subscription subscription = PinkNet.bindSmall(UserManager.getInstance().userInfo.getCustomernum(), info.getWeibosmallNumId(), info.getSmallWeiboNum(),
                info.getUsepwd(), info.getSmallWeiboName(), BindManager.getInstance().groupInfo.getCustomergroupnum(), new Subscriber<CommonResp<NoBodyEntity>>() {
                    @Override
                    public void onCompleted() {
                        iView.getAdapter().notifyDataSetChanged();
                        iView.showBindingDialog(false);

                    }

                    @Override
                    public void onError(Throwable e) {
                        failList.add(info);
                        bindLogInfo.setStatus(0);
                        bindLogInfo.setMsg(e.getMessage());
                        iView.getAdapter().notifyDataSetChanged();
                        iView.showBindingDialog(false);
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
                            dbHelper.insertOrReplace(smallInfo);
                            successList.add(smallInfo);
                            bindLogInfo.setSmallInfo(smallInfo);
                            bindLogInfo.setStatus(1);
                        } else {
                            failList.add(info);
                            bindLogInfo.setStatus(0);
                            bindLogInfo.setMsg(noBodyEntityCommonResp.getMsg());
                        }
                    }

                });
        mSubscriptions.add(subscription);
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
                    WeiboTokenInfo tokenInfo = new WeiboTokenInfo().valueOf(token);
                    tokenInfo.setType(0);
                    HelperFactory.getTokenDbHelper().insertOrReplace(tokenInfo);
                    bindingLogInfo.getSmallInfo().setWeibosmallNumId(token.getUid());
                    WeiboNet.getUserInfo(token.getUid(), token.getToken(), new Subscriber<WeiboUserInfo>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            failList.add(bindingLogInfo.getSmallInfo());
                            bindingLogInfo.setStatus(0);
                            bindingLogInfo.setMsg("未获取到微博账号信息");
                            iView.getAdapter().notifyDataSetChanged();
                            iView.showBindingDialog(false);
                        }

                        @Override
                        public void onNext(WeiboUserInfo weiboUserInfo) {
                            bindingLogInfo.getSmallInfo().setSmallWeiboName(weiboUserInfo.getName());
                            bindSmallSingle(bindingLogInfo);
                        }
                    });
                }
            });
        }

        @Override
        public void cancel() {
            failList.add(bindingLogInfo.getSmallInfo());
            bindingLogInfo.setStatus(0);
            bindingLogInfo.setMsg("登录取消");
            iView.getAdapter().notifyDataSetChanged();
            iView.showBindingDialog(false);
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            failList.add(bindingLogInfo.getSmallInfo());
            bindingLogInfo.setStatus(0);
            bindingLogInfo.setMsg(errorMessage.getErrorCode() + "_" + errorMessage.getErrorMessage());
            iView.getAdapter().notifyDataSetChanged();
            iView.showBindingDialog(false);
        }
    }

    private void getWeiboUserInfo() {

    }

    public void onDestroy() {
        mSubscriptions.clear();
    }

}
