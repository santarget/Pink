package com.ssy.pink.mvp.iview;

import com.ssy.pink.adapter.BindLogAdapter;
import com.ssy.pink.bean.weibo.PreLoginInfo;

public interface IBindSmallActivityView extends IView {
    void setCurrentProgress(int finished);

    BindLogAdapter getAdapter();

    void showCodeInputDialog(PreLoginInfo preLoginInfo);
}
