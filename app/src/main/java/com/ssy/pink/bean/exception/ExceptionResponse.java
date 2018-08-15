package com.ssy.pink.bean.exception;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2016/12/13.
 *
 */

public class ExceptionResponse {

    @JSONField(name = "code")
    private String errorCode;
    private String type;
    private String message;
    private String requestId;
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getErrorCode() {
        if (errorCode == null){
            return "";
        }
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
