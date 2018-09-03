package com.ssy.pink.network.api;

import com.ssy.pink.bean.WeiboUserInfo;
import com.ssy.pink.common.ConstantUrl;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import rx.Observable;

public interface WeiboApi {
    @GET(ConstantUrl.WEIBO_USER_INFO)
    Observable<WeiboUserInfo> getWeiboUserInfo(@Body RequestBody requestBody);

}
