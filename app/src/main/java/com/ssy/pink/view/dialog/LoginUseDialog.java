package com.ssy.pink.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.ssy.pink.R;

/**
 * @author ssy
 * @date 2018/8/14
 */
public class LoginUseDialog extends BaseDialog {
    public LoginUseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_login_use;
    }

    @Override
    protected void init() {
        rootView.findViewById(R.id.tvOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
