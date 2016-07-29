package com.smartown.study.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-07-25 9:50
 * <p/>
 * 描述：
 */
public class RefreshTop extends TextView {

    public RefreshTop(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
    }

    public RefreshTop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
