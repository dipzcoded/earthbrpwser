package com.example.earthbrowserapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class EmptyActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView urlSearch;
    private AppCompatEditText urlSearchEdit;
    private ImageView urlHome;
    private Toolbar toolbar;
    String url;
    String  urlAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);


        urlSearch = findViewById(R.id.searchurlBtn);
        urlHome = findViewById(R.id.homeurlBtn);
        urlSearchEdit = findViewById(R.id.searchurlEdit);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Earth Browser");
        urlSearch.setOnClickListener(this);
        urlHome.setOnClickListener(this);

        url = getIntent().getExtras().getString("url_address").toString();
        urlSearchEdit.setText(url);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {


        if(v == urlHome)
        {

            finish();
            Intent homePage = new Intent(EmptyActivity.this,BrowserActivity.class);
            startActivity(homePage);

        }

        if(v == urlSearch)
        {

            if(ConnectionManager.checkConnection(getBaseContext()))
            {
                //                connected
                searchWebAddress();

            }

            else
            {
//                not connected
                emptyState();

            }


        }

    }

    private void searchWebAddress() {


        urlAddress = urlSearchEdit.getText().toString();
        if(TextUtils.isEmpty(urlAddress))
        {
            Toast.makeText(getApplicationContext(),"Enter Valid Url Address",Toast.LENGTH_SHORT).show();

        }

        else
        {


            Intent search = new Intent(EmptyActivity.this,UrlSearch.class);
            search.putExtra("url_address", urlAddress);
            startActivity(search);
            finish();

            urlSearchEdit.setText("");
            urlSearchEdit.requestFocus();
        }

    }


    private void emptyState()
    {

        urlAddress = urlSearchEdit.getText().toString();
        Intent emptyState = new Intent(EmptyActivity.this,EmptyActivity.class);
        emptyState.putExtra("url_address",urlAddress);
        startActivity(emptyState);
        finish();

    }
}
