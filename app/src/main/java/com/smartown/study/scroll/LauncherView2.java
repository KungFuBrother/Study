package com.smartown.study.scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

/**
 * Created by Tiger on 2016-04-29.
 */
public class LauncherView2 extends ViewGroup {

    private int viewHeight = 0;
    private int currentPointerY = 0;
    private Scroller scroller;

    public LauncherView2(Context context) {
        super(context);
        init(context);
    }

    public LauncherView2(Context context, AttributeSet attrs) {
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 如果当前正在滚动，调用父类的onTouchEvent
        if (!scroller.isFinished()) {
            return super.onTouchEvent(event);
        }
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                currentPointerY = y;
                break;
            case MotionEvent.ACTION_MOVE:

                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                int dy = currentPointerY - y;

                // 边界值检查
                int scrollY = getScrollY();
                // 已经到达顶端，下拉多少，就往上滚动多少
                if (dy < 0 && scrollY + dy < 0) {
                    dy = -scrollY;
                }
                // 已经到达底部，上拉多少，就往下滚动多少
                if (dy > 0 && scrollY + dy > getHeight()) {
                    dy = getHeight() - scrollY;
                }
                scrollBy(0, dy);
                currentPointerY = y;
                break;
            case MotionEvent.ACTION_UP:
                if (getScrollY() > getHeight() / 2)// 往上滑动
                {
                    scrollUp();
                } else {
                    scrollDown();
                }
                postInvalidate();
                break;
        }

        return true;
    }

    public void scrollUp() {
        if (!scroller.isFinished()) {
            return;
        }
        scroller.startScroll(0, getScrollY(), 0, getHeight() - getScrollY(), getHeight() - getScrollY());
        invalidate();
    }

    public void scrollDown() {
        if (!scroller.isFinished()) {
            return;
        }
        scroller.startScroll(0, getScrollY(), 0, -getScrollY(), getScrollY());
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
