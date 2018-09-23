package com.ssy.pink.mvp.presenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.ssy.greendao.helper.HelperFactory;
import com.ssy.greendao.helper.SmallInfoDbHelper;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.BindLogInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.WeiboTokenInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.iview.IBindSmallActivityView;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BindSmallActivityPresenter extends BasePresenter {
    private static final int CODE_BIND_NEXT = 1;
    private Activity activity;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private IBindSmallActivityView iView;
    private List<SmallInfo> successList = new ArrayList<>();
    private List<SmallInfo> failList = new ArrayList<>();
    private List<SmallInfo> totalList = new ArrayList<>();//待绑定的小号集合，用于计数
    private SsoHandler mSsoHandler;
    private BindLogInfo bindingLogInfo;//正在绑定的对象
    private SmallInfoDbHelper dbHelper;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_BIND_NEXT:
                    totalList.remove(0);
                    if (ListUtils.isEmpty(totalList)) {
                        Log.i("aaaa", "绑定结束");
                    } else {
                        bindWeiboSingle();
                    }
                    break;
            }
        }
    };

    public BindSmallActivityPresenter(IBindSmallActivityView iView, Activity activity) {
        this.iView = iView;
        this.activity = activity;
        mSsoHandler = new SsoHandler(activity);
        dbHelper = HelperFactory.getSmallInfoDbHelper();
    }

    public void bindSmall() {
        totalList.clear();
        totalList.addAll(BindManager.getInstance().smallInfos);
        bindWeiboSingle();
       /* for (SmallInfo info : BindManager.getInstance().smallInfos) {
            BindLogInfo logInfo = new BindLogInfo();
            logInfo.setSmallInfo(info);
            logInfo.setStatus(2);
            logInfos.add(0, logInfo);
            iView.getAdapter().notifyItemChanged(0);
            bindWeiboSingle(logInfo);
        }*/
    }

    private void bindWeiboSingle() {
        BindLogInfo logInfo = new BindLogInfo();
        logInfo.setSmallInfo(totalList.get(0));
        logInfo.setStatus(2);
        iView.getAdapter().getDatas().add(0, logInfo);
        iView.getAdapter().notifyItemChanged(0);
        bindingLogInfo = logInfo;
        mSsoHandler.authorizeWeb(new SelfWbAuthListener());
    }

    private void bindSmallSingle(final BindLogInfo bindLogInfo) {
        final SmallInfo info = bindLogInfo.getSmallInfo();

        Subscription subscription = PinkNet.bindSmall(UserManager.getInstance().userInfo.getCustomernum(), info.getWeibosmallNumId(), info.getSmallWeiboNum(),
                info.getUsepwd(), info.getSmallWeiboName(), BindManager.getInstance().groupInfo.getCustomergroupnum(), new Subscriber<CommonResp<NoBodyEntity>>() {
                    @Override
                    public void onCompleted() {
                        iView.setCurrentProgress(getFinishCount());
                        iView.getAdapter().notifyDataSetChanged();
                        handler.sendEmptyMessage(CODE_BIND_NEXT);
                    }

                    @Override
                    public void onError(Throwable e) {
                        failList.add(info);
                        bindLogInfo.setStatus(0);
                        bindLogInfo.setMsg(e.getMessage());

                        iView.setCurrentProgress(getFinishCount());
                        iView.getAdapter().notifyDataSetChanged();
                        handler.sendEmptyMessage(CODE_BIND_NEXT);
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
                    WeiboTokenInfo tokenInfo = new WeiboTokenInfo(token.getUid(), token.getToken(), token.getRefreshToken(),
                            token.getExpiresTime(), 0);
                    HelperFactory.getTokenDbHelper().insertOrReplace(tokenInfo);
                    bindingLogInfo.getSmallInfo().setWeibosmallNumId(token.getUid());
                    bindSmallSingle(bindingLogInfo);
                }
            });
        }

        @Override
        public void cancel() {
            Log.i("aaaa", "绑定取消：" + bindingLogInfo.getSmallInfo().getSmallWeiboNum());
            failList.add(bindingLogInfo.getSmallInfo());
            iView.setCurrentProgress(getFinishCount());
            bindingLogInfo.setStatus(0);
            bindingLogInfo.setMsg("登录取消");
            iView.getAdapter().notifyItemChanged(0);
            handler.sendEmptyMessage(CODE_BIND_NEXT);
            iView.setCurrentProgress(getFinishCount());
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Log.i("aaaa", "绑定失败：" + bindingLogInfo.getSmallInfo().getSmallWeiboNum());
            failList.add(bindingLogInfo.getSmallInfo());
            iView.setCurrentProgress(getFinishCount());
            bindingLogInfo.setStatus(0);
            bindingLogInfo.setMsg(errorMessage.getErrorCode() + "_" + errorMessage.getErrorMessage());
            iView.getAdapter().notifyItemChanged(0);
        }
    }

    public void onDestroy() {
        mSubscriptions.clear();
    }

}
