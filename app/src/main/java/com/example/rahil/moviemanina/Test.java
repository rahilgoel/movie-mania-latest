package com.example.rahil.moviemanina;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by rahil on 6/23/2015.
 */
public class Test extends Activity implements View.OnClickListener {
    Button s1,s2;
    SoundPool sp;
    int explosion=0;
    int explosion2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.test);
        s1=(Button)findViewById(R.id.s1);
        s2=(Button)findViewById(R.id.s2);
        s1.setOnClickListener(this);
        s2.setOnClickListener(this);
        sp=new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        explosion=sp.load(this,R.raw.positive,1);
        explosion2=sp.load(this,R.raw.negitive,1);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.s1:
                sp.play(explosion,1,1,0,0,1);
                break;
            case R.id.s2:
                sp.play(explosion2,1,1,0,0,1);
                break;



        }

    }
}
