package com.example.asgamesnake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class FoodSprite {
    private int screenWidth;
    private int screenHeight;
    private int zeroPointX;
    private int zeroPointY;
    private int foodDimension;
    private SnakeSprite snakeSprite;
    private ScoreText scoreText;
    private int positionX=-1;
    private int positionY=-1;


    public FoodSprite(int screenWidth,int screenHeight,int zeroPointX,int zeroPointY,SnakeSprite snakeSprite, ScoreText scoreText)
    {
        foodDimension=10;
        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;
        this.zeroPointX=zeroPointX;
        this.zeroPointY=zeroPointY;
        this.snakeSprite=snakeSprite;
        this.scoreText=scoreText;
    }

    public void update()
    {
        if((positionX==-1&&positionY==-1))
        {

            snakeSprite.setLength(snakeSprite.getLength()+1);

            boolean foodCoincideInSnakeBody=true;
            while(foodCoincideInSnakeBody)
            {
                updatePositionXPositionY();
                foodCoincideInSnakeBody=snakeSprite.isFoodCoincidingWithSnakeBody(positionX,positionY);
            }

        }
        else if((positionX==snakeSprite.getHeadX()&&positionY==snakeSprite.getHeadY()))
        {
            snakeSprite.setLength(snakeSprite.getLength()+1);
            scoreText.update(scoreText.getScore()+10);

            boolean foodCoincideInSnakeBody=true;
            while(foodCoincideInSnakeBody)
            {
                updatePositionXPositionY();
                foodCoincideInSnakeBody=snakeSprite.isFoodCoincidingWithSnakeBody(positionX,positionY);
            }
        }

    }

    public void updatePositionXPositionY()
    {
        long intermediateValue1=Math.round((Math.random()*(screenWidth-zeroPointX-foodDimension))/(double)foodDimension);
        //Round intermdiate value to multiples of foodDimension
        intermediateValue1=intermediateValue1*10;
        positionX=(int)intermediateValue1+zeroPointX;


        long intermediateValue2=Math.round((Math.random()*(screenHeight-zeroPointY-foodDimension))/(double)foodDimension);
        intermediateValue2=intermediateValue2*10;
        positionY=(int)intermediateValue2+zeroPointY;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.rgb(0, 0, 250));
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(positionX, positionY, positionX+foodDimension, positionY+foodDimension, paint);
    }
}
