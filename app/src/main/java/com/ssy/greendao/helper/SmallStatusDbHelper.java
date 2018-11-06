package com.ssy.greendao.helper;

import android.text.TextUtils;

import com.ssy.greendao.gen.SmallStatusDao;
import com.ssy.pink.bean.SmallStatus;

import org.greenrobot.greendao.query.WhereCondition;

/**
 * @author ssy
 * @date 2018/9/20
 */
public class SmallStatusDbHelper extends BaseDbHelper<SmallStatusDao, SmallStatus> {

    public SmallStatusDbHelper(SmallStatusDao dao) {
        super(dao);
    }

    public SmallStatus uniqueQuery(String uid) {
        if (TextUtils.isEmpty(uid)) {
            return null;
        }
        return super.uniqueQuery("and", new WhereCondition[]{SmallStatusDao.Properties.Uid.eq(uid)});
    }

    public long insertOrReplace(String uid, boolean normal) {
        SmallStatus smallStatus = new SmallStatus(uid, normal);
        return super.insertOrReplace(smallStatus);
    }

    public void deleteById(String uid) {
        SmallStatus smallStatus = uniqueQuery(uid);
        if (smallStatus != null) {
            delete(smallStatus);
        }
    }
}
