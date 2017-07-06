package com.railwayticket.myapp.mywork.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tianyuanyuan on 2016/12/27.
 */
public class GuaguaCardView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();//绘制的路径
    private Canvas canvas;
    private Bitmap mBitmap;
    private int lastX;//上一个点的X
    private int lastY;//上一个点的Y

    private Bitmap mBackBitmap;

    private boolean isComplete;
    private int percent;
    private ShowButton showButton;

    private Paint paintUp = new Paint();
    private String tipText = "1000猪宝刮一次";
    private Rect tipBound = new Rect();

    public GuaguaCardView(Context context) {
        super(context);
    }

    public GuaguaCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GuaguaCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();
    }

    public void setShowButton(ShowButton showButton) {
        this.showButton = showButton;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(mBitmap);

        setPaint();
        setPaintUp();
        canvas.drawColor(Color.parseColor("#AAAAAA"));
        canvas.drawText(tipText, getWidth() / 2 - tipBound.width() / 2,
                getHeight() / 2 + tipBound.height() / 2, paintUp);
    }

    private void setPaintUp() {

        paintUp.setStyle(Paint.Style.FILL);
        paintUp.setColor(Color.parseColor("#ff0000"));
        paintUp.setTextSize(22);
        paintUp.getTextBounds(tipText, 0, tipText.length(), tipBound);

    }


    private Paint mBackPint = new Paint();
    private Rect mTextBound = new Rect();
    private String mText = "500,0000,000";


    /**
     * 初始化canvas的绘制用的画笔
     */


    private void setPaint() {

        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ff0000"));
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(20);


        mBackPint.setStyle(Paint.Style.FILL);
        mBackPint.setTextScaleX(2f);
        mBackPint.setColor(Color.DKGRAY);
        mBackPint.setTextSize(22);
        mBackPint.getTextBounds(mText, 0, mText.length(), mTextBound);

    }


    @Override
    protected void onDraw(Canvas canvas) {

//        canvas.drawBitmap(mBackBitmap,(int)(getWidth()/2-this.getWidth()/2),0, null);
        canvas.drawText(mText, getWidth() / 2 - mTextBound.width() / 2,
                getHeight() / 2 + mTextBound.height() / 2, mBackPint);

        if (!isComplete) {
            drawPath();
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    private void drawPath() {

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawPath(path, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                lastX = x;
                lastY = y;
                path.moveTo(lastX, lastY);

                break;
            case MotionEvent.ACTION_MOVE:

                int dx = Math.abs(x - lastX);
                int dy = Math.abs(y - lastY);

                if (dx > 3 || dy > 3) {
                    path.lineTo(x, y);
                }

                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
//                new Thread(mRunnable).start();
                run();
                showButton.show(percent);
                break;
        }

        invalidate();
        return true;
    }


    //    private Runnable mRunnable = new Runnable() {
    private int[] mPixels;

    //
//        @Override
    public void run() {

        int w = getWidth();
        int h = getHeight();

        float wipeArea = 0;
        float totalArea = w * h;

        Bitmap bitmap = mBitmap;

        mPixels = new int[w * h];

        /**
         * 拿到所有的像素信息
         */
        bitmap.getPixels(mPixels, 0, w, 0, 0, w, h);

        /**
         * 遍历统计擦除的区域
         */
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int index = i + j * w;
                if (mPixels[index] == 0) {
                    wipeArea++;
                }
            }
        }

        /**
         * 根据所占百分比，进行一些操作
         */
        if (wipeArea > 0 && totalArea > 0) {
            percent = (int) (wipeArea * 100 / totalArea);
            Log.e("TAG", percent + "");

            if (percent > 50) {
                isComplete = true;
                postInvalidate();

            }
        }
    }
//
//    };


    public interface ShowButton {
        void show(int percent);
    }

}
