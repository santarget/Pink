package com.ssy.pink.network.api;

import com.ssy.pink.bean.WeiboInfo;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ConstantUrl;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 这个类里的接口base url都为 http://api.t.sina.com.cn/
 */
public interface WeiboApi2 {
    //    @POST("oauth/access_token")
    @POST("oauth/request_token")
    Observable<NoBodyEntity> login(@HeaderMap Map<String, String> headers);

    @POST("oauth/request_token")
    Observable<NoBodyEntity> login2(@Header("consumer secret") String secret, @Header("oauth_consumer_key") String oauth_consumer_key,
                                    @Header("oauth_signature_method") String oauth_signature_method,
                                    @Header("oauth_signature") String oauth_signature, @Header("oauth_timestamp") String oauth_timestamp,
                                    @Header("oauth_nonce") String oauth_nonce, @Header("oauth_version") String oauth_version);


    @POST(ConstantUrl.WEIBO_REPOST)
    Observable<WeiboInfo> repostWeibo(@Query("access_token") String access_token, @Query("uid") String uid,@Body RequestBody requestBody);


}
