package com.ssy.pink.network.api;

import com.ssy.pink.bean.WeiboUserInfo;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ConstantUrl;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface WeiboApi {
    //    @POST("oauth/access_token")
    @POST("oauth/request_token")
    Observable<NoBodyEntity> login(@HeaderMap Map<String, String> headers);

    @GET(ConstantUrl.WEIBO_USER_INFO)
    Observable<WeiboUserInfo> getWeiboUserInfo(@HeaderMap Map<String, String> headers, @Query("access_token") String access_token);

    @HTTP(method = "GET", path = ConstantUrl.WEIBO_USER_INFO, hasBody = true)
    Call<Void> getUser(@Body HashMap<String, String> content);

}
