package com.ssy.greendao.helper;

import android.text.TextUtils;

import com.ssy.greendao.gen.WeiboLoginInfoDao;
import com.ssy.greendao.gen.WeiboTokenInfoDao;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.weibo.WeiboLoginInfo;
import com.ssy.pink.bean.weibo.WeiboTokenInfo;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * @author ssy
 * @date 2018/9/20
 */
public class WeiboLoginDbHelper extends BaseDbHelper<WeiboLoginInfoDao, WeiboLoginInfo> {

    public WeiboLoginDbHelper(WeiboLoginInfoDao dao) {
        super(dao);
    }

    public WeiboLoginInfo uniqueQuery(String weiboId) {
        if (TextUtils.isEmpty(weiboId)) {
            return null;
        }
        return super.uniqueQuery("and", new WhereCondition[]{WeiboLoginInfoDao.Properties.Uid.eq(weiboId)});
    }

    public void deleteById(String uid) {
        WeiboLoginInfo weiboLoginInfo = uniqueQuery(uid);
        if (weiboLoginInfo != null) {
            delete(weiboLoginInfo);
        }
    }
    /**
     * 查询所有大号的微博授权
     *
     * @return
     *//*
    public List<WeiboLoginInfo> queryAllBig() {
        return query("and", new WhereCondition[]{WeiboLoginInfoDao.Properties.Type.eq(1)},
                null, null, null);
    }*/
}
