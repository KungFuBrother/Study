package com.smartown.study.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-07-25 13:09
 * <p>
 * 描述：
 */
public class RefreshView<T extends Scrollable> extends ViewGroup {

    private Scrollable scrollable;

    public RefreshView(Context context) {
        super(context);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

}
