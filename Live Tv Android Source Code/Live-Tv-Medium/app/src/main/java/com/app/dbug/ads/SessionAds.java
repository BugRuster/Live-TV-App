package com.app.dbug.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;

import com.app.dbug.activity.SplashActivity;
import com.appnext.ads.AdsError;
import com.appnext.ads.interstitial.Interstitial;
import com.appnext.base.Appnext;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.LoadAdError;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorInfo;

import static com.app.dbug.activity.SplashActivity.APPNEX_INTER;
import static com.app.dbug.activity.SplashActivity.STARTAPP_APP_ID;
import static com.app.dbug.activity.SplashActivity.UNITY_APP_ID_GAME_ID;


public class SessionAds {

    Context context;
    InterstitialAd interstitialAd;
    com.facebook.ads.InterstitialAd interstitialAdFB;
    SharedPreferences sharedpreferences;

    InterAdClickInterFace interAdClickInterFace;
    SharedPreferences.Editor editor;
    Interstitial interstitialAd1;
    static String interstitialAndroid = "Interstitial_Android";

    public SessionAds(Context context) {
        this.context = context;

        this.sharedpreferences = context.getSharedPreferences(SplashActivity.MYPREFERENCE, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        load();
    }

    public SessionAds(Context context, InterAdClickInterFace interAdClickInterFace) {
        this.context = context;
        this.interAdClickInterFace = interAdClickInterFace;
        this.sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.sharedpreferences = context.getSharedPreferences(SplashActivity.MYPREFERENCE, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        load();
    }

    public void load() {

        Log.d("dskfjs", "load: ");
        if (sharedpreferences.getString(SplashActivity.ADSKEY, "").equals("1")) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(context, sharedpreferences.getString(SplashActivity.GINTERS_ID, ""), adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd1) {
                            interstitialAd = interstitialAd1;
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            interstitialAd = null;
                        }
                    });


        } else if (sharedpreferences.getString(SplashActivity.ADSKEY, "").equals("2")) {
            interstitialAdFB = new com.facebook.ads.InterstitialAd(context, sharedpreferences.getString(SplashActivity.FBINTERS_ID, ""));
            interstitialAdFB.loadAd();

        } else if (sharedpreferences.getString(SplashActivity.ADSKEY, "").equals("4")) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(context, sharedpreferences.getString(SplashActivity.GINTERS_ID, ""), adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd1) {
                            interstitialAd = interstitialAd1;
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            interstitialAd = null;
                        }
                    });

            interstitialAdFB = new com.facebook.ads.InterstitialAd(context, sharedpreferences.getString(SplashActivity.FBINTERS_ID, ""));
            interstitialAdFB.loadAd();
        }
//        UnityAds.initialize(context, sharedpreferences.getString(APPNEX_BANNER, ""), false);
        UnityAdsListener myAdsListener = new UnityAdsListener();
        UnityAds.initialize(context, sharedpreferences.getString(UNITY_APP_ID_GAME_ID, ""), true, myAdsListener);
//        UnityAds.addListener(myAdsListener);
        StartAppSDK.init(context, sharedpreferences.getString(STARTAPP_APP_ID, ""), false);
        StartAppAd.disableSplash();

        Appnext.init(context);
        interstitialAd1 = new Interstitial(context, sharedpreferences.getString(APPNEX_INTER, ""));
        interstitialAd1.loadAd();

    }

    public void showInter() {
        int intervalAds = sharedpreferences.getInt("adddd", 1);

        if (intervalAds >= sharedpreferences.getInt(SplashActivity.ADD_COUNT, 1)) {
            int addInt = 1;
            editor.putInt("adddd", addInt);
            editor.apply();

        } else {
            int addInt = intervalAds + 1;
            editor.putInt("adddd", addInt);
            editor.apply();
        }

        show();
    }

    public void show() {
        if (sharedpreferences.getString(SplashActivity.ADSKEY, "").equals("1")) {
            if (interstitialAd != null) {
                interstitialAd.show((Activity) context);
            } else {
            }
            load();
        } else if (sharedpreferences.getString(SplashActivity.ADSKEY, "").equals("2")) {
            interstitialAdFB.loadAd(interstitialAdFB.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                    Log.d("dgf", "ADS DISPLAYED");

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    Log.d("dfg", "ADS CLOSED");

                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    Log.d("sdf", adError.getErrorMessage());


                }

                @Override
                public void onAdLoaded(Ad ad) {
                    Log.d("sdf", "ADS LOADED");

                }

                @Override
                public void onAdClicked(Ad ad) {
                    Log.d("fgfd", "ADS CLICKED");

                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    Log.d("TAG", "onLoggingImpression: ");
                }
            }).build());
            if (interstitialAdFB.isAdLoaded()) {
                interstitialAdFB.show();
            }
            load();
        } else if (sharedpreferences.getString(SplashActivity.ADSKEY, "").equals("3")) {

            appnext();

        } else if (sharedpreferences.getString(SplashActivity.ADSKEY, "").equals("4")) {

            startapp();
        } else if (sharedpreferences.getString(SplashActivity.ADSKEY, "").equals("5")) {

            showUnityAds();
        }
    }


    public void showUnityAds() {
        UnityAds.show((Activity) context, interstitialAndroid);
    }


    public void appnext() {
        interstitialAd1.setOnAdClosedCallback(() -> Log.d("lkjflkfjkd", "onAdClosed: "));
        interstitialAd1.showAd();

    }

    public static class UnityAdsListener implements IUnityAdsInitializationListener {

        @Override
        public void onInitializationComplete() {
            UnityAds.load(interstitialAndroid);
        }

        @Override
        public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {

        }
    }


    public static class UnityBannerListener implements com.unity3d.services.banners.BannerView.IListener {
        @Override
        public void onBannerLoaded(com.unity3d.services.banners.BannerView bannerAdView) {
            //Empty
        }

        @Override
        public void onBannerClick(com.unity3d.services.banners.BannerView bannerAdView) {
            //Empty
        }

        @Override
        public void onBannerFailedToLoad(com.unity3d.services.banners.BannerView bannerAdView, BannerErrorInfo errorInfo) {
            //Empty
        }

        @Override
        public void onBannerLeftApplication(com.unity3d.services.banners.BannerView bannerView) {
            //Empty
        }
    }

    public void startapp() {
        StartAppAd.showAd(context);
    }
}
