package com.ssy.pink.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseRecycleViewAdapter;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.bean.SmallInfo;

import java.text.Format;
import java.util.List;

/**
 * @author ssy
 * @date 2018/8/29
 */
public class HandleAbnormalAdapter extends BaseRecycleViewAdapter<SmallInfo> {

    public HandleAbnormalAdapter(Context context, List<SmallInfo> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HandleAbnormalViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_handle_abnormal, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SmallInfo info = data.get(position);
        HandleAbnormalViewHolder myHolder = (HandleAbnormalViewHolder) holder;
        String str = String.format("%s账号", info.getSmallWeiboNum());
        myHolder.tvAccout.setText(str);

    }

    class HandleAbnormalViewHolder extends BaseViewHolder {
        TextView tvAccout;

        public HandleAbnormalViewHolder(View itemView) {
            super(itemView);
            tvAccout = (TextView) itemView.findViewById(R.id.tvAccout);
        }
    }
}
