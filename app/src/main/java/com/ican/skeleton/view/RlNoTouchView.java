package com.ican.skeleton.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by twy on 2018/1/30.
 */

public class RlNoTouchView extends RelativeLayout {
    public RlNoTouchView(Context context) {
        this(context,null);
    }

    public RlNoTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RlNoTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
