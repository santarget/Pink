package com.ssy.pink;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.ssy.pink.common.ConfigProp;
import com.ssy.pink.glide.OkHttpUrlLoader;
import com.ssy.pink.network.OkHttpClientProvider;
import com.ssy.pink.utils.LogUtil;
import com.ssy.pink.utils.SharedPreferencesUtil;
import com.ssy.pink.view.header.MaterialHeader;

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
        initSmartRefreshLayout();
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


}
