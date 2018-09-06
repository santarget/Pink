package com.ssy.pink.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.activity.MainActivity;
import com.ssy.pink.mvp.iview.IView;
import com.ssy.pink.utils.ToastUtils;

/**
 * Created by Tys on 2017/1/27.
 */
public abstract class BaseFragment extends Fragment implements IView {
    public MainActivity mainActivity;
    public TextView tvTitle;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;

    public void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        //super.onSaveInstanceState(outState);   //将这一行注释掉，阻止activity保存fragment的状态
        if (outState != null) {//存在Bundle数据,去除fragments的状态保存，解决Fragme错乱问题。
            String FRAGMENTS_TAG = "android:support:fragments";
            outState.remove(FRAGMENTS_TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            return inflater.inflate(getContentViewLayoutID(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    //在onCreateView之后触发,在onStart之后
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        super.onViewCreated(view, savedInstanceState);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        initViewsAndEvents(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    protected abstract int getContentViewLayoutID();

    protected abstract void initViewsAndEvents(View view);

    protected abstract void DetoryViewAndThing();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DetoryViewAndThing();
    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            onUserInvisible();
            //正常应该走下面
            /*if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }*/
        }
    }

    protected abstract void onFirstUserVisible();

    protected abstract void onUserVisible();

    protected void onFirstUserInvisible() {
    }

    protected abstract void onUserInvisible();

    @Override
    public void showToast(int strId) {
        ToastUtils.showToast(mainActivity, strId);
    }

    @Override
    public void showToast(String str) {
        ToastUtils.showToast(mainActivity, str);
    }


}
