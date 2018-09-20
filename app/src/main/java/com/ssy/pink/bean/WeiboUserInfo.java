package com.ssy.pink.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.Objects;

import org.greenrobot.greendao.annotation.Generated;

/**
 * 微博用户对象
 */
@Entity
public class WeiboUserInfo implements Serializable {
    private static final long serialVersionUID = 7673264398640396954L;
    @Unique
    @Id
    private long id;//用户UID

    private String screen_name;//用户昵称

    private String name;//友好显示名称

    private String description;

    private String url;//用户博客地址

    private String profile_image_url;//用户头像地址（中图），50×50像素

    private String domain;//用户的个性化域名

    private String gender;//性别，m：男、f：女、n：未知

    private int followers_count;//粉丝数

    private int friends_count;//关注数

    private int statuses_count;//微博数

    private int favourites_count;//收藏数

    private String created_at;//用户创建（注册）时间

    private boolean verified;//是否是微博认证用户

    private String avatar_large;//用户头像地址（大图），180×180像素

    private String avatar_hd;// 用户头像地址（高清），高清头像原图


    @Generated(hash = 424666052)
    public WeiboUserInfo(long id, String screen_name, String name,
            String description, String url, String profile_image_url, String domain,
            String gender, int followers_count, int friends_count,
            int statuses_count, int favourites_count, String created_at,
            boolean verified, String avatar_large, String avatar_hd) {
        this.id = id;
        this.screen_name = screen_name;
        this.name = name;
        this.description = description;
        this.url = url;
        this.profile_image_url = profile_image_url;
        this.domain = domain;
        this.gender = gender;
        this.followers_count = followers_count;
        this.friends_count = friends_count;
        this.statuses_count = statuses_count;
        this.favourites_count = favourites_count;
        this.created_at = created_at;
        this.verified = verified;
        this.avatar_large = avatar_large;
        this.avatar_hd = avatar_hd;
    }

    @Generated(hash = 547930477)
    public WeiboUserInfo() {
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getScreen_name() {
        return this.screen_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getProfile_image_url() {
        return this.profile_image_url;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFollowers_count() {
        return this.followers_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public int getFriends_count() {
        return this.friends_count;
    }

    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    public int getStatuses_count() {
        return this.statuses_count;
    }

    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }

    public int getFavourites_count() {
        return this.favourites_count;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean getVerified() {
        return this.verified;
    }


    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
    }

    public String getAvatar_large() {
        return this.avatar_large;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getAvatar_hd() {
        return avatar_hd;
    }

    public void setAvatar_hd(String avatar_hd) {
        this.avatar_hd = avatar_hd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeiboUserInfo)) return false;
        WeiboUserInfo that = (WeiboUserInfo) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "WeiboUserInfo{" +
                "id=" + id +
                ", screen_name='" + screen_name + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", profile_image_url='" + profile_image_url + '\'' +
                ", domain='" + domain + '\'' +
                ", gender='" + gender + '\'' +
                ", followers_count=" + followers_count +
                ", friends_count=" + friends_count +
                ", statuses_count=" + statuses_count +
                ", favourites_count=" + favourites_count +
                ", created_at='" + created_at + '\'' +
                ", verified=" + verified +
                ", avatar_large='" + avatar_large + '\'' +
                ", avatar_hd='" + avatar_hd + '\'' +
                '}';
    }
}
