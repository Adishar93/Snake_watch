package com.example.asgamesnake;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SnakeSprite {
    int length;
    int x,y;
    int snakeHeight,snakeWidth;
    int xVelocity;
    int yVelocity;
    private int screenWidth;
    private int screenHeight;
    private int zeroPointX;
    private int zeroPointY;

    public SnakeSprite(int screenWidth,int screenHeight,int zeroPointX,int zeroPointY,int len)
    {
        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;
        this.zeroPointX=zeroPointX;
        this.zeroPointY=zeroPointY;

        x=50;
        y=80;
        snakeHeight=20;
        snakeWidth=20;
        xVelocity=6;
        yVelocity=3;
        length=len;
    }

    public void update()
    {
        x=x+xVelocity;
        y=y+yVelocity;

        if(x+snakeWidth>=screenWidth||x<=zeroPointX)
        {
            xVelocity=-xVelocity;
        }
        if(y+snakeHeight>=screenHeight||y<=zeroPointY)
        {
            yVelocity=-yVelocity;
        }
    }

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.rgb(250, 0, 0));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x+snakeWidth, y+snakeHeight, paint);
    }
}
