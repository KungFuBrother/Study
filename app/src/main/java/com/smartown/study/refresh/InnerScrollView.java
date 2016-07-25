package com.smartown.study.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-07-25 13:11
 * <p>
 * 描述：
 */
public class InnerScrollView extends ScrollView implements Scrollable {

    public InnerScrollView(Context context) {
        super(context);
    }

    public InnerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean cannotScrollUp() {
        if (InnerScrollView.this.getChildAt(0) == null) {
            return true;
        }
        return InnerScrollView.this.getScrollY() == 0;
    }

    @Override
    public boolean cannotScrollDown() {
        if (InnerScrollView.this.getChildAt(0) == null) {
            return false;
        }
        return InnerScrollView.this.getChildAt(0).getMeasuredHeight() == InnerScrollView.this.getScrollY() + getHeight();
    }

}