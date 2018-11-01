package com.ssy.pink.bean.weibo;

import com.ssy.greendao.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author ssy
 * @date 2018/10/26
 */
@Entity
public class WeiboLoginInfo implements Serializable {
    private static final long serialVersionUID = 5222683504629426720L;
    //{"retcode":"2092","reason":"\u62b1\u6b49\uff01\u767b\u5f55\u5931\u8d25\uff0c\u8bf7\u7a0d\u5019\u518d\u8bd5"}
    /*
    4049 请输入验证码
	2092 登录失败，请稍后再试
    2070 输入的验证码不正确
     */

    String retcode;
    String ticket;
    @Id
    @Unique
    String uid;
    String nick;
    
    @Convert(columnType = String.class, converter = StringConverter.class)
    List<String> crossDomainUrlList;

    String reason;//登录失败时有值

    @Generated(hash = 461988535)
    public WeiboLoginInfo(String retcode, String ticket, String uid, String nick, List<String> crossDomainUrlList,
            String reason) {
        this.retcode = retcode;
        this.ticket = ticket;
        this.uid = uid;
        this.nick = nick;
        this.crossDomainUrlList = crossDomainUrlList;
        this.reason = reason;
    }

    @Generated(hash = 65261401)
    public WeiboLoginInfo() {
    }

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeiboLoginInfo)) return false;
        WeiboLoginInfo that = (WeiboLoginInfo) o;
        return Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid);
    }

    @Override
    public String toString() {
        return "WeiboLoginInfo{" +
                "retcode='" + retcode + '\'' +
                ", ticket='" + ticket + '\'' +
                ", uid='" + uid + '\'' +
                ", nick='" + nick + '\'' +
                ", crossDomainUrlList=" + crossDomainUrlList +
                ", reason='" + reason + '\'' +
                '}';
    }
}
