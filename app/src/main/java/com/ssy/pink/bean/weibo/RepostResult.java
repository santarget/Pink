package com.ssy.pink.bean.weibo;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/10/31
 */
public class RepostResult implements Serializable {
    private static final long serialVersionUID = -5567476992943324584L;
    //{"code":"100001","msg":"\u5fae\u535a\u53d1\u7684\u592a\u591a\u5566\uff0c\u4f11\u606f\u4e00\u4f1a\u518d\u53d1\u5566\uff01(100001)","data":{"close":1}}
    public static final String ERROR = "-1";//自定义错误码
    public static final String ERROR_RELOAD = "-2";//自定义错误码,重新登录授权
    public static final String SUCCESS = "100000";
    public static final String TOO_MUCH = "100001";//微博发的太多啦，休息一会再发啦
    String code = ERROR;
    String msg;

    public RepostResult() {
    }

    public RepostResult(String msg) {
        this.msg = msg;
    }

    public RepostResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RepostResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
