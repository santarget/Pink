package com.ssy.pink.manager;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.ssy.pink.MyApplication;
import com.ssy.pink.bean.weibo.WeiboUserInfo;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ConstantWeibo;
import com.ssy.pink.network.NobodyConverterFactory;
import com.ssy.pink.network.UnsafeOkHttpClient;
import com.ssy.pink.network.api.WeiboApi2;
import com.ssy.pink.utils.LogUtil;
import com.ssy.pink.utils.MyUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeiboManager {
    private static WeiboManager instance;
    private AuthInfo mAuthInfo;
    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    public Oauth2AccessToken mAccessToken;
    public WeiboUserInfo userInfo;


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

    public void login(String userName, String pwd) {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        Map<String, String> heads = new HashMap<>();
//        heads.put("x_auth_username", userName);
//        heads.put("x_auth_password", pwd);
//        heads.put("x_auth_mode", "client_auth");
        heads.put("consumer secret", ConstantWeibo.APP_SECRET);
        heads.put("oauth_consumer_key", ConstantWeibo.APP_KEY);
        heads.put("oauth_signature_method", "HMAC-SHA1");
        heads.put("oauth_signature", MyUtils.getOauthSignature(userName, pwd, timeStamp));
        heads.put("oauth_timestamp", timeStamp);
        heads.put("oauth_nonce", "s");
        heads.put("oauth_version", "1.0");

        getRetrofit(getClient()).create(WeiboApi2.class).login(heads)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NoBodyEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NoBodyEntity o) {
                    }
                });
    }

    public void login2(String userName, String pwd) {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        getRetrofit(getClient()).create(WeiboApi2.class).login2(ConstantWeibo.APP_SECRET, ConstantWeibo.APP_KEY,
                "HMAC-SHA1", MyUtils.getOauthSignature(userName, pwd, timeStamp),
                timeStamp, "s", "1.0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NoBodyEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MyUtils.handleExcep(e);
                    }

                    @Override
                    public void onNext(NoBodyEntity o) {

                    }
                });
    }

    //http://api.t.sina.com.cn/oauth/access_token
    private Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("http://api.t.sina.com.cn/")
                .addConverterFactory(NobodyConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    private OkHttpClient getClient() {
        OkHttpClient.Builder builder = UnsafeOkHttpClient.getUnsafeOkHttpClientBuilder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtil.i("weibologin", message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .readTimeout(8000, TimeUnit.MILLISECONDS)
                .writeTimeout(8000, TimeUnit.MILLISECONDS);

        return builder.build();
    }

//    public String PostOnSina(String contents) {
////        https://www.cnblogs.com/dragonpig/archive/2011/01/06/1929145.html
//        String username = "username";
//        String password = "username";
//        String appKey = "username";
//        String url = "http://api.t.sina.com.cn/statuses/update.xml";
//        var request = WebRequest.Create(url) as HttpWebRequest;
//
//        request.Credentials = new NetworkCredential(username, password);
//        byte[] authBytes = Encoding.UTF8.GetBytes(username + ":" + password);
//        request.Headers["Authorization"] = "Basic " + Convert.ToBase64String(authBytes);
//        request.Method = "POST";
//
//        request.ContentType = "application/x-www-form-urlencoded";
//        String body = "source=" + HttpUtility.UrlEncode(appKey) + "&status=" + HttpUtility.UrlEncode(contents);
//
//        using(var writer = new StreamWriter(request.GetRequestStream()))
//        {
//            writer.Write(body);
//        }
//
//        WebResponse response = request.GetResponse();
//        using(Stream receiveStream = response.GetResponseStream())
//        {
//            StreamReader reader = new StreamReader(receiveStream, Encoding.UTF8);
//            String result = reader.ReadToEnd();
//            return result;
//        }
//    }
}
