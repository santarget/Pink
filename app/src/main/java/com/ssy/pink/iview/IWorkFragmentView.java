package com.ssy.pink.iview;

import com.ssy.pink.bean.GroupInfo;

import java.util.List;

public interface IWorkFragmentView extends IView {
    void loadGroups(List<GroupInfo> groupInfos);
}
