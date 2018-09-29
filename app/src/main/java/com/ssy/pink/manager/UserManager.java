package com.ssy.pink.manager;

import com.ssy.greendao.helper.HelperFactory;
import com.ssy.pink.MyApplication;
import com.ssy.pink.bean.CustomerInfo;
import com.ssy.pink.bean.ProductInfo;
import com.ssy.pink.bean.weibo.EmotionInfo;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.UserProductInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.network.api.WeiboNet;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.utils.MyUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import rx.Subscriber;

public class UserManager {
    public static UserManager instance;
    public CustomerInfo userInfo = new CustomerInfo();
    public List<UserProductInfo> orderedInfos;//用户已订购的产品列表
    public MoneyInfo moneyInfo = new MoneyInfo();//用户的金额信息
    public FansOrgInfo fansOrgInfo;//当前用户所属的粉丝组织
    public List<ProductInfo> productInfos;//所有产品列表

    private UserManager() {

    }

    /**
     * 同步主账号信息后再调用
     */
    public void initAfterSync() {
        listProduct();
        listOrderedInfo();
        getUserMoney();
        getWeiboEmotions();
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

    public void listProduct() {
        PinkNet.listProduct(new Subscriber<CommonListResp<ProductInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonListResp<ProductInfo> productInfoCommonListResp) {
                if (ResponseCode.CODE_SUCCESS.equalsIgnoreCase(productInfoCommonListResp.getCode())) {
                    productInfos = productInfoCommonListResp.getData();
                }
            }
        });
    }

    /**
     * 同步主账号信息
     */
    public void syncCustomer(String weiboId, String weiboNum, String weiboName, String fansOrgNum) {
        PinkNet.syncCustomer(weiboId, weiboNum, weiboName, fansOrgNum, new Subscriber<CommonResp<CustomerInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonResp<CustomerInfo> weiboCustomerInfoCommonListResp) {
                if (ResponseCode.CODE_SUCCESS.equalsIgnoreCase(weiboCustomerInfoCommonListResp.getCode())) {
                    userInfo = weiboCustomerInfoCommonListResp.getData();
                    MyApplication.getInstance().setToken(userInfo.getSessionid());
                }
            }
        });
    }

    /**
     * 列举用户已订购产品
     */
    public void listOrderedInfo() {
        PinkNet.listOrderedInfo(userInfo.getCustomernum(), new Subscriber<CommonListResp<UserProductInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonListResp<UserProductInfo> userProductInfoCommonListResp) {
                if (ResponseCode.CODE_SUCCESS.equalsIgnoreCase(userProductInfoCommonListResp.getCode())) {
                    orderedInfos = userProductInfoCommonListResp.getData();
                }
            }
        });
    }

    /**
     * 获取用户的金额信息
     */
    public void getUserMoney() {
        PinkNet.getUserMoney(userInfo.getCustomernum(), new Subscriber<CommonResp<MoneyInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonResp<MoneyInfo> moneyInfoCommonResp) {
                EventBus.getDefault().post(EventCode.GET_MONEY_INFO);
                if (moneyInfoCommonResp.getData() != null) {
                    moneyInfo = moneyInfoCommonResp.getData();
                } else {
                    moneyInfo = new MoneyInfo();
                }
            }
        });
    }

    public void getWeiboEmotions() {
        WeiboNet.getEmotions(new Subscriber<List<EmotionInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<EmotionInfo> emotionInfos) {
                if (!ListUtils.isEmpty(emotionInfos)) {
                    HelperFactory.getEmotionDbHelper().deleteAll();
                    HelperFactory.getEmotionDbHelper().insertOrReplaceList(emotionInfos);
                }
            }
        });
    }

    /**
     * 退出登录，重置
     */
    public void reset() {
        userInfo = new CustomerInfo();
        orderedInfos = null;
        moneyInfo = new MoneyInfo();
        fansOrgInfo = null;
    }
}
