package com.ssy.greendao.helper;

import com.ssy.greendao.gen.LoopLogInfoDao;
import com.ssy.greendao.gen.WeiboTokenInfoDao;
import com.ssy.greendao.gen.WeiboUserInfoDao;
import com.ssy.pink.bean.weibo.LoopLogInfo;
import com.ssy.pink.bean.weibo.WeiboTokenInfo;
import com.ssy.pink.bean.weibo.WeiboUserInfo;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * @author ssy
 * @date 2018/9/20
 */
public class LoopLogInfoDbHelper extends BaseDbHelper<LoopLogInfoDao, LoopLogInfo> {

    public LoopLogInfoDbHelper(LoopLogInfoDao dao) {
        super(dao);
    }

    /**
     * 查询当前大号的所有轮博日志
     *
     * @return
     */
    public List<LoopLogInfo> queryAllLog(String weiboUid) {
        return query("and", new WhereCondition[]{LoopLogInfoDao.Properties.WeiboUid.eq(weiboUid)},
                null, null, "DESC", LoopLogInfoDao.Properties.Time);
    }
}
