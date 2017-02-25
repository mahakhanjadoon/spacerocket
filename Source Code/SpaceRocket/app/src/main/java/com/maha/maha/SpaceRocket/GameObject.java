package com.maha.maha.SpaceRocket;


import android.graphics.Rect;


//all objects in the game are going to extend this class
public abstract class GameObject {
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;

    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y=y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }

    //to check if rectangles of objects are intersecting
    public Rect getRectangle()
    {
        //rectangle space that the object takes up
        return new Rect(x, y, x+getWidth(), y+getHeight());
    }

}

