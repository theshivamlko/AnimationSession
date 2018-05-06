package com.navoki.animationdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class MySurfaceView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private MyThread myThread;
    private Paint circlePaint = new Paint();

    private int colorArray[] = {Color.BLACK, Color.GREEN, Color.BLACK, Color.CYAN, Color.YELLOW
            , Color.RED, Color.MAGENTA, Color.DKGRAY, ContextCompat.getColor(getContext(),R.color.colorAccent)
    ,ContextCompat.getColor(getContext(),R.color.colorPrimary),ContextCompat.getColor(getContext(),R.color.colorPrimaryDark)};

    int midX, midY;
    boolean reverse = false;
    int circleRadius = 100;
    int X = 11;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context,
                         AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context,
                         AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

        myThread = new MyThread(this);

        surfaceHolder = getHolder();
        circlePaint.setColor(Color.GREEN);

        midX = Utils.deviceWidth / 2;
        midY = Utils.deviceHeight / 2;
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                myThread.setRunning(true);
                myThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                myThread.setRunning(false);
                while (retry) {
                    try {
                        myThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    /**
     * Write ur code here
     *
     * @param canvas
     */
    protected void drawSomething(Canvas canvas) {


      canvas.drawColor(Color.WHITE);

     //   canvas.drawCircle(midX,midY,100,circlePaint);
     //   midY=midY-10;

    //    animateCircleInLine(canvas);
        animateCircleInRing(canvas, circlePaint);

    }


    /**
     * to animate circle in a line at angle 45 degree
     *
     * @param canvas
     */
    void animateCircleInLine(Canvas canvas) {

        // y= mx+c; m=tan(45) c=10;
        // (y-c)/m=x   m=1;
        // y-c=x

        midY = midY - 10;

        canvas.drawCircle(midX, midY, 100, circlePaint);

        // formulae
        midX = (midY - 10);
    }

    /**
     * Animate circle in ring like revolution of earth
     *
     * @param canvas
     */
    void animateCircleInRing(Canvas canvas, Paint paint) {

        int radius = 500;
       /*
       Cartesian Co-ordinates for circle from (0,0) x*x + y*y =r*r
       Cartesian Co-ordinates for circle from (a,b) (x-a)^2 + (y-b)^2 =r*r
       X= SQRT( Y*Y  - R*R )
        */

        int Y = (int) Math.sqrt((radius * radius) - (X - midX) * (X - midX));

        Y = Math.abs(Y - midY);

        canvas.drawCircle(X, Y, circleRadius, paint);

        if (X < 10 || X > Utils.deviceWidth) {
            reverse = !reverse;
            Random random = new Random();
            int i = random.nextInt(10);
            circlePaint.setColor(colorArray[i]);
            circleRadius = circleRadius + 50;
        }


        if (!reverse)
            X = X + 10;
        else
            X = X - 10;


    }

}