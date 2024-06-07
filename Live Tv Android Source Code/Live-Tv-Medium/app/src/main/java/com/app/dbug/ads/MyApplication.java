package com.app.dbug.ads;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.app.dbug.activity.SplashActivity;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.onesignal.OneSignal;

import xyz.doikki.videoplayer.ijk.IjkPlayerFactory;
import xyz.doikki.videoplayer.player.VideoViewConfig;
import xyz.doikki.videoplayer.player.VideoViewManager;


public class MyApplication extends Application {

    SharedPreferences sharedPreferences ;
    String openAppCode;
    String oneSignalCode;


    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        openAppCode = sharedPreferences.getString(SplashActivity.GOPENAPP, "");
        oneSignalCode = sharedPreferences.getString(SplashActivity.ONE_SIGNAL_ID, "");

        Log.d("sdljfhsd", "onCreate: "+openAppCode);
        MobileAds.initialize(this, initializationStatus -> new AppOpenManager(MyApplication.this,openAppCode));
        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
                .setPlayerFactory(IjkPlayerFactory.create())
                .build());
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(oneSignalCode);


    }
}
