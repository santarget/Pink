package com.ssy.pink.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.bean.GroupInfo;

/**
 * @author ssy
 * @date 2018/9/3
 */
public class ChooseGroupView extends FrameLayout {
    private Context context;
    private CheckBox checkbox;
    private TextView tvName;
    private TextView tvNumber;
    private GroupInfo groupInfo;

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
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        tvName = (TextView) findViewById(R.id.tvName);
        tvNumber = (TextView) findViewById(R.id.tvNumber);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                groupInfo.setChecked(isChecked);
            }
        });
    }

    public ChooseGroupView setData(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
        checkbox.setChecked(groupInfo.isChecked());
        tvName.setText(groupInfo.getCustomerGroupName());
        String str = String.format("[%d/%d]", 2, 4);
        tvNumber.setText(str);
        return this;
    }
}
