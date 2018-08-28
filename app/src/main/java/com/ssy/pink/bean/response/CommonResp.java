package com.ssy.pink.bean.response;

import java.io.Serializable;
import java.util.List;

public class CommonResp<T> implements Serializable {
    private static final long serialVersionUID = -471782101375908894L;
    private String code;

    private List<T> data;

    private String msg;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "CommonResp{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
