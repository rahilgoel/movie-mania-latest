package com.example.rahil.moviemanina;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by rahil on 7/9/2015.
 */
public class Names extends Activity {
    EditText p1,p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_players);
        p1=(EditText) findViewById(R.id.editText1);
        p2=(EditText) findViewById(R.id.editText2);
        String player1=p1.getText().toString();
        String player2=p2.getText().toString();
        SharedPreferences prefs=this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
         String score=prefs.getString("key", player1);
SharedPreferences prefs2=this.getSharedPreferences("myPrefsKey",Context.MODE_PRIVATE);
        String score2=prefs.getString("key2",player2);
    }
}
