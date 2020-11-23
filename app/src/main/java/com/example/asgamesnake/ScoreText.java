package com.example.asgamesnake;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import static android.content.Context.MODE_PRIVATE;

public class ScoreText {

    long score=0;
    long highScore=0;
    private static String LOCAL_DATA="localData";
    SharedPreferences localData;

    public ScoreText(Context context)
    {
        localData = context.getSharedPreferences(LOCAL_DATA, MODE_PRIVATE);

        highScore=localData.getLong("high score",0);

    }

    public void update(long score)
    {
        this.score=score;
        if(score>highScore)
        {
            highScore=score;

            // Writing data to SharedPreferences
            SharedPreferences.Editor editor = localData.edit();
            editor.putLong("high score", highScore);
            editor.apply();
        }
    }

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(16);

        canvas.drawText("Score: "+score, 120, 40, paint);

        canvas.drawText("High Score: "+highScore, 120, 60, paint);
    }

    public long getScore() {
        return score;
    }
}
