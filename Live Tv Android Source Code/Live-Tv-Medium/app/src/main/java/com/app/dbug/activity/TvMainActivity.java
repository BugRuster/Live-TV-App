package com.app.dbug.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.dbug.ads.InterAdClickInterFace;
import com.app.dbug.ads.SessionAds;
import com.app.dbug.api.ApiInter;
import com.app.dbug.fragmant.HomeFragment;
import com.app.dbug.fragmant.SettingFragment;
import com.app.dbug.FcmTokenRegistrationService;
import com.app.dbug.model.HomeDiloagModel;
import com.app.dbug.retofit.RetrofitClient;
import com.app.dbug.websetting.HTML5WebView;
import com.app.dbug.R;
import com.app.dbug.utils.FirebaseMessageReceiver;
import com.app.dbug.utils.RtlUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;


import java.util.ArrayList;
import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.app.dbug.activity.SplashActivity.MYPREFERENCE;

public class  TvMainActivity extends AppCompatActivity implements InterAdClickInterFace {

    ViewPager viewPager;


    TabLayout tabLayout;
    TabLayout.Tab newTab1;
    TabLayout.Tab newTab5;
    boolean doubleBackToExitPressedOnce = false;
    boolean isDark;
    SharedPreferences sharedPreferences;
    FrameLayout webViewEm;
    HTML5WebView mWebView;
    SessionAds sessionAds;
    TextView tabOne,tabTwo;
    View lineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        if (isDark) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppThemeLight);
        }
        setContentView(R.layout.activity_tv_main);
        super.onCreate(savedInstanceState);

        sessionAds = new SessionAds(TvMainActivity.this,this);

        RtlUtils.setScreenDirection(this);
        Intent intentBackgroundService = new Intent(this, FirebaseMessageReceiver.class);
        startService(intentBackgroundService);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewpager);


        lineView = findViewById(R.id.line_view);
        tabLayout = findViewById(R.id.tab_layout);
        newTab1 = tabLayout.newTab();
        newTab5 = tabLayout.newTab();
        tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Home");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home_icon_w, 0, 0);
        tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Settings");
        if (isDark){
            lineView.setBackgroundColor(ContextCompat.getColor(this, R.color.line_color_dark));
            tabTwo.setTextColor(getColor(R.color.second_text_color_dark));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));
        }else {
            lineView.setBackgroundColor(ContextCompat.getColor(this, R.color.line_color));
            tabTwo.setTextColor(getColor(R.color.second_text_color_light));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.text_color));
        }
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_setting_icon_black, 0, 0);
        newTab1.setCustomView(tabOne);
        tabLayout.addTab(newTab1);
        newTab5.setCustomView(tabTwo);
        tabLayout.addTab(newTab5);
        adapter.addFrag(new HomeFragment(this), "");
        adapter.addFrag(new SettingFragment(), "");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);

        try {
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                public void onTabReselected(TabLayout.Tab tab) {
                    //empty
                }
                public void onTabUnselected(TabLayout.Tab tab) {
                    //empty
                }
                public void onTabSelected(TabLayout.Tab tab) {
                    try {
                        viewPager.setCurrentItem(tab.getPosition());
                        if (tab.getPosition() == 0) {
                            if (isDark){
                                tabOne.setTextColor(getColor(R.color.main_text_color_dark));
                                tabTwo.setTextColor(getColor(R.color.second_text_color_dark));
                            }else {
                                tabOne.setTextColor(getColor(R.color.text_color));
                                tabTwo.setTextColor(getColor(R.color.second_text_color_light));
                            }
                            tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home_icon_w, 0, 0);
                            tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_setting_icon_black, 0, 0);
                            newTab1.setCustomView(tabOne);
                            newTab5.setCustomView(tabTwo);
                        } else if (tab.getPosition() == 1) {
                            if (isDark){
                                tabOne.setTextColor(getColor(R.color.second_text_color_dark));
                                tabTwo.setTextColor(getColor(R.color.main_text_color_dark));
                            }else {
                                tabOne.setTextColor(getColor(R.color.second_text_color_light));
                                tabTwo.setTextColor(getColor(R.color.text_color));
                            }
                            tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home_icon_black, 0, 0);
                            tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_setting_icon_w, 0, 0);
                            newTab1.setCustomView(tabOne);
                            newTab5.setCustomView(tabTwo);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            this.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        } catch (Exception e) {
            e.printStackTrace();
        }

        createChannel();

        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        apiInter.getHomeDilogBody()
                .enqueue(new Callback<HomeDiloagModel>() {
                    @Override
                    public void onResponse(Call<HomeDiloagModel> call, Response<HomeDiloagModel> response) {
                        if (response.isSuccessful()) {
                            HomeDiloagModel homeDiloagModel = response.body();
                            Log.d("sdkfjlksdjf", "onResponse: "+homeDiloagModel.visibility);


                            if (homeDiloagModel.visibility.equals("yes")) {
                                showDialog(TvMainActivity.this, homeDiloagModel.url);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HomeDiloagModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sendFcmRegistrationToken();


    }

    @Override
    public void onAdClick() {
        Log.d(TAG, "onAdClick: ");
    }

    @Override
    public void onAdFailed() {

        Log.d(TAG, "onAdFailed: ");
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            new AnimIntent.Builder(TvMainActivity.this).performSlideToRight();
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    // Creates notification channel.
    private void createChannel() {
        // Notification channel should only be created for devices running Android API level 26+.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(TvMainActivity.this.NOTIFICATION_SERVICE);
            NotificationChannel chan1 = new NotificationChannel(
                    "default",
                    "default",
                    NotificationManager.IMPORTANCE_NONE);
            chan1.setLightColor(Color.TRANSPARENT);
            chan1.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            notificationManager.createNotificationChannel(chan1);
        }
    }


    private void sendFcmRegistrationToken() {
        Intent intent = new Intent(this, FcmTokenRegistrationService.class);
        startService(intent);
        FirebaseMessaging.getInstance().subscribeToTopic("glob")
                .addOnCompleteListener(task -> {
                    String msg = "sfdggfdgsfg";
                    if (!task.isSuccessful()) {
                        msg = "faild";
                    }
                    Log.d(TAG, msg);

                });

    }


    public void showDialog(Activity activity, String url) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.home_dilog_web_view);
        TextView closeId = dialog.findViewById(R.id.closeId);


        mWebView = new HTML5WebView(activity);
        webViewEm = dialog.findViewById(R.id.webViewEm);


        mWebView.loadUrl(url);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
//        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.OFF);
        mWebView.getSettings().setAllowFileAccess(true);
        webViewEm.removeView(mWebView.getLayout());
        webViewEm.addView(mWebView.getLayout());
        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }
        });

        closeId.setOnClickListener(v -> dialog.dismiss());



        dialog.show();

    }
}
