package com.example.asgamesnake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ScoreText {

    long score=0;

    public void update(long score)
    {
        this.score=score;
    }

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(16);
        canvas.drawText("Score: "+score, 100, 50, paint);
    }

    public long getScore() {
        return score;
    }
}
