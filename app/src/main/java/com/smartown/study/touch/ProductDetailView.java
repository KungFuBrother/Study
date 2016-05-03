package com.smartown.study.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

/**
 * Created by Tiger on 2016-04-29.
 */
public class ProductDetailView extends ViewGroup {

    private int currentPointerY = 0;
    private Scroller scroller;

    private TopView topView;
    private BottomView bottomView;

    private boolean isTopViewShowing = true;

    public ProductDetailView(Context context) {
        super(context);
        init(context);
    }

    public ProductDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        scroller = new Scroller(context, new LinearInterpolator());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (topView == null || bottomView == null) {
            return;
        }
        topView.measure(widthMeasureSpec, heightMeasureSpec);
        bottomView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (topView == null || bottomView == null) {
            return;
        }
        int childCount = getChildCount();
        int y = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(0, y, getMeasuredWidth(), y + getMeasuredHeight());
            y += getMeasuredHeight(); //校准每个子View的起始布局位置
        }
    }

    public void setBottomView(BottomView bottomView) {
        this.bottomView = bottomView;
        addViews();
    }

    public void setTopView(TopView topView) {
        this.topView = topView;
        addViews();
    }

    private void addViews() {
        if (topView != null & bottomView != null) {
            addView(topView);
            addView(bottomView);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (topView == null || bottomView == null) {
            return false;
        }
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                currentPointerY = (int) ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaY = currentPointerY - (int) ev.getY();
                if (isTopViewShowing) {
                    if (topView.atBottom()) {
                        if (deltaY < 0) {
//                            topView在最底部时网上滑动
                            return false;
                        }
                        return true;
                    }
                } else {
                    if (bottomView.atTop()) {
                        if (deltaY > 0) {
//                            bottomView在最顶部时往下滑动
                            return false;
                        }
                        return true;
                    }
                }
                break;

        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (topView == null || bottomView == null) {
            return super.onTouchEvent(event);
        }
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
                    scrollToBottom();
                } else {
                    scrollToTop();
                }
                break;
        }

        return true;
    }

    public void scrollToBottom() {
        if (!scroller.isFinished()) {
            return;
        }
        isTopViewShowing = false;
        scroller.startScroll(0, getScrollY(), 0, getHeight() - getScrollY(), getHeight() - getScrollY());
        invalidate();
    }

    public void scrollToTop() {
        if (!scroller.isFinished()) {
            return;
        }
        isTopViewShowing = true;
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

    public interface ScrollListener {

        boolean atTop();

        boolean atBottom();

    }

}
