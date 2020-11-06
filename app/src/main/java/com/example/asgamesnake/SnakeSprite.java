package com.example.asgamesnake;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SnakeSprite {
    int length;
    int x,y;
    int height,width;
    int xVelocity=4;
    int yVelocity=3;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public SnakeSprite(int len)
    {
        x=50;
        y=50;
        height=20;
        width=20;
        length=len;
    }

    public void update()
    {
        x=x+xVelocity;
        y=y+yVelocity;

        if(x+width>=screenWidth||x<=0)
        {
            xVelocity=-xVelocity;
        }
        if(y+height>=screenHeight||y<=0)
        {
            yVelocity=-yVelocity;
        }
    }

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.rgb(250, 0, 0));
        canvas.drawRect(x, y, x+width, y+height, paint);
    }
}
