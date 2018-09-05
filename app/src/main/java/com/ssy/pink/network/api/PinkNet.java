package com.ssy.pink.network.api;

import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.RechargeRecordInfo;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.ProductInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.SmallStatusInfo;
import com.ssy.pink.bean.UserProductInfo;
import com.ssy.pink.bean.WeiboCustomerInfo;
import com.ssy.pink.bean.request.AddGroupReq;
import com.ssy.pink.bean.request.DeleteGroupReq;
import com.ssy.pink.bean.request.OnlyCustomernumReq;
import com.ssy.pink.bean.request.OrderProductReq;
import com.ssy.pink.bean.request.SyncRechargeRecordReq;
import com.ssy.pink.bean.request.SyncCustomerReq;
import com.ssy.pink.bean.request.SyncSpendRecordReq;
import com.ssy.pink.bean.request.UpdateGroupReq;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
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

    public static Subscription listFansOrg(Subscriber<CommonListResp<FansOrgInfo>> subscriber) {
        Subscription subscription = getPinkApi().listFansOrg()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription listProduct(Subscriber<CommonListResp<ProductInfo>> subscriber) {
        Subscription subscription = getPinkApi().listProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    /**
     * @param weiboId    微博id
     * @param weiboNum   微博账号
     * @param weiboName  微博名
     * @param fansOrgNum 粉丝组织编号
     * @param subscriber
     * @return
     */
    public static Subscription syncCustomer(String weiboId, String weiboNum, String weiboName, String fansOrgNum,
                                            Subscriber<CommonResp<WeiboCustomerInfo>> subscriber) {
        SyncCustomerReq req = new SyncCustomerReq();
        req.setWeiboid(weiboId);
        req.setWeibonum(weiboNum);
        req.setWeiboname(weiboName);
        req.setFansorginfonum(fansOrgNum);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().syncCustomer(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription syncRechargeRecord(Subscriber<CommonResp<RechargeRecordInfo>> subscriber) {
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

    public static Subscription syncSpendRecord(Subscriber<NoBodyEntity> subscriber) {
        SyncSpendRecordReq req = new SyncSpendRecordReq();
        req.setCustomernum("C0827002054681009578");
        req.setTransactionid("12312312223231");
        req.setSpendbeannum(123321);
        req.setSpenddesc("消费说明，买了个啥");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().syncSpendRecord(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    /**
     * 订购某个产品
     *
     * @param subscriber
     * @return
     */
    public static Subscription orderProduct(Subscriber<NoBodyEntity> subscriber) {
        OrderProductReq req = new OrderProductReq();
        req.setCustomernum("C0825231708734008524");
        req.setTransactionid("1231231222323231");
        req.setProductnum("2");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().orderProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    /**
     * 查询已经订购的产品
     *
     * @param subscriber
     * @return
     */
    public static Subscription listOrderedInfo(String customerNum, Subscriber<CommonListResp<UserProductInfo>> subscriber) {
        OnlyCustomernumReq req = new OnlyCustomernumReq();
        req.setCustomernum(customerNum);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().listOrderedInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription getUserMoney(String customerNum, Subscriber<CommonResp<MoneyInfo>> subscriber) {
        OnlyCustomernumReq req = new OnlyCustomernumReq();
        req.setCustomernum(customerNum);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().getUserMoney(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;

    }

    /**
     * 查询分组信息
     *
     * @param customerNum
     * @param subscriber
     * @return
     */
    public static Subscription listGroup(String customerNum, Subscriber<CommonListResp<GroupInfo>> subscriber) {
        OnlyCustomernumReq req = new OnlyCustomernumReq();
        req.setCustomernum(customerNum);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().listGroup(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    /**
     * 查询所有绑定小号的信息
     *
     * @param customerNum
     * @param subscriber
     * @return
     */
    public static Subscription listSmall(String customerNum, Subscriber<CommonListResp<SmallInfo>> subscriber) {
        OnlyCustomernumReq req = new OnlyCustomernumReq();
        req.setCustomernum(customerNum);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().listSmall(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    /**
     * 获取全部和有效的小号数量
     *
     * @param customerNum
     * @param subscriber
     * @return
     */
    public static Subscription getSmallStutas(String customerNum, Subscriber<CommonListResp<SmallStatusInfo>> subscriber) {
        OnlyCustomernumReq req = new OnlyCustomernumReq();
        req.setCustomernum(customerNum);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().getSmallStutas(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription addGroup(String customerNum, String name, Subscriber<CommonResp<GroupInfo>> subscriber) {
        AddGroupReq req = new AddGroupReq(customerNum, name);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().addGroup(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription deleteGroup(String customerNum, String groupNum, Subscriber<CommonResp<NoBodyEntity>> subscriber) {
        DeleteGroupReq req = new DeleteGroupReq(customerNum, groupNum);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().deleteGroup(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription updateGroup(String customerNum, String groupNum, String newName, Subscriber<CommonResp<GroupInfo>> subscriber) {
        UpdateGroupReq req = new UpdateGroupReq(customerNum, groupNum,newName);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().updateGroup(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }


}
