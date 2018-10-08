package com.ssy.pink.utils;

import com.ssy.pink.bean.ProductInfo;
import com.ssy.pink.bean.exception.ClientException;
import com.ssy.pink.bean.exception.ExceptionResponse;
import com.ssy.pink.common.ConstantWeibo;

import org.greenrobot.eventbus.EventBus;

import java.net.SocketTimeoutException;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLException;

import cn.testin.analysis.bug.BugOutApi;
import retrofit2.adapter.rxjava.HttpException;

/**
 * @author ssy
 * @date 2018/8/29
 */
public class MyUtils {
    /**
     * 异常处理
     *
     * @param throwable
     */
    public static void handleExcep(Throwable throwable) {
        BugOutApi.reportException(throwable);
        if (throwable instanceof HttpException) {
            if (((HttpException) throwable).code() == 502) {
                ExceptionResponse exception = new ExceptionResponse();
                exception.setStatusCode(502);
                exception.setMessage(((HttpException) throwable).message());
                EventBus.getDefault().post(exception);
            } else {
                try {
                    ExceptionResponse exception = JsonUtils.toObject(((HttpException) throwable).response().errorBody().string(), ExceptionResponse.class);
                    exception.setStatusCode(((HttpException) throwable).code());
                    EventBus.getDefault().post(exception);
                } catch (Exception e) {
                    e.printStackTrace();
                    EventBus.getDefault().post(new ClientException(((HttpException) throwable).code(), ((HttpException) throwable).message()));
                }
            }
        } else if (throwable instanceof SocketTimeoutException) {
            EventBus.getDefault().post(throwable);
        } else if (throwable instanceof SSLException) {
            EventBus.getDefault().post(new ClientException(ClientException.CODE_CERT_EXCEPTION, throwable.getMessage()));
        } else {
            EventBus.getDefault().post(new ClientException(throwable.getMessage()));
        }
    }

    /**
     * "使用%3D替代=号，并且使用%26作为每个参数之间的分隔符，拼接成一个字符串"
     * 详见 http://open.weibo.com/wiki/XAuth
     *
     * @return
     */
    public static String getOauthSignature(String userName, String pwd, String timeStamp) {
        String baseString = "POST&http%3A%2F%2Fapi.t.sina.com.cn%2Foauth%2F" +
                "request_token&" +
//                "access_token&" +
//                "x_auth_username%3D" + userName +
//                "%26x_auth_password%3D" + pwd +
//                "%26x_auth_mode%3Dclient_auth" +
                "%26consumer secret%3D" + ConstantWeibo.APP_SECRET +
                "%26oauth_consumer_key%3D" + ConstantWeibo.APP_KEY +
                "%26oauth_signature_method%3DHMAC-SHA1" +
                "%26oauth_timestamp%3D" + timeStamp +
                "%26oauth_nonce%3Ds" +
                "%26oauth_version%3D1.0";

        try {
            String oauth_signature = getSignature(baseString, ConstantWeibo.APP_SECRET + "&");
            return oauth_signature;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * 生成签名数据
     *
     * @param data 待加密的数据
     * @param key  加密使用的key
     * @throws Exception
     */
    public static String getSignature(String data, String key) throws Exception {
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : rawHmac) {
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }

    private static String byteToHexString(byte ib) {
        char[] Digit = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0f];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    /**
     * 检查app是否可用，过期则不可用
     * 30天有效期
     *
     * @return true可用 false不可用
     */
    public static boolean isAppValid() {
        if (SharedPreferencesUtil.getFirstUseTime() == 0) {
            //第一次使用
            SharedPreferencesUtil.setFirstTime(System.currentTimeMillis());
            return true;
        } else {
            return System.currentTimeMillis() - SharedPreferencesUtil.getFirstUseTime() < 30 * 24 * 3600 * 1000l;
        }
    }

    /**
     * 生成订单号
     * 打头字母 m订购包月产品 t按次产品
     *
     * @param info
     * @return
     */
    public static String getTransactionId(ProductInfo info) {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        if (info.getProducttype().equalsIgnoreCase("1")) {
            //包月产品
            uuid = "m" + uuid;
        } else {
            uuid = "t" + uuid;
        }
        return uuid;
    }
}
