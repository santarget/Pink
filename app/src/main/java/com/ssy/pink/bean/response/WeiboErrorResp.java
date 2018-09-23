package com.ssy.pink.bean.response;

import java.io.Serializable;

public class WeiboErrorResp implements Serializable {
    public static final String APPLICATION_RESTRICTIONS = "applications over the unaudited use restrictions";
    public static final String NO_DOMAIN = "text not find domain";
    public static final String REPEAT_CONTENT = "repeat content";
    private static final long serialVersionUID = -6444870257947849379L;
    String error;
    long error_code;
    String request;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getError_code() {
        return error_code;
    }

    public void setError_code(long error_code) {
        this.error_code = error_code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "WeiboErrorResp{" +
                "error='" + error + '\'' +
                ", error_code=" + error_code +
                ", request='" + request + '\'' +
                '}';
    }
}
