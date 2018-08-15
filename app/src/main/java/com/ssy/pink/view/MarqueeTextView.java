package com.ssy.pink.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * 跑马灯TextView
 * 代码设置单行无效，需要在xml文件配置
 *
 * @author ssy
 * @date 2018/7/16
 */
public class MarqueeTextView extends android.support.v7.widget.AppCompatTextView {
    public MarqueeTextView(Context context) {
        super(context);
        init();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setMarqueeRepeatLimit(-1);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public void setEllipsize(TextUtils.TruncateAt where) {
        super.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }
}
