package com.ssy.pink.bean.weibo;

import java.io.Serializable;

public class WeiboInfo implements Serializable {
    private static final long serialVersionUID = 3289537289320364892L;
    String created_at;
    long id;//微博ID
    String idstr;// 字符串型的微博ID
    String text;// 	微博信息内容
    String source;//微博来源
    String favorited;
    String mid;//微博MID
    String thumbnail_pic;//缩略图片地址，没有时不返回此字段
    String bmiddle_pic;//中等尺寸图片地址，没有时不返回此字段
    String original_pic;//原始图片地址，没有时不返回此字段
    String reposts_count;//转发数
    String comments_count;//评论数
    WeiboUserInfo user;//微博作者的用户信息字段
    WeiboInfo retweeted_status;//被转发的原微博信息字段，当该微博为转发微博时返回
//    Object pic_ids;  //微博配图ID。多图时返回多图ID，用来拼接图片url。用返回字段thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFavorited() {
        return favorited;
    }

    public void setFavorited(String favorited) {
        this.favorited = favorited;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public void setThumbnail_pic(String thumbnail_pic) {
        this.thumbnail_pic = thumbnail_pic;
    }

    public String getBmiddle_pic() {
        return bmiddle_pic;
    }

    public void setBmiddle_pic(String bmiddle_pic) {
        this.bmiddle_pic = bmiddle_pic;
    }

    public String getOriginal_pic() {
        return original_pic;
    }

    public void setOriginal_pic(String original_pic) {
        this.original_pic = original_pic;
    }

    public String getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(String reposts_count) {
        this.reposts_count = reposts_count;
    }

    public String getComments_count() {
        return comments_count;
    }

    public void setComments_count(String comments_count) {
        this.comments_count = comments_count;
    }

    public WeiboUserInfo getUser() {
        return user;
    }

    public void setUser(WeiboUserInfo user) {
        this.user = user;
    }

    public WeiboInfo getRetweeted_status() {
        return retweeted_status;
    }

    public void setRetweeted_status(WeiboInfo retweeted_status) {
        this.retweeted_status = retweeted_status;
    }

    @Override
    public String toString() {
        return "WeiboInfo{" +
                "created_at='" + created_at + '\'' +
                ", id=" + id +
                ", idstr='" + idstr + '\'' +
                ", text='" + text + '\'' +
                ", source='" + source + '\'' +
                ", favorited='" + favorited + '\'' +
                ", mid='" + mid + '\'' +
                ", thumbnail_pic='" + thumbnail_pic + '\'' +
                ", bmiddle_pic='" + bmiddle_pic + '\'' +
                ", original_pic='" + original_pic + '\'' +
                ", reposts_count='" + reposts_count + '\'' +
                ", comments_count='" + comments_count + '\'' +
                ", user=" + user +
                ", retweeted_status=" + retweeted_status +
                '}';
    }
}
