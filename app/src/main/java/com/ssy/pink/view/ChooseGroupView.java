package com.ssy.pink.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.ssy.pink.R;

/**
 * @author ssy
 * @date 2018/9/3
 */
public class ChooseGroupView extends FrameLayout {
    private Context context;

    public ChooseGroupView(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ChooseGroupView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ChooseGroupView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.view_choose_group, this);
    }
}
