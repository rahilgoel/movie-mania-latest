package com.example.rahil.moviemanina;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/**
 * Created by rahil on 6/16/2015.
 */
public class Players extends AppCompatActivity  implements View.OnClickListener {
    RelativeLayout rev_lay;
    ImageButton single,multi;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players2);
        rev_lay=(RelativeLayout) findViewById(R.id.rev);
        single =(ImageButton) findViewById(R.id.single1);
        multi =(ImageButton) findViewById(R.id.multi1);
        single.setOnClickListener(this);
        multi.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.single:
                Intent j=new Intent(Players.this,GameActivitySingle.class);
                startActivity(j);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                Intent myIntent = new Intent(Players.this, MyService.class);
                stopService(myIntent);

                break;
            case R.id.multi:
                Intent i=new Intent(Players.this, Word.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                Intent myIntent2 = new Intent(Players.this, MyService.class);
                stopService(myIntent2);
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent myIntent = new Intent(Players.this, MyService.class);
            stopService(myIntent);
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
