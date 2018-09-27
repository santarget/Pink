package com.ssy.greendao.helper;

import android.text.TextUtils;

import com.ssy.greendao.gen.WeiboTokenInfoDao;
import com.ssy.greendao.gen.WeiboUserInfoDao;
import com.ssy.pink.bean.weibo.WeiboTokenInfo;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * @author ssy
 * @date 2018/9/20
 */
public class TokenDbHelper extends BaseDbHelper<WeiboTokenInfoDao, WeiboTokenInfo> {

    public TokenDbHelper(WeiboTokenInfoDao dao) {
        super(dao);
    }

    public WeiboTokenInfo uniqueQuery(String weiboId) {
        if (TextUtils.isEmpty(weiboId)) {
            return null;
        }
        return super.uniqueQuery("and", new WhereCondition[]{WeiboTokenInfoDao.Properties.WeiboUid.eq(weiboId)});
    }

    /**
     * 查询所有大号的微博授权
     *
     * @return
     */
    public List<WeiboTokenInfo> queryAllBig() {
        return query("and", new WhereCondition[]{WeiboTokenInfoDao.Properties.Type.eq(1)},
                null, null, null);
    }
}
