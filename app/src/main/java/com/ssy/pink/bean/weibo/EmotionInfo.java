package com.ssy.pink.bean.weibo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.Objects;

import org.greenrobot.greendao.annotation.Generated;

@Entity
public class EmotionInfo implements Serializable {
    private static final long serialVersionUID = -2925199767759765861L;
    String category;
    boolean common;
    boolean hot;
    String icon;//图片地址
    String phrase;//"[呵呵]",
    //    String picid;
    String type;//表情类别，face：普通表情、ani：魔法表情、cartoon：动漫表情，默认为face。
    String url;//"http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/eb/smile.gif",
    @Unique
    @Id
    String value;//"[呵呵]",

    @Generated(hash = 724519342)
    public EmotionInfo(String category, boolean common, boolean hot, String icon, String phrase,
                       String type, String url, String value) {
        this.category = category;
        this.common = common;
        this.hot = hot;
        this.icon = icon;
        this.phrase = phrase;
        this.type = type;
        this.url = url;
        this.value = value;
    }

    @Generated(hash = 1190716014)
    public EmotionInfo() {
    }

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

    public boolean getCommon() {
        return this.common;
    }

    public boolean getHot() {
        return this.hot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmotionInfo)) return false;
        EmotionInfo that = (EmotionInfo) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url, value);
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
