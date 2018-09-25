package com.ssy.pink.bean.weibo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.Objects;

import org.greenrobot.greendao.annotation.Generated;

/**
 * @author ssy
 * @date 2018/9/20
 */
@Entity
public class WeiboTokenInfo implements Serializable {
    private static final long serialVersionUID = -5432446563950751801L;
    @Unique
    @Id
    String weiboUid;
    String mAccessToken = "";
    String mRefreshToken = "";
    long mExpiresTime;//access_token的生命周期，单位是秒数 expires_in
    int type;//0 小号  1 大号

    @Generated(hash = 1925069491)
    public WeiboTokenInfo(String weiboUid, String mAccessToken, String mRefreshToken,
                          long mExpiresTime, int type) {
        this.weiboUid = weiboUid;
        this.mAccessToken = mAccessToken;
        this.mRefreshToken = mRefreshToken;
        this.mExpiresTime = mExpiresTime;
        this.type = type;
    }

    @Generated(hash = 1947192650)
    public WeiboTokenInfo() {
    }

    public String getWeiboUid() {
        return weiboUid;
    }

    public void setWeiboUid(String weiboUid) {
        this.weiboUid = weiboUid;
    }

    public String getmAccessToken() {
        return mAccessToken;
    }

    public void setmAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    public String getmRefreshToken() {
        return mRefreshToken;
    }

    public void setmRefreshToken(String mRefreshToken) {
        this.mRefreshToken = mRefreshToken;
    }

    public long getmExpiresTime() {
        return mExpiresTime;
    }

    public void setmExpiresTime(long mExpiresTime) {
        this.mExpiresTime = mExpiresTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeiboTokenInfo)) return false;
        WeiboTokenInfo that = (WeiboTokenInfo) o;
        return weiboUid == that.weiboUid &&
                Objects.equals(mAccessToken, that.mAccessToken);
    }

    @Override
    public int hashCode() {

        return Objects.hash(weiboUid, mAccessToken);
    }

    @Override
    public String toString() {
        return "WeiboTokenInfo{" +
                "weiboUid=" + weiboUid +
                ", mAccessToken='" + mAccessToken + '\'' +
                ", mRefreshToken='" + mRefreshToken + '\'' +
                ", mExpiresTime=" + mExpiresTime +
                ", type=" + type +
                '}';
    }

    public String getMAccessToken() {
        return this.mAccessToken;
    }

    public void setMAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    public String getMRefreshToken() {
        return this.mRefreshToken;
    }

    public void setMRefreshToken(String mRefreshToken) {
        this.mRefreshToken = mRefreshToken;
    }

    public long getMExpiresTime() {
        return this.mExpiresTime;
    }

    public void setMExpiresTime(long mExpiresTime) {
        this.mExpiresTime = mExpiresTime;
    }
}
