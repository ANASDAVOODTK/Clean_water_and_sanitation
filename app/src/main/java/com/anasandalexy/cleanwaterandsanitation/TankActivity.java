package com.anasandalexy.cleanwaterandsanitation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class TankActivity extends AppCompatActivity {

    private WebView mywebView;
    ProgressBar progressBar;
    String url = "https://kwa.kerala.gov.in/collection-drive/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mywebView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

        mywebView.setWebViewClient(new notreWebView());
        mywebView.getSettings().setJavaScriptEnabled(true);
        mywebView.getSettings().setBuiltInZoomControls(true);
        mywebView.getSettings().setDisplayZoomControls(false);
        mywebView.loadUrl(url);

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