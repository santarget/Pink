package com.ssy.pink.network.api.sin;

import java.io.Serializable;
import java.util.List;

/**
 * @author ssy
 * @date 2018/10/26
 */
public class WeiboLoginInfo implements Serializable{
    private static final long serialVersionUID = 5222683504629426720L;
    String retcode;
    String ticket;
    String uid;
    String nick;
    List<String> crossDomainUrlList;

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public List<String> getCrossDomainUrlList() {
        return crossDomainUrlList;
    }

    public void setCrossDomainUrlList(List<String> crossDomainUrlList) {
        this.crossDomainUrlList = crossDomainUrlList;
    }

    @Override
    public String toString() {
        return "WeiboLoginInfo{" +
                "retcode='" + retcode + '\'' +
                ", ticket='" + ticket + '\'' +
                ", uid='" + uid + '\'' +
                ", nick='" + nick + '\'' +
                ", crossDomainUrlList=" + crossDomainUrlList +
                '}';
    }
}
