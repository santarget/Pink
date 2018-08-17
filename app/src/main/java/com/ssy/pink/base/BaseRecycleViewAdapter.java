package com.ssy.pink.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.ssy.pink.utils.ListUtils;

import java.util.List;


/**
 * RecycleView Adapter 的基类，方便对数据的统一操作（排序，过滤）
 *
 * @param <DataType> Adapter的数据类型
 */
public abstract class BaseRecycleViewAdapter<DataType> extends RecyclerView.Adapter {
    protected List<DataType> data;
    protected Context context;
    protected OnItemClickListen itemClickListen;
    protected OnItemLongClickListen itemLongClickListen;

    public BaseRecycleViewAdapter(Context context, List<DataType> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public void addData(DataType data) {
        this.data.add(data);
        adapterEmptyCallBack();
        notifyItemChanged(getItemCount());
    }

    public void addData(DataType data, int position) {
        this.data.add(position, data);
        adapterEmptyCallBack();
        notifyDataSetChanged();
    }

    private void adapterEmptyCallBack() {
        if (this.data.isEmpty()) {
            adapterIsEmpty(true);
        } else {
            adapterIsEmpty(false);
        }
    }


    public void addData(List<DataType> data) {
        this.data.addAll(data);
        adapterEmptyCallBack();
        sortFile(null, null);
        notifyDataSetChanged();
    }

    public void addDataInHead(List<DataType> data) {
        this.data.addAll(0, data);
        adapterEmptyCallBack();
        sortFile(null, null);
        notifyDataSetChanged();
    }

    public void insertOrUpdate(DataType newData) {
        int index = this.data.indexOf(newData);
        if (index >= 0) {
            this.data.remove(index);
            this.data.add(index, newData);
            notifyItemChanged(index);
        } else {
            addData(newData, 0);
        }
    }

    public void updateAll(List<DataType> data) {
        this.data.clear();
        if (!ListUtils.isEmpty(data)){
            this.data.addAll(data);
        }
        adapterEmptyCallBack();
        notifyDataSetChanged();
    }

    public void changeDataSource(List<DataType> data) {
        this.data = data;
        if (this.data.isEmpty()) {
            adapterIsEmpty(true);
        } else {
            adapterIsEmpty(false);
        }
        sortFile(null, null);
        notifyDataSetChanged();
    }

    /**
     * 批量添加数据时调用
     *
     * @param isEmpty 添加后数据是否为空
     */
    protected void adapterIsEmpty(boolean isEmpty) {
    }

    /**
     * 批量添加数据时调用
     *
     * @param sortType  排序的字段，为null则表示使用当前默认字段
     * @param direction 排序的方向，为null则表示使用当前默认的方向
     */
    public void sortFile(@Nullable String sortType, @Nullable String direction) {
    }

    public void delete(DataType data) {
        int index = this.data.indexOf(data);
        if (index >= 0) {
            this.data.remove(index);
            adapterEmptyCallBack();
            notifyDataSetChanged();
        }
    }

    public List<DataType> getDatas() {
        return this.data;
    }

    public DataType getData(int position) {
        return this.data.get(position);
    }

    public void cleanData() {
        this.data.clear();
    }

    public void setItemClickListen(OnItemClickListen clickListen) {
        this.itemClickListen = clickListen;
    }

    public void setItemLongClickListen(OnItemLongClickListen longClickListen) {
        this.itemLongClickListen = longClickListen;
    }

    public interface OnItemClickListen {
        /**
         * 文件夹下的单击
         *
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListen {
        void onItemLongClick(View view, int position);
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        public BaseViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (itemLongClickListen != null) {
                itemLongClickListen.onItemLongClick(v, getAdapterPosition());
                return true;
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            if (itemClickListen != null)
                itemClickListen.onItemClick(v, getAdapterPosition());
            onItemClick(v, getAdapterPosition());
        }

        public void onItemClick(View view, int position) {

        }
    }
}
