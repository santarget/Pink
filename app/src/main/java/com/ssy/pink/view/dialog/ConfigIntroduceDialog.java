package com.ssy.pink.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.ssy.pink.R;

/**
 * @author ssy
 * @date 2018/8/14
 */
public class ConfigIntroduceDialog extends BaseDialog {
    private TextView tvTips;

    public ConfigIntroduceDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_config_introduce;
    }

    @Override
    protected void init() {
        tvTips = (TextView) rootView.findViewById(R.id.tvTips);
    }

    public ConfigIntroduceDialog setTips(String str) {
        if (tvTips != null) {
            tvTips.setText(str);
        }
        return this;
    }

    public ConfigIntroduceDialog setTips(int strId) {
        if (tvTips != null) {
            tvTips.setText(strId);
        }
        return this;
    }
}
