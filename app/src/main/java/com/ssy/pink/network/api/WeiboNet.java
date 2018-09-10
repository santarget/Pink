package com.ssy.pink.network.api;

import android.util.Log;

import com.ssy.pink.bean.WeiboUserInfo;
import com.ssy.pink.bean.request.WeiboReq;
import com.ssy.pink.manager.WeiboManager;
import com.ssy.pink.network.OkHttpClientProvider;
import com.ssy.pink.utils.JsonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class WeiboNet {
    private static CompositeSubscription mSubscriptions = new CompositeSubscription();
    private static WeiboApi weiboApi;

    private static WeiboApi getWeiboApi() {
        if (weiboApi == null) {
            synchronized (WeiboNet.class) {
                if (weiboApi == null) {
                    weiboApi = OkHttpClientProvider.getWeiboRetrofit().create(WeiboApi.class);
                }
            }
        }
        return weiboApi;
    }

    public static Subscription getUserInfo(Subscriber<WeiboUserInfo> subscriber) {
        Subscription subscription = getWeiboApi().getWeiboUserInfo(WeiboManager.getInstance().mAccessToken.getToken(),
                WeiboManager.getInstance().mAccessToken.getUid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }


}
