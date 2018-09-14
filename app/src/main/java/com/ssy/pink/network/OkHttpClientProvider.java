package com.ssy.pink.network;

import android.content.res.AssetManager;

import com.ssy.pink.MyApplication;
import com.ssy.pink.common.ConfigProp;
import com.ssy.pink.utils.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * 提供OkHttpClient
 * 带或不带token请求头，需不需要校验https证书。是否需要校验证书由配置文件决定，其他地方不允许修改。
 *
 * @author ssy
 * @date 2018/7/2
 */
public class OkHttpClientProvider {
    private static OkHttpClient weiboClient, pinkClient, noTokenClient;
    private static Retrofit weiboRetrofit, weiboRetrofit2, pinkRetrofit, noSessionRetrofit;

    public static OkHttpClient getWeiboClient() {
        if (weiboClient == null) {
            synchronized (OkHttpClientProvider.class) {
                if (weiboClient == null) {
                    weiboClient = getUnsafeBuilder(false).build();
                }
            }
        }
        return weiboClient;
    }

    public static OkHttpClient getNoTokenClient() {
        if (noTokenClient == null) {
            synchronized (OkHttpClientProvider.class) {
                if (noTokenClient == null) {
                    if (ConfigProp.verifyCert) {
                        noTokenClient = getSafeBuilder(false).build();
                    } else {
                        noTokenClient = getUnsafeBuilder(false).build();
                    }
                }
            }
        }
        return noTokenClient;
    }

    public static OkHttpClient getPinkClient() {
        if (pinkClient == null) {
            synchronized (OkHttpClientProvider.class) {
                if (pinkClient == null) {
                    if (ConfigProp.verifyCert) {
                        pinkClient = getSafeBuilder(true).build();
                    } else {
                        pinkClient = getUnsafeBuilder(true).build();
                    }
                }
            }
        }
        return pinkClient;
    }

    private static OkHttpClient.Builder getSafeBuilder(boolean hasToken) {
        HttpsUtils.SSLParams sslParams = null;
        try {
            sslParams = HttpsUtils.getSslSocketFactory(
                    new InputStream[]{MyApplication.getInstance().getApplicationContext().getAssets().open(ConfigProp.certName, AssetManager.ACCESS_RANDOM)},
                    null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .hostnameVerifier(new MyHostnameVerifier())
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtil.i("safeclient", message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new TokenInterceptor())
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .readTimeout(15000, TimeUnit.MILLISECONDS)
                .writeTimeout(15000, TimeUnit.MILLISECONDS);
        if (hasToken) {
            addTokenHeader(builder);
        }
        return builder;
    }

    private static OkHttpClient.Builder getUnsafeBuilder(boolean hasToken) {
        OkHttpClient.Builder builder = UnsafeOkHttpClient.getUnsafeOkHttpClientBuilder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtil.i("serverdata", message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new TokenInterceptor())
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .readTimeout(8000, TimeUnit.MILLISECONDS)
                .writeTimeout(8000, TimeUnit.MILLISECONDS);
        if (hasToken) {
//            addTokenHeader(builder);
            addSessionHeader(builder);
        }
        return builder;
    }

    private static void addSessionHeader(OkHttpClient.Builder builder) {
        builder.addInterceptor(new AddCookiesInterceptor());
    }

    /**
     * 添加带token的请求头
     *
     * @param builder
     */
    private static void addTokenHeader(OkHttpClient.Builder builder) {
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
//                        .addHeader("Authorization", MyApplication.token)
                        .addHeader("sessionid", MyApplication.token)
                        .addHeader("Content-Type", "application/json; charset=UTF-8")
//                    .addHeader("Accept-Encoding", "*")
//                    .addHeader("Connection", "keep-alive")
//                    .addHeader("Accept", "*/*")
//                    .addHeader("Access-Control-Allow-Origin", "*")
//                    .addHeader("Access-Control-Allow-Headers", "X-Requested-With")
//                    .addHeader("Vary", "Accept-Encoding")
                        .build();
                return chain.proceed(request);
            }
        });
    }

    public static Retrofit getWeiboRetrofit() {
        if (weiboRetrofit == null) {
            synchronized (OkHttpClientProvider.class) {
                if (weiboRetrofit == null) {
                    weiboRetrofit = new Retrofit.Builder()
                            .baseUrl("https://api.weibo.com/")
                            .addConverterFactory(NobodyConverterFactory.create())
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(OkHttpClientProvider.getWeiboClient())
                            .build();
                }
            }
        }
        return weiboRetrofit;
    }

    public static Retrofit getWeiboRetrofit2() {
        if (weiboRetrofit2 == null) {
            synchronized (OkHttpClientProvider.class) {
                if (weiboRetrofit2 == null) {
                    weiboRetrofit2 = new Retrofit.Builder()
                            .baseUrl("http://api.t.sina.com.cn/")
                            .addConverterFactory(NobodyConverterFactory.create())
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(OkHttpClientProvider.getWeiboClient())
                            .build();
                }
            }
        }
        return weiboRetrofit2;
    }

    public static Retrofit getPinkRetrofit() {
        if (pinkRetrofit == null) {
            synchronized (OkHttpClientProvider.class) {
                if (pinkRetrofit == null) {
                    pinkRetrofit = new Retrofit.Builder()
                            .baseUrl(ConfigProp.serverUrl)
                            .addConverterFactory(NobodyConverterFactory.create())//无响应体时
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //增加返回值为Oservable<T>的支持
                            .client(OkHttpClientProvider.getPinkClient())
                            .build();
                }
            }
        }
        return pinkRetrofit;
    }

    public static Retrofit getNoSessionPinkRetrofit() {
        if (noSessionRetrofit == null) {
            synchronized (OkHttpClientProvider.class) {
                if (noSessionRetrofit == null) {
                    noSessionRetrofit = new Retrofit.Builder()
                            .baseUrl(ConfigProp.serverUrl)
                            .addConverterFactory(NobodyConverterFactory.create())
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(OkHttpClientProvider.getNoTokenClient())
                            .build();
                }
            }
        }
        return noSessionRetrofit;
    }
}
