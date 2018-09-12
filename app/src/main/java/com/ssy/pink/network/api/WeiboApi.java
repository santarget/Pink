package com.ssy.pink.network.api;

import com.ssy.pink.bean.EmotionInfo;
import com.ssy.pink.bean.WeiboInfo;
import com.ssy.pink.bean.WeiboUserInfo;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ConstantUrl;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface WeiboApi {
    //    @POST("oauth/access_token")
    @POST("oauth/request_token")
    Observable<NoBodyEntity> login(@HeaderMap Map<String, String> headers);

    /**
     * 获取用户信息
     *
     * @param access_token
     * @param uid
     * @return
     */
    @GET(ConstantUrl.WEIBO_USER_INFO)
    Observable<WeiboUserInfo> getWeiboUserInfo(@Query("access_token") String access_token, @Query("uid") String uid);

    /**
     * 获取官方表情
     *
     * @param access_token
     * @return
     */
    @GET(ConstantUrl.WEIBO_EMOTIONS)
    Observable<List<EmotionInfo>> getEmotions(@Query("access_token") String access_token);

    /**
     * 获取某个用户最新发表的微博列表,最多5条
     *
     * @param access_token
     * @param uid
     * @return
     */
    @GET(ConstantUrl.WEIBO_LATEST_STATUS)
    Observable<List<WeiboInfo>> getLatestWeibo(@Query("access_token") String access_token, @Query("uid") String uid);

    @POST(ConstantUrl.WEIBO_REPOST)
    Observable<List<WeiboInfo>> repostWeibo(@Query("access_token") String access_token, @Query("uid") String uid);


}
