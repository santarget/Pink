package com.ssy.pink.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseRecycleViewAdapter;
import com.ssy.pink.bean.BindLogInfo;
import com.ssy.pink.bean.FansOrgInfo;

import java.util.List;

/**
 * @author ssy
 * @date 2018/8/29
 */
public class BindLogAdapter extends BaseRecycleViewAdapter<BindLogInfo> {

    public BindLogAdapter(Context context, List<BindLogInfo> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindLogViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_bind_log, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BindLogInfo info = data.get(position);
        BindLogViewHolder myHolder = (BindLogViewHolder) holder;
        if (info.getStatus() == 1) {
            String str = String.format("%s 账号绑定成功。", info.getSmallInfo().getSmallWeiboNum());
            myHolder.tvLog.setText(str);
            myHolder.tvLog.setTextColor(context.getResources().getColor(R.color.txt_dialog_title));
        } else if (info.getStatus() == 0) {
            String str = String.format("%s 账号绑定失败，原因：%s", info.getSmallInfo().getSmallWeiboNum(), info.getMsg());
            myHolder.tvLog.setText(str);
            myHolder.tvLog.setTextColor(context.getResources().getColor(R.color.txt_small_log_red));
        } else {
            String str = String.format("%s 账号绑定中...", info.getSmallInfo().getSmallWeiboNum());
            myHolder.tvLog.setText(str);
            myHolder.tvLog.setTextColor(context.getResources().getColor(R.color.txt_dialog_title));
        }

        if (position == 2) {
            String str = String.format("%s 账号绑定失败，原因：%s", info.getSmallInfo().getSmallWeiboNum(), info.getMsg());
            myHolder.tvLog.setText(str + "用户被锁定");
            myHolder.tvLog.setTextColor(context.getResources().getColor(R.color.txt_small_log_red));
        }
    }

    class BindLogViewHolder extends BaseViewHolder {
        TextView tvLog;

        public BindLogViewHolder(View itemView) {
            super(itemView);
            tvLog = (TextView) itemView.findViewById(R.id.tvLog);
        }
    }
}
