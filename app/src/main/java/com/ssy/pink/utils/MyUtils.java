package com.ssy.pink.utils;

import com.ssy.pink.bean.exception.ClientException;
import com.ssy.pink.bean.exception.ExceptionResponse;

import org.greenrobot.eventbus.EventBus;

import java.net.SocketTimeoutException;

import javax.net.ssl.SSLException;

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
}
