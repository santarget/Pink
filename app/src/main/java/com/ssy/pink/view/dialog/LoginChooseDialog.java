package com.ssy.pink.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputType;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ssy.pink.R;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.EventWithObj;

import org.greenrobot.eventbus.EventBus;

/**
 * @author ssy
 * @date 2018/8/14
 */
public class LoginChooseDialog extends BaseDialog {
    private EditText ed1, ed2, ed3;

    public LoginChooseDialog(@NonNull Context context) {
        super(context);
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
        ed1 = (EditText) rootView.findViewById(R.id.ed1);
        ed2 = (EditText) rootView.findViewById(R.id.ed2);
        ed3 = (EditText) rootView.findViewById(R.id.ed3);

        ed1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeSelectedStatus(v);
                }
            }
        });
        ed2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeSelectedStatus(v);
                }
            }
        });
        ed3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeSelectedStatus(v);
                }
            }
        });
        changeSelectedStatus(ed1);
    }

    private String getOrg() {
        String str;
        if (ed1.isSelected()) {
            str = ed1.getText().toString();
        } else if (ed2.isSelected()) {
            str = ed2.getText().toString();
        } else {
            str = ed3.getText().toString();
        }
        return str;
    }

    private void changeSelectedStatus(View v) {
        ed1.setSelected(false);
        ed2.setSelected(false);
        ed3.setSelected(false);
        v.setSelected(true);
    }
}
