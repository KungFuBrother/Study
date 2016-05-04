package com.smartown.study.label;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartown.study.R;
import com.smartown.study.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiger on 2016-05-03.
 */
public class LabelView extends ViewGroup {

    private int dividerWidth = ScreenUtil.dip2px(8);
    private int labelPadding = ScreenUtil.dip2px(8);
    private int labelTextSize = ScreenUtil.dip2px(12);
    private int labelTextColor = Color.WHITE;
    private int labelBackGround = R.color.colorPrimary;
    private List<String> stringArray = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public LabelView(Context context) {
        this(context, null);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
            dividerWidth = typedArray.getDimensionPixelSize(R.styleable.LabelView_dividerWidth, ScreenUtil.dip2px(8));
            labelPadding = typedArray.getDimensionPixelSize(R.styleable.LabelView_labelPadding, ScreenUtil.dip2px(8));
            labelTextSize = typedArray.getDimensionPixelSize(R.styleable.LabelView_labelTextSize, ScreenUtil.dip2px(12));
            labelTextColor = typedArray.getColor(R.styleable.LabelView_labelTextColor, Color.WHITE);
            labelBackGround = typedArray.getResourceId(R.styleable.LabelView_labelBackGround, R.color.colorPrimary);
            CharSequence[] charSequences = typedArray.getTextArray(R.styleable.LabelView_stringArray);
            if (charSequences != null) {
                for (int i = 0; i < charSequences.length; i++) {
                    stringArray.add((String) charSequences[i]);
                }
            }
            typedArray.recycle();
        }
        for (int i = 0; i < stringArray.size(); i++) {
            initLabel(stringArray.get(i));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int totalHeight = 0;
        int usedWidth = 0;
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            LayoutParams lp = child.getLayoutParams();
            child.measure(getChildMeasureSpec(widthMeasureSpec, 0, lp.width),
                    getChildMeasureSpec(heightMeasureSpec, 0, lp.height));
            if (usedWidth + child.getMeasuredWidth() + 2 * dividerWidth > getMeasuredWidth()) {
                usedWidth = 0;
            }
            if (usedWidth == 0) {
                totalHeight += dividerWidth + child.getHeight();
            }
            usedWidth += (dividerWidth + child.getWidth());
        }
        setMeasuredDimension(getMeasuredWidth(), totalHeight + dividerWidth);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int totalHeight = 0;
        int usedWidth = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final int position = i;
            View child = getChildAt(i);
            if (onItemClickListener != null) {
                child.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(position);
                    }
                });
            }
            if (usedWidth + child.getMeasuredWidth() + 2 * dividerWidth > getMeasuredWidth()) {
                usedWidth = 0;
            }
            if (usedWidth == 0) {
                totalHeight += dividerWidth + child.getHeight();
            }
            child.layout(usedWidth + dividerWidth,
                    totalHeight - child.getMeasuredHeight(),
                    usedWidth + dividerWidth + child.getMeasuredWidth(),
                    totalHeight);
            usedWidth += (dividerWidth + child.getWidth());
        }
    }

    private void initLabel(String label) {
        TextView textView = new TextView(getContext());
        textView.setPadding(labelPadding, labelPadding, labelPadding, labelPadding);
        textView.setBackgroundResource(labelBackGround);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, labelTextSize);
        textView.setTextColor(labelTextColor);
        textView.setText(label);
        textView.setGravity(Gravity.CENTER);
        addView(textView);
    }

    public void addLabel(String label) {
        addLabel(label, labelTextSize, labelTextColor, labelBackGround);
    }

    public void addLabel(String label, int labelBackGround) {
        addLabel(label, labelTextSize, labelTextColor, labelBackGround);
    }

    public void addLabel(String label, int labelTextSize, int labelTextColor, int labelBackGround) {
        stringArray.add(label);
        TextView textView = new TextView(getContext());
        textView.setPadding(labelPadding, labelPadding, labelPadding, labelPadding);
        textView.setBackgroundResource(labelBackGround);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, labelTextSize);
        textView.setTextColor(labelTextColor);
        textView.setText(label);
        textView.setGravity(Gravity.CENTER);
        addView(textView);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(int position);

    }

}
