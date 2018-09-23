package com.ssy.pink.mvp.iview;

import com.ssy.pink.bean.VersionInfo;

public interface ICheckUpdateActivityView extends IView {
    void loadVersion(VersionInfo versionInfo);
}
