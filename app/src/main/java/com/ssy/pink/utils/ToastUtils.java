package com.ssy.pink.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ssy.pink.R;


/**
 * @author ssy
 * @date 2018/3/28
 */
public class ToastUtils {
    private static Toast mToast;

    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId));
    }

    public static void showToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
//            mToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

   /* public static void showLastAccessToast(Context context) {
        if (LoginManager.getInstance().getLoginResponse() == null) {
            return;
        }
        LastAccessTerminal lastAccessTerminal = LoginManager.getInstance().getLoginResponse().getLastAccessTerminal();
        if (lastAccessTerminal == null) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //自定义布局
        View view = inflater.inflate(R.layout.toast_last_access, null);
        //自定义toast文本
        TextView tvTime, tvIp, tvType;
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        tvIp = (TextView) view.findViewById(R.id.tvIp);
        tvType = (TextView) view.findViewById(R.id.tvType);
        tvTime.setText(CommonUtils.formatData(null, Long.valueOf(lastAccessTerminal.getLastAccessAt())));
        tvIp.setText(lastAccessTerminal.getLastAccessIP());
        tvType.setText(lastAccessTerminal.getDeviceType());
        Toast toast = new Toast(context);

        //设置toast居中显示
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);

        toast.show();
    }*/
}
