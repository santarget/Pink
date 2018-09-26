package com.ssy.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.weibo.EmotionInfo;
import com.ssy.pink.bean.weibo.WeiboTokenInfo;
import com.ssy.pink.bean.weibo.WeiboUserInfo;

import com.ssy.greendao.gen.SmallInfoDao;
import com.ssy.greendao.gen.EmotionInfoDao;
import com.ssy.greendao.gen.WeiboTokenInfoDao;
import com.ssy.greendao.gen.WeiboUserInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig smallInfoDaoConfig;
    private final DaoConfig emotionInfoDaoConfig;
    private final DaoConfig weiboTokenInfoDaoConfig;
    private final DaoConfig weiboUserInfoDaoConfig;

    private final SmallInfoDao smallInfoDao;
    private final EmotionInfoDao emotionInfoDao;
    private final WeiboTokenInfoDao weiboTokenInfoDao;
    private final WeiboUserInfoDao weiboUserInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        smallInfoDaoConfig = daoConfigMap.get(SmallInfoDao.class).clone();
        smallInfoDaoConfig.initIdentityScope(type);

        emotionInfoDaoConfig = daoConfigMap.get(EmotionInfoDao.class).clone();
        emotionInfoDaoConfig.initIdentityScope(type);

        weiboTokenInfoDaoConfig = daoConfigMap.get(WeiboTokenInfoDao.class).clone();
        weiboTokenInfoDaoConfig.initIdentityScope(type);

        weiboUserInfoDaoConfig = daoConfigMap.get(WeiboUserInfoDao.class).clone();
        weiboUserInfoDaoConfig.initIdentityScope(type);

        smallInfoDao = new SmallInfoDao(smallInfoDaoConfig, this);
        emotionInfoDao = new EmotionInfoDao(emotionInfoDaoConfig, this);
        weiboTokenInfoDao = new WeiboTokenInfoDao(weiboTokenInfoDaoConfig, this);
        weiboUserInfoDao = new WeiboUserInfoDao(weiboUserInfoDaoConfig, this);

        registerDao(SmallInfo.class, smallInfoDao);
        registerDao(EmotionInfo.class, emotionInfoDao);
        registerDao(WeiboTokenInfo.class, weiboTokenInfoDao);
        registerDao(WeiboUserInfo.class, weiboUserInfoDao);
    }
    
    public void clear() {
        smallInfoDaoConfig.clearIdentityScope();
        emotionInfoDaoConfig.clearIdentityScope();
        weiboTokenInfoDaoConfig.clearIdentityScope();
        weiboUserInfoDaoConfig.clearIdentityScope();
    }

    public SmallInfoDao getSmallInfoDao() {
        return smallInfoDao;
    }

    public EmotionInfoDao getEmotionInfoDao() {
        return emotionInfoDao;
    }

    public WeiboTokenInfoDao getWeiboTokenInfoDao() {
        return weiboTokenInfoDao;
    }

    public WeiboUserInfoDao getWeiboUserInfoDao() {
        return weiboUserInfoDao;
    }

}
