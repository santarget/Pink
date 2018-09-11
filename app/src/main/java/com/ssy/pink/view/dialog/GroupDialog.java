package com.ssy.pink.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.ssy.pink.R;
import com.ssy.pink.adapter.ChooseGroupAdapter;
import com.ssy.pink.adapter.ChooseOrgAdapter;
import com.ssy.pink.base.BaseRecycleViewAdapter;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.EventWithObj;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.view.recyclerViewBase.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author ssy
 * @date 2018/8/14
 */
public class GroupDialog extends BaseDialog {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ChooseGroupAdapter adapter;
    private List<GroupInfo> datas;
    private GroupInfo selectedInfo;


    public GroupDialog(@NonNull Context context) {
        super(context);
    }

    public void setDatas(List<GroupInfo> fansOrgInfoList) {
        this.datas = fansOrgInfoList;
        adapter = new ChooseGroupAdapter(context, datas);
        adapter.setItemClickListen(new BaseRecycleViewAdapter.OnItemClickListen() {
            @Override
            public void onItemClick(View view, int position) {
                selectedInfo = datas.get(position);
                changeSelectedStatus();
            }
        });
        recyclerView.setAdapter(adapter);
        showProgress(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_group;
    }

    @Override
    protected void init() {
        rootView.findViewById(R.id.tvOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventWithObj<>(EventCode.MOVE_SMALL, getGroup()));
                dismiss();
            }
        });
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        //设置RecyclerView垂直布局
        recyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.VERTICAL, false));
        //设置分割线
        recyclerView.addItemDecoration(new SpaceItemDecoration(15));
        showProgress(true);
    }

    private GroupInfo getGroup() {
        return selectedInfo;
    }

    private void changeSelectedStatus() {
        if (ListUtils.isEmpty(datas)) {
            return;
        }
        for (GroupInfo fansOrgInfo : datas) {
            fansOrgInfo.setChecked(false);
        }
        selectedInfo.setChecked(true);
        adapter.notifyDataSetChanged();
    }

    private void showProgress(boolean showProgress) {
        if (showProgress) {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
