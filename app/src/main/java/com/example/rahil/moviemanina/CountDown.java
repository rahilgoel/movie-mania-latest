package com.example.rahil.moviemanina;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;

/**
 * Created by rahil on 6/16/2015.
 */
public class CountDown extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    private Activity act;
    private Class cls;
    public CountDown(long millisInFuture, long countDownInterval,Activity act1,Class cls1) {
        super(millisInFuture, countDownInterval);
        act=act1;
        cls=cls1;

    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

act.startActivity(new Intent(act,cls));
        act.finish();
    }
}
