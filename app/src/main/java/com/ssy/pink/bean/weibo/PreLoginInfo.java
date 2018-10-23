package com.ssy.pink.bean.weibo;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/10/23
 */
public class PreLoginInfo implements Serializable {
    private static final long serialVersionUID = 3375570763550319210L;
    String servertime;//= "1540098120";

    String nonce;// "IJMSY8";

    String rsakv;

    String sp;

    String su;

    String door = "";//验证码

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getRsakv() {
        return rsakv;
    }

    public void setRsakv(String rsakv) {
        this.rsakv = rsakv;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public String getSu() {
        return su;
    }

    public void setSu(String su) {
        this.su = su;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }
}
