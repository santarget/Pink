package com.ssy.pink.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssy.pink.R;

/**
 * @author ssy
 * @date 2018/8/14
 */
public class InvalidDialog extends BaseDialog {

    public InvalidDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_invalid;
    }

    @Override
    protected void init() {
        rootView.findViewById(R.id.tvOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                System.exit(0);
            }
        });

    }
}
