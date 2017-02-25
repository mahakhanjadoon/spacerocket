package com.maha.maha.SpaceRocket;

import android.graphics.Bitmap;
import android.graphics.Canvas;


/**
 * Created by HP on 5/24/2016.
 */
public class Player extends GameObject {

    private Bitmap spritesheet;
    private int score;
    private boolean up;
    private boolean playing;
    private long startTime; //for the score

    public Player (Bitmap res, int w, int h)
    {
        x=100;
        y= GamePanel.HEIGHT/2;
        dy=0;
        score=0;
        height=h;
        width=w;

        //to store different sprites of the image

        spritesheet=res;

        startTime=System.nanoTime();

    }

    //this method will be called by the motion event (when user touches screen)
    public void setUp(boolean b)
    {
     up=b;
    }
    public void update()
    {
        long elapsed = (System.nanoTime()-startTime);

        //every 100 ms, score will be incremented
        if(elapsed>100)
        {
            score++;
            startTime= System.nanoTime();
        }


        if(up)
        {
            if(y>=0) {
                dy -= 8;
            }
            else
            {
                dy=0;
            }

        }
        else
        {
            if(y<=(GamePanel.HEIGHT)-90) {
                dy += 8;
            }
            else
            {
                dy=0;
            }

        }

        //speed of the helicopter
        if(dy>80)
        {
            dy=80;
        }
        if(dy<-80)
        {
            dy= -80;
        }
        y+= dy*2;
        dy=0;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(spritesheet, x, y, null);
    }
    public int getScore()
    {
        return score;
    }
    public boolean getPlaying()
    {
        return playing;
    }
    public void setPlaying(Boolean b)
    {
        playing=b;
    }
    public void resetDy()
    {
        dy=0;
    }
    public void resetScore()
    {
        score=0;
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
