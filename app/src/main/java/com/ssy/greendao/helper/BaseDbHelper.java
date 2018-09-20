package com.ssy.greendao.helper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by zhengxiang_wu on 2017/3/13.
 */

public abstract class BaseDbHelper<T extends AbstractDao, W> {
    private final T dao;

    public BaseDbHelper(T dao) {
        this.dao = dao;
    }

    public long insert(@NonNull W obj) {
        return dao.insert(obj);
    }

    public void insert(@NonNull List<W> objs) {
        dao.insertInTx(objs);
    }

    public long insertOrReplace(@NonNull W obj) {
        return dao.insertOrReplace(obj);
    }

    public void insertOrReplaceList(@NonNull List<W> objs) {
        dao.insertOrReplaceInTx(objs);
    }

    public List<W> query(@Nullable String and_or, @Nullable WhereCondition[] whereArgs, @Nullable Integer offset, @Nullable Integer limit, @Nullable String order, @Nullable Property... filed) {
        QueryBuilder mBuilder = dao.queryBuilder();
        if (limit != null)
            mBuilder.limit(limit);
        if (offset != null)
            mBuilder.offset(offset);
        if (!TextUtils.isEmpty(order) && filed != null) {
            if (order.equalsIgnoreCase("ASC")) {
                mBuilder.orderAsc(filed);
            }
            if (order.equalsIgnoreCase("DESC")) {
                mBuilder.orderDesc(filed);
            }
        }
        if (whereArgs != null) {
            if (whereArgs.length == 1) {
                mBuilder.where(whereArgs[0]);
            } else {
                if (TextUtils.isEmpty(and_or)) {
                    throw new IllegalArgumentException("and_or is null");
                }
                WhereCondition where = null;
                if (and_or.equalsIgnoreCase("AND")) {
                    if (whereArgs.length == 2) {
                        where = mBuilder.and(whereArgs[0], whereArgs[1]);
                    } else if (whereArgs.length == 3) {
                        where = mBuilder.and(whereArgs[0], whereArgs[1], whereArgs[2]);
                    } else if (whereArgs.length == 4) {
                        where = mBuilder.and(whereArgs[0], whereArgs[1], whereArgs[2], whereArgs[3]);
                    }

                } else {
                    if (whereArgs.length == 2) {
                        where = mBuilder.or(whereArgs[0], whereArgs[1]);
                    } else if (whereArgs.length == 3) {
                        where = mBuilder.or(whereArgs[0], whereArgs[1], whereArgs[2]);
                    } else if (whereArgs.length == 4) {
                        where = mBuilder.or(whereArgs[0], whereArgs[1], whereArgs[2], whereArgs[3]);
                    }
                }
                mBuilder.where(where);
            }
        }
        return mBuilder.build().list();
    }

    public W uniqueQuery(@Nullable String and_or, @NonNull WhereCondition[] whereArgs) {
        QueryBuilder mBuilder = dao.queryBuilder();
        if (whereArgs.length == 1) {
            mBuilder.where(whereArgs[0]);
        } else {
            if (TextUtils.isEmpty(and_or)) {
                throw new IllegalArgumentException("and_or is null");
            }
            WhereCondition where = null;
            if (and_or.equalsIgnoreCase("AND")) {
                if (whereArgs.length == 2) {
                    where = mBuilder.and(whereArgs[0], whereArgs[1]);
                } else if (whereArgs.length == 3) {
                    where = mBuilder.and(whereArgs[0], whereArgs[1], whereArgs[2]);
                } else if (whereArgs.length == 4) {
                    where = mBuilder.and(whereArgs[0], whereArgs[1], whereArgs[2], whereArgs[3]);
                }
            } else {
                if (whereArgs.length == 2) {
                    where = mBuilder.or(whereArgs[0], whereArgs[1]);
                } else if (whereArgs.length == 3) {
                    where = mBuilder.or(whereArgs[0], whereArgs[1], whereArgs[2]);
                } else if (whereArgs.length == 4) {
                    where = mBuilder.or(whereArgs[0], whereArgs[1], whereArgs[2], whereArgs[3]);
                }
            }
            mBuilder.where(where);
        }
        return (W) mBuilder.build().unique();
    }

    public void update(@NonNull W newObj) {
        dao.update(newObj);
    }

    public void delete(@NonNull W obj) {
        dao.delete(obj);
    }

    public void deleteAll() {
        dao.deleteAll();
    }

}
