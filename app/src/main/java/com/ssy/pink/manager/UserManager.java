package com.ssy.pink.manager;

import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.UserProductInfo;
import com.ssy.pink.bean.WeiboCustomerInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.MyUtils;

import rx.Subscriber;

public class UserManager {
    public static UserManager instance;
    public WeiboCustomerInfo userInfo;
    public UserProductInfo orderedInfo;
    public MoneyInfo moneyInfo;

    private UserManager() {

    }

    public void init() {
        getUserInfo();
        listOrderedInfo();
        getUserMoney();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    /**
     * 同步主账号信息
     */
    public void getUserInfo() {
        PinkNet.syncCustomer(new Subscriber<CommonResp<WeiboCustomerInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonResp<WeiboCustomerInfo> weiboCustomerInfoCommonListResp) {
                userInfo = (WeiboCustomerInfo) weiboCustomerInfoCommonListResp.getData();
            }
        });
    }

    /**
     * 列举用户已订购产品
     */
    public void listOrderedInfo() {
        PinkNet.listOrderedInfo(new Subscriber<CommonListResp<UserProductInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonListResp<UserProductInfo> userProductInfoCommonListResp) {
                orderedInfo = (UserProductInfo) userProductInfoCommonListResp.getData();
            }
        });
    }

    /**
     * 获取用户的金额信息
     */
    public void getUserMoney() {
        PinkNet.getUserMoney(new Subscriber<CommonResp<MoneyInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonResp<MoneyInfo> moneyInfoCommonListResp) {
                moneyInfo = (MoneyInfo) moneyInfoCommonListResp.getData();
            }
        });
    }
}
