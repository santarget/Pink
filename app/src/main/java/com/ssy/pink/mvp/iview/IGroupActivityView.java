package com.ssy.pink.mvp.iview;

/**
 * @author ssy
 * @date 2018/8/17
 */
public interface IGroupActivityView extends IView {
    void loadGroups();

    void finishRefresh();

    void updateDefaultGroup();
}
