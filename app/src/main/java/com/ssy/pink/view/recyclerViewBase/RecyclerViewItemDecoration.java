package com.ssy.pink.view.recyclerViewBase;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Tys on 2017/3/20.
 */
public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {

    private int mDividerWidth = 2;
    private int mDividerColor = Color.WHITE;
    private Drawable mDivider;
    private Paint mPaint;

    public RecyclerViewItemDecoration() {
        initPaint();
    }

    public RecyclerViewItemDecoration(int dividerWidth) {
        this.mDividerWidth = dividerWidth;
        initPaint();
    }

    public RecyclerViewItemDecoration(int dividerWidth, int dividerColor) {
        this.mDividerWidth = dividerWidth;
        this.mDividerColor = dividerColor;
        initPaint();
    }

    public RecyclerViewItemDecoration(int dividerWidth, Drawable mDivider) {
        this.mDividerWidth = dividerWidth;
        this.mDivider = mDivider;
        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mDividerColor);
        mPaint.setStrokeWidth(mDividerWidth);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManger = parent.getLayoutManager();
        if (layoutManger != null) {
            if (layoutManger instanceof LinearLayoutManager && !(layoutManger instanceof GridLayoutManager)) {
                if (((LinearLayoutManager) layoutManger).getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    outRect.set(0, 0, mDividerWidth, 0);
                } else {
                    outRect.set(0, 0, 0, mDividerWidth);
                }
            } else {
                outRect.set(mDividerWidth, mDividerWidth, mDividerWidth, mDividerWidth);
            }
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        RecyclerView.LayoutManager layoutManger = parent.getLayoutManager();
        if (layoutManger != null) {
            if (layoutManger instanceof LinearLayoutManager && !(layoutManger instanceof GridLayoutManager)) {
                if (((LinearLayoutManager) layoutManger).getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    drawHorizontal(c, parent);
                } else {
                    drawVertical(c, parent);
                }
            } else {
                hrawGrideView(c, parent);
            }
        }
    }

    //绘制GrideView item 分割线
    private void hrawGrideView(Canvas canvas, RecyclerView parent) {
        drawGrideVerticalDivider(canvas, parent);
        drawGrideHorizontalDivider(canvas,parent);
    }

    //绘制纵向 item 分割线
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            RecyclerView.LayoutParams itemLayoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            final int top = itemView.getBottom() + itemLayoutParams.bottomMargin;
            final int bottom = top + mDividerWidth;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    //绘制横向 item 分割线
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            int bottom = itemView.getHeight();
            RecyclerView.LayoutParams itemViewLayoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            final int left = itemView.getRight() + itemViewLayoutParams.rightMargin;
            final int right = left + mDividerWidth;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    public void drawGrideVerticalDivider(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        GridLayoutManager linearLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = linearLayoutManager.getSpanCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;

            int left = 0;
            int right = 0;

            //左边第一列
            if ((i % spanCount) == 0) {
                //item左边分割线
                left = child.getLeft();
                right = left + mDividerWidth;
                if( null != mDivider){
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }else{
                    c.drawRect(left, top, right, bottom, mPaint);
                }
                //item右边分割线
                left = child.getRight() + params.rightMargin - mDividerWidth;
                right = left + mDividerWidth;
            } else {
                //非左边第一列
                left = child.getRight() + params.rightMargin - mDividerWidth;
                right = left + mDividerWidth;
            }
            //画分割线
            if(null != mDivider){
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }else{
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    public void drawGrideHorizontalDivider(Canvas c, RecyclerView parent) {

        final int childCount = parent.getChildCount();
        GridLayoutManager linearLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = linearLayoutManager.getSpanCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getLeft() - params.leftMargin - mDividerWidth;
            final int right = child.getRight() + params.rightMargin;
            int top = 0;
            int bottom = 0;

            // 最上面一行
            if ((i / spanCount) == 0) {
                //当前item最上面的分割线
                top = child.getTop();
                //当前item下面的分割线
                bottom = top + mDividerWidth;
                if (null != mDivider) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                } else {
                    c.drawRect(left, top, right, bottom, mPaint);
                }
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mDividerWidth;
            } else {
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mDividerWidth;
            }
            //画分割线
            if (null != mDivider) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            } else {
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}
