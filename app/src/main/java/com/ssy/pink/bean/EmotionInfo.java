package com.ssy.pink.bean;

import java.io.Serializable;

public class EmotionInfo implements Serializable {
    private static final long serialVersionUID = -2925199767759765861L;
    String category;
    boolean common;
    boolean hot;
    String icon;//图片地址
    String phrase;//"[呵呵]",
    //    String picid;
    String type;//"face",
    String url;//"http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/eb/smile.gif",
    String value;//"[呵呵]",

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EmotionInfo{" +
                "category='" + category + '\'' +
                ", common=" + common +
                ", hot=" + hot +
                ", icon='" + icon + '\'' +
                ", phrase='" + phrase + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
