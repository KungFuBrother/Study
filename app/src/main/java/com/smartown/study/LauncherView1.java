package com.smartown.study;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

/**
 * Created by Tiger on 2016-04-29.
 */
public class LauncherView1 extends ViewGroup {

    private Scroller scroller;

    public LauncherView1(Context context) {
        super(context);
        init(context);
    }

    public LauncherView1(Context context, AttributeSet attrs) {
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

        scroller = new Scroller(context, new LinearInterpolator());
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
        if (!scroller.isFinished()) {
            return;
        }
        int currentY = getScrollY();
        int deltaY = 0;
        if (currentY < 2 * getMeasuredHeight()) {
            deltaY = getMeasuredHeight();
        } else {
            deltaY = -2 * getMeasuredHeight();
        }
        scroller.startScroll(0, currentY, 0, deltaY, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
    }
}
