package com.ssy.pink.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.ssy.pink.R;

/**
 * @author ssy
 * @date 2018/8/14
 */
public class BindingDialog extends BaseDialog {

    public BindingDialog(@NonNull Context context) {
        super(context);
        setCancelable(false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_binding;
    }

    @Override
    protected void init() {

    }
}
