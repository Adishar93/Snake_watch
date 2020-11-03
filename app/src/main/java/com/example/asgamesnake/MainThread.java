package com.example.asgamesnake;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

    private SurfaceHolder mSurfaceHolder;
    private GameView mGameView;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder,GameView gameView)
    {
        super();
        mSurfaceHolder=surfaceHolder;
        mGameView=gameView;

    }

    @Override
    public void run() {
        while (running) {
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
        }
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }
}
