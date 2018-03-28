package com.ican.skeleton.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.ican.skeleton.R;
import com.ican.skeleton.databinding.ViewErrorBinding;
import com.ican.skeleton.entity.OnRetryClickListner;


/**
 * @author yelin.wu 16/3/22 下午2:50.
 * @description
 */
public class ErrorView extends LinearLayout {

    private ViewErrorBinding mNoDataViewBinding;

    public ErrorView(Context context) {
        super(context);
        init(context);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mNoDataViewBinding = DataBindingUtil.inflate((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE), R.layout.view_error, this, false);
        addView(mNoDataViewBinding.getRoot());

        initListner();
    }

    private void initListner() {
        mNoDataViewBinding.tvRetry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner!=null){
                    listner.OnRetry(v);
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    public OnRetryClickListner listner;

    public void setOnRetryClickListner(OnRetryClickListner listner){
        this.listner = listner;
    }
}
