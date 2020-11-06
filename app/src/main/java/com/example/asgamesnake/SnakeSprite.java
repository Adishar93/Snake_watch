package com.example.asgamesnake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class SnakeSprite {
    private int length;
    private int headX, headY;
    private int snakeHeight,snakeWidth;
    private int velocity;
    private ArrayList<SnakeSegmentData> snakeState;

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

        headX =50;
        headY =80;
        snakeHeight=10;
        snakeWidth=10;
        velocity=10;
        length=len;

        snakeState=new ArrayList<>(len);
        for(int i=0;i<len;i++)
        {
            int blockX= headX -i*snakeWidth;
            //int blockY=y-i*snakeHeight;
            snakeState.add(i,new SnakeSegmentData(blockX, headY,SnakeSegmentData.DIRECTION_RIGHT));
        }
    }

    public void update(int len)
    {
        //Code to grow snake's tail
//        if(len>0) {
//            int newSegmentCount=len-length;
//            for(int i=0;i<newSegmentCount;i++)
//            {
//                snakeState.add(new SnakeSegmentData())
//            }
//            this.length = len;
//        }
        SnakeSegmentData currentData=null;
        SnakeSegmentData previousData=null;

        for(int i=0;i<length;i++)
        {

            currentData=snakeState.get(i);
            int direction=currentData.getDirection();

            //Update snake position
            switch(direction)
            {
                case SnakeSegmentData.DIRECTION_RIGHT:
                    currentData.setX(currentData.getX()+velocity);
                    break;

                case SnakeSegmentData.DIRECTION_LEFT:
                    currentData.setX(currentData.getX()-velocity);
                    break;

                case SnakeSegmentData.DIRECTION_UP:
                    currentData.setX(currentData.getY()-velocity);
                    break;

                case SnakeSegmentData.DIRECTION_DOWN:
                    currentData.setX(currentData.getY()+velocity);
                    break;
            }

            //Handle direction change in snake
            int tempDirectionValue=99;
            if(previousData!=null)
            {
                if(currentData.getDirection()!=previousData.getDirection()&&currentData.getDirection()!=tempDirectionValue)
                {
                    tempDirectionValue=currentData.getDirection();
                    currentData.setDirection(previousData.getDirection());
                }
            }
            previousData=currentData;
        }



//        if(x+snakeWidth>=screenWidth||x<=zeroPointX)
//        {
//            xVelocity=-xVelocity;
//        }
//        if(y+snakeHeight>=screenHeight||y<=zeroPointY)
//        {
//            yVelocity=-yVelocity;
//        }
    }

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.rgb(250, 0, 0));
        paint.setStyle(Paint.Style.FILL);
        for(int i=0;i<length;i++)
        {
            //int blockY=y-i*snakeHeight;
            int xVal=snakeState.get(i).getX();
            int yVal=snakeState.get(i).getY();
            canvas.drawRect(xVal, yVal, xVal+snakeWidth, yVal +snakeHeight, paint);
        }

    }
}
