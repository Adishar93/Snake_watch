package com.example.asgamesnake;

public class SnakeSegmentData {
    public static final int DIRECTION_RIGHT=1;
    public static final int DIRECTION_LEFT=2;
    public static final int DIRECTION_UP=3;
    public static final int DIRECTION_DOWN=4;

    private int direction;
    private int x;
    private int y;

    public SnakeSegmentData()
    {

    }

    public SnakeSegmentData(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
