package com.ssy.pink.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ssy.pink.R;
import com.ssy.pink.adapter.ChooseOrgAdapter;
import com.ssy.pink.base.BaseRecycleViewAdapter;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.EventWithObj;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.view.recyclerViewBase.LinerRecyclerItemDecoration;
import com.ssy.pink.view.recyclerViewBase.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ssy
 * @date 2018/8/14
 */
public class LoginChooseDialog extends BaseDialog {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ChooseOrgAdapter adapter;
    private List<FansOrgInfo> datas;
    private FansOrgInfo selectedInfo;


    public LoginChooseDialog(@NonNull Context context) {
        super(context);
    }

    public void setDatas(List<FansOrgInfo> fansOrgInfoList) {
        this.datas = fansOrgInfoList;
        adapter = new ChooseOrgAdapter(context, datas);
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
        return R.layout.dialog_login_choose;
    }

    @Override
    protected void init() {
        rootView.findViewById(R.id.tvOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventWithObj<>(EventCode.LOGIN_CHOOSE_ORG, getOrg()));
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

    private FansOrgInfo getOrg() {
        return selectedInfo;
    }

    private void changeSelectedStatus() {
        if (ListUtils.isEmpty(datas)) {
            return;
        }
        for (FansOrgInfo fansOrgInfo : datas) {
            fansOrgInfo.setSelected(false);
        }
        selectedInfo.setSelected(true);
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
