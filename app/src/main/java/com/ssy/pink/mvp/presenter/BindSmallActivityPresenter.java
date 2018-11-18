package com.ssy.pink.mvp.presenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.ssy.greendao.helper.HelperFactory;
import com.ssy.greendao.helper.SmallInfoDbHelper;
import com.ssy.greendao.helper.SmallStatusDbHelper;
import com.ssy.greendao.helper.WeiboLoginDbHelper;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.BindLogInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.exception.ClientException;
import com.ssy.pink.bean.response.CheckSmallResp;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.bean.weibo.PreLoginInfo;
import com.ssy.pink.bean.weibo.WeiboLoginInfo;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.EventWithObj;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.BindManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.iview.IBindSmallActivityView;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.network.api.sina.SinaSSO;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.utils.MyUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
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
    private SmallStatusDbHelper statusDbHelper;
    private final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private Thread thread;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_BIND_NEXT:
                    iView.getAdapter().notifyDataSetChanged();
                    totalList.remove(0);
                    if (ListUtils.isEmpty(totalList)) {
                    } else {
                        bindWeiboSingle();
                    }
                    break;
            }
        }
    };

    public BindSmallActivityPresenter(IBindSmallActivityView iBindSmallActivityView, Activity activity) {
        this.iView = iBindSmallActivityView;
        this.activity = activity;
//        mSsoHandler = new SsoHandler(activity);
        smallDbHelper = HelperFactory.getSmallInfoDbHelper();
        weiboDbHelper = HelperFactory.getWeiboLoginDbHelper();
        statusDbHelper = HelperFactory.getSmallStatusDbHelper();
    }

    public void bindSmall() {
        totalList.clear();
        List<SmallInfo> checkList = new ArrayList<>();
        checkList.addAll(BindManager.getInstance().smallInfos);
        checkSmall(checkList);
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
                        handleBindFail(ClientException.WEIBO_LOGIN_NULL_ERROR + "_账号验证不通过");
                    } else if (!weiboLoginInfo.getRetcode().equals("0")) {
                        if (weiboLoginInfo.getRetcode().equals("4049")) {
                            //输入验证码
                            PreLoginInfo preLoginInfo = SinaSSO.getInstance().getServerInfo();
                            if (preLoginInfo == null) {
                                handleBindFail(weiboLoginInfo.getRetcode() + "_" + weiboLoginInfo.getReason());
                            } else {
                                File file = SinaSSO.getInstance().downloadImage(preLoginInfo);
                                if (file != null && file.exists()) {
                                    iView.showCodeInputDialog(preLoginInfo);
                                } else {
                                    handleBindFail(ClientException.WEIBO_LOGIN_PIC_ERROR + "_验证码获取失败");
                                }
                            }
                        } else {
                            failList.add(bindingLogInfo.getSmallInfo());
                            iView.setCurrentProgress(getFinishCount());
                            bindingLogInfo.setStatus(0);
                            bindingLogInfo.setMsg(weiboLoginInfo.getRetcode() + "_" + weiboLoginInfo.getReason());
                            handler.sendEmptyMessage(CODE_BIND_NEXT);
                        }
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
                    handler.sendEmptyMessage(CODE_BIND_NEXT);
                }
            }
        });

    }

    /**
     * 输入验证码重新绑定
     */
    public void reBind(final PreLoginInfo preLoginInfo, final String door) {
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                WeiboLoginInfo weiboLoginInfo = SinaSSO.getInstance().loginWithDoor(preLoginInfo, bindingLogInfo.getSmallInfo().getSmallWeiboNum(), bindingLogInfo.getSmallInfo().getUsepwd(), door);
                if (weiboLoginInfo == null) {
                    handleBindFail(ClientException.WEIBO_LOGIN_NULL_ERROR + "_" + "账号验证不通过");
                } else if (!weiboLoginInfo.getRetcode().equals("0")) {
                    handleBindFail(weiboLoginInfo.getRetcode() + "_" + weiboLoginInfo.getReason());
                } else {
                    weiboDbHelper.insertOrReplace(weiboLoginInfo);
                    bindingLogInfo.getSmallInfo().setWeibosmallNumId(weiboLoginInfo.getUid());
                    bindingLogInfo.setMsg("账号验证通过，正在绑定到大号");
                    bindSmallSingle(bindingLogInfo);
                }
            }
        });
    }

    public void cancelRebind() {
        handleBindFail(ClientException.WEIBO_LOGIN_PIC_CANCEL_ERROR + "_" + "取消输入验证码");
    }

    private void bindSmallSingle(final BindLogInfo bindLogInfo) {
        final SmallInfo info = bindLogInfo.getSmallInfo();

        Subscription subscription = PinkNet.bindSmall(UserManager.getInstance().userInfo.getCustomernum(), info.getWeibosmallNumId(), info.getSmallWeiboNum(),
                info.getUsepwd(), info.getSmallWeiboName(), BindManager.getInstance().groupInfo.getCustomergroupnum(), new Subscriber<CommonResp<NoBodyEntity>>() {
                    @Override
                    public void onCompleted() {
                        iView.setCurrentProgress(getFinishCount());
                        handler.sendEmptyMessage(CODE_BIND_NEXT);
                    }

                    @Override
                    public void onError(Throwable e) {
                        failList.add(info);
                        bindLogInfo.setStatus(0);
                        bindLogInfo.setMsg(e.getMessage());

                        iView.setCurrentProgress(getFinishCount());
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
                            statusDbHelper.insertOrReplace(info.getWeibosmallNumId(), true);
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

    private void checkSmall(final List<SmallInfo> checkList) {
        StringBuilder sb = new StringBuilder();
        for (SmallInfo smallInfo : checkList) {
            sb.append(smallInfo.getSmallWeiboNum()).append(";");
        }
        String smallWeiboNum = sb.toString();
        if (!TextUtils.isEmpty(smallWeiboNum)) {
            Subscription subscription = PinkNet.checkSmall(UserManager.getInstance().userInfo.getCustomernum(), smallWeiboNum,
                    new Subscriber<CommonListResp<CheckSmallResp>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            MyUtils.handleExcep(e);
                        }

                        @Override
                        public void onNext(CommonListResp<CheckSmallResp> checkSmallRespCommonListResp) {
                            if (checkSmallRespCommonListResp.getCode().equals(ResponseCode.CODE_SUCCESS)) {
                                List<CheckSmallResp> result = checkSmallRespCommonListResp.getData();
                                for (CheckSmallResp resp : result) {
                                    for (int i = checkList.size() - 1; i >= 0; i--) {
                                        SmallInfo smallInfo = checkList.get(i);
                                        if (resp.getWeiboNum().equals(smallInfo.getSmallWeiboNum())) {
                                            if (!resp.isExist()) {//未被占用
                                                totalList.add(smallInfo);
                                            } else {
                                                failList.add(smallInfo);
                                            }
                                            checkList.remove(smallInfo);
                                            break;
                                        }
                                    }
                                }
                                handleCheckFail();
                                if (ListUtils.isEmpty(totalList)) {
                                    iView.setCurrentProgress(getFinishCount());
                                } else {
                                    bindWeiboSingle();
                                }
                            } else {
                                iView.showToast("检查微博账号是否被占用失败_" + checkSmallRespCommonListResp.getMsg());
                            }
                        }
                    });
            mSubscriptions.add(subscription);
        }
    }

    private void handleBindFail(String msg) {
        failList.add(bindingLogInfo.getSmallInfo());
        iView.setCurrentProgress(getFinishCount());
        bindingLogInfo.setStatus(0);
        bindingLogInfo.setMsg(msg);
        handler.sendEmptyMessage(CODE_BIND_NEXT);
    }

    private void handleCheckFail() {
        List<BindLogInfo> list = new ArrayList<>();
        for (SmallInfo smallInfo : failList) {
            BindLogInfo logInfo = new BindLogInfo();
            logInfo.setSmallInfo(smallInfo);
            logInfo.setStatus(0);
            logInfo.setMsg("该账号已被占用");
            list.add(logInfo);
        }
        iView.getAdapter().getDatas().addAll(0, list);
        iView.getAdapter().notifyDataSetChanged();
    }

    public void onDestroy() {
        mSubscriptions.clear();
    }

}
