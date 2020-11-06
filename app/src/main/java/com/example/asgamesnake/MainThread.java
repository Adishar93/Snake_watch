package com.example.asgamesnake;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

    private SurfaceHolder mSurfaceHolder;
    private GameView mGameView;
    private boolean running;
    public static Canvas canvas;

    long targetFPS=60;
    long startTime;
    long timeMillis;
    long waitTime;
    long totalTime = 0;
    int frameCount = 0;
    long targetTime = 1000 / targetFPS;

    public MainThread(SurfaceHolder surfaceHolder,GameView gameView)
    {
        super();
        mSurfaceHolder=surfaceHolder;
        mGameView=gameView;

    }

    @Override
    public void run() {
        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = mSurfaceHolder.lockCanvas();
                synchronized(mSurfaceHolder) {
                    mGameView.update();
                    mGameView.draw(canvas);
                }
            } catch (Exception e) {} finally {
                if (canvas != null) {
                    try {
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;

            waitTime = targetTime - timeMillis;
            try {
                this.sleep(waitTime);
            } catch (Exception e) {
                Log.e("Thread error:","Thread was unable to sleep "+e.toString());
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == targetFPS)        {
                long averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                Log.d("Average FPS: ",""+averageFPS);
            }

        }
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }
}
