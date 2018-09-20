package com.ssy.greendao.helper;

import com.ssy.greendao.gen.DaoSession;
import com.ssy.pink.MyApplication;

/**
 * @author ssy
 * @date 2018/9/20
 */
public class HelperFactory {
    private static SmallInfoDbHelper smallInfoDbHelper;
    private static WeiboUserlInfoDbHelper weiboUserlInfoDbHelper;
    private static TokenDbHelper tokenDbHelper;

    public static SmallInfoDbHelper getSmallInfoDbHelper() {
        if (smallInfoDbHelper == null) {
            smallInfoDbHelper = new SmallInfoDbHelper(MyApplication.getInstance().getDaoSession().getSmallInfoDao());
        }
        return smallInfoDbHelper;
    }

    public static WeiboUserlInfoDbHelper getWeiboUserlInfoDbHelper() {
        if (weiboUserlInfoDbHelper == null) {
            weiboUserlInfoDbHelper = new WeiboUserlInfoDbHelper(MyApplication.getInstance().getDaoSession().getWeiboUserInfoDao());
        }
        return weiboUserlInfoDbHelper;
    }

    public static TokenDbHelper getTokenDbHelper() {
        if (tokenDbHelper == null) {
            tokenDbHelper = new TokenDbHelper(MyApplication.getInstance().getDaoSession().getWeiboTokenInfoDao());
        }
        return tokenDbHelper;
    }

}
