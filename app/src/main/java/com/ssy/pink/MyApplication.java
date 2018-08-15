package com.ssy.pink;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.ssy.pink.common.ConfigProp;
import com.ssy.pink.glide.OkHttpUrlLoader;
import com.ssy.pink.network.OkHttpClientProvider;
import com.ssy.pink.utils.LogUtil;
import com.ssy.pink.utils.SharedPreferencesUtil;

import java.io.InputStream;


public class MyApplication extends Application {
    private static MyApplication instance;
    /**
     * 用户相关
     */
    public static String token = "";
    public static long tokenTimeStamp = 0;//获取token的时间戳，30min有效
    //Database相关
    private final String dbName = "PINK_DATABASE";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initCrashReport();
        //初始化配置文件
        initConfigProp();
        //初始化网络
        //让Glide能获取https图片
        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpClientProvider.getNoTokenClient()));
        doUpgrade();
    }

    private void initCrashReport() {
//        ReportManager.getInstance().init();
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


}
