package com.ssy.greendao.helper;

import com.ssy.greendao.gen.WeiboTokenInfoDao;
import com.ssy.greendao.gen.WeiboUserInfoDao;
import com.ssy.pink.bean.WeiboTokenInfo;
import com.ssy.pink.bean.WeiboUserInfo;

import org.greenrobot.greendao.query.WhereCondition;

/**
 * @author ssy
 * @date 2018/9/20
 */
public class TokenDbHelper extends BaseDbHelper<WeiboTokenInfoDao, WeiboTokenInfo> {

    public TokenDbHelper(WeiboTokenInfoDao dao) {
        super(dao);
    }

    public WeiboTokenInfo uniqueQuery(long weiboId) {
        return super.uniqueQuery("and", new WhereCondition[]{WeiboTokenInfoDao.Properties.WeiboUid.eq(weiboId)});
    }
}
