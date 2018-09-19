package com.ssy.pink.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssy.pink.R;

/**
 * @author ssy
 * @date 2018/8/14
 */
public class BindAbnormalDialog extends BaseDialog {
    private TextView tvTips;
    BindAbnormalListener listener;

    public BindAbnormalDialog(@NonNull Context context) {
        super(context);
    }

    public BindAbnormalDialog(@NonNull Context context, BindAbnormalListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_bind_abnormal;
    }

    @Override
    protected void init() {
        rootView.findViewById(R.id.tvOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEnd(BindAbnormalDialog.this);
                }
            }
        });
        rootView.findViewById(R.id.tvHandle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onHandle(BindAbnormalDialog.this);
                }
            }
        });
        tvTips = (TextView) rootView.findViewById(R.id.tvTips);
    }

    public BindAbnormalDialog setTips(int count) {
        SpannableString spannableString = new SpannableString("有" + String.valueOf(count) + "个账号绑定失败");
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF919191")), 0, 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tv2.text = spannableString
        String str = "有" + String.valueOf(count) + "个账号绑定失败";
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#FF919191")), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#FFDF3F61")), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#FF919191")), 2, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTips.setText(style);
        return this;
    }

    public interface BindAbnormalListener {
        void onEnd(BindAbnormalDialog dialog);

        void onHandle(BindAbnormalDialog dialog);
    }
}
