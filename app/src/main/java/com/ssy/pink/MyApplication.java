package com.ssy.pink;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.ssy.greendao.gen.DaoMaster;
import com.ssy.greendao.gen.DaoSession;
import com.ssy.greendao.helper.MyOpenHelper;
import com.ssy.pink.common.ConfigProp;
import com.ssy.pink.common.Constants;
import com.ssy.pink.glide.OkHttpUrlLoader;
import com.ssy.pink.manager.WeiboManager;
import com.ssy.pink.network.OkHttpClientProvider;
import com.ssy.pink.utils.LogUtil;
import com.ssy.pink.utils.SharedPreferencesUtil;
import com.ssy.pink.view.header.MaterialHeader;

import java.io.InputStream;

import cn.testin.analysis.data.TestinDataApi;
import cn.testin.analysis.data.TestinDataConfig;


public class MyApplication extends Application {
    private static MyApplication instance;
    /**
     * 用户相关
     */
    private String token = "";
    public static long tokenTimeStamp = 0;//获取token的时间戳，30min有效
    //Database相关
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initCrashReport();
        initSmartRefreshLayout();
        //初始化配置文件
        initConfigProp();
        initDatabase();
        //让Glide能获取https图片
        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpClientProvider.getNoTokenClient()));
        doUpgrade();
        WeiboManager.getInstance();
    }

    private void initCrashReport() {
//        ReportManager.getInstance().init();
        //设置启动参数
        TestinDataConfig testinDataConfig = new TestinDataConfig()
                .openShake(false)//设置是否打开摇一摇反馈bug功能
                .collectCrash(true)//设置是否收集app崩溃信息
                .collectANR(true)//设置是否收集ANR异常信息
                .collectLogCat(false)//设置是否收集logcat系统日志
                .collectUserSteps(true);//设置是否收集用户操作步骤
        //SDK初始化
        TestinDataApi.init(this, Constants.BUGOUT_APP_KEY, testinDataConfig);
    }

    private void initSmartRefreshLayout() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new MaterialHeader(context).setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                BallPulseFooter footer = new BallPulseFooter(context);
                layout.setPrimaryColors(context.getResources().getColor(R.color.colorPrimary));
                return footer;
            }
        });
    }

    /**
     * 版本更新操作，只在升级时执行一次
     */
    private void doUpgrade() {
        if (SharedPreferencesUtil.getLastVersion() < BuildConfig.VERSION_CODE) {
            SharedPreferencesUtil.setLastVersion(BuildConfig.VERSION_CODE);
            LogUtil.e("MyApplication", "doUpgrade to version " + BuildConfig.VERSION_NAME
                    + "(" + BuildConfig.VERSION_CODE + ")");
        }
    }


    private void initConfigProp() {
        ConfigProp.loadConfigProp();
    }

    /**
     * 获取MyApplication的实例
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 初始化greenDao
     */
    private void initDatabase() {
        final String dbName = "PINK_DATABASE";
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        MyOpenHelper helper = new MyOpenHelper(this, dbName, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        daoSession.clear();////清空所有数据表的缓存
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            initDatabase();
        }
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public void setToken(String token) {
        this.token = token;
        SharedPreferencesUtil.setLastToken(token);
    }

    public String getToken() {
//        if (TextUtils.isEmpty(token)) {
//            token = SharedPreferencesUtil.getLastToken();
//            Log.i("aaaa", "getToken 空：" + token);
//        } else {
//            Log.i("aaaa", "getToken 不为空：" + token);
//
//        }
        Log.i("aaaa", "getToken ：" + token);
        return token;
    }
}
