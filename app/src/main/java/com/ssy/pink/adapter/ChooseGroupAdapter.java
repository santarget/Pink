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
import com.ssy.pink.bean.GroupInfo;

import java.util.List;

/**
 * @author ssy
 * @date 2018/8/29
 */
public class ChooseGroupAdapter extends BaseRecycleViewAdapter<GroupInfo> {

    public ChooseGroupAdapter(Context context, List<GroupInfo> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseOrgViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_choose_org, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GroupInfo info = data.get(position);
        ChooseOrgViewHolder myHolder = (ChooseOrgViewHolder) holder;
        myHolder.tvOrg.setText(info.getCustomergroupname());
        myHolder.tvOrg.setSelected(info.isChecked());

    }

    class ChooseOrgViewHolder extends BaseViewHolder {
        TextView tvOrg;

        public ChooseOrgViewHolder(View itemView) {
            super(itemView);
            tvOrg = (TextView) itemView.findViewById(R.id.tvOrg);
        }
    }
}
