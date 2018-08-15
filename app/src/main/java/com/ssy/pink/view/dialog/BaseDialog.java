package com.ssy.pink.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ssy.pink.R;
import com.ssy.pink.utils.CommonUtils;


/**
 * @author ssy
 * @date 2018/8/14
 */
public abstract class BaseDialog extends Dialog {
    public Context context;
    public View rootView;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        setCancelable(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int) (CommonUtils.getScreenWidth() * .95f);
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = mInflater.inflate(getLayoutId(), null);
        // 上两句如同这一句LayoutInflater.from(context).inflate(resourceId,null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(rootView, params);

        View close = rootView.findViewById(R.id.ivClose);
        if (close != null) {
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        init();
    }

    public abstract int getLayoutId();

    protected abstract void init();
}
