package com.ssy.pink.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseRecycleViewAdapter;
import com.ssy.pink.bean.GroupInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ssy
 * @date 2018/8/17
 */
public class GroupAdapter extends BaseRecycleViewAdapter<GroupInfo> {
    private OnSlideMenuListener menuListener;

    public GroupAdapter(Context context, List<GroupInfo> data) {
        super(context, data);
    }

    public void setMenuListener(OnSlideMenuListener menuListener) {
        this.menuListener = menuListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupRecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_group, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GroupInfo info = data.get(position);
        GroupRecycleViewHolder myHolder = (GroupRecycleViewHolder) holder;
        myHolder.tvName.setText(info.name);
        myHolder.tvTotalCount.setText(String.valueOf(info.totalCount));
        myHolder.tvNormalCount.setText(String.valueOf(info.normalCount));
    }

    class GroupRecycleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvTotalCount)
        TextView tvTotalCount;
        @BindView(R.id.tvNormalCount)
        TextView tvNormalCount;


        public GroupRecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.menuEdit, R.id.menuDelete})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.menuEdit:
                    if (menuListener != null) {
                        menuListener.onEdit(getAdapterPosition());
                    }
                    break;
                case R.id.menuDelete:
                    if (menuListener != null) {
                        menuListener.onDelete(getAdapterPosition());
                    }
                    break;
                default:
                    if (itemClickListen != null) {
                        itemClickListen.onItemClick(view, getAdapterPosition());
                    }
                    break;
            }
        }
    }

    public interface OnSlideMenuListener {
        void onEdit(int position);

        void onDelete(int position);
    }
}
