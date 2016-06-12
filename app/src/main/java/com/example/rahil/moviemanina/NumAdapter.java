package com.example.rahil.moviemanina;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;


/**
 * Created by rahil on 6/30/2015.
 */
public class NumAdapter extends BaseAdapter {
    private String[] numbers;
    LayoutInflater NumInf;
public NumAdapter(Context c){


    numbers=new String[10];
    for(int a=0;a<10;a++){
        numbers[a]=""+(char)(a+'0');
    }
    NumInf=LayoutInflater.from(c);
}









    public int getCount() {
        return numbers.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button NumBtn;
        if(convertView==null){
            NumBtn=(Button)NumInf.inflate(R.layout.number,parent,false);
        }
        else {
            NumBtn = (Button) convertView;
        }
        NumBtn.setTextColor(Color.parseColor("#000000"));
        NumBtn.setText(numbers[position]);

        return NumBtn;
    }
}
