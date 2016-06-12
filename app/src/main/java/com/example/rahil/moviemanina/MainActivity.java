package com.example.rahil.moviemanina;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class MainActivity extends Activity {
    MediaPlayer OurSong;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, (WindowManager.LayoutParams.FLAG_FULLSCREEN));
        setContentView(R.layout.splashjar);
        Intent myIntent = new Intent(MainActivity.this, MyService.class);
        startService(myIntent);
        CountDown tik;
        tik = new CountDown(5000, 5000, this, Players.class);
        tik.start();
        StartAnimations();
        //MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.breaking_bad);
        //mp.start();

}

    public void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l = (RelativeLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
      // anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        //anim.reset();
       // ImageView iv = (ImageView) findViewById(R.id.logo);
        //iv.clearAnimation();
        //iv.startAnimation(anim);
       final Animation anim2=AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

                anim2.reset();
                ImageView iv2=(ImageView) findViewById(R.id.splash);
                iv2.startAnimation(anim2);
               // ImageView iv3=(ImageView) findViewById(R.id.logo);
                //YoYo.with(Techniques.RotateIn).duration(1000).playOn(iv3);
                //iv.clearAnimation();
                //YoYo.with(Techniques.TakingOff).duration(4000).playOn(iv);
            }
        }, 2000);


    }

public void pause(){
    OurSong.pause();
}
}

