package com.railwayticket.myapp.mywork.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by tianyuanyuan on 2016/8/26.
 */
public class MextraImageView extends ImageView {
    public MextraImageView(Context context) {
        super(context);
    }

    public MextraImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MextraImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected boolean onSetAlpha(int alpha) {
        return super.onSetAlpha(alpha);
    }

    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();
    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
