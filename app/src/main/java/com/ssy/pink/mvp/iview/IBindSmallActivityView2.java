package com.ssy.pink.mvp.iview;

import com.ssy.pink.adapter.BindLogAdapter;

public interface IBindSmallActivityView2 extends IView {

    BindLogAdapter getAdapter();

    void showBindingDialog(boolean show);
}
