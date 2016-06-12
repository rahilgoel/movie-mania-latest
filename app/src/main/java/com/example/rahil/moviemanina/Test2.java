package com.example.rahil.moviemanina;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by rahil on 8/8/2015.
 */
public class Test2 extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        Button button=(Button) findViewById(R.id.button4);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(Test2.this,Pop.class);
        startActivity(i);
    }
}
