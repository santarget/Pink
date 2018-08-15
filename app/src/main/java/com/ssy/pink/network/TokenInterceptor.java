package com.ssy.pink.network;

import com.ssy.pink.MyApplication;
import com.ssy.pink.utils.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ssy
 * @date 2018/7/3
 */
public class TokenInterceptor implements Interceptor {
    private final String TAG = "TokenInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (isTokenExpired()) {
            LogUtil.i(TAG, "token is out of time,refresh now.");
            getNewToken();
            //使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .build();
            //重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }

    /**
     * 根据Response，判断Token是否失效 *
     *
     * @return
     */
    private boolean isTokenExpired() {
        if (MyApplication.tokenTimeStamp != 0 && System.currentTimeMillis() - MyApplication.tokenTimeStamp > 28 * 60 * 1000) {
            return true;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private void getNewToken() throws IOException {
//        LoginManager.getInstance().refreshToken();
    }
}