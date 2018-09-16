package com.ssy.pink.network.api;

import com.ssy.pink.bean.WeiboInfo;
import com.ssy.pink.bean.request.RepostWeiboReq;
import com.ssy.pink.manager.WeiboManager;
import com.ssy.pink.network.OkHttpClientProvider;
import com.ssy.pink.utils.JsonUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class WeiboNet2 {
    private static CompositeSubscription mSubscriptions = new CompositeSubscription();
    private static WeiboApi2 weiboApi2;

    private static WeiboApi2 getWeiboApi2() {
        if (weiboApi2 == null) {
            synchronized (WeiboNet2.class) {
                if (weiboApi2 == null) {
                    weiboApi2 = OkHttpClientProvider.getWeiboRetrofit().create(WeiboApi2.class);
                }
            }
        }
        return weiboApi2;
    }

    public static Subscription repostWeibo(Subscriber<WeiboInfo> subscriber) {
        RepostWeiboReq req = new RepostWeiboReq();
        req.setId(4282854814589720L);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getWeiboApi2().repostWeibo(WeiboManager.getInstance().mAccessToken.getToken(),
                WeiboManager.getInstance().mAccessToken.getUid(), requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription uploadWeibo(Subscriber<WeiboInfo> subscriber) {
        RepostWeiboReq req = new RepostWeiboReq();
        req.setId(4282854814589720L);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getWeiboApi2().repostWeibo(WeiboManager.getInstance().mAccessToken.getToken(),
                WeiboManager.getInstance().mAccessToken.getUid(), requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }
}
