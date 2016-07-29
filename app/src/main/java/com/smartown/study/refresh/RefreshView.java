package com.smartown.study.refresh;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-07-25 13:09
 * <p/>
 * 描述：
 */
public class RefreshView extends ViewGroup {

    private int currentPointerY = 0;

    private RefreshTop refreshTop;
    private RefreshBottom refreshBottom;
    private Scrollable contentView;

    public RefreshView(Context context) {
        this(context, null);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        refreshTop = new RefreshTop(context);
        refreshTop.setBackgroundColor(Color.GRAY);
        refreshTop.setText("TOP");
        addView(refreshTop);
        refreshBottom = new RefreshBottom(context);
        refreshBottom.setBackgroundColor(Color.GRAY);
        refreshBottom.setText("BOTTOM");
        addView(refreshBottom);
//        SwipeRefreshLayout

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof RefreshTop) {
                child.measure(widthMeasureSpec, 200);
            } else if (child instanceof RefreshBottom) {
                child.measure(widthMeasureSpec, 200);
            } else {
                child.measure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof RefreshTop) {
                child.layout(0, 0, getMeasuredWidth(), 200);
            } else if (child instanceof RefreshBottom) {
                child.layout(0, getMeasuredHeight() - 200, getMeasuredWidth(), getMeasuredHeight());
            } else {
                child.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        refreshTop.setTranslationY(-200);
        refreshBottom.setTranslationY(200);
    }

    public Scrollable getContentView() {
        return contentView;
    }

    public void setContentView(Scrollable contentView) {
        if (contentView == null || this.contentView != null) {
            return;
        }
        this.contentView = contentView;
        addView(contentView.getView(), 0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (contentView == null) {
            return false;
        }
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                currentPointerY = (int) ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaY = currentPointerY - (int) ev.getY();
                if (contentView.cannotScrollUp()) {
                    if (deltaY < 0) {
                        //在最顶部时往上滑动
                        return true;
                    }
                } else if (contentView.cannotScrollDown()) {
                    if (deltaY > 0) {
                        //在最低部时往下滑动
                        return true;
                    }
                }
                break;

        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (contentView == null) {
            return super.onTouchEvent(event);
        }
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                currentPointerY = y;
                break;
            case MotionEvent.ACTION_MOVE:

                int dy = currentPointerY - y;

//                // 边界值检查
//                int scrollY = getScrollY();
//                // 已经到达顶端，下拉多少，就往上滚动多少
//                if (dy < 0 && scrollY + dy < 0) {
//                    dy = -scrollY;
//                }
//                // 已经到达底部，上拉多少，就往下滚动多少
//                if (dy > 0 && scrollY + dy > getHeight()) {
//                    dy = getHeight() - scrollY;
//                }
                currentPointerY = y;
                if (contentView.cannotScrollUp()) {
                    refreshTop.setTranslationY(refreshTop.getTranslationY() - dy);
//                    refreshTop.offsetTopAndBottom(-dy);
                } else if (contentView.cannotScrollDown()) {
//                    refreshBottom.offsetTopAndBottom(-dy);
                    refreshBottom.setTranslationY(refreshBottom.getTranslationY() - dy);
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }

}
