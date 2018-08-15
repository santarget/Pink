package com.ssy.pink.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.ssy.pink.R;

/**
 * @author ssy
 * @date 2018/8/14
 */
public class LoginQuestionDialog extends BaseDialog {
    public LoginQuestionDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_login_question;
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
