package com.smartown.study;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tiger on 2016-04-29.
 */
public class LauncherView extends ViewGroup {

    public LauncherView(Context context) {
        super(context);
        init(context);
    }

    public LauncherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view1 = new View(context);
        view1.setBackgroundColor(Color.RED);

        View view2 = new View(context);
        view2.setBackgroundColor(Color.GREEN);

        View view3 = new View(context);
        view3.setBackgroundColor(Color.BLUE);
        addView(view1);
        addView(view2);
        addView(view3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 设置该ViewGroup的大小
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            // 设置每个子视图的大小
            child.measure(width, height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int y = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(0, y, getMeasuredWidth(), y + getMeasuredHeight());
            y += getMeasuredHeight(); //校准每个子View的起始布局位置
        }
    }

    public void clickToScroll() {
        if (getScrollY() < 2 * getMeasuredHeight()) {
            scrollBy(0, getMeasuredHeight());
        } else {
            scrollTo(0, 0);
        }
    }

}
