package com.app.dbug.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.app.dbug.R;


public class LoadActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Intent callingIntent = getIntent();
        url = callingIntent.getStringExtra("url");

        if (url!= null){
            Uri uri = Uri.parse(url);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browserIntent);

        }else {
            startActivity(new Intent(this, TvMainActivity.class));
            finish();
        }
    }
}