package com.maha.maha.SpaceRocket;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

import static com.maha.maha.SpaceRocket.R.drawable;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final int WIDTH=1920;
    public static final int HEIGHT=1080;
    public static final int MOVESPEED = -10;
    private MainThread thread;
    private Background bg;
    private Player player;
    private Explosion explosion;
    private ArrayList<Rock> rocks;
    private long rockStartTime;
    private Random rand= new Random();
    private boolean exploded;
    public int highScore=0;
    private MediaPlayer mediaPlayer;
    private Context c;




    public GamePanel(Context context){

        super(context);
        c=context;

        //add callback to surface holder to intercept events
        getHolder().addCallback(this);


        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry= true;
        int counter=0;

        while(retry && counter<1000)
        {
            counter++;
            try{
                thread.setRunning(false);
                thread.join();
                retry=false;
                thread=null;
            } catch(InterruptedException e){e.printStackTrace();}

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        bg= new Background(BitmapFactory.decodeResource(getResources(), drawable.spacebackgroundblack1));
        player= new Player(BitmapFactory.decodeResource(getResources(), drawable.rockett1), 270, 128);
        rocks= new ArrayList<Rock>();
        rockStartTime=System.nanoTime();

        thread = new MainThread(getHolder(), this);


        thread.setRunning(true);
        thread.start();



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

            if(event.getAction()==MotionEvent.ACTION_DOWN) {
                if (!player.getPlaying()) {
                    if(exploded=true) {
                        exploded = false;
                        player.resetScore();
                        player.setPlaying(true);
                    }
                    else
                        player.setPlaying(true);

                }

                player.setUp(true);
                return true;
            }

            if(event.getAction()==MotionEvent.ACTION_UP) {
                player.setUp(false);
                return true;
            }

            return super.onTouchEvent(event);

    }

    public void update() {

        if(player.getPlaying()) {
            bg.update();
            player.update();

            //add rocks on timer
            long rockElapsed = (System.nanoTime()-rockStartTime)/1000000;
            //as score goes higher, rocks will be launching faster
            if(rockElapsed>(2000 - player.getScore()/4)) {
                //firs rock in the middle
                if (rocks.size() == 0) {
                    rocks.add(new Rock(BitmapFactory.decodeResource(getResources(), drawable.rock1), WIDTH + 10, HEIGHT / 2, 276, 166, player.getScore()));
                }

                else {
                    rocks.add(new Rock(BitmapFactory.decodeResource(getResources(), drawable.rock1), WIDTH + 10, (int) (rand.nextDouble() * (HEIGHT)), 276, 166, player.getScore()));
                }
                rockStartTime=System.nanoTime();
            }
            for(int i=0; i<rocks.size(); i++)
            {
                rocks.get(i).update();
                if(collision(rocks.get(i),player))
                {
                    mediaPlayer = MediaPlayer.create(c, R.raw.explosion2);
                    mediaPlayer.setVolume(100, 100);

                    mediaPlayer.start();
                    exploded=true;
                    rocks.remove(i);
                    explosion = new Explosion(BitmapFactory.decodeResource(getResources(), drawable.explosionn1),player.getX()+30, player.getY()-30,204, 196);

                    player.setPlaying(false);


                    break;
                }
                if(rocks.get(i).getX()<-100)
                {
                    rocks.remove(i);
                    break;
                }
            }
        }
    }

    public boolean collision(GameObject a, GameObject b)
    {
        if(Rect.intersects(a.getRectangle(), b.getRectangle()))
        {
            return true;
        }
        return false;
    }

   @Override
    public void draw(Canvas canvas){

       super.draw(canvas);
       final float scaleFactorX = getWidth()/(WIDTH*1.f);
       final float scaleFactorY = getHeight()/(HEIGHT*1.f);
       Paint paint = new Paint();
       Paint gameOver = new Paint();

       gameOver.setColor(Color.RED);
       gameOver.setTextSize(150);
       paint.setColor(Color.WHITE);
       paint.setTextSize(42);



       if(canvas!=null) {

           final int savedState = canvas.save();
           canvas.scale(scaleFactorX, scaleFactorY);
           bg.draw(canvas);

           for(Rock r: rocks )
           {
               r.draw(canvas);
           }

           if(!exploded) {
               player.draw(canvas);

           }
           else
           {
               explosion.draw(canvas);
               canvas.drawText("GAME OVER", (WIDTH / 2) - 500, HEIGHT / 2, gameOver);
               canvas.drawText("Touch to Restart", (WIDTH/2)-600, (HEIGHT / 2)+300, gameOver);


           }

           canvas.drawText("Score: "+ player.getScore(), 20, 50, paint);


           //to stop it from keep getting scaled everytime draw is called
           canvas.restoreToCount(savedState);
       }

   }
}
