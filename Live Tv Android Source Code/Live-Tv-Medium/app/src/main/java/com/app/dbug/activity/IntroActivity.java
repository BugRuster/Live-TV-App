package com.app.dbug.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.app.dbug.R;
import com.cuberto.liquid_swipe.LiquidPager;
import com.app.dbug.SessionIntro;
import com.app.dbug.adapter.viewPager;
import com.app.dbug.utils.RtlUtils;


public class IntroActivity extends AppCompatActivity {


    // declare LiquidPager
    LiquidPager pager;

    // declare viewPager
    com.app.dbug.adapter.viewPager viewPager;

    SessionIntro session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        RtlUtils.setScreenDirection(this);
        // Checking for first time launch - before calling setContentView()
        session = new SessionIntro(this);
        if (!session.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // definition of pager using method findViewById()
        pager = findViewById(R.id.pager);
        pager.setButtonDrawable(R.drawable.ic_baseline_arrow_back_ios);
        // calling the constructor of viewPager class
        viewPager = new viewPager(getSupportFragmentManager(), 1);
        pager.setAdapter(viewPager);

    }
    private void launchHomeScreen() {
        session.setFirstTimeLaunch(false);
        startActivity(new Intent(IntroActivity.this, TvMainActivity.class));
        finish();
    }

}
