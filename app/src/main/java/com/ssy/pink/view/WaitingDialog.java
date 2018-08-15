package com.ssy.pink.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ssy.pink.MyApplication;
import com.ssy.pink.R;



public class WaitingDialog extends Dialog {
    private Context context;

    /***
     * @param context
     */
    public WaitingDialog(Context context) {
        super(context, R.style.dialog_normal);
        this.context = context;
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.y = -TypedValue.complexToDimensionPixelSize(15, MyApplication.getInstance().getResources().getDisplayMetrics());
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View conventView = mInflater.inflate(R.layout.dialog_waiting, null);
        // 上两句如同这一句LayoutInflater.from(context).inflate(resourceId,null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(conventView, params);
    }

}
