package com.ssy.pink.mvp.iview;

import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.SmallStatusInfo;

import java.util.List;

public interface IMyFragmentView extends IView {
    void setFansOrgList(List<FansOrgInfo> list);

    void hasGotOrgs(boolean hasGot);

    void loadSmallCount(List<SmallStatusInfo> smallStatusInfos);
}
