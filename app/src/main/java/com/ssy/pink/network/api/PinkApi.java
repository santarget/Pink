package com.ssy.pink.network.api;

import com.ssy.pink.bean.CustomerInfo;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.RechargeRecordInfo;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.ProductInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.SmallStatusInfo;
import com.ssy.pink.bean.UserProductInfo;
import com.ssy.pink.bean.response.CheckSmallResp;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.bean.response.VersionResp;
import com.ssy.pink.bean.weibo.WeiboInfo;
import com.ssy.pink.common.ConstantUrl;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface PinkApi {

    @POST(ConstantUrl.LIST_FANS_Org)
    Observable<CommonListResp<FansOrgInfo>> listFansOrg();

    @POST(ConstantUrl.LIST_PRODUCT)
    Observable<CommonListResp<ProductInfo>> listProduct();

    @POST(ConstantUrl.SYNC_CUSTOMER)
    Observable<CommonResp<CustomerInfo>> syncCustomer(@Body RequestBody requestBody);

    @POST(ConstantUrl.SYNC_CUSTOMER)
    Call<CommonResp<CustomerInfo>> refreshCustomer(@Body RequestBody requestBody);

    @POST(ConstantUrl.SYNC_RECHARGE_RECORD)
    Observable<CommonResp<RechargeRecordInfo>> syncRechargeRecord(@Body RequestBody requestBody);

    /**
     * 添加消费记录信息
     *
     * @param requestBody
     * @return
     */
    @POST(ConstantUrl.SYNC_SPEND_RECORD)
    Observable<NoBodyEntity> syncSpendRecord(@Body RequestBody requestBody);

    @POST(ConstantUrl.ORDER_ONE_PRODUCE)
    Observable<CommonResp<NoBodyEntity>> orderProduct(@Body RequestBody requestBody);

    /**
     * 查询已经订购的产品
     *
     * @param requestBody
     * @return
     */
    @POST(ConstantUrl.GET_ORDER_INFO)
    Observable<CommonListResp<UserProductInfo>> listOrderedInfo(@Body RequestBody requestBody);

    @POST(ConstantUrl.GET_USER_MONEY_INFO)
    Observable<CommonResp<MoneyInfo>> getUserMoney(@Body RequestBody requestBody);

    /**
     * 查询所有分组
     *
     * @param requestBody
     * @return
     */
    @POST(ConstantUrl.LIST_GROUP_INFO)
    Observable<CommonListResp<GroupInfo>> listGroup(@Body RequestBody requestBody);

    /**
     * 查询所有小号信息
     *
     * @param requestBody
     * @return
     */
    @POST(ConstantUrl.LIST_SMALL_INFO)
    Observable<CommonListResp<SmallInfo>> listSmall(@Body RequestBody requestBody);

    /**
     * 获取全部和有效的小号数量
     *
     * @param requestBody
     * @return
     */
    @POST(ConstantUrl.GET_SMALL_STATUS)
    Observable<CommonListResp<SmallStatusInfo>> getSmallStutas(@Body RequestBody requestBody);

    @POST(ConstantUrl.ADD_GROUP)
    Observable<CommonResp<GroupInfo>> addGroup(@Body RequestBody requestBody);

    @POST(ConstantUrl.DELETE_GROUP)
    Observable<CommonResp<NoBodyEntity>> deleteGroup(@Body RequestBody requestBody);

    @POST(ConstantUrl.UPDATE_GROUP)
    Observable<CommonResp<GroupInfo>> updateGroup(@Body RequestBody requestBody);

    @POST(ConstantUrl.BIND_SMALL)
    Observable<CommonResp<NoBodyEntity>> bindSmall(@Body RequestBody requestBody);

    @POST(ConstantUrl.DELETE_SMALL)
    Observable<CommonResp<NoBodyEntity>> deleteSmall(@Body RequestBody requestBody);

    @POST(ConstantUrl.MOVE_SMALL)
    Observable<CommonResp<NoBodyEntity>> moveSmall(@Body RequestBody requestBody);

    @POST(ConstantUrl.CHECK_SMALL)
    Observable<CommonListResp<CheckSmallResp>> checkSmall(@Body RequestBody requestBody);

    @POST(ConstantUrl.GET_VERSION)
    Observable<CommonResp<VersionResp>> getVersion();

    @GET("https://wxpay.wxutil.com/pub_v2/app/app_pay.php")
    Call<WeiboInfo> pay(@Field("access_token") String access_token, @Field("id") long id);
}
