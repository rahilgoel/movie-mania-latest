package com.example.rahil.moviemanina;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by rahil on 6/15/2015.
 */
public class LetterAdapter extends BaseAdapter {

    //store letters
    private String[] letters;
    //inflater for button layout
    private LayoutInflater letterInf;

    public LetterAdapter(Context c) {
        //instantiate alphabet array
        letters = new String[21];
        letters[0] = "B";
        letters[1] = "C";
        letters[2] = "D" ;
        letters[3] = "F" ;
        letters[4] = "G"  ;
        letters[5] = "H" ;
        letters[6] = "J" ;
        letters[7] = "K" ;
        letters[8] = "L" ;
        letters[9] = "M" ;
        letters[10] = "N" ;
        letters[11] = "P" ;
        letters[12] = "Q" ;
        letters[13] = "R" ;
        letters[14] = "S" ;
        letters[15] = "T" ;
        letters[16] = "V" ;
        letters[17] = "W" ;
        letters[18] = "X" ;
        letters[19] = "Y";
        letters[20] = "Z" ;
        //specify layout to inflate
        letterInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return letters.length;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //create a button for the letter at this position in the alphabet
        Button letterBtn;
        if (convertView == null) {
            //inflate the button layout
            letterBtn = (Button) letterInf.inflate(R.layout.letter, parent, false);
        } else {
            letterBtn = (Button) convertView;
        }
        //set the text to this letter
        letterBtn.setTextColor(Color.parseColor("#000000"));
        letterBtn.setText(letters[position]);

        return letterBtn;
    }

}
