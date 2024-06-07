package com.app.dbug.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.dbug.R;
import com.app.dbug.SessionIntro;
import com.app.dbug.api.ApiInter;
import com.app.dbug.model.Ads;
import com.app.dbug.model.DeviceToken;
import com.app.dbug.model.SettingModle;
import com.app.dbug.retofit.RetrofitClient;


import com.google.firebase.messaging.FirebaseMessaging;
import com.onesignal.OneSignal;


import java.util.Calendar;
import java.util.Date;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {


    String channelId = "";
    SharedPreferences sharedpreferences;
    public static final String MYPREFERENCE = "mypref";
    public static final String ADSKEY = "adsKey";
    public static final String BANNER_ADS = "banner_ads";
    public static final String GBANNER_ID = "gbanner_id";
    public static final String GINTERS_ID = "ginters_id";
    public static final String GNATIVE_ID = "gnative_id";
    public static final String GREWORD_ID = "greward";
    public static final String GOPENAPP = "gopeenApp";

    public static final String FBBANNER_ID = "fbbanner_id";
    public static final String FBINTERS_ID = "fbinters_id";
    public static final String FBNATIVE_ID = "fbnative_id";
    public static final String FBREWORD_ID = "fbreward";

    public static final String APPNEX_INTER = "appnex_inter";
    public static final String UNITY_APP_ID_GAME_ID = "appnex_banner";
    public static final String STARTAPP_APP_ID = "startapp_app_id";

    public static final String ADD_COUNT = "addcount";
    public static final String GET_ADD_COUNT_NATIVE = "getAdd_count_native";
    public static final String CHANNEL_ID = "notification_channel";
    public static final String HOME_ADS = "homeAds";

    //Setting
    public static final String PRIVACY_URL = "privacy_url";
    public static final String ONE_SIGNAL_ID = "one_signalID";
    public static final String SUPPORT_EMAIL = "support_email";

    AlertDialog.Builder builder;


    ImageView splashViewId;
    boolean isDark;
    SharedPreferences.Editor editor;
    TextView appNameInSplash;
    SessionIntro session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedpreferences = getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        session = new SessionIntro(this);
        if (session.isFirstTimeLaunch()){
            editor.putBoolean("dark",false);
            editor.apply();
        }
        isDark = sharedpreferences.getBoolean("dark", false);
        builder = new AlertDialog.Builder(this);

        splashViewId = findViewById(R.id.splashViewId);
        appNameInSplash = findViewById(R.id.app_name_in_splash);
        if (isDark) {
            splashViewId.setImageResource(R.drawable.splash_b);
            appNameInSplash.setTextColor(getColor(R.color.white));
        } else {
            splashViewId.setImageResource(R.drawable.splash_w);
            appNameInSplash.setTextColor(getColor(R.color.black));
        }

        boolean notificationEnable = NotificationManagerCompat.from(this).areNotificationsEnabled();
        if (!notificationEnable) {
            builder.setMessage("Do you want to use this application ? must be enable notification.")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, id) -> {
                        finish();
                        Intent settingsIntent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName())
                                .putExtra(Settings.EXTRA_CHANNEL_ID, CHANNEL_ID);
                        startActivity(settingsIntent);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel());
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("Allow notification ");
            alert.show();

            return;
        }


        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(HOME_ADS, true);
        editor.apply();

        adsFromServer();
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                return;
            }
            String token = task.getResult();
            String deviceName = android.os.Build.MODEL;
            tokenPost(token);
        });

        settingDataLoad();


        channelId = getString(R.string.app_name);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(getString(R.string.app_name), getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(getString(R.string.app_name));
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }


    }

    private void adsFromServer() {
        ApiInter ads = RetrofitClient.getRetrofit().create(ApiInter.class);
        ads.getAds()
                .enqueue(new Callback<Ads>() {
                    @Override
                    public void onResponse(Call<Ads> call, Response<Ads> response) {
                        if (response.isSuccessful()) {
                            Ads ads = response.body();

                            Log.d("sdkfljsdfks", "onResponse: " + ads);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(ADSKEY, ads.data.addtype_id + "");
                            editor.putString(BANNER_ADS, getResources().getString(R.string.banner_ads));

                            editor.putString(GBANNER_ID, ads.data.admob_banner);
                            editor.putString(GINTERS_ID, ads.data.admob_inter);
                            editor.putString(GNATIVE_ID, ads.data.admob_native);
                            editor.putString(GREWORD_ID, ads.data.admob_reward);
                            editor.putString(GOPENAPP, ads.data.admob_appopen);

                            editor.putString(FBBANNER_ID, ads.data.fb_banner);
                            editor.putString(FBINTERS_ID, ads.data.fb_inter);
                            editor.putString(FBNATIVE_ID, ads.data.fb_native);
                            editor.putString(FBREWORD_ID, ads.data.fb_reward);

                            editor.putString(UNITY_APP_ID_GAME_ID, ads.data.appnex_banner);
                            editor.putString(APPNEX_INTER, ads.data.appnex_inter);
                            editor.putString(STARTAPP_APP_ID, ads.data.startapp_app_id);

                            editor.putInt(ADD_COUNT, ads.data.add_count);
                            editor.putInt(GET_ADD_COUNT_NATIVE, ads.data.getAdd_count_native());
                            editor.apply();

                            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                Intent intent = new Intent(SplashActivity.this, MainIntroActivity.class);
                                startActivity(intent);
                                finish();
                                new AnimIntent.Builder(SplashActivity.this).performSlideToLeft();
                            }, 3000);


                        }
                    }

                    @Override
                    public void onFailure(Call<Ads> call, Throwable t) {

                        new Handler(Looper.getMainLooper()).postDelayed(() -> {

                            Intent intent = new Intent(SplashActivity.this, MainIntroActivity.class);
                            startActivity(intent);
                            finish();
                            new AnimIntent.Builder(SplashActivity.this).performSlideToLeft();
                        }, 3000);
                    }
                });
    }

    public void tokenPost(String token) {
        SharedPreferences sharedpreferences = getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String oldToken = sharedpreferences.getString("deviceToken", "dfgsg");
        String token1 = token;
        Log.d("sada", "old " + oldToken);
        Log.d("sada", "new " + token1);
        if (!oldToken.equals(token1)) {
            Log.d("fsjsldasdf", "tokenPost: if ");
            ApiInter tokenApi = RetrofitClient.getRetrofit().create(ApiInter.class);
            Date currentTime = Calendar.getInstance().getTime();
            String name = currentTime + "";
            DeviceToken deviceToken = new DeviceToken(name, token1);
            editor.putString("deviceToken", token1).apply();
            tokenApi.postToken(deviceToken)
                    .enqueue(new Callback<DeviceToken>() {
                        @Override
                        public void onResponse(Call<DeviceToken> call, Response<DeviceToken> response) {
                            if (response.isSuccessful()) {
                                Log.d("dsfds", "onResponse: ");
                            }

                        }

                        @Override
                        public void onFailure(Call<DeviceToken> call, Throwable t) {
                            Log.d("dsfds", "onFailure: " + t.getMessage());

                        }
                    });
        } else {
            Log.d("fsjsldasdf", "tokenPost: els ");
        }
    }



    public void settingDataLoad() {
        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        apiInter.getSettingData()
                .enqueue(new Callback<SettingModle>() {
                    @Override
                    public void onResponse(Call<SettingModle> call, Response<SettingModle> response) {
                        if (response.isSuccessful()){
                            SettingModle settingModle = response.body();

                            editor.putString(PRIVACY_URL,settingModle.data.privacy_url);
                            editor.putString(ONE_SIGNAL_ID,settingModle.data.one_signalID);
                            editor.putString(SUPPORT_EMAIL,settingModle.data.support_email);
                            if (sharedpreferences.getInt("spanCout",0)!=1){
                                editor.putInt("spanCout", 1);
                            }
                            editor.apply();

                            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
                            OneSignal.initWithContext(getApplicationContext());
                            OneSignal.setAppId(settingModle.data.one_signalID);


                        }
                    }

                    @Override
                    public void onFailure(Call<SettingModle> call, Throwable t) {
                        Log.d("logg", "onFailure: ");
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}