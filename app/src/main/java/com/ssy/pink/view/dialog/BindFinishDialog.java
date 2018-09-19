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
public class BindFinishDialog extends BaseDialog {
    BindFinishListener listener;

    public BindFinishDialog(@NonNull Context context) {
        super(context);
    }

    public BindFinishDialog(@NonNull Context context, BindFinishListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_bind_finish;
    }

    @Override
    protected void init() {
        rootView.findViewById(R.id.tvOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onOK(BindFinishDialog.this);
                } else {
                    dismiss();
                }
            }
        });
    }

    public interface BindFinishListener {
        void onOK(BindFinishDialog dialog);
    }
}
