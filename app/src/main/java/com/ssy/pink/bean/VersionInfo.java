package com.ssy.pink.bean;

import java.io.Serializable;

/**
 * Created by tys on 2017/4/28.
 */

public class VersionInfo implements Serializable {
    private static final long serialVersionUID = 3254978773364425886L;
    String appDesc;
    String appName;
    int appVersion;//用于比较版本号
    String appVersionName;//用于给用户展示
    String downloadAddress;

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getDownloadAddress() {
        return downloadAddress;
    }

    public void setDownloadAddress(String downloadAddress) {
        this.downloadAddress = downloadAddress;
    }

    @Override
    public String toString() {
        return "VersionInfo{" +
                "appDesc='" + appDesc + '\'' +
                ", appName='" + appName + '\'' +
                ", appVersion=" + appVersion +
                ", appVersionName='" + appVersionName + '\'' +
                ", downloadAddress='" + downloadAddress + '\'' +
                '}';
    }
}
