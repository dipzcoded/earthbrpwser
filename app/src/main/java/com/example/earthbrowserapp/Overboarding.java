package com.example.earthbrowserapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.earthbrowserapp.helpers.Content;

import java.util.ArrayList;
import java.util.List;

public class Overboarding extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tabLayout;
    private AppCompatButton nextBtn;
    PagerAdapter adapter;
    List<Content> contentList;
    static  int position =0;
    AppCompatButton getStarted;
    Animation btnAnim;
    Animation disappearAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overboarding);

        pager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.indicators);
        nextBtn = findViewById(R.id.nextBtn);
        getStarted = findViewById(R.id.getstartedbtn);

        contentList = new ArrayList<>();
        contentList.add(new Content(R.drawable.crash,"Antivirus Secured","it helps to protect you from harmed virus which uses the latest anti-virus technology."));
        contentList.add(new Content(R.drawable.secured,"Prone From Hacking","it provides 24/7 support from hackers by protecting your ip address to be hidden while stop easy access from them."));
        contentList.add(new Content(R.drawable.sucesspic,"Fast & Efficient","The browser have fast downloads speed and it always efficients 24/7"));
        contentList.add(new Content(R.drawable.updates,"Connect You To Your Loved Ones","it helps you to easily connects to your loved ones by giving notifications of their daily activities"));
        contentList.add(new Content(R.drawable.lauching,"Fast Internet Speed","it has a strong 4G network speed connection which makes your surfing interesting"));

        adapter = new com.example.earthbrowserapp.helpers.PagerAdapter(this,contentList);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        disappearAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.disappearanim);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            position = pager.getCurrentItem();
            if(position < contentList.size() - 1)
            {

                position++;
                pager.setCurrentItem(position);

            }

            if(position == contentList.size() - 1)
            {
                
               loadLastScreen();
                
            }


            }
        });

        //tablayout add change listener
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() == contentList.size() -1)
                {

                    loadLastScreen();


                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getStarted.startAnimation(disappearAnim);
                browsingActivity();

            }
        });

    }

    private void browsingActivity() {

        Intent intent = new Intent(Overboarding.this,BrowserActivity.class);
        startActivity(intent);
        finish();

    }

    private void loadLastScreen() {

        getStarted.setVisibility(View.VISIBLE);
        nextBtn.setVisibility(View.INVISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);

        //TODO: Add an animation to the getstared button
        getStarted.setAnimation(btnAnim);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
