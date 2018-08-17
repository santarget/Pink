package com.ssy.pink.bean;

import java.io.Serializable;

/**
 * Created by tys on 2017/4/28.
 */

public class VersionInfo implements Serializable{
    private static final long serialVersionUID = 3254978773364425886L;
    private int versionNum;
    private String versionName;
    private long totalSize;
    private String explainCn;
    private String explainUs;
    private String downloadUrl;

    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public String getExplainCn() {
        return explainCn;
    }

    public void setExplainCn(String explainCn) {
        this.explainCn = explainCn;
    }

    public String getExplainUs() {
        return explainUs;
    }

    public void setExplainUs(String explainUs) {
        this.explainUs = explainUs;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "VersionInfo{" +
                "versionNum=" + versionNum +
                ", versionName='" + versionName + '\'' +
                ", totalSize=" + totalSize +
                ", explainCn='" + explainCn + '\'' +
                ", explainUs='" + explainUs + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }


}
