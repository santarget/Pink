package com.ssy.pink.network.api;

import android.util.Log;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.ssy.pink.MyApplication;
import com.ssy.pink.bean.EmotionInfo;
import com.ssy.pink.bean.WeiboInfo;
import com.ssy.pink.bean.WeiboUserInfo;
import com.ssy.pink.bean.request.DeleteSmallReq;
import com.ssy.pink.bean.request.ShareWeiboReq;
import com.ssy.pink.bean.response.WeiboListResp;
import com.ssy.pink.manager.WeiboManager;
import com.ssy.pink.network.OkHttpClientProvider;
import com.ssy.pink.utils.JsonUtils;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    /**
     * 获取当前用户最新微博 最多5条
     *
     * @param subscriber
     * @return
     */
    public static Subscription getLatestWeibo(Subscriber<WeiboListResp> subscriber) {
        Subscription subscription = getWeiboApi().getLatestWeibo(WeiboManager.getInstance().mAccessToken.getToken(),
                WeiboManager.getInstance().mAccessToken.getUid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription getEmotions(Subscriber<List<EmotionInfo>> subscriber) {
        Subscription subscription = getWeiboApi().getEmotions(WeiboManager.getInstance().mAccessToken.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    /**
     * @param status     用户分享到微博的文本内容，必须做URLencode，内容不超过140个汉字，文本中不能包含“#话题词#”，
     *                   同时文本中必须包含至少一个第三方分享到微博的网页URL，且该URL只能是该第三方（调用方）绑定域下的URL链接，
     *                   绑定域在“我的应用 － 应用信息 － 基本应用信息编辑 － 安全域名”里设置。
     * @param subscriber
     * @return
     */
    public static Subscription shareWeibo(String status, Subscriber<WeiboInfo> subscriber) {
        ShareWeiboReq req = new ShareWeiboReq(WeiboManager.getInstance().mAccessToken.getToken(), status);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), JsonUtils.toString(req));
//        RequestBody requestBody = RequestBody.create(MediaType.parse("x-www-form-urlencoded"), JsonUtils.toString(req));
        Subscription subscription = getWeiboApi().shareWeibo(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static void shareWeibo(String status, Callback callback) {
        Call call = getWeiboApi().share(WeiboManager.getInstance().mAccessToken.getToken(), status);
//        Call call = getWeiboApi().share(AccessTokenKeeper.readAccessToken(MyApplication.getInstance()).getToken(), status);
        call.enqueue(callback);
    }

}
