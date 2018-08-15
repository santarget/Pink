package com.ssy.pink.network;

import android.content.res.AssetManager;
import android.text.TextUtils;

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
    private static OkHttpClient client, noTokenClient, taskClient;
    private static Retrofit retrofit, uamRetrofit, ufmRetrofit;

    public static OkHttpClient getClient() {
        if (client == null) {
            synchronized (OkHttpClientProvider.class) {
                if (client == null) {
                    if (ConfigProp.verifyCert) {
                        client = getSafeBuilder(true).build();
                    } else {
                        client = getUnsafeBuilder(true).build();
                    }
                }
            }
        }
        return client;
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

    public static OkHttpClient getTaskClient() {
        if (taskClient == null) {
            synchronized (OkHttpClientProvider.class) {
                if (taskClient == null) {
                    if (ConfigProp.verifyCert) {
                        OkHttpClient.Builder builder = getSafeBuilder(false);
                        taskClient = addTaskHeader(builder).build();

                    } else {
                        OkHttpClient.Builder builder = getUnsafeBuilder(false);
                        taskClient = addTaskHeader(builder).build();
                    }
                }
            }
        }
        return taskClient;
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
                        LogUtil.i("unsafeclient", message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new TokenInterceptor())
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .readTimeout(8000, TimeUnit.MILLISECONDS)
                .writeTimeout(8000, TimeUnit.MILLISECONDS);
        if (hasToken) {
            addTokenHeader(builder);
        }
        return builder;
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
                        .addHeader("Authorization", MyApplication.token)
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

    /**
     * 上传下载任务的的请求头
     *
     * @param builder
     */
    private static OkHttpClient.Builder addTaskHeader(OkHttpClient.Builder builder) {
        builder.connectTimeout(50000, TimeUnit.MILLISECONDS)
                .readTimeout(100000, TimeUnit.MILLISECONDS)
                .writeTimeout(100000, TimeUnit.MILLISECONDS);
        return builder;
    }

    public static Retrofit getRetrofit(OkHttpClient client) {
        if (retrofit == null) {
            synchronized (OkHttpClientProvider.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(ConfigProp.serverUrl)
                            .addConverterFactory(NobodyConverterFactory.create())
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client)
                            .build();
                }
            }
        }
        return retrofit;
    }

    public static Retrofit getUamRetrofit() {
        if (uamRetrofit == null) {
            synchronized (OkHttpClientProvider.class) {
                if (uamRetrofit == null) {
                    uamRetrofit = new Retrofit.Builder()
//                            .baseUrl(TextUtils.isEmpty(MyApplication.uamAddress) ? ConfigProp.serverUrl : MyApplication.uamAddress)
                            .addConverterFactory(NobodyConverterFactory.create())//无响应体时
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //增加返回值为Oservable<T>的支持
                            .client(OkHttpClientProvider.getClient())
                            .build();
                }
            }
        }
        return uamRetrofit;
    }

    public static Retrofit getUfmRetrofit() {
        if (ufmRetrofit == null) {
            synchronized (OkHttpClientProvider.class) {
                if (ufmRetrofit == null) {
                    ufmRetrofit = new Retrofit.Builder()
//                            .baseUrl(TextUtils.isEmpty(MyApplication.ufmAddress) ? ConfigProp.serverUrl : MyApplication.ufmAddress)
                            .addConverterFactory(NobodyConverterFactory.create())//无响应体时
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //增加返回值为Oservable<T>的支持
                            .client(OkHttpClientProvider.getClient())
                            .build();
                }
            }
        }
        return ufmRetrofit;
    }
}
