package com.ssy.pink.bean.response;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/9/14
 */
public class VersionResp implements Serializable {
    private static final long serialVersionUID = -6071015767341609968L;
    String settingid;//不用关心
    String content;//json 解析出VersionInfo对象

    public String getSettingid() {
        return settingid;
    }

    public void setSettingid(String settingid) {
        this.settingid = settingid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "VersionResp{" +
                "settingid='" + settingid + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
