package com.ssy.pink.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ssy.pink.MyApplication;
import com.ssy.pink.R;
import com.ssy.pink.glide.GlideUtils;
import com.ssy.pink.utils.ToastUtils;

import java.io.File;

/**
 * @author ssy
 * @date 2018/8/14
 */
public class CodeInputDialog extends BaseDialog {

    private ImageView ivCode;
    private EditText etCode;
    CodeDialogListener listener;

    public CodeInputDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_code_input;
    }

    public void setListener(CodeDialogListener listener) {
        this.listener = listener;
    }

    @Override
    protected void init() {
        ivCode = (ImageView) rootView.findViewById(R.id.ivCode);
        GlideUtils.loadImageWithoutCache(context, ivCode, new File(MyApplication.getInstance().getExternalFilesDir(null) + "", "verify_code.png"));
        etCode = (EditText) rootView.findViewById(R.id.etCode);
        rootView.findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeStr = etCode.getText().toString();
                if (TextUtils.isEmpty(codeStr)) {
                    ToastUtils.showToast(context, "请输入验证码");
                } else {
                    if (listener != null) {
                        listener.onOKClicked(CodeInputDialog.this, codeStr);
                    }
                }
            }
        });
        rootView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancelClicked(CodeInputDialog.this);
            }
        });
    }

    public interface CodeDialogListener {
        void onOKClicked(CodeInputDialog dialog, String codeStr);

        void onCancelClicked(CodeInputDialog dialog);
    }
}
