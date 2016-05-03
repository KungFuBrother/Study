package com.smartown.study.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Tiger on 2016-05-03.
 */
public class TopView extends ScrollView implements ProductDetailView.ScrollListener {

    public TopView(Context context) {
        super(context);
    }

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean atTop() {
        if (getChildAt(0) == null) {
            return true;
        }
        return getScrollY() == 0;
    }

    @Override
    public boolean atBottom() {
        if (getChildAt(0) == null) {
            return false;
        }
        return getChildAt(0).getMeasuredHeight() == getScrollY() + getHeight();
    }

}
