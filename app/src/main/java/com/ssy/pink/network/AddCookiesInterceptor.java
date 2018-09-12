package com.ssy.pink.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ssy.pink.manager.UserManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;

/**
 * @author ssy
 * @date 2018/9/12
 */
public class AddCookiesInterceptor implements Interceptor {
//    private Context context;
//    private String lang;

    public AddCookiesInterceptor() {
        super();
//        this.context = context;
//        this.lang = lang;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (chain == null)
            Log.d("http", "Addchain == null");
        final Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("sessionId", UserManager.getInstance().userInfo.getSessionid());
        Log.d("============", "AddCookiesInterceptor:" + UserManager.getInstance().userInfo.getSessionid());
        return chain.proceed(builder.build());
    }
}
