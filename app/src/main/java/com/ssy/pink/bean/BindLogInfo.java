package com.ssy.pink.bean;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/9/10
 */
public class BindLogInfo implements Serializable {
    private static final long serialVersionUID = 4178782583962571271L;
    SmallInfo smallInfo;
    int status;//0 失败 1成功 2绑定中
    String msg;

    public SmallInfo getSmallInfo() {
        return smallInfo;
    }

    public void setSmallInfo(SmallInfo smallInfo) {
        this.smallInfo = smallInfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BindLogInfo{" +
                "smallInfo=" + smallInfo +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
