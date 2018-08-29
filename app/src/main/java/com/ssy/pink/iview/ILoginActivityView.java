package com.ssy.pink.iview;


import com.ssy.pink.bean.FansOrgInfo;

import java.util.List;

public interface ILoginActivityView extends IView {
    void setOrgsList(List<FansOrgInfo> orgsList);

    void hasGotOrgs(boolean hasGot);
}
