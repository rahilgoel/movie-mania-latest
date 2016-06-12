package com.example.rahil.moviemanina;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by rahil on 7/25/2015.
 */
public class MyService extends Service {
    String tag="MyService";
    MediaPlayer mp;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp=MediaPlayer.create(getApplicationContext(),R.raw.breaking_bad);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mp.release();
        super.onDestroy();
    }
}
