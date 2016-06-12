package com.example.rahil.moviemanina;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

/**
 * Created by rahil on 6/15/2015.
 */
public class GameActivitySingle extends Activity {
    SoundPool sp;
    int explosion = 0;
    int explosion2 = 0;
    int explosion3;
    int explosion4;
CountDownTimer time;
    ImageView b, o, l, l2, y, w, o2, o3, d;
    private String[] words;
    //random for word selection
    private Random rand;
    //store the current word
    private String currWord;
    //the layout holding the answer
    private LinearLayout wordLayout;
    private LinearLayout wordLayout2;
    //text views for each letter in the answer
    private TextView[] charViews;
    private TextView[] charViews2;
    private ImageView[] bollywood;
    //letter button grid
    private GridView letters;
    private GridView letters2;
    //body part images
    //letter button adapter
    private LetterAdapter ltrAdapt;
    private NumAdapter numAdapter;
    //total parts
    private int numParts = 10;
    //current part
    private int currPart;
    //num chars in word
    private int numChars;
    //num correct so far
    private int numCorr;
    private TextView timer;
    //help
    private AlertDialog helpAlert;
    int game = 1;
    int player1 = 0;
    int player2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        currWord = "";
        Resources res = getResources();
        words = res.getStringArray(R.array.words);
        rand = new Random();
        //get answer area
        timer=(TextView) findViewById(R.id.timer);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = sp.load(this, R.raw.positive3, 1);
        explosion2 = sp.load(this, R.raw.negitive, 1);
        explosion3 = sp.load(this, R.raw.winning, 1);
        explosion4 = sp.load(this, R.raw.losing, 1);
        wordLayout = (LinearLayout) findViewById(R.id.word1);
        wordLayout2 = (LinearLayout) findViewById(R.id.word2);
        LinearLayout main = (LinearLayout) findViewById(R.id.main);
        letters = (GridView) findViewById(R.id.gView);
        letters2 = (GridView) findViewById(R.id.gView2);
        //get letter button grid
        bollywood = new ImageView[numParts];
        bollywood[0] = (ImageView) findViewById(R.id.m);
        bollywood[1] = (ImageView) findViewById(R.id.o);
        bollywood[2] = (ImageView) findViewById(R.id.v);
        bollywood[3] = (ImageView) findViewById(R.id.i);
        bollywood[4] = (ImageView) findViewById(R.id.e);
        bollywood[5] = (ImageView) findViewById(R.id.m2);
        bollywood[6] = (ImageView) findViewById(R.id.a);
        bollywood[7] = (ImageView) findViewById(R.id.n);
        bollywood[8] = (ImageView) findViewById(R.id.i2);
        bollywood[9] = (ImageView) findViewById(R.id.a2);
        //set home as up
        //start gameplay


        playGame();

    }

    private void playGame()

    {
        bollywood[0].setImageResource(R.drawable.mw);
        bollywood[1].setImageResource(R.drawable.ow);
        bollywood[2] .setImageResource(R.drawable.vw);
        bollywood[3].setImageResource(R.drawable.i);
        bollywood[4].setImageResource(R.drawable.ew);
        bollywood[5].setImageResource(R.drawable.mw);
        bollywood[6] .setImageResource(R.drawable.aw);
        bollywood[7].setImageResource(R.drawable.nw);
        bollywood[8] .setImageResource(R.drawable.i);
        bollywood[9].setImageResource(R.drawable.aw);

        time=new CountDownTimer(200000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);

            }

            public void onFinish() {
                timer.setText("You Lose!");
                sp.play(explosion4, 1, 1, 0, 0, 1);
                AlertDialog.Builder winBuild = new AlertDialog.Builder(GameActivitySingle.this);
                winBuild.setTitle("Times Up");
                winBuild.setMessage("You Loose!\n\nThe answer was:\n\n" + currWord);

                winBuild.setPositiveButton("Play Again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(GameActivitySingle.this, Word.class);
                                startActivity(i);
                            }
                        });


                winBuild.setNegativeButton("Exit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                GameActivitySingle.this.finish();
                            }
                        });
                winBuild.show();


            }
        }.start();
//            Bundle bundle = getIntent().getExtras();
        //          String newWord = bundle.getString("word");

        String newWord = words[rand.nextInt(words.length)];
        StringBuilder sb = new StringBuilder(newWord);
        while (newWord.equals(currWord)) newWord = words[rand.nextInt(words.length)];

        for (int index = 0; index < sb.length(); index++) {
            char c = sb.charAt(index);
            if (Character.isLowerCase(c)) {
                sb.setCharAt(index, Character.toUpperCase(c));
            }
        }
        ;

        //make sure not same word as last time
        currWord = newWord;
            //update current word
            //create new array for character text views


            if (currWord.length() > 12) {
                charViews = new TextView[12];
                charViews2 = new TextView[(currWord.length() - 12)];
                //remove any existing letters
                wordLayout.removeAllViews();
                wordLayout2.removeAllViews();
                for (int c = 0; c < 12; c++) {
                    charViews[c] = new TextView(this);
                    charViews[c].setTextSize(25);
                    //set the current letter
                    charViews[c].setText("" + currWord.charAt(c));
                    //set layout
                    charViews[c].setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                            ActionBar.LayoutParams.WRAP_CONTENT));
                    charViews[c].setGravity(Gravity.CENTER);
                    charViews[c].setTextColor(0xF4C34D);
                    if (currWord.charAt(c) == 'A') {
                        charViews[c].setTextColor(Color.WHITE);
                    }

                    if (currWord.charAt(c) == '!') {
                        charViews[c].setTextColor(Color.WHITE);
                    }

                    if (currWord.charAt(c) == '-') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == '.') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == 'E') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == 'I') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == 'O') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == 'U') {
                        charViews[c].setTextColor(Color.WHITE);

                    }
                    if (currWord.charAt(c) == ' ') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == ' ') {
                        charViews[c].setVisibility(View.INVISIBLE);
                    } else {

                        charViews[c].setBackgroundResource(R.drawable.letter_bg);
                    }
                    //add to display
                    wordLayout.addView(charViews[c]);

                }
                for (int d = 0; d < (currWord.length() - 12); d++) {
                    charViews2[d] = new TextView(this);
                    charViews2[d].setTextSize(25);
                    //set the current letter
                    charViews2[d].setText("" + currWord.charAt(12 + d));
                    //set layout
                    charViews2[d].setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                            ActionBar.LayoutParams.WRAP_CONTENT));
                    charViews2[d].setGravity(Gravity.CENTER);
                    charViews2[d].setTextColor(0xF4C34D);
                    if (currWord.charAt(12 + d) == 'A') {
                        charViews2[d].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(12 + d) == 'E') {
                        charViews2[d].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(12 + d) == 'I') {
                        charViews2[d].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(12+d) == '!') {
                        charViews[d].setTextColor(Color.WHITE);
                    }

                    if (currWord.charAt(12+d) == '-') {
                        charViews[d].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(12+d) == '.') {
                        charViews[d].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(12 + d) == 'O') {
                        charViews2[d].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(12 + d) == 'U') {
                        charViews2[d].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(12 + d) == ' ') {
                        charViews2[d].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(12 + d) == ' ') {
                        charViews2[d].setVisibility(View.INVISIBLE);
                    } else {

                        charViews2[d].setBackgroundResource(R.drawable.letter_bg);
                    }
                    //add to display
                    wordLayout2.addView(charViews2[d]);

                }


                //reset adapter
                numAdapter = new NumAdapter(this);
                letters2.setAdapter(numAdapter);
                ltrAdapt = new LetterAdapter(this);
                letters.setAdapter(ltrAdapt);
                currPart = 0;
                //set word length and correct choices
                numChars = currWord.length();
                numCorr = 0;

                for (int k = 0; k < currWord.length(); k++) {
                    if (currWord.charAt(k) == 'A') {
                        numCorr++;


                    }
                    if (currWord.charAt(k) == 'E') {
                        numCorr++;

                    }
                    if (currWord.charAt(k) == 'I') {
                        numCorr++;

                    }
                    if (currWord.charAt(k) == '.') {
                        numCorr++;


                    } if (currWord.charAt(k) == '!') {
                        numCorr++;


                    } if (currWord.charAt(k) == '-') {
                        numCorr++;


                    }
                    if (currWord.charAt(k) == 'O') {
                        numCorr++;

                    }
                    if (currWord.charAt(k) == 'U') {
                        numCorr++;
                    }
                    if (currWord.charAt(k) == ' ') {
                        numCorr++;
                    }
                }
                for (int t = 0; t < 9; t++) {
                    bollywood[t].setBackgroundColor(0xF4C34D);
                }
            } else {

                charViews = new TextView[currWord.length()];
                //remove any existing letters
                wordLayout.removeAllViews();
                wordLayout2.removeAllViews();
                for (int c = 0; c < currWord.length(); c++) {
                    charViews[c] = new TextView(this);
                    //set the current letter
                    charViews[c].setTextSize(25);
                    charViews[c].setText("" + currWord.charAt(c));
                    //set layout
                    charViews[c].setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                            ActionBar.LayoutParams.WRAP_CONTENT));
                    charViews[c].setGravity(Gravity.CENTER);
                    charViews[c].setTextColor(0xF4C34D);
                    if (currWord.charAt(c) == 'A') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == '!') {
                        charViews[c].setTextColor(Color.WHITE);
                    }

                    if (currWord.charAt(c) == '-') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == '.') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == 'E') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == 'I') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == 'O') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == 'U') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == ' ') {
                        charViews[c].setTextColor(Color.WHITE);
                    }
                    if (currWord.charAt(c) == ' ') {
                        charViews[c].setVisibility(View.INVISIBLE);
                    } else {
                        charViews[c].setBackgroundResource(R.drawable.letter_bg);
                    }
                    //add to display
                    wordLayout.addView(charViews[c]);
                }

                //reset adapter
                numAdapter = new NumAdapter(this);
                letters2.setAdapter(numAdapter);
                ltrAdapt = new LetterAdapter(this);
                letters.setAdapter(ltrAdapt);
                currPart = 0;
                //set word length and correct choices
                numChars = currWord.length();
                numCorr = 0;

                for (int k = 0; k < currWord.length(); k++) {
                    if (currWord.charAt(k) == 'A') {
                        numCorr++;


                    } if (currWord.charAt(k) == '.') {
                        numCorr++;


                    } if (currWord.charAt(k) == '!') {
                        numCorr++;


                    } if (currWord.charAt(k) == '-') {
                        numCorr++;


                    }
                    if (currWord.charAt(k) == 'E') {
                        numCorr++;

                    }
                    if (currWord.charAt(k) == 'I') {
                        numCorr++;

                    }
                    if (currWord.charAt(k) == 'O') {
                        numCorr++;


                    }
                    if (currWord.charAt(k) == 'U') {
                        numCorr++;


                    }
                    if (currWord.charAt(k) == ' ') {

                        numCorr++;
                    }
                }


                for (int t = 0; t < 9; t++) {
                    bollywood[t].setBackgroundColor(0xF4C34D);
                }


            }
        }


    //letter pressed method
    public void letterPressed(View view) {
        //find out which letter was pressed
        String ltr = ((TextView) view).getText().toString();
        char letterChar = ltr.charAt(0);
//disable view
        view.setEnabled(false);
        YoYo.with(Techniques.ZoomOut).duration(1000).playOn(view);


//check if correct
        boolean correct = false;
        if (currWord.length() < 12) {

            for (int k = 0; k < currWord.length(); k++) {

                if (currWord.charAt(k) == letterChar) {
                    correct = true;
                    sp.play(explosion, 1, 1, 0, 0, 1);
                    numCorr++;
                    charViews[k].setTextColor(Color.WHITE);

                }
            }
            if (correct == false) {
                sp.play(explosion2, 1, 1, 0, 0, 1);

            }
        } else {
            for (int k = 0; k < currWord.length(); k++) {
                if (currWord.charAt(k) == letterChar) {
                    correct = true;
                    numCorr++;
                    if (explosion != 0) {
                        sp.play(explosion, 1, 1, 0, 0, 1);
                    }
                    if (k < 12) {
                        charViews[k].setTextColor(Color.WHITE);
                    } else {
                        charViews2[k - 12].setTextColor(Color.WHITE);

                    }
                }
            }


        }
        if (correct == false) {
            sp.play(explosion2, 1, 1, 0, 0, 1);
        }
        if (correct) {
            if (numCorr == numChars) {
                //disable all buttons
                //let user know they have won, ask if they want to play again
                time.cancel();
                sp.play(explosion3, 1, 1, 0, 0, 1);
                AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
                winBuild.setTitle("YAY");
                winBuild.setMessage("You win!\n\nThe answer was:\n\n" + currWord);

                winBuild.setPositiveButton("Play Again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                GameActivitySingle.this.playGame();
                            }
                        });


                winBuild.setNegativeButton("Exit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                GameActivitySingle.this.finish();
                            }
                        });
                winBuild.show();
            }

        } else if (currPart < numParts) {
            //show next part
            switch (currPart) {
                case 0:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(bollywood[currPart]);
                    bollywood[currPart].setImageResource(R.drawable.mr);
                    currPart++;
                    break;
                case 1:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(bollywood[currPart]);
                    bollywood[currPart].setImageResource(R.drawable.or);
                    currPart++;
                    break;
                case 2:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(bollywood[currPart]);
                    bollywood[currPart].setImageResource(R.drawable.vr);
                    currPart++;
                    break;
                case 3:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(bollywood[currPart]);
                    bollywood[currPart].setImageResource(R.drawable.ir);
                    currPart++;
                    break;
                case 4:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(bollywood[currPart]);
                    bollywood[currPart].setImageResource(R.drawable.er);
                    currPart++;
                    break;
                case 5:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(bollywood[currPart]);
                    bollywood[currPart].setImageResource(R.drawable.mr);
                    currPart++;
                    break;
                case 6:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(bollywood[currPart]);
                    bollywood[currPart].setImageResource(R.drawable.ar);
                    currPart++;
                    break;
                case 7:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(bollywood[currPart]);
                    bollywood[currPart].setImageResource(R.drawable.nr);
                    currPart++;
                    break;
                case 8:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(bollywood[currPart]);
                    bollywood[currPart].setImageResource(R.drawable.ir);
                    currPart++;
                    break;
                case 9:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(bollywood[currPart]);
                    bollywood[currPart].setImageResource(R.drawable.ar);
                    currPart++;
                    //user has lost
                    //let the user know they lost, ask if they want to play again
                    time.cancel();
                    sp.play(explosion4, 1, 1, 0, 0, 1);
                    AlertDialog.Builder loseBuild = new AlertDialog.Builder(this);
                    loseBuild.setTitle("OOPS");
                    loseBuild.setMessage("You lose!\n\nThe answer was:\n\n" + currWord);
                    loseBuild.setPositiveButton("Play Again",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    GameActivitySingle.this.playGame();
                                }
                            });
                    loseBuild.setNegativeButton("Exit",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    GameActivitySingle.this.finish();
                                }
                            });
                    loseBuild.show();



                    break;
            }
        }
    }

    public void disableBtns() {
        int numLetters = letters.getChildCount();
        for (int l = 0; l < numLetters; l++) {
            letters.getChildAt(l).setEnabled(false);
            int num = letters2.getChildCount();
            for (int i = 0; i < num; i++) {
                letters.getChildAt(i).setEnabled(false);
            }
        }
    }
}












