package com.ssy.greendao.helper;

import com.ssy.greendao.gen.SmallInfoDao;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.utils.ArrayUtils;

import org.greenrobot.greendao.query.WhereCondition;

/**
 * @author ssy
 * @date 2018/9/20
 */
public class SmallInfoDbHelper extends BaseDbHelper<SmallInfoDao, SmallInfo> {

    public SmallInfoDbHelper(SmallInfoDao dao) {
        super(dao);
    }

    public SmallInfo uniqueQuery(long smallWeiboId) {
        return super.uniqueQuery("and", new WhereCondition[]{SmallInfoDao.Properties.WeibosmallNumId.eq(smallWeiboId)});
    }


    public void deleteById(long smallWeiboId) {
        SmallInfo smallInfo = uniqueQuery(smallWeiboId);
        if (smallInfo != null) {
            delete(uniqueQuery(smallWeiboId));
        }
    }

    public void deleteByIdsStr(String smallWeiboIds) {
        String[] ids = smallWeiboIds.split(";");
        if (!ArrayUtils.isEmpty(ids)) {
            for (String id : ids) {
                deleteById(Long.valueOf(id));
            }
        }
    }
}
