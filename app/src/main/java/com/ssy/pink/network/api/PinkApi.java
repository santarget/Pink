package com.ssy.pink.network.api;

import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.RechargeRecordInfo;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.ProductInfo;
import com.ssy.pink.bean.UserProductInfo;
import com.ssy.pink.bean.WeiboCustomerInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ConstantUrl;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface PinkApi {

    @POST(ConstantUrl.LIST_FANS_Org)
    Observable<CommonResp<FansOrgInfo>> listFansOrg();

    @POST(ConstantUrl.LIST_PRODUCT)
    Observable<CommonResp<ProductInfo>> listProduct();

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
    Observable<CommonResp<UserProductInfo>> listOrderedInfo(@Body RequestBody requestBody);

    @POST(ConstantUrl.GET_USER_MONEY_INFO)
    Observable<CommonResp<MoneyInfo>> getUserMoney(@Body RequestBody requestBody);
}
