package com.maha.maha.SpaceRocket;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
    MediaPlayer mMediaPlayer = null;



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if(action=="PLAY")
        {
            mMediaPlayer = MediaPlayer.create(this, R.raw.sound1);
            mMediaPlayer.setVolume(70,70);
            mMediaPlayer.start();

        }
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }


}
