package com.ssy.pink.manager;

import android.widget.Toast;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.ssy.pink.MyApplication;
import com.ssy.pink.R;
import com.ssy.pink.common.ConstantWeibo;

public class WeiboManager {
    private static WeiboManager instance;
    private AuthInfo mAuthInfo;
    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    public Oauth2AccessToken mAccessToken;


    public static WeiboManager getInstance() {
        if (instance == null) {
            synchronized (WeiboManager.class) {
                if (instance == null) {
                    instance = new WeiboManager();
                }
            }
        }
        return instance;
    }

    private WeiboManager() {
        init();
    }

    private void init() {
        mAuthInfo = new AuthInfo(MyApplication.getInstance(), ConstantWeibo.APP_KEY, ConstantWeibo.REDIRECT_URL, ConstantWeibo.SCOPE);
        WbSdk.install(MyApplication.getInstance(), mAuthInfo);
    }
}
