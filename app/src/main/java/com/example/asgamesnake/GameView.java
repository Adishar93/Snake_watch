package com.example.asgamesnake;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels-40;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels-70;
    private int zeroPointX=40;
    private int zeroPointY=70;

    private MainThread thread;
    private Paint paint;
    private SnakeSprite snakeSprite;

    public static final int TOUCH_RIGHT=1;
    public static final int TOUCH_LEFT=2;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread=new MainThread(getHolder(),this);
        setFocusable(true);

        paint=new Paint();
        snakeSprite=new SnakeSprite(screenWidth,screenHeight,zeroPointX,zeroPointY,3);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        snakeSprite.update(3);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            paint.setColor(Color.rgb(0, 0, 0));
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(zeroPointX,zeroPointY,screenWidth,screenHeight,paint);

            snakeSprite.draw(canvas,paint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        float x=event.getX();
        float y=event.getY();

        if(x>180)
        {
            snakeSprite.handleTouch(TOUCH_RIGHT);
        }
        else
        {
            snakeSprite.handleTouch(TOUCH_LEFT);
        }
        return super.onTouchEvent(event);
    }
}
