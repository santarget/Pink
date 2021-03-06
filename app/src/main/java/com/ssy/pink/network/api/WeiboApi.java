package com.ssy.pink.network.api;

import com.ssy.pink.bean.weibo.EmotionInfo;
import com.ssy.pink.bean.weibo.RankInfo;
import com.ssy.pink.bean.weibo.WeiboInfo;
import com.ssy.pink.bean.weibo.WeiboUserInfo;
import com.ssy.pink.bean.response.WeiboListResp;
import com.ssy.pink.common.ConstantUrl;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 这个类里的接口base url都为 https://api.weibo.com/
 */
public interface WeiboApi {

    /**
     * 获取用户信息
     *
     * @param access_token
     * @param uid
     * @return
     */
    @GET(ConstantUrl.WEIBO_USER_INFO)
    Observable<WeiboUserInfo> getWeiboUserInfo(@Query("uid") String uid, @Query("access_token") String access_token);

//    @GET(ConstantUrl.WEIBO_RANK)
//    Observable<RankInfo> getWeiboRank(@Query("uid") long uid, @Query("access_token") String access_token);

    /**
     * 获取某个用户最新发表的微博列表,最多5条
     *
     * @param access_token
     * @param uid
     * @return
     */
    @GET(ConstantUrl.WEIBO_LATEST_STATUS)
    Observable<WeiboListResp> getLatestWeibo(@Query("access_token") String access_token, @Query("uid") String uid);

    /**
     * 获取官方表情
     *
     * @param access_token
     * @return
     */
    @GET(ConstantUrl.WEIBO_EMOTIONS)
    Observable<List<EmotionInfo>> getEmotions(@Query("access_token") String access_token);

    /**
     * 第三方分享一条链接发布微博
     *
     * @return
     */
    @POST(ConstantUrl.WEIBO_SHARE)
    Observable<WeiboInfo> shareWeibo(@Body RequestBody body);

    @FormUrlEncoded
    @POST(ConstantUrl.WEIBO_SHARE)
    Call<WeiboInfo> share(@Field("access_token") String access_token, @Field(value = "status", encoded = true) String status);

    @FormUrlEncoded
    @POST(ConstantUrl.WEIBO_REPOST)
    Call<WeiboInfo> repost(@Field("access_token") String access_token, @Field("id") long id);
}
