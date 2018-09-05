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
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.utils.ListUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ssy
 * @date 2018/8/17
 */
public class SmallAdapter extends BaseRecycleViewAdapter<SmallInfo> {

    private OnSlideMenuListener menuListener;

    public SmallAdapter(Context context, List<SmallInfo> data) {
        super(context, data);
    }

    public void setMenuListener(OnSlideMenuListener menuListener) {
        this.menuListener = menuListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupRecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_small, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SmallInfo info = data.get(position);
        GroupRecycleViewHolder myHolder = (GroupRecycleViewHolder) holder;
        myHolder.tvName.setText(info.getSmallWeiboName());
        myHolder.tvWeiboAccout.setText(info.getSmallWeiboNum());
    }

    class GroupRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.menuDelete)
        TextView menuDelete;
        @BindView(R.id.tvWeiboAccout)
        TextView tvWeiboAccout;
        @BindView(R.id.tvLog)
        TextView tvLog;
        @BindView(R.id.tvTime)
        TextView tvTime;

        public GroupRecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @OnClick({R.id.menuDelete})
        public void onViewClicked(View view) {
            switch (view.getId()) {
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
        void onDelete(int position);
    }
}
