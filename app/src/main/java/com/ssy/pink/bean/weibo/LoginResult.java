package com.ssy.pink.bean.weibo;

import java.io.Serializable;

/**
 * 微博登录失败响应
 *
 * @author ssy
 * @date 2018/10/31
 */
public class LoginResult implements Serializable {
    //{"retcode":"2092","reason":"\u62b1\u6b49\uff01\u767b\u5f55\u5931\u8d25\uff0c\u8bf7\u7a0d\u5019\u518d\u8bd5"}
    /*
    4049 请输入验证码
	2092 登录失败，请稍后再试

     */
    private static final long serialVersionUID = 3568654016194216152L;
    String retcode;
    String reason;


    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "retcode='" + retcode + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
