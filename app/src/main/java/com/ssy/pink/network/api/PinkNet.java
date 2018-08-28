package com.ssy.pink.network.api;

import com.ssy.pink.bean.RechargeRecordInfo;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.ProductInfo;
import com.ssy.pink.bean.WeiboCustomerInfo;
import com.ssy.pink.bean.request.SyncRechargeRecordReq;
import com.ssy.pink.bean.request.SyncCustomerReq;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.network.OkHttpClientProvider;
import com.ssy.pink.utils.JsonUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class PinkNet {
    private static CompositeSubscription mSubscriptions = new CompositeSubscription();
    private static PinkApi pinkApi;

    private static PinkApi getPinkApi() {
        if (pinkApi == null) {
            synchronized (PinkNet.class) {
                if (pinkApi == null) {
                    pinkApi = OkHttpClientProvider.getPinkRetrofit().create(PinkApi.class);
                }
            }
        }
        return pinkApi;
    }

    public static Subscription listFansOrg(Subscriber<CommonResp<FansOrgInfo>> subscriber) {
        Subscription subscription = getPinkApi().listFansOrg()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription listProduct(Subscriber<CommonResp<ProductInfo>> subscriber) {
        Subscription subscription = getPinkApi().listProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription syncCustomer(Subscriber<CommonResp<WeiboCustomerInfo>> subscriber) {
        SyncCustomerReq req = new SyncCustomerReq();
        req.setWeiboid("weiboid97979");
        req.setWeibonum("1234567777");
        req.setWeiboname("weibo name ssy");
        req.setFansorginfonum("1");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().syncCustomer(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription addRechargeRecord(Subscriber<CommonResp<RechargeRecordInfo>> subscriber) {
        SyncRechargeRecordReq req = new SyncRechargeRecordReq();
        req.setCustomernum("weiboid97979");
        req.setTransactionid("1234567777");
        req.setAddmountval(11111);
        req.setBeannum(520);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().syncRechargeRecord(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }


}
