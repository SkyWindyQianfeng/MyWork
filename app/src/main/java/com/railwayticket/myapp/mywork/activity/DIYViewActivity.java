package com.railwayticket.myapp.mywork.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class DIYViewActivity extends BaseActivity implements View.OnTouchListener {
    MyView myView;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        /*设置ContentView为自定义的MyVieW*/
            myView=new MyView(this);
            setContentView(myView);
            DisplayMetrics dm = getResources().getDisplayMetrics();
            screenWidth = dm.widthPixels;
            screenHeight = dm.heightPixels - 50;
            myView.setOnTouchListener(this);
        }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x1=0;
        int y1=0;

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            x1= (int) event.getX();
            y1= (int) event.getX();
        }
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            int dx= (int) (event.getX()-x1);
            int dy= (int) (event.getY()-y1);

        }



        return super.onTouchEvent(event);
    }

    int screenWidth;
    int screenHeight;
    int lastX;
    int lastY;
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action=event.getAction();
        Log.i("@@@@@@", "Touch:"+action);
        //Toast.makeText(DraftTest.this, "λ�ã�"+x+","+y, Toast.LENGTH_SHORT).show();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            /**
             * layout(l,t,r,b)
             * l  Left position, relative to parent
             t  Top position, relative to parent
             r  Right position, relative to parent
             b  Bottom position, relative to parent
             * */
            case MotionEvent.ACTION_MOVE:
                int dx =(int)event.getRawX() - lastX;
                int dy =(int)event.getRawY() - lastY;

                int left = v.getLeft() + dx;
                int top = v.getTop() + dy;
                int right = v.getRight() + dx;
                int bottom = v.getBottom() + dy;
                if(left < 0){
                    left = 0;
                    right = left + v.getWidth();
                }
                if(right > screenWidth){
                    right = screenWidth;
                    left = right - v.getWidth();
                }
                if(top < 0){
                    top = 0;
                    bottom = top + v.getHeight();
                }
                if(bottom > screenHeight){
                    bottom = screenHeight;
                    top = bottom - v.getHeight();
                }
                v.layout(left, top, right, bottom);
                Log.i("@@@@@@", "position��" + left + ", " + top + ", " + right + ", " + bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }


        return false;
    }

    /* 自定义继承View 的MyView*/
        private class MyView extends ImageView
        {


            @Override
            public boolean onTouchEvent(MotionEvent event) {



                return super.onTouchEvent(event);
            }

            public MyView(Context context){
                super(context) ;
            }
            /*重写onDraw（）*/
            @Override
            protected void onDraw(Canvas canvas)
            {
                super.onDraw(canvas);
         /*设置背景为白色*/
                canvas.drawColor(Color.WHITE);
                Paint paint=new Paint();
          /*去锯齿*/
                paint.setAntiAlias(true);
          /*设置paint的颜色*/
                paint.setColor(Color.RED);
          /*设置paint的 style 为STROKE：空心*/
                paint.setStyle(Paint.Style.STROKE);
          /*设置paint的外框宽度*/
                paint.setStrokeWidth(3);
          /*画一个空心圆形*/
                canvas.drawCircle(40, 40, 30, paint);
          /*画一个空心正方形*/
                canvas.drawRect(10, 90, 70, 150, paint);
          /*画一个空心长方形*/
                canvas.drawRect(10, 170, 70,200, paint);
          /*画一个空心椭圆形*/
                RectF re=new RectF(10,220,70,250);
                canvas.drawOval(re, paint);
          /*画一个空心三角形*/
                Path path=new Path();
                path.moveTo(10, 330);
                path.lineTo(70,330);
                path.lineTo(40,270);
                path.close();
                canvas.drawPath(path, paint);
          /*画一个空心梯形*/
                Path path1=new Path();
                path1.moveTo(10, 410);
                path1.lineTo(70,410);
                path1.lineTo(55,350);
                path1.lineTo(25, 350);
                path1.close();
                canvas.drawPath(path1, paint);

          /*设置paint 的style为 FILL：实心*/
                paint.setStyle(Paint.Style.FILL);
          /*设置paint的颜色*/
                paint.setColor(Color.BLUE);
          /*画一个实心圆*/
                canvas.drawCircle(120,40,30, paint);
          /*画一个实心正方形*/
                canvas.drawRect(90, 90, 150, 150, paint);
          /*画一个实心长方形*/
                canvas.drawRect(90, 170, 150,200, paint);
          /*画一个实心椭圆*/
                RectF re2=new RectF(90,220,150,250);
                canvas.drawOval(re2, paint);
          /*画一个实心三角形*/
                Path path2=new Path();
                path2.moveTo(90, 330);
                path2.lineTo(150,330);
                path2.lineTo(120,270);
                path2.close();
                canvas.drawPath(path2, paint);
          /*画一个实心梯形*/
                Path path3=new Path();
                path3.moveTo(90, 410);
                path3.lineTo(150,410);
                path3.lineTo(135,350);
                path3.lineTo(105, 350);
                path3.close();
                canvas.drawPath(path3, paint);
          /*设置渐变色*/
                Shader mShader=new LinearGradient(0,0,100,100,
                        new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW},
                        null,Shader.TileMode.REPEAT);
                paint.setShader(mShader);

          /*画一个渐变色圆*/
                canvas.drawCircle(200,40,30, paint);
          /*画一个渐变色正方形*/
                canvas.drawRect(170, 90, 230, 150, paint);
          /*画一个渐变色长方形*/
                canvas.drawRect(170, 170, 230,200, paint);
          /*画一个渐变色椭圆*/
                RectF re3=new RectF(170,220,230,250);
                canvas.drawOval(re3, paint);
          /*画一个渐变色三角形*/
                Path path4=new Path();
                path4.moveTo(170,330);
                path4.lineTo(230,330);
                path4.lineTo(200,270);
                path4.close();
                canvas.drawPath(path4, paint);
          /*画一个渐变色梯形*/
                Path path5=new Path();
                path5.moveTo(170, 410);
                path5.lineTo(230,410);
                path5.lineTo(215,350);
                path5.lineTo(185, 350);
                path5.close();
                canvas.drawPath(path5, paint);

          /*写字*/
                paint.setTextSize(24);
                canvas.drawText("圆形", 240, 50, paint);
                canvas.drawText("正方形", 240, 120, paint);
                canvas.drawText("长方形", 240, 190, paint);
                canvas.drawText("椭圆形", 240, 250, paint);
                canvas.drawText("三角形", 240, 320, paint);
                canvas.drawText("梯形", 240, 390, paint);
            }
        }
    }

