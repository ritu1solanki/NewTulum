package com.example.tulupcoffee;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NewRecipe extends AppCompatActivity {
    ProgressBar progressBar;
    TextView s1, s2, s3,/*s4,s5,s6,s7,s8,s9,s10,s11,s12*/
            method_name;
    ImageView start, back;
    Button pause, resume;
    MyCountDownTimer myCountDownTimer;
    //int total_seconds = 5000;
    long timeLeft, t1, t2, t3, t4, t5, t6, t8, t7, t10, t11, t9;

    String flag;
    int px = 160;

    private boolean isPaused = false;
    private boolean isCanceled = false;
    private long timeRemaining = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initialise();
//        resume.setEnabled(false);
//        pause.setEnabled(false);
        steps_setter();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaused = false;
                isCanceled = false;

                if (flag.equals("1")) {
                    t1 = 5;
                    t2 = 10;
                    t3 = 15;
                    t4 = 35;
                    t5 = 42;
                    t6 = 46;
                    t7 = 106;
                    t8 = 118;
                    progressBar.setMax((int) t8);
                    myCountDownTimer = new MyCountDownTimer(t8 * 1000, 1000);
                    myCountDownTimer.start();
                } else if (flag.equals("2")) {
                    t1 = 5;
                    t2 = 15;
                    t3 = 21;
                    t4 = 40;
                    t5 = 50;
                    t6 = 290;
                    t7 = 300;
                    t8 = 305;
                    progressBar.setMax((int) t8);
                    myCountDownTimer = new MyCountDownTimer(t8 * 1000, 1000);
                    myCountDownTimer.start();
                } else if (flag.equals("3")) {
                    t1 = 5;
                    t2 = 12;
                    t3 = 17;
                    t4 = 32;
                    t5 = 52;
                    t6 = 66;
                    t7 = 86;
                    t8 = 87;
                    progressBar.setMax((int) t8);
                    myCountDownTimer = new MyCountDownTimer(t8 * 1000, 1000);
                    myCountDownTimer.start();
                } else if (flag.equals("4")) {
                    t1 = 10;
                    t2 = 16;
                    t3 = 20;
                    t4 = 32;
                    t5 = 36;
                    t6 = 96;
                    t7 = 111;
                    t8 = 126;
                    t9 = 141;
                    t10 = 146;
                    t11 = 156;
                    progressBar.setMax((int) t11);
                    myCountDownTimer = new MyCountDownTimer(t11 * 1000, 1000);
                    myCountDownTimer.start();
                }

            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                isPaused = false;
                isCanceled = false;
                long millisInFuture = timeRemaining;
                long countDownInterval = 1000;
                new CountDownTimer(millisInFuture, countDownInterval){
                    public void onTick(long millisUntilFinished){
                        if(isPaused || isCanceled)
                        {
                            cancel();
                        }
                        else {
                            timeLeft++;
//            ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", (int) timeLeft);
//            progressAnimator.setInterpolator(new DecelerateInterpolator());
//            progressAnimator.start();
                            progressBar.setInterpolator(new DecelerateInterpolator());
                            progressBar.setProgress((int) timeLeft);
                            steps_changer();
                            timeRemaining = millisUntilFinished;
                        }
                    }
                    public void onFinish(){
                        //Do something when count down finished
                        progressBar.setProgress((int)t11);
                        Intent intent = new Intent(NewRecipe.this,CompleteCoffee.class);
                        intent.putExtra("Flag",""+flag);
                        startActivity(intent);
                        finish();
                    }
                }.start();
                pause.setBackgroundTintList(getResources().getColorStateList(R.color.CreamColor));
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPaused = true;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewRecipe.this, Ingrediant_Selection.class);
                intent.putExtra("Flag", flag);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewRecipe.this, Ingrediant_Selection.class);
        intent.putExtra("Flag", flag);
        startActivity(intent);
        finish();
    }

    private void initialise() {
        s1 = findViewById(R.id.step1);
        s2 = findViewById(R.id.step2);
        s3 = findViewById(R.id.step3);
       /* s4 = findViewById(R.id.step4);
        s5 = findViewById(R.id.step5);
        s6 = findViewById(R.id.step6);
        s7 = findViewById(R.id.step7);
        s8 = findViewById(R.id.step8);
        s9 = findViewById(R.id.step9);
        s10 = findViewById(R.id.step10);
        s11 = findViewById(R.id.step11);
        s12 = findViewById(R.id.step12);*/
        progressBar = findViewById(R.id.barTimer);
        start = findViewById(R.id.play);
        progressBar.setProgress(0);
        method_name = findViewById(R.id.method);
        back = findViewById(R.id.back);
        pause = findViewById(R.id.pause);
        resume = findViewById(R.id.resume);

    }

    public void steps_setter() {
        Intent intent = getIntent();
        String dose = intent.getStringExtra("dose");
        String water_amt = intent.getStringExtra("water_amt");
        flag = intent.getStringExtra("Flag");
        //Toast.makeText(this, "Flag"+flag, Toast.LENGTH_SHORT).show();
        water_amt = water_amt.substring(0, 3);
        if (flag.equals("1")) {
            method_name.setText("AeroPress");
            double initial = Double.parseDouble(water_amt) / 8;
            double step5_water = Double.parseDouble(water_amt) - initial;
            s1.setText("Add " + dose + " of coffee into the chamber");
            s2.setText("Pour " + initial + "ml of water into the chamber");
            s3.setText("Stir in one Direction");
            /*s4.setText("Wait for coffee to bloom");
            s5.setText("Pour "+step5_water+"ml of water");
            s6.setText("Place the pluger on the top and pull it a little bit till you feel the suction");
            s7.setText("let the coffee Brew");
            s8.setText("Gently press the plunger");
            s9.setText("Ready to sip!");*/

        }


        else if (flag.equals("2")) {
            method_name.setText("FrenchPress");
            double initial = Double.parseDouble(water_amt) / 6;
            double step5_water = Double.parseDouble(water_amt) - initial;
            s1.setText("Add " + dose + " of coffee into the chamber");
            s2.setText("Pour " + String.format("%.0f", initial) + "ml of water to immerse the coffee grounds");
            s3.setText("Stir in one Direction");
           /* s4.setText("Wait for coffee to bloom");
            s5.setText("Pour "+String.format("%.0f", step5_water)+"ml of water");
            s6.setText("Let the coffee brew for 4 minutes");
            s7.setText("Put the filter in and gently push it down");
            s8.setText("Serve in a cup");
            s9.setText("Ready to sip!");*/


        }


        else if (flag.equals("3")) {
            method_name.setText("v60 PourOver");
            double initial = Double.parseDouble(water_amt) / 5;
            double step5_water = Double.parseDouble(water_amt) - initial;
            s1.setText("Put the filter paper in the cone");
            s2.setText("Wet the paper with hot water (discard that water)");
            s3.setText("Add " + dose + " gms of coffee into the paper");
            /*s4.setText("Pour "+String.format("%.0f", initial)+ "ml of water in a circular motion");
            s5.setText("Let the coffee bloom");
            s6.setText("Pour "+String.format("%.0f", step5_water)+"ml of water in a circular motion");
            s7.setText("Wait for the coffee to drip");
            s9.setText("Ready to sip!");
            s8.setVisibility(View.GONE);*/


        } else if (flag.equals("4")) {
            // Change your steps here s1 means step 1 s2 means step 2 and so on so accordingly change your steps. Also those values from previous
            // activity is taken in 2 data initial and step5_answer so accordingly kar lena waisa changes.
            // about the timing part for each step t1 t2 t3 are their respective timing so vo hisab se changes kar dena
            method_name.setText("Moka Pot");
            /*double initial = Double.parseDouble(water_amt)/8;
            double step5_water = Double.parseDouble(water_amt) - initial;*/
            s1.setText("Pour hot water into the bottom chamber up till the screw");
            s2.setText("Add the ground coffee into the funnel.");
            s3.setText("Insert the funnel in the bottom chamber ");
          /*  s4.setText("Tighten the top chamber onto the bottom chamber carefully");
            s5.setText("Keep the moka pot on a stove on medium heater");
            s6.setText("Wait for the water to boil and let the coffee start oozing");
            s7.setText("Once you hear the hissing sound turn of the stove and take the moka pot down");
            s8.setText("Run tap water on the bottom chamber to cool down the moka pot");
            s9.setText("Pour the espresso shot it in a cup");
            s10.setText("Add water or milk as per taste");
            s11.setText("Ready to sip!");
*/
        }
    }
    public void steps_changer() {

        Intent intent = getIntent();
        String dose = intent.getStringExtra("dose");
        String water_amt = intent.getStringExtra("water_amt");
        flag = intent.getStringExtra("Flag");







        if (flag.equals("1")) {

            water_amt = water_amt.substring(0, 3);
            double initial = Double.parseDouble(water_amt) / 8;
            double step5_water = Double.parseDouble(water_amt) - initial;

            if (timeLeft == t1) {
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);

            } else if (timeLeft == t2) {
                /*s1.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );*/
               /* params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s2.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));

                s1.setText("Pour" + initial +" ml of water into the chamber");
                s2.setText("Stir in one Direction");
                s3.setText("Wait for coffee to bloom");

                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
            } else if (timeLeft == t3) {
               /* s2.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s3.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s3.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds  = 20000;
                // time = 1000*1000/total_seconds;


                //Toast.makeText(this, "Flag"+flag, Toast.LENGTH_SHORT).show();



                s1.setText("Stir in one Direction");
                s2.setText("Wait for coffee to bloom");
                s3.setText("Pour " + step5_water+ "ml of water");


            } else if (timeLeft == t4) {
               /* s3.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s4.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 7000;
                //time = 1000*1000/total_seconds;


                //Toast.makeText(this, "Flag"+flag, Toast.LENGTH_SHORT).show();



                s1.setText("Wait for coffee to bloom");
                s2.setText("Pour" +step5_water+" ml of water");
                s3.setText("Place the pluger on the top and pull it a little bit till you feel the suction");

            } else if (timeLeft == t5) {
               /*// s4.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s5.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 4000;
                // time = 1000*1000/total_seconds;



                s1.setText("Pour " + step5_water + "ml of water");
                s2.setText("Place the pluger on the top and pull it a little bit till you feel the suction");
                s3.setText("let the coffee Brew");

            } else if (timeLeft == t6) {
               /* s5.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(40, px, 40, 0);
                s6.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 60000;
                //time = 1000*1000/total_seconds;

                s1.setText("Place the pluger on the top and pull it a little bit till you feel the suction");
                s2.setText("let the coffee Brew");
                s3.setText("Gently press the plunger");


            } else if (timeLeft == t7) {
               /* s6.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s7.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 12000;
                //time = 1000*1000/total_seconds;

                s1.setText("let the coffee Brew");
                s2.setText("Gently press the plunger");
                s3.setText("Ready to sip!");

            } else if (timeLeft == t8) {
               /* s7.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s8.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));

                s1.setText("Gently press the plunger");
                s2.setText("Ready to sip!");
                /*s3.setText("Wait for coffee to bloom");*/

                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_vehicles_aircraft_call_bell_dual_tone_44562);
                mediaPlayer.start();

                Intent inte = new Intent(NewRecipe.this, CompleteCoffee.class);
                inte.putExtra("Flag", "" + flag);
                startActivity(inte);
                finish();
            }


        }



        else if (flag.equals("2")) {

            water_amt = water_amt.substring(0, 3);
            double initial = Double.parseDouble(water_amt) / 6;
            double step5_water = Double.parseDouble(water_amt) - initial;

            if (timeLeft == t1) {
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);

            } else if (timeLeft == t2) {
                /*s1.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );*/
               /* params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s2.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));




                s1.setText("Pour "+String.format("%.0f", initial)+"ml of water to immerse the coffee grounds");
                s2.setText("Stir in one Direction");
                s3.setText("Wait for coffee to bloom");

                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
            } else if (timeLeft == t3) {
               /* s2.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s3.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s3.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds  = 20000;
                // time = 1000*1000/total_seconds;


                s1.setText("Stir in one Direction");
                s2.setText("Wait for coffee to bloom");
                s3.setText("Pour "+String.format("%.0f", step5_water)+"ml of water");


            } else if (timeLeft == t4) {
               /* s3.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s4.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 7000;
                //time = 1000*1000/total_seconds;

                s1.setText("Wait for coffee to bloom");
                s2.setText("Pour "+String.format("%.0f", step5_water)+"ml of water");
                s3.setText("Let the coffee brew for 4 minutes");

            } else if (timeLeft == t5) {
               /*// s4.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s5.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 4000;
                // time = 1000*1000/total_seconds;



                s1.setText("Pour "+String.format("%.0f", step5_water)+"ml of water");
                s2.setText("Let the coffee brew for 4 minutes");
                s3.setText("Put the filter in and gently push it down");

            } else if (timeLeft == t6) {
               /* s5.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(40, px, 40, 0);
                s6.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 60000;
                //time = 1000*1000/total_seconds;

                s1.setText("Let the coffee brew for 4 minutes");
                s2.setText("Put the filter in and gently push it down");
                s3.setText("Serve in a cup");


            } else if (timeLeft == t7) {
               /* s6.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s7.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 12000;
                //time = 1000*1000/total_seconds;

                s1.setText("Put the filter in and gently push it down");
                s2.setText("Serve in a cup");
                s3.setText("Ready to sip!");

            } else if (timeLeft == t8) {
               /* s7.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s8.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));

                s1.setText("Serve in a cup");
                s2.setText("Ready to sip!");
                /*s3.setText("Wait for coffee to bloom");*/

                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_vehicles_aircraft_call_bell_dual_tone_44562);
                mediaPlayer.start();
                Intent inte = new Intent(NewRecipe.this, CompleteCoffee.class);
                inte.putExtra("Flag", "" + flag);
                startActivity(inte);
                finish();
            }
        }












        else if (flag.equals("3")) {

            water_amt = water_amt.substring(0, 3);
            double initial = Double.parseDouble(water_amt) / 5;
            double step5_water = Double.parseDouble(water_amt) - initial;

            if (timeLeft == t1) {
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);

            } else if (timeLeft == t2) {
                /*s1.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );*/
               /* params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s2.setLayoutParams(params);*/







                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));

                s1.setText("Wet the paper with hot water (discard that water)");
                s2.setText("Add " + dose + " gms of coffee into the paper");
                s3.setText("Pour "+String.format("%.0f", initial)+ "ml of water in a circular motion");

                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
            } else if (timeLeft == t3) {
               /* s2.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s3.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s3.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds  = 20000;
                // time = 1000*1000/total_seconds;

                s1.setText("Add " + dose + " gms of coffee into the paper");
                s2.setText("Pour "+String.format("%.0f", initial)+ "ml of water in a circular motion");
                s3.setText("Let the coffee bloom");


            } else if (timeLeft == t4) {
               /* s3.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s4.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 7000;
                //time = 1000*1000/total_seconds;

                s1.setText("Pour "+String.format("%.0f", initial)+ "ml of water in a circular motion");
                s2.setText("Let the coffee bloom");
                s3.setText("Pour "+String.format("%.0f", step5_water)+"ml of water in a circular motion");

            } else if (timeLeft == t5) {
               /*// s4.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s5.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 4000;
                // time = 1000*1000/total_seconds;



                s1.setText("Let the coffee bloom");
                s2.setText("Pour "+String.format("%.0f", step5_water)+"ml of water in a circular motion");
                s3.setText("Wait for the coffee to drip");

            } else if (timeLeft == t6) {
               /* s5.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(40, px, 40, 0);
                s6.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 60000;
                //time = 1000*1000/total_seconds;

                s1.setText("Pour "+String.format("%.0f", step5_water)+"ml of water in a circular motion");
                s2.setText("Wait for the coffee to drip");
                s3.setText("Ready to sip!");


            } else if (timeLeft == t7) {
               /* s6.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s7.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 12000;
                //time = 1000*1000/total_seconds;

                s1.setText("Wait for the coffee to drip");
                s2.setText("Ready to sip!");
                /*s3.setText("Ready to sip!");*/

                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_vehicles_aircraft_call_bell_dual_tone_44562);
                mediaPlayer.start();
                Intent inte = new Intent(NewRecipe.this, CompleteCoffee.class);
                inte.putExtra("Flag", "" + flag);
                startActivity(inte);
                finish();

            } /*else if (timeLeft == t8) {
               *//* s7.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s8.setLayoutParams(params);*//*
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));

                s1.setText("Serve in a cup");
                s2.setText("Ready to sip!");
                *//*s3.setText("Wait for coffee to bloom");*//*

               *//* MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_vehicles_aircraft_call_bell_dual_tone_44562);
                mediaPlayer.start();
                Intent intent = new Intent(NewRecipe.this, CompleteCoffee.class);
                intent.putExtra("Flag", "" + flag);
                startActivity(intent);
                finish();*//*
            }*/
        }else if(flag.equals("4"))
        {
            if (timeLeft == t1) {
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);

            } else if (timeLeft == t2) {
                /*s1.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );*/
               /* params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s2.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));

                s1.setText("Add the ground coffee into the funnel");
                s2.setText("Insert the funnel in the bottom chamber");
                s3.setText("Tighten the top chamber onto the bottom chamber carefully");

                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
            } else if (timeLeft == t3) {
               /* s2.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s3.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s3.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds  = 20000;
                // time = 1000*1000/total_seconds;


//                double initial = Double.parseDouble(water_amt) / 5;
//                double step5_water = Double.parseDouble(water_amt) - initial;
                s1.setText("Insert the funnel in the bottom chamber");
                s2.setText("Tighten the top chamber onto the bottom chamber carefully");
                s3.setText("Keep the moka pot on a stove on medium heater");


            } else if (timeLeft == t4) {
               /* s3.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s4.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 7000;
                //time = 1000*1000/total_seconds;

//                double initial = Double.parseDouble(water_amt) / 5;
//                double step5_water = Double.parseDouble(water_amt) - initial;
                s1.setText("Tighten the top chamber onto the bottom chamber carefully");
                s2.setText("Keep the moka pot on a stove on medium heater");
                s3.setText("Wait for the water to boil and let the coffee start oozing");

            } else if (timeLeft == t5) {
               /*// s4.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s5.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 4000;
                // time = 1000*1000/total_seconds;



                s1.setText("Keep the moka pot on a stove on medium heater");
                s2.setText("Wait for the water to boil and let the coffee start oozing");
                s3.setText("Once you hear the hissing sound turn of the stove and take the moka pot down");

            } else if (timeLeft == t6) {
               /* s5.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(40, px, 40, 0);
                s6.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 60000;
                //time = 1000*1000/total_seconds;

                s1.setText("Wait for the water to boil and let the coffee start oozing");
                s2.setText("Once you hear the hissing sound turn of the stove and take the moka pot down");
                s3.setText("Run tap water on the bottom chamber to cool down the moka pot");

            } else if (timeLeft == t7) {
               /* s6.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s7.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));
                s3.setAlpha((float) 0.3);
                s3.setTextColor(getResources().getColor(R.color.GrayColor));
                //total_seconds = 12000;
                //time = 1000*1000/total_seconds;

                s1.setText("Once you hear the hissing sound turn of the stove and take the moka pot down");
                s2.setText("Run tap water on the bottom chamber to cool down the moka pot");
                s3.setText("Pour the espresso shot it in a cup");



            } else if (timeLeft == t8) {
             /* s7.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s8.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));

                s1.setText("Run tap water on the bottom chamber to cool down the moka pot");
                s2.setText("Pour the espresso shot it in a cup");
                s3.setText("Add water or milk as per taste");


            }


            else if (timeLeft == t9) {
             /* s7.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s8.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));

                s1.setText("Pour the espresso shot it in a cup");
                s2.setText("Add water or milk as per taste");
                s3.setText("Ready to sip!");


            }

            else if (timeLeft == t10) {
             /* s7.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0, px, 0, 0);
                s8.setLayoutParams(params);*/
                s1.setAlpha((float) 0.3);
                s1.setTextSize(20);
                s1.setTextColor(getResources().getColor(R.color.GrayColor));
                s2.setAlpha(1);
                s2.setTextSize(30);
                s2.setTextColor(getResources().getColor(R.color.GreenColor));

                s1.setText("Add water or milk as per taste");
                s2.setText("Ready to sip!");

                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_vehicles_aircraft_call_bell_dual_tone_44562);
                mediaPlayer.start();
                Intent inte = new Intent(NewRecipe.this, CompleteCoffee.class);
                inte.putExtra("Flag", "" + flag);
                startActivity(inte);
                finish();
            }
    }




}
    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(isPaused || isCanceled)
            {
                cancel();
            }
            else {
                timeLeft++;
//            ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", (int) timeLeft);
//            progressAnimator.setInterpolator(new DecelerateInterpolator());
//            progressAnimator.start();
                progressBar.setInterpolator(new DecelerateInterpolator());
                progressBar.setProgress((int) timeLeft);
                steps_changer();
                timeRemaining = millisUntilFinished;
            }


        }
        @Override
        public void onFinish() {
            progressBar.setProgress((int)t11);
            Intent intent = new Intent(NewRecipe.this,CompleteCoffee.class);
            intent.putExtra("Flag",""+flag);
            startActivity(intent);
            finish();
            //timeLeft = 1;
            //time = 0;
            // Toast.makeText(NewRecipe.this, "Flag "+flag, Toast.LENGTH_SHORT).show();
        }
    }
}
