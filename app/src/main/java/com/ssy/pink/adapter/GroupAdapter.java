package com.ssy.pink.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseRecycleViewAdapter;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.utils.ListUtils;

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
        myHolder.tvName.setText(info.getCustomerGroupName());
        myHolder.tvTotalCount.setText(ListUtils.isEmpty(info.getAllSmallInfos()) ? "0" : String.valueOf(info.getAllSmallInfos().size()));
        myHolder.tvNormalCount.setText(ListUtils.isEmpty(info.getValidSmallInfos()) ? "0" : String.valueOf(info.getValidSmallInfos().size()));
        if (position % 2 == 0) {
            myHolder.ivIcon.setImageResource(R.drawable.ic_group2);
        } else {
            myHolder.ivIcon.setImageResource(R.drawable.ic_group3);
        }
    }

    class GroupRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvTotalCount)
        TextView tvTotalCount;
        @BindView(R.id.tvNormalCount)
        TextView tvNormalCount;
        @BindView(R.id.ivIcon)
        ImageView ivIcon;


        public GroupRecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
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
                    onClick(view);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            if (itemClickListen != null) {
                itemClickListen.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnSlideMenuListener {
        void onEdit(int position);

        void onDelete(int position);
    }
}
