package com.example.earthbrowserapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class BrowserActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RelativeLayout homeBrowser;
    ImageView homeSplash;
    AppCompatEditText homeUrlEdit;
    ImageView homeSearchBtn;
    ImageView homeBtn;
    LinearLayout homeLinks;
    Animation downToUp;
    AnimationDrawable bgDraw;

//    links buttons
    ImageView facebookbtn;
    ImageView amazonbtn;
    ImageView spotifyBtn;
    ImageView linkedinBtn;
    ImageView pinterestbtn;
    ImageView slackBtn;
    ImageView twitterBtn;
    ImageView gmailBtn;

    String urlAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);


//        components
        toolbar = findViewById(R.id.toolbar);
        homeBrowser = findViewById(R.id.homebrowser);
        homeSplash = findViewById(R.id.browsertheme);
        homeUrlEdit = findViewById(R.id.browserurlEdit);
        homeSearchBtn = findViewById(R.id.browsersearchBtn);
        homeBtn = findViewById(R.id.browserhomeBtn);
        homeLinks = findViewById(R.id.favContainer);
        bgDraw = (AnimationDrawable) homeSplash.getBackground();
        bgDraw.setEnterFadeDuration(18000);
        bgDraw.setExitFadeDuration(10000);
        bgDraw.start();



//        button links
        facebookbtn = findViewById(R.id.facebookbtn);
        amazonbtn = findViewById(R.id.amazonbtn);
        spotifyBtn = findViewById(R.id.spotifybtn);
        linkedinBtn = findViewById(R.id.linkedinbtn);
        pinterestbtn = findViewById(R.id.pinterestbtn);
        slackBtn = findViewById(R.id.slackbtn);
        twitterBtn = findViewById(R.id.twitterbtn);
        gmailBtn = findViewById(R.id.gmailbtn);

        downToUp = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        homeLinks.startAnimation(downToUp);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Earth Browser");
        homeBrowser.setOnClickListener(null);
        homeSearchBtn.setOnClickListener(this);
        homeBtn.setOnClickListener(this);

//        setting onclick listener
        facebookbtn.setOnClickListener(this);
        amazonbtn.setOnClickListener(this);
        spotifyBtn.setOnClickListener(this);
        linkedinBtn.setOnClickListener(this);
        pinterestbtn.setOnClickListener(this);
        slackBtn.setOnClickListener(this);
        twitterBtn.setOnClickListener(this);
        gmailBtn.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {

        if(v == homeSearchBtn) {

            if(ConnectionManager.checkConnection(getBaseContext()))
            {

//                connected
                openWebsite();

            }

            else
            {

//                not connected
                emptyState();

            }

        }

         if(v == facebookbtn)
        {

            if(ConnectionManager.checkConnection(getBaseContext()))
            {

               openfaceBook();

            }

            else
            {

                emptyState();

            }




        }

         if (v == amazonbtn)
        {


            if(ConnectionManager.checkConnection(getBaseContext()))
            {

               openAmazon();

            }

            else
            {

                emptyState();

            }

        }

        if(v == spotifyBtn)
        {


            if(ConnectionManager.checkConnection(getBaseContext()))
            {

                openSpotify();

            }

            else
            {

                emptyState();

            }


        }

         if(v == linkedinBtn)
        {


            if(ConnectionManager.checkConnection(getBaseContext()))
            {

               openLinkedin();

            }

            else
            {

                emptyState();

            }

        }

         if(v == pinterestbtn)
        {


            if(ConnectionManager.checkConnection(getBaseContext()))
            {

                openPinterest();

            }

            else
            {

                emptyState();

            }

        }

         if(v == gmailBtn)
        {


            if(ConnectionManager.checkConnection(getBaseContext()))
            {

                openGmail();

            }

            else
            {

                emptyState();

            }


        }

         if(v == slackBtn)
        {


            if(ConnectionManager.checkConnection(getBaseContext()))
            {

                openSlack();

            }

            else
            {

                emptyState();

            }


        }

         if(v == twitterBtn)
        {


            if(ConnectionManager.checkConnection(getBaseContext()))
            {

                openTwitter();

            }

            else
            {

                emptyState();

            }



        }

    }

    private void openfaceBook() {

        Intent open_facebook = new Intent(BrowserActivity.this,UrlSearch.class);
        open_facebook.putExtra("url_address","facebook.com");
        startActivity(open_facebook);

    }
    private void openTwitter(){
        Intent open_twitter = new Intent(BrowserActivity.this,UrlSearch.class);
        open_twitter.putExtra("url_address","twitter.com");
        startActivity(open_twitter);

    }
    private void openGmail(){
        Intent open_gmail = new Intent(BrowserActivity.this,UrlSearch.class);
        open_gmail.putExtra("url_address","gmail.com");
        startActivity(open_gmail);

    }
    private void openSlack(){
        Intent open_slack = new Intent(BrowserActivity.this,UrlSearch.class);
        open_slack.putExtra("url_address","slack.com");
        startActivity(open_slack);

    }
    private void openPinterest(){
        Intent open_pinterest = new Intent(BrowserActivity.this,UrlSearch.class);
        open_pinterest.putExtra("url_address","pinterest.com");
        startActivity(open_pinterest);

    }
    private void openLinkedin(){
        Intent open_linkedin = new Intent(BrowserActivity.this,UrlSearch.class);
        open_linkedin.putExtra("url_address","linkedin.com");
        startActivity(open_linkedin);

    }
    private void openSpotify(){
        Intent open_spotify = new Intent(BrowserActivity.this,UrlSearch.class);
        open_spotify.putExtra("url_address","spotify.com");
        startActivity(open_spotify);

    }
    private void openAmazon(){

        Intent open_amazon = new Intent(BrowserActivity.this,UrlSearch.class);
        open_amazon.putExtra("url_address","amazon.com");
        startActivity(open_amazon);

    }

    private void openWebsite() {

        urlAddress = homeUrlEdit.getText().toString();
        if(TextUtils.isEmpty(urlAddress))
        {
            Toast.makeText(getApplicationContext(),"Enter Valid Url Address",Toast.LENGTH_SHORT).show();

        }

        else
        {

            String url_without_https = urlAddress.replace("https;//www.","");
            String https = "https://";
            String www = "www.";

            Intent search = new Intent(BrowserActivity.this,UrlSearch.class);
            search.putExtra("url_address",https+www+url_without_https);
            startActivity(search);
            finish();

            homeUrlEdit.setText("");
            homeUrlEdit.requestFocus();

        }


    }

    private void emptyState()
    {

        urlAddress = homeUrlEdit.getText().toString();
        Intent emptyState = new Intent(BrowserActivity.this,EmptyActivity.class);
        emptyState.putExtra("url_address",urlAddress);
        startActivity(emptyState);

    }
}
