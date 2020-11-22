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

    public void update()
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

        int tempDirectionValue=99;

        for(int i=0;i<length;i++)
        {

            currentData=snakeState.get(i);
            int direction=currentData.getDirection();

            //Update snake position
            int currPosition;
            switch(direction)
            {
                case SnakeSegmentData.DIRECTION_RIGHT:
                    currPosition=currentData.getX();
                    if(currPosition+velocity>=screenWidth)
                    {
                        currentData.setX(zeroPointX);
                    }
                    else {
                        currentData.setX(currPosition + velocity);
                    }
                    break;

                case SnakeSegmentData.DIRECTION_LEFT:
                    currPosition=currentData.getX();
                    if(currPosition-velocity<zeroPointX)
                    {
                        currentData.setX(screenWidth-snakeWidth);
                    }
                    else
                    {
                        currentData.setX(currPosition-velocity);
                    }

                    break;

                case SnakeSegmentData.DIRECTION_UP:
                    currPosition=currentData.getY();
                    if(currPosition-velocity<zeroPointY)
                    {
                        currentData.setY(screenHeight-snakeHeight);
                    }
                    else {
                        currentData.setY(currPosition - velocity);
                    }
                    break;

                case SnakeSegmentData.DIRECTION_DOWN:
                    currPosition=currentData.getY();
                    if(currPosition+velocity>=screenHeight)
                    {
                        currentData.setY(zeroPointY);
                    }
                    else {
                        currentData.setY(currPosition + velocity);
                    }
                    break;
            }

            //Handle direction change in snake

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

        headX=snakeState.get(0).getX();
        headY=snakeState.get(0).getY();



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

    public void handleTouch(int touchDirection) {


            SnakeSegmentData headData = snakeState.get(0);

                int snakeDirection = headData.getDirection();

                switch (touchDirection) {
                    case GameView.TOUCH_LEFT:
                        switch (snakeDirection) {
                            case SnakeSegmentData.DIRECTION_RIGHT:
                                headData.setDirection(SnakeSegmentData.DIRECTION_UP);
                                break;

                            case SnakeSegmentData.DIRECTION_LEFT:
                                headData.setDirection(SnakeSegmentData.DIRECTION_DOWN);
                                break;
                            case SnakeSegmentData.DIRECTION_UP:
                                headData.setDirection(SnakeSegmentData.DIRECTION_LEFT);
                                break;
                            case SnakeSegmentData.DIRECTION_DOWN:
                                headData.setDirection(SnakeSegmentData.DIRECTION_RIGHT);
                                break;
                        }
                        break;

                    case GameView.TOUCH_RIGHT:
                        switch (snakeDirection) {
                            case SnakeSegmentData.DIRECTION_RIGHT:
                                headData.setDirection(SnakeSegmentData.DIRECTION_DOWN);
                                break;

                            case SnakeSegmentData.DIRECTION_LEFT:
                                headData.setDirection(SnakeSegmentData.DIRECTION_UP);
                                break;
                            case SnakeSegmentData.DIRECTION_UP:
                                headData.setDirection(SnakeSegmentData.DIRECTION_RIGHT);
                                break;
                            case SnakeSegmentData.DIRECTION_DOWN:
                                headData.setDirection(SnakeSegmentData.DIRECTION_LEFT);
                                break;
                        }
                        break;
                }




        }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        SnakeSegmentData snakeSegmentData=snakeState.get(snakeState.size()-1);

        if(snakeSegmentData.getDirection()==SnakeSegmentData.DIRECTION_UP)
        {
            snakeState.add(new SnakeSegmentData(snakeSegmentData.getX(), snakeSegmentData.getY()+snakeHeight,SnakeSegmentData.DIRECTION_UP));
        }
        else if(snakeSegmentData.getDirection()==SnakeSegmentData.DIRECTION_DOWN)
        {
            snakeState.add(new SnakeSegmentData(snakeSegmentData.getX(), snakeSegmentData.getY()-snakeHeight,SnakeSegmentData.DIRECTION_DOWN));
        }
        else if(snakeSegmentData.getDirection()==SnakeSegmentData.DIRECTION_LEFT)
        {
            snakeState.add(new SnakeSegmentData(snakeSegmentData.getX()+snakeWidth, snakeSegmentData.getY(),SnakeSegmentData.DIRECTION_LEFT));
        }
        else if(snakeSegmentData.getDirection()==SnakeSegmentData.DIRECTION_RIGHT)
        {
            snakeState.add(new SnakeSegmentData(snakeSegmentData.getX()-snakeWidth, snakeSegmentData.getY(),SnakeSegmentData.DIRECTION_RIGHT));
        }

    }

    public int getHeadX() {
        return headX;
    }

    public int getHeadY() {
        return headY;
    }
}
