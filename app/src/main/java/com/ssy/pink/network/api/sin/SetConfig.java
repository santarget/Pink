package com.ssy.pink.network.api.sin;

public class SetConfig {
    private int retcode;
    private String servertime;
    private String pcid;
    private String nonce;
    private String pubkey;
    private String rsakv;
    private int is_openlock;
    private int lm;
    private String smsurl;//https:\/\/login.sina.com.cn\/sso\/msglogin?entry=weibo&mobile=18312493107&s=3d318bde20b61b52ebcdbf02ffd46f2d
    private int showpin;
    private String exectime;

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    public String getExectime() {
        return exectime;
    }

    public void setExectime(String exectime) {
        this.exectime = exectime;
    }

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

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public int getIs_openlock() {
        return is_openlock;
    }

    public void setIs_openlock(int is_openlock) {
        this.is_openlock = is_openlock;
    }

    public int getLm() {
        return lm;
    }

    public void setLm(int lm) {
        this.lm = lm;
    }

    public String getSmsurl() {
        return smsurl;
    }

    public void setSmsurl(String smsurl) {
        this.smsurl = smsurl;
    }

    public int getShowpin() {
        return showpin;
    }

    public void setShowpin(int showpin) {
        this.showpin = showpin;
    }

    @Override
    public String toString() {
        return "SetConfig{" +
                "retcode=" + retcode +
                ", servertime='" + servertime + '\'' +
                ", pcid='" + pcid + '\'' +
                ", nonce='" + nonce + '\'' +
                ", pubkey='" + pubkey + '\'' +
                ", rsakv='" + rsakv + '\'' +
                ", is_openlock=" + is_openlock +
                ", lm=" + lm +
                ", smsurl='" + smsurl + '\'' +
                ", showpin=" + showpin +
                ", exectime='" + exectime + '\'' +
                '}';
    }
}
