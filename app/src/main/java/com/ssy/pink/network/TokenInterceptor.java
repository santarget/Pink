package com.ssy.pink.network;

import android.text.TextUtils;

import com.ssy.pink.MyApplication;
import com.ssy.pink.bean.CustomerInfo;
import com.ssy.pink.bean.request.SyncCustomerReq;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.network.api.PinkApi;
import com.ssy.pink.utils.JsonUtils;
import com.ssy.pink.utils.SharedPreferencesUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;

/**
 * @author ssy
 * @date 2018/7/3
 */
public class TokenInterceptor implements Interceptor {
    private final String TAG = "TokenInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (isTokenExpired()) {
            getNewToken();
            //使用新的Token，创建新的请求
            //重新请求
            OkHttpClientProvider.refreshSessionPink();
            final Request.Builder builder = chain.request().newBuilder();
            builder.header("sessionid", MyApplication.getInstance().getToken());
            return chain.proceed(builder.build());
        }
        return response;
    }

    /**
     * 根据Response，判断Token是否失效 *
     *
     * @return
     */
    private boolean isTokenExpired() {
        if (TextUtils.isEmpty(MyApplication.getInstance().getToken())) {
            return true;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private void getNewToken() throws IOException {
        CustomerInfo customerInfo = SharedPreferencesUtil.getLastLoginUser();
        SyncCustomerReq req = new SyncCustomerReq();
        req.setWeiboid(customerInfo.getWeiboid());
        req.setWeibonum(customerInfo.getWeibonum());
        req.setWeiboname(customerInfo.getWeiboname());
        req.setFansorginfonum(customerInfo.getFansorginfonum());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtils.toString(req));

        Call call = OkHttpClientProvider.getNoSessionPinkRetrofit().create(PinkApi.class).refreshCustomer(requestBody);
        retrofit2.Response<CommonResp<CustomerInfo>> response = call.execute();
        UserManager.getInstance().userInfo = response.body().getData();
        MyApplication.getInstance().setToken(UserManager.getInstance().userInfo.getSessionid());
    }
}