package com.maha.maha.SpaceRocket;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Explosion {
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap spritesheet;

    public Explosion(Bitmap res, int x, int y, int w, int h)
    {
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;

        spritesheet=res;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(spritesheet, x, y, null);
    }
}
