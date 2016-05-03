package com.smartown.study.label;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartown.study.R;
import com.smartown.study.ScreenUtil;

/**
 * Created by Tiger on 2016-05-03.
 */
public class LabelView extends ViewGroup {

    private int margin = ScreenUtil.dip2px(8);

    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currentLine = 0;
        int usedWidth = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (usedWidth + 56 + 2 * margin > getWidth() - 2 * margin) {
                currentLine += 1;
                usedWidth = 0;
            }
            child.layout(usedWidth + margin,
                    currentLine * (56 + 2 * margin) + margin,
                    usedWidth + margin + 56,
                    currentLine * (56 + 2 * margin) + margin + 56);
            usedWidth += 56 + 2 * margin;
        }
    }

    public void addLabel(String label) {
        TextView textView = new TextView(getContext());
        textView.setPadding(ScreenUtil.dip2px(8), ScreenUtil.dip2px(8), ScreenUtil.dip2px(8), ScreenUtil.dip2px(8));
        textView.setBackgroundResource(R.drawable.selector_label);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView.setTextColor(Color.WHITE);
        textView.setText(label);
        textView.setGravity(Gravity.CENTER);
        addView(textView);
    }

}
