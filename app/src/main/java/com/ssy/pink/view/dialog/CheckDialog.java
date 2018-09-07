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
public class CheckDialog extends BaseDialog {
    public static final int STATUS_CHECKING = 0;
    public static final int STATUS_FINISH = 1;
    public static final int STATUS_ERROR = 2;

    private ImageView ivIcon;
    private TextView tvTips, tvErrorWord;

    public CheckDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_check;
    }

    @Override
    protected void init() {
        rootView.findViewById(R.id.tvOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ivIcon = (ImageView) rootView.findViewById(R.id.ivIcon);
        tvTips = (TextView) rootView.findViewById(R.id.tvTips);
        tvErrorWord = (TextView) rootView.findViewById(R.id.tvErrorWord);
    }

    public CheckDialog setStatus(int status) {
        if (status == STATUS_CHECKING) {
            ivIcon.setImageResource(R.drawable.icon_dialog_check_error);
            tvTips.setText("正在检测...");
            tvErrorWord.setVisibility(View.GONE);
        } else if (status == STATUS_ERROR) {
            ivIcon.setImageResource(R.drawable.icon_dialog_check_error);
            tvTips.setText("发现异常账号格式，请检查");
            tvErrorWord.setVisibility(View.VISIBLE);
        } else {
            ivIcon.setImageResource(R.drawable.icon_dialog_check_normal);
            tvTips.setText("检测完成，未发现异常格式");
            tvErrorWord.setVisibility(View.GONE);
        }
        return this;
    }

    public CheckDialog setErrorWord(String errorWord) {
        if (TextUtils.isEmpty(errorWord)) {
            tvErrorWord.setVisibility(View.GONE);
        } else {
            tvErrorWord.setText("异常位置：“ " + errorWord + " ”");
            tvErrorWord.setVisibility(View.VISIBLE);
        }
        return this;
    }
}
