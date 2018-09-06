package com.ssy.pink.mvp.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.mvp.iview.IGroupDetailActivityView;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class GroupDetailActivityPresenter extends BasePresenter {
    private IGroupDetailActivityView iView;

    public GroupDetailActivityPresenter(IGroupDetailActivityView iView) {
        this.iView = iView;
    }

    public void deleteSmall(SmallInfo info){

    }
}
