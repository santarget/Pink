package com.ssy.pink.mvp.iview;

import com.ssy.pink.adapter.SmallAdapter;

/**
 * @author ssy
 * @date 2018/9/5
 */
public interface IGroupDetailActivityView extends IView {
    SmallAdapter getAdapter();

    void updateData();

    void finishRefresh();
}
