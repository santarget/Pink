package com.ssy.pink.mvp.presenter;

import com.ssy.greendao.helper.HelperFactory;
import com.ssy.greendao.helper.SmallInfoDbHelper;
import com.ssy.pink.R;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.iview.IGroupDetailActivityView;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class GroupDetailActivityPresenter extends BasePresenter {
    private IGroupDetailActivityView iView;
    GroupInfo groupInfo;
    List<SmallInfo> selectList = new ArrayList<>();
    SmallInfoDbHelper helper;

    public GroupDetailActivityPresenter(IGroupDetailActivityView iView) {
        this.iView = iView;
        helper = HelperFactory.getSmallInfoDbHelper();
    }

    public GroupDetailActivityPresenter setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
        return this;
    }

    public void deleteSmall(final String smallWeiboId) {
        PinkNet.deleteSmall(UserManager.getInstance().userInfo.getCustomernum(), smallWeiboId, new Subscriber<CommonResp<NoBodyEntity>>() {
            @Override
            public void onCompleted() {
                listSmall();
            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonResp<NoBodyEntity> noBodyEntityCommonResp) {
                if (noBodyEntityCommonResp.getCode().equals(ResponseCode.CODE_SUCCESS)) {
                    iView.showToast(R.string.delete_success);
                    helper.deleteByIdsStr(smallWeiboId);
                } else {
                    iView.showToast(noBodyEntityCommonResp.getMsg());
                }
            }
        });
    }

    public void moveSmall(String smallWeiboId, String targetGroupNum) {
        PinkNet.moveSmall(UserManager.getInstance().userInfo.getCustomernum(), smallWeiboId, targetGroupNum, new Subscriber<CommonResp<NoBodyEntity>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonResp<NoBodyEntity> noBodyEntityCommonResp) {
                if (noBodyEntityCommonResp.getCode().equals(ResponseCode.CODE_SUCCESS)) {
                    iView.showToast(R.string.move_success);
                } else {
                    iView.showToast(noBodyEntityCommonResp.getMsg());
                }
            }
        });
    }

    public void listSmall() {
        PinkNet.listSmall(UserManager.getInstance().userInfo.getCustomernum(), new Subscriber<CommonListResp<SmallInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                iView.finishRefresh();
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonListResp<SmallInfo> smallInfoCommonListResp) {
                GroupManager.getInstance().smallInfos.clear();
                if (ListUtils.isEmpty(smallInfoCommonListResp.getData())) {
                    iView.finishRefresh();
                    HelperFactory.getSmallInfoDbHelper().deleteAll();
                    return;
                }
//                HelperFactory.getSmallInfoDbHelper().insertOrReplaceList(smallInfoCommonListResp.getData());
                GroupManager.getInstance().smallInfos.addAll(smallInfoCommonListResp.getData());
                boolean b = GroupManager.getInstance().classifySmall(new Subscriber<List<GroupInfo>>() {
                    @Override
                    public void onCompleted() {
                        iView.finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.finishRefresh();
                        MyUtils.handleExcep(e);
                    }

                    @Override
                    public void onNext(List<GroupInfo> groupInfos) {
                        iView.updateData();
                    }
                });
                if (!b) {
                    //不需要给小号分类
                    iView.finishRefresh();
                }
            }
        });
    }

    /**
     * 全选或取消全选
     *
     * @param selectAll
     */
    public void selectAll(boolean selectAll) {
        for (SmallInfo smallInfo : groupInfo.getAllSmallInfos()) {
            smallInfo.setChecked(selectAll);
        }
        iView.getAdapter().notifyDataSetChanged();
    }

    /**
     * 所有异常账号
     */
    public void selectAbnormal() {
        selectAll(true);
        for (SmallInfo smallInfo : groupInfo.getValidSmallInfos()) {
            smallInfo.setChecked(false);
        }
        iView.getAdapter().notifyDataSetChanged();
    }

}
