package com.railwayticket.myapp.mywork.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by tianyuanyuan on 2016/10/24.
 */
public class MyRoundImageView extends ImageView {

    public MyRoundImageView(Context context) {
        super(context);
    }

    public MyRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.bg_yuan_down);
    }

    public MyRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint=new Paint();
        paint.setARGB(66,66,66,66);
        canvas.drawCircle(100,100,40,paint);
    }
}
