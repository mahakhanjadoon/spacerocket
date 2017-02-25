 package com.maha.maha.SpaceRocket;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by HP on 5/24/2016.
 */
public class Background {

    private Bitmap image;
    private int x,y, dx;

    public Background(Bitmap res)
    {
        image=res;
        dx = GamePanel.MOVESPEED;
    }

    public void update(){
        x+=dx;

        //if the image is off the screen
        if(x<-GamePanel.WIDTH)
        {
            x=0;
        }
    }

    public void draw(Canvas canvas){
      canvas.drawBitmap(image, x,y, null);

        //draw second image infront of this image for scrolling
       if(x<0){
           canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
       }
    }




}
