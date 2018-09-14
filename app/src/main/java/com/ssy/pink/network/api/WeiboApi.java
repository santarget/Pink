package com.ssy.pink.network.api;

import com.ssy.pink.bean.EmotionInfo;
import com.ssy.pink.bean.WeiboInfo;
import com.ssy.pink.bean.WeiboUserInfo;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.bean.response.WeiboListResp;
import com.ssy.pink.common.ConstantUrl;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
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
    Observable<WeiboUserInfo> getWeiboUserInfo(@Query("access_token") String access_token, @Query("uid") String uid);

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


}
