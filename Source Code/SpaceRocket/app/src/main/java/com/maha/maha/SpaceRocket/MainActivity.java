package com.maha.maha.SpaceRocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    Intent intent=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //turn off title
       requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new GamePanel(this));

       intent = new Intent(this, MusicService.class);
        intent.setAction("PLAY");
        startService(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }
}
