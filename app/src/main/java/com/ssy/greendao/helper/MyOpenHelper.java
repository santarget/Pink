package com.ssy.greendao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.ssy.greendao.gen.DaoMaster;
import com.ssy.greendao.gen.EmotionInfoDao;
import com.ssy.greendao.gen.LoopLogInfoDao;
import com.ssy.greendao.gen.SmallInfoDao;
import com.ssy.greendao.gen.WeiboTokenInfoDao;
import com.ssy.greendao.gen.WeiboUserInfoDao;

import org.greenrobot.greendao.database.Database;

/**
 * @author ssy 避免升级数据库丢失数据
 * @date 2018/10/8
 * <p>
 * 1）新增的字段或修改的字段，其变量类型应使用基础数据类型的包装类，如使用Integer而不是int，避免升级过程中报错。
 * 2）根据MigrationHelper中的代码，升级后，新增的字段和修改的字段，都会默认被赋予null值。
 */
public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("aaaa", "onUpgrade:" + oldVersion + "***" + newVersion);
        //把需要管理的数据库表DAO作为最后一个参数传入到方法中
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                Log.i("aaaa", "onCreateAllTables:" + ifNotExists);
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                Log.i("aaaa", "onDropAllTables:" + ifExists);
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, EmotionInfoDao.class, SmallInfoDao.class, WeiboTokenInfoDao.class, WeiboUserInfoDao.class, LoopLogInfoDao.class);
    }


}
