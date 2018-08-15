package com.ssy.pink.bean.exception;

/**
 * Created by Administrator on 2016/10/27.
 */
public class ClientException extends Exception {
    //自定义异常错误码
    public static final int CODE_CERT_EXCEPTION = 902;//证书异常
    public static final int CODE_DISABLE_NETWORK_900 = 900; //网络不可用
    private String detailMessage;
    private int errorCode = 901; //自定义异常错误码

    public ClientException(String detailMessage) {
        super(detailMessage);
        this.detailMessage = detailMessage;
    }

    public ClientException(int code, String detailMessage) {
        super(detailMessage);
        this.errorCode = code;
        this.detailMessage = detailMessage;
    }


    public ClientException(int code, Throwable throwable) {
        super(throwable);
        this.errorCode = code;
    }


    public ClientException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "detailMessage='" + detailMessage + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
