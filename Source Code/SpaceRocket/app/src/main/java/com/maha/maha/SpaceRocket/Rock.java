package com.maha.maha.SpaceRocket;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by HP on 5/26/2016.
 */
public class Rock extends GameObject {

   private int score;
    private int speed;
    private Random rand= new Random();
    Bitmap spritesheet;

    public Rock(Bitmap res, int x, int y, int w, int h, int s)
    {
        super.x=x;
        super.y=y;

        width=w;
        height=h;


        score=s;

        //speed increases as score increases
        speed= 7+ (int) (rand.nextDouble()*score/30);

        if(speed>150)
        {
            speed=150;
        }
        spritesheet=res;
    }

    public void update(){
        x-=speed;
    }
    public void draw(Canvas canvas)
    {
        try {
            canvas.drawBitmap(spritesheet, x, y, null);
        }catch (Exception e){}
    }
    @Override
    public int getWidth()
    {
        return width - 50;
    }

    @Override
    public int getHeight()
    {
        return height - 70;
    }

}
