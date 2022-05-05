package com.example.earthbrowserapp;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class UrlSearch extends AppCompatActivity implements View.OnClickListener {

    private ImageView urlSearch;
    private AppCompatEditText urlSearchEdit;
    private ImageView urlHome;
    private WebView webView;
    private SwipeRefreshLayout refreshLayout;
    private ProgressBar progressBar;
    Toolbar toolbar;
    String url;
    String urlFormatted;
    String  urlAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_search);

        urlSearch = findViewById(R.id.searchurlBtn);
        urlHome = findViewById(R.id.homeurlBtn);
        urlSearchEdit = findViewById(R.id.searchurlEdit);
        webView = findViewById(R.id.webView);
        refreshLayout = findViewById(R.id.swiperefreshlayout);
        progressBar = findViewById(R.id.progressbar);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        url = getIntent().getExtras().getString("url_address").toString();
        urlFormatted = "https://www." + url;

        if(url.contains("https://www."))
        {
            urlSearchEdit.setText(url);
            webView.loadUrl(url);

        }

        else
        {
            urlSearchEdit.setText(urlFormatted);
            webView.loadUrl(urlFormatted);
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Earth Browser");




//        webview



        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });


        WebViewClient webViewClient = new WebViewClient()
        {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
               refreshLayout.setRefreshing(false);
            }


            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);

                refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorFinishedRefreshed));
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

                refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorLoadRefresh));

            }
        };

        webView.setWebViewClient(webViewClient);
        urlSearch.setOnClickListener(this);
        urlHome.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
       if(webView.canGoBack())
       {

           webView.goBack();

       }

       else
       {
           super.onBackPressed();
       }
    }

    @Override
    public void onClick(View v) {

        if(v == urlHome)
        {

            finish();
            Intent homePage = new Intent(UrlSearch.this,BrowserActivity.class);
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


      String  urlAddress = urlSearchEdit.getText().toString();
        if(TextUtils.isEmpty(urlAddress))
        {
            Toast.makeText(getApplicationContext(),"Enter Valid Url Address",Toast.LENGTH_SHORT).show();

        }

        else
        {

            Intent search = new Intent(UrlSearch.this,UrlSearch.class);
            search.putExtra("url_address",urlAddress);
            startActivity(search);
            finish();

            urlSearchEdit.setText("");
            urlSearchEdit.requestFocus();
        }

    }

    private void emptyState()
    {

        urlAddress = urlSearchEdit.getText().toString();
        Intent emptyState = new Intent(UrlSearch.this,EmptyActivity.class);
        emptyState.putExtra("url_address",urlAddress);
        startActivity(emptyState);
        finish();

    }
}
