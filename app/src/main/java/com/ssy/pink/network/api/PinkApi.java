package com.ssy.pink.network.api;

import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.RechargeRecordInfo;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.ProductInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.UserProductInfo;
import com.ssy.pink.bean.WeiboCustomerInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ConstantUrl;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface PinkApi {

    @POST(ConstantUrl.LIST_FANS_Org)
    Observable<CommonListResp<FansOrgInfo>> listFansOrg();

    @POST(ConstantUrl.LIST_PRODUCT)
    Observable<CommonListResp<ProductInfo>> listProduct();

    @POST(ConstantUrl.SYNC_CUSTOMER)
    Observable<CommonResp<WeiboCustomerInfo>> syncCustomer(@Body RequestBody requestBody);

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
    Observable<NoBodyEntity> orderProduct(@Body RequestBody requestBody);

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
}
