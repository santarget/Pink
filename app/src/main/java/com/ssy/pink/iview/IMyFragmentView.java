package com.ssy.pink.iview;

import com.ssy.pink.bean.FansOrgInfo;

import java.util.List;

public interface IMyFragmentView extends IView {
    void setFansOrgList(List<FansOrgInfo> list);

    void hasGotOrgs(boolean hasGot);
}
