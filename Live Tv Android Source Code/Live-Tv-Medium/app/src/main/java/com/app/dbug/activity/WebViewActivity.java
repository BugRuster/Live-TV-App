package com.app.dbug.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.widget.FrameLayout;

import com.app.dbug.websetting.HTML5WebView;
import com.app.dbug.R;

public class WebViewActivity extends AppCompatActivity {



    String imageString;
    String titleString;
    String categoryString;
    String fullString;
    String id1;
    String catId;
    String createdAt;
    String updatedAt;
    String videoUpload;
    String videoUrl1;
    String typeName;
    String typePosition;

    String user_agent;
    String token;
    String url_id;
    String sID;


    FrameLayout webViewEm;
    HTML5WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent callingIntent = getIntent();
        id1 = callingIntent.getStringExtra("id");
        catId = callingIntent.getStringExtra("cat_id");
        createdAt = callingIntent.getStringExtra("created_at");
        updatedAt = callingIntent.getStringExtra("updated_at");
        imageString = callingIntent.getStringExtra("Image");
        titleString = callingIntent.getStringExtra("Title");
        categoryString = callingIntent.getStringExtra("Category");
        fullString = callingIntent.getStringExtra("Full");
        videoUrl1 = callingIntent.getStringExtra("tv_url");
        videoUpload = callingIntent.getStringExtra("video_upload");
        typePosition = callingIntent.getStringExtra("type_postion");
        typeName = callingIntent.getStringExtra("type_name");
        url_id = callingIntent.getStringExtra("url_id");
        sID = callingIntent.getStringExtra("stID");
        user_agent = callingIntent.getStringExtra("user_agent");
        token = callingIntent.getStringExtra("token");


        mWebView = new HTML5WebView(this);
        webViewEm = findViewById(R.id.webViewEm);
        mWebView.loadUrl(videoUrl1);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
//        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.OFF);
        mWebView.getSettings().setAllowFileAccess(true);
        webViewEm.removeView(mWebView.getLayout());

        webViewEm.addView(mWebView.getLayout());

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mWebView.handleBack();
    }
}