package com.example.rahil.moviemanina;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by rahil on 8/8/2015.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, (WindowManager.LayoutParams.FLAG_FULLSCREEN));
        setContentView(R.layout.splashjar);
        ImageView moviemaniac=(ImageView) findViewById(R.id.imageView2);
        //moviemaniac.setVisibility(View.INVISIBLE);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.setStartOffset(1000);
        moviemaniac.startAnimation(anim);
        //YoYo.with(Techniques.FadeInUp).duration(3000).playOn(moviemaniac);
    }
}
