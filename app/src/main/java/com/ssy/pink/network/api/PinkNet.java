package com.ssy.pink.network.api;

import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.RechargeRecordInfo;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.ProductInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.SmallStatusInfo;
import com.ssy.pink.bean.UserProductInfo;
import com.ssy.pink.bean.CustomerInfo;
import com.ssy.pink.bean.request.AddGroupReq;
import com.ssy.pink.bean.request.BindSmallReq;
import com.ssy.pink.bean.request.CheckSmallReq;
import com.ssy.pink.bean.request.DeleteGroupReq;
import com.ssy.pink.bean.request.DeleteSmallReq;
import com.ssy.pink.bean.request.MoveSmallReq;
import com.ssy.pink.bean.request.OnlyCustomernumReq;
import com.ssy.pink.bean.request.OrderProductReq;
import com.ssy.pink.bean.request.SyncRechargeRecordReq;
import com.ssy.pink.bean.request.SyncCustomerReq;
import com.ssy.pink.bean.request.SyncSpendRecordReq;
import com.ssy.pink.bean.request.UpdateGroupReq;
import com.ssy.pink.bean.response.CheckSmallResp;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.bean.response.VersionResp;
import com.ssy.pink.network.OkHttpClientProvider;
import com.ssy.pink.utils.JsonUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class PinkNet {
    private static CompositeSubscription mSubscriptions = new CompositeSubscription();
    private static PinkApi pinkApi, noSessionPinkApi;

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

    private static PinkApi getNoSessionPinkApi() {
        if (noSessionPinkApi == null) {
            synchronized (PinkNet.class) {
                if (noSessionPinkApi == null) {
                    noSessionPinkApi = OkHttpClientProvider.getNoSessionPinkRetrofit().create(PinkApi.class);
                }
            }
        }
        return noSessionPinkApi;
    }

    public static Subscription listFansOrg(Subscriber<CommonListResp<FansOrgInfo>> subscriber) {
        Subscription subscription = getNoSessionPinkApi().listFansOrg()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription listProduct(Subscriber<CommonListResp<ProductInfo>> subscriber) {
        Subscription subscription = getNoSessionPinkApi().listProduct()
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
                                            Subscriber<CommonResp<CustomerInfo>> subscriber) {
        SyncCustomerReq req = new SyncCustomerReq();
        req.setWeiboid(weiboId);
        req.setWeibonum(weiboNum);
        req.setWeiboname(weiboName);
        req.setFansorginfonum(fansOrgNum);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getNoSessionPinkApi().syncCustomer(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription syncRechargeRecord(String customerNum, String transactionid, long addmountval,
                                                  long beannum, Subscriber<CommonResp<RechargeRecordInfo>> subscriber) {
        SyncRechargeRecordReq req = new SyncRechargeRecordReq();
        req.setCustomernum(customerNum);
        req.setTransactionid(transactionid);
        req.setAddmountval(addmountval);
        req.setBeannum(beannum);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().syncRechargeRecord(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription syncSpendRecord(String customerNum, String transactionid, long spendbeannum,
                                               String spenddesc, Subscriber<NoBodyEntity> subscriber) {
        SyncSpendRecordReq req = new SyncSpendRecordReq();
        req.setCustomernum(customerNum);
        req.setTransactionid(transactionid);
        req.setSpendbeannum(spendbeannum);
        req.setSpenddesc(spenddesc);
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
    public static Subscription orderProduct(String customerNum, String transactionid, String productnum, int quantity, Subscriber<CommonResp<NoBodyEntity>> subscriber) {
        OrderProductReq req = new OrderProductReq();
        req.setCustomernum(customerNum);
        req.setTransactionid(transactionid);
        req.setProductnum(productnum);
        req.setQuantity(quantity);
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
        UpdateGroupReq req = new UpdateGroupReq(customerNum, groupNum, newName);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().updateGroup(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }

    public static Subscription bindSmall(final String customerNum, final String smallWeiboId, final String smallWeiboNum,
                                         final String smallWeiboPwd, final String smallWeiboName, final String customerGroupNum,
                                         Observer<CommonResp<NoBodyEntity>> observer) {
        BindSmallReq req = new BindSmallReq(customerNum, smallWeiboId, smallWeiboNum, smallWeiboPwd, smallWeiboName, customerGroupNum);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().bindSmall(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
//        mSubscriptions.add(subscription);
        return subscription;
    }

    /**
     * 删除微博小号
     *
     * @param customerNum
     * @param smallWeiboId 可以是多个小号的id,多个之间采用“;”进行拼接
     * @param observer
     * @return
     */
    public static Subscription deleteSmall(final String customerNum, final String smallWeiboId, Subscriber<CommonResp<NoBodyEntity>> observer) {
        DeleteSmallReq req = new DeleteSmallReq(customerNum, smallWeiboId);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().deleteSmall(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        mSubscriptions.add(subscription);
        return subscription;
    }

    /**
     * 移动微博小号
     *
     * @param customerNum
     * @param smallWeiboId 可以是多个小号的id,多个之间采用“;”进行拼接
     * @param observer
     * @return
     */
    public static Subscription moveSmall(final String customerNum, final String smallWeiboId, String groupNum, Subscriber<CommonResp<NoBodyEntity>> observer) {
        MoveSmallReq req = new MoveSmallReq(customerNum, smallWeiboId, groupNum);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().moveSmall(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        mSubscriptions.add(subscription);
        return subscription;
    }

    /**
     * 检查微博账号是否被占用
     *
     * @param customerNum
     * @param weiboNumStr 多个微博账号,之间采用“;”进行拼接
     * @param subscriber
     * @return
     */
    public static Subscription checkSmall(final String customerNum, final String weiboNumStr, Subscriber<CommonListResp<CheckSmallResp>> subscriber) {
        CheckSmallReq req = new CheckSmallReq(customerNum, weiboNumStr);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));
        Subscription subscription = getPinkApi().checkSmall(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscription;
    }
    public static Subscription getVerison(Subscriber<CommonResp<VersionResp>> observer) {
        Subscription subscription = getNoSessionPinkApi().getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        mSubscriptions.add(subscription);
        return subscription;
    }


}
