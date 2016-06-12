package com.example.rahil.moviemanina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;

/**
 * Created by rahil on 6/15/2015.
 */
public class Word extends Activity implements View.OnClickListener  {
    EditText name;
    Button give;
    TextView player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word);
        name=(EditText) findViewById(R.id.word);

        give=(Button) findViewById(R.id.button);
        give.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String getWord=name.getText().toString();
        if(TextUtils.isEmpty(getWord)) {
            name.setError("Enter the Movie");
            return;
        }
        Bundle bundle=new Bundle();
        bundle.putString("word",getWord);
        Intent i=new Intent(Word.this,GameActivity.class);
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }
}
