package com.ssy.greendao.helper;

import com.ssy.greendao.gen.WeiboUserInfoDao;
import com.ssy.pink.bean.weibo.WeiboUserInfo;

import org.greenrobot.greendao.query.WhereCondition;

/**
 * @author ssy
 * @date 2018/9/20
 */
public class WeiboUserlInfoDbHelper extends BaseDbHelper<WeiboUserInfoDao, WeiboUserInfo> {

    public WeiboUserlInfoDbHelper(WeiboUserInfoDao dao) {
        super(dao);
    }

    public WeiboUserInfo uniqueQuery(long weiboId) {
        return super.uniqueQuery("and", new WhereCondition[]{WeiboUserInfoDao.Properties.Id.eq(weiboId)});
    }
}
