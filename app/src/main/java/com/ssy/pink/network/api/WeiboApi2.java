package com.ssy.pink.network.api;

import com.ssy.pink.bean.WeiboInfo;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ConstantUrl;

import java.util.Map;

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


    @POST(ConstantUrl.WEIBO_REPOST)
    Observable<WeiboInfo> repostWeibo(@Query("access_token") String access_token, @Query("uid") String uid);


}
