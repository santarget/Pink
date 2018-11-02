package com.ssy.pink.mvp.presenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.ssy.greendao.helper.HelperFactory;
import com.ssy.greendao.helper.SmallInfoDbHelper;
import com.ssy.greendao.helper.WeiboLoginDbHelper;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.BindLogInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.exception.ClientException;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.bean.weibo.WeiboLoginInfo;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.iview.IBindSmallActivityView;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.network.api.sina.SinaSSO;
import com.ssy.pink.utils.ListUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    //    private SsoHandler mSsoHandler;
    private BindLogInfo bindingLogInfo;//正在绑定的对象
    private SmallInfoDbHelper smallDbHelper;
    private WeiboLoginDbHelper weiboDbHelper;
    private final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
private final Thread thread = new Thread();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_BIND_NEXT:
                    totalList.remove(0);
                    if (ListUtils.isEmpty(totalList)) {
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
//        mSsoHandler = new SsoHandler(activity);
        smallDbHelper = HelperFactory.getSmallInfoDbHelper();
        weiboDbHelper = HelperFactory.getWeiboLoginDbHelper();
    }

    public void bindSmall() {
        totalList.clear();
        totalList.addAll(BindManager.getInstance().smallInfos);
        bindWeiboSingle();
    }

    private void bindWeiboSingle() {
        BindLogInfo logInfo = new BindLogInfo();
        logInfo.setSmallInfo(totalList.get(0));
        logInfo.setStatus(2);
        iView.getAdapter().getDatas().add(0, logInfo);
        iView.getAdapter().notifyItemChanged(0);
        bindingLogInfo = logInfo;
//        mSsoHandler.authorizeWeb(new SelfWbAuthListener());

        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    WeiboLoginInfo weiboLoginInfo = SinaSSO.getInstance().login(bindingLogInfo.getSmallInfo().getSmallWeiboNum(), bindingLogInfo.getSmallInfo().getUsepwd());
                    if (weiboLoginInfo == null) {
                        failList.add(bindingLogInfo.getSmallInfo());
                        iView.setCurrentProgress(getFinishCount());
                        bindingLogInfo.setStatus(0);
                        bindingLogInfo.setMsg(ClientException.WEIBO_LOGIN_NULL_ERROR + "_" + "账号验证不通过");
                        iView.getAdapter().notifyItemChanged(0);
                        handler.sendEmptyMessage(CODE_BIND_NEXT);
                    } else if (!weiboLoginInfo.getRetcode().equals("0")) {
                        failList.add(bindingLogInfo.getSmallInfo());
                        iView.setCurrentProgress(getFinishCount());
                        bindingLogInfo.setStatus(0);
                        bindingLogInfo.setMsg(weiboLoginInfo.getRetcode() + "_" + weiboLoginInfo.getReason());
                        iView.getAdapter().notifyItemChanged(0);
                        handler.sendEmptyMessage(CODE_BIND_NEXT);
                    } else {
//                        WeiboTokenInfo tokenInfo = new WeiboTokenInfo(token.getUid(), token.getToken(), token.getRefreshToken(),
//                                token.getExpiresTime(), 0);
//                        HelperFactory.getTokenDbHelper().insertOrReplace(tokenInfo);
//                        bindingLogInfo.getSmallInfo().setWeibosmallNumId(token.getUid());
//                        bindSmallSingle(bindingLogInfo);
                        weiboDbHelper.insertOrReplace(weiboLoginInfo);
                        bindingLogInfo.getSmallInfo().setWeibosmallNumId(weiboLoginInfo.getUid());
                        bindingLogInfo.setMsg("账号验证通过，正在绑定到大号");
                        bindSmallSingle(bindingLogInfo);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    failList.add(bindingLogInfo.getSmallInfo());
                    iView.setCurrentProgress(getFinishCount());
                    bindingLogInfo.setStatus(0);
                    bindingLogInfo.setMsg(ClientException.WEIBO_LOGIN_IO_ERROR + "_" + e.getMessage());
                    iView.getAdapter().notifyItemChanged(0);
                    handler.sendEmptyMessage(CODE_BIND_NEXT);
                }
            }
        });

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
                            smallDbHelper.insertOrReplace(smallInfo);
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


  /*  private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
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
            failList.add(bindingLogInfo.getSmallInfo());
            iView.setCurrentProgress(getFinishCount());
            bindingLogInfo.setStatus(0);
            bindingLogInfo.setMsg("登录取消");
            iView.getAdapter().notifyItemChanged(0);
            handler.sendEmptyMessage(CODE_BIND_NEXT);

        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            failList.add(bindingLogInfo.getSmallInfo());
            iView.setCurrentProgress(getFinishCount());
            bindingLogInfo.setStatus(0);
            bindingLogInfo.setMsg(errorMessage.getErrorCode() + "_" + errorMessage.getErrorMessage());
            iView.getAdapter().notifyItemChanged(0);
            handler.sendEmptyMessage(CODE_BIND_NEXT);
        }
    }*/

    public void onDestroy() {
        mSubscriptions.clear();
    }

}
