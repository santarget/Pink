package com.ssy.pink.mvp.iview;

import com.ssy.pink.adapter.BindLogAdapter;

public interface IBindSmallActivityView extends IView {
    void setCurrentProgress(int finished);

    BindLogAdapter getAdapter();
}
