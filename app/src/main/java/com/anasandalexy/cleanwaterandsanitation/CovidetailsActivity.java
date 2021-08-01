package com.anasandalexy.cleanwaterandsanitation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CovidetailsActivity extends AppCompatActivity {

    private WebView mywebView;
    ProgressBar progressBar;
    String url = "https://anasdavoodtk1.gitbook.io/liquida/";
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidetails);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.getMenu().getItem(1).setChecked(true);

        mywebView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

        mywebView.setWebViewClient(new notreWebView());
        mywebView.getSettings().setJavaScriptEnabled(true);
        mywebView.getSettings().setBuiltInZoomControls(true);
        mywebView.getSettings().setDisplayZoomControls(false);
        mywebView.loadUrl(url);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.more:
                        //Intent intent1 = new Intent(HotspotActivity.this,MoreActivity.class);
                        overridePendingTransition(0, 0);
                        //startActivity(intent1);
                        finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.home:
                        Intent intent = new Intent(CovidetailsActivity.this,MainActivity.class);
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.vaccine:

                        //Intent intent2 = new Intent(HotspotActivity.this,VaccineActivity.class);
                        overridePendingTransition(0, 0);
                       // startActivity(intent2);
                        finish();
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });

    }


    public  class notreWebView extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }

    }


    @Override
    public void onBackPressed() {
        if(mywebView.canGoBack ()){
            mywebView.goBack ();
        }else{
            super.onBackPressed ();

        }

    }
}