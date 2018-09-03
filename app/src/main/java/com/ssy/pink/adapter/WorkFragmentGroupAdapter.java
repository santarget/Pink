package com.ssy.pink.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseRecycleViewAdapter;
import com.ssy.pink.bean.GroupInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ssy
 * @date 2018/8/17
 */
public class WorkFragmentGroupAdapter extends BaseRecycleViewAdapter<GroupInfo> {

    public WorkFragmentGroupAdapter(Context context, List<GroupInfo> data) {
        super(context, data);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupRecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_group_work_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GroupInfo info = data.get(position);
        GroupRecycleViewHolder myHolder = (GroupRecycleViewHolder) holder;
        myHolder.tvName.setText(info.getCustomerGroupName());
        String str = String.format("[%d/%d]", 2, 4);
        myHolder.tvNumber.setText(str);
        myHolder.checkbox.setChecked(info.isChecked());
    }

    class GroupRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvNumber)
        TextView tvNumber;
        @BindView(R.id.checkbox)
        CheckBox checkbox;

        public GroupRecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListen != null) {
                GroupInfo info = data.get(getAdapterPosition());
                info.setChecked(!info.isChecked());
                checkbox.setChecked(info.isChecked());
                itemClickListen.onItemClick(v, getAdapterPosition());
            }
        }
    }


}
