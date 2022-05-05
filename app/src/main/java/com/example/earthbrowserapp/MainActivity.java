package com.example.earthbrowserapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

//    properties
    private ImageView appLogo;
    private TextView appHint;
    private ProgressBar appProgress;
    private static int splashTimeOut = 4000;
    private static int current =0;
    Handler intentHandler;
    Runnable runnable;
    Timer timer;
    Animation appLogoAnim;
    Animation revealAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appProgress = findViewById(R.id.progressbar);
        appHint = findViewById(R.id.apphint);
        appLogo = findViewById(R.id.applogo);
        appLogoAnim = AnimationUtils.loadAnimation(this,R.anim.applogoanim);
        revealAnim = AnimationUtils.loadAnimation(this,R.anim.revealanim);
        appLogo.startAnimation(appLogoAnim);
        appHint.startAnimation(revealAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            intentHandler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {

                    if (++current < 20)
                    {

                        appProgress.setVisibility(View.INVISIBLE);

                    }
                    else {

                    appProgress.setVisibility(View.GONE);
                    current = 0;
                    timer.cancel();
                    newActivity();
                    }

                }
            };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                intentHandler.post(runnable);

            }
        },2000,120);

            }
        },splashTimeOut);

    }

    private void newActivity() {

        Intent newActivity = new Intent(MainActivity.this,Overboarding.class);
        startActivity(newActivity);
        finish();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
