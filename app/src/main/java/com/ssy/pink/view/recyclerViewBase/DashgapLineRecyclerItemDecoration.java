package com.ssy.pink.view.recyclerViewBase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ssy.pink.MyApplication;
import com.ssy.pink.R;


/**
 * 虚线分割线
 */
public class DashgapLineRecyclerItemDecoration extends RecyclerView.ItemDecoration {
    private final Context context;
    private final int mOrientation;
    private Paint pain;
    private int defaultDividingLineColor = R.color.line;
    private float defaultDividerWidth = 10;
    private float leftOffset = 0;
    private float rightOffset = 0;


    public DashgapLineRecyclerItemDecoration(Context context, int orientation) {
        if (orientation != OrientationHelper.VERTICAL && orientation != OrientationHelper.HORIZONTAL) {
            throw new IllegalArgumentException("invalide orientation");
        } else {
            this.mOrientation = orientation;
            this.context = context;
        }
        pain = new Paint(Paint.ANTI_ALIAS_FLAG);
        settingPaint();
    }

    protected void settingPaint() {
        pain.setStyle(Paint.Style.STROKE);
        pain.setAntiAlias(true);
        pain.setColor(getDividingLineColor());
        pain.setStrokeWidth(getDividerWidth());
        pain.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == OrientationHelper.HORIZONTAL) {
            drawHorizontal(c, parent, state);
        } else {
            drawVertical(c, parent, state);
        }
    }

    /**
     * item  偏移 分割线会占据一定的距离
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == OrientationHelper.HORIZONTAL) {
            outRect.set(0, 0, (int) getDividerWidth(), 0);
        } else {
            outRect.set(0, 0, 0, (int) getDividerWidth());
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {

        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            RecyclerView.LayoutParams itemViewLayoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            final float left = itemView.getRight() + itemViewLayoutParams.rightMargin;
//            final float right = left + getDividerWidth();
            Path path = new Path();
            path.moveTo(left, top);
            path.lineTo(left,bottom);
            c.drawPath(path, pain);
//            c.drawRect(left, top, right, bottom, pain);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            RecyclerView.LayoutParams itemLayoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            final float top = itemView.getBottom() + itemLayoutParams.bottomMargin;
            final float bottom = top + getDividerWidth();

            c.drawRect(left + leftOffset, top, right - rightOffset, bottom, pain);
        }
    }

    public float getDividerWidth() {
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                defaultDividerWidth, context.getResources().getDisplayMetrics());
        return defaultDividerWidth;
    }

    public int getDividingLineColor() {
        return MyApplication.getInstance().getResources().getColor(defaultDividingLineColor);
    }

    public void setDividingLineColor(int defaultDividingLineColor) {
        this.defaultDividingLineColor = defaultDividingLineColor;
    }

    public void setDividerWidth(float defaultDividerWidth) {
        this.defaultDividerWidth = defaultDividerWidth;
    }

    public void setLeftOffset(int leftOffset) {
        this.leftOffset = leftOffset;
    }

    public void setRightOffset(int rightOffset) {
        this.rightOffset = rightOffset;
    }
}
