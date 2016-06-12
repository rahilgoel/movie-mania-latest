package com.example.rahil.moviemanina;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by rahil on 7/9/2015.
 */
public class BestOf extends Activity implements View.OnClickListener {
    Button bestof3,bestof5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_of);
        bestof3=(Button) findViewById(R.id.button2);
        bestof5=(Button) findViewById(R.id.button3);
        bestof3.setOnClickListener(this);
        bestof5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2 :
                break;
            case R.id.button3:
                break;





        }
    }
}
