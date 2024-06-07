package com.app.dbug.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.app.dbug.R;
import com.app.dbug.adapter.AdapterChannelList;

import com.app.dbug.ads.SessionAds;
import com.app.dbug.model.Channel;
import com.app.dbug.utils.RtlUtils;


import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerListener;
import com.appnext.banners.BannerSize;
import com.appnext.banners.BannerView;
import com.appnext.core.AppnextAdCreativeType;
import com.appnext.core.AppnextError;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.UnityBannerSize;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;

import static com.app.dbug.activity.SplashActivity.ADSKEY;
import static com.app.dbug.activity.SplashActivity.APPNEX_INTER;
import static com.app.dbug.activity.SplashActivity.FBBANNER_ID;
import static com.app.dbug.activity.SplashActivity.FBNATIVE_ID;
import static com.app.dbug.activity.SplashActivity.GBANNER_ID;
import static com.app.dbug.activity.SplashActivity.GET_ADD_COUNT_NATIVE;
import static com.app.dbug.activity.SplashActivity.GNATIVE_ID;
import static com.app.dbug.activity.SplashActivity.MYPREFERENCE;
import static com.app.dbug.activity.SplashActivity.UNITY_APP_ID_GAME_ID;

public class RecentViewActivity extends AppCompatActivity {

    ArrayList<Channel.Datum> channelList;
    com.facebook.ads.AdView fadView;
    String fbBanar;
    String gBanar;
    AdLoader adLoader;
    RecyclerView myListRv;
    AdapterChannelList adapterChannelList;
    List<Object> modelBase = new ArrayList<>();
    List<UnifiedNativeAd> modelAds = new ArrayList<>();
    List<NativeAd> nativeAd = new ArrayList<>();
    NativeAdsManager fbNativeManager;
    String fbNativeAdId;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ShimmerFrameLayout shimmerViewContainer;
    RelativeLayout mainLayoutRecentViewActivity;
    boolean isDark;
    int nativeAdsCount;

    RelativeLayout mainLayoytAllReV;
    RelativeLayout backArrow;
    SwipeRefreshLayout swiperefresh;
    LinearLayout brokenHartLayout;
    RelativeLayout toolbarId;
    int spanCout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        nativeAdsCount = sharedPreferences.getInt(GET_ADD_COUNT_NATIVE, 3);
        spanCout = sharedPreferences.getInt("spanCout", 3);

        if (isDark) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppThemeLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_view);
        RtlUtils.setScreenDirection(this);

        mainLayoytAllReV = findViewById(R.id.main_layoyt_all_reV);
        backArrow = findViewById(R.id.back_button);
        swiperefresh = findViewById(R.id.swiperefresh);
        brokenHartLayout = findViewById(R.id.broken_hart_layout);
        toolbarId = findViewById(R.id.toolbarId);

        backArrow.setOnClickListener(v -> {
            finish();
            new AnimIntent.Builder(v.getContext()).performSlideToRight();
        });

        swiperefresh.setOnRefreshListener(() -> {
            swiperefresh.setRefreshing(true);
            shimmerViewContainer.setVisibility(View.VISIBLE);
            shimmerViewContainer.startShimmer();
            mainLayoutRecentViewActivity.setVisibility(View.GONE);
            if (!modelBase.isEmpty()) {
                modelBase.clear();
            }


            loadData();

        });


        fbNativeAdId = sharedPreferences.getString(FBNATIVE_ID, "");
        fbNativeManager = new NativeAdsManager(RecentViewActivity.this, fbNativeAdId, 3);
        sharedPreferences = getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();


        myListRv = findViewById(R.id.allRecent);
        shimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mainLayoutRecentViewActivity = findViewById(R.id.main_layout_recent_view_activity);
        shimmerViewContainer.startShimmer();


        loadData();




        if (sharedPreferences.getString(ADSKEY, "1").equals("1")){
            gBanar = sharedPreferences.getString(GBANNER_ID, "");
            FrameLayout adContainer =findViewById(R.id.adView);
            adContainer.removeAllViews();
            com.google.android.gms.ads.AdView adView = new com.google.android.gms.ads.AdView(this);
            adView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
            adView.setAdUnitId(gBanar);
            com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adContainer.addView(adView);

        }else if (sharedPreferences.getString(ADSKEY, "2").equals("2")){
            fbBanar = sharedPreferences.getString(FBBANNER_ID, "");
            FrameLayout adContainer = (FrameLayout) findViewById(R.id.adView);
            adContainer.removeAllViews();
            fadView = new com.facebook.ads.AdView(this,fbBanar, AdSize.BANNER_HEIGHT_50);
            adContainer.addView(fadView);
            fadView.loadAd();
        }else if (sharedPreferences.getString(ADSKEY, "3").equals("3")){

            FrameLayout adContainer = (FrameLayout) findViewById(R.id.adView);
            adContainer.removeAllViews();
            BannerView banner = new BannerView(this);
            banner.setPlacementId(sharedPreferences.getString(APPNEX_INTER, ""));
            banner.setBannerSize(BannerSize.BANNER);
            banner.loadAd(new BannerAdRequest());

            adContainer.addView(banner);

        }else if (sharedPreferences.getString(ADSKEY, "5").equals("5")){

            String unityGameID = sharedPreferences.getString(UNITY_APP_ID_GAME_ID, "");
            String adUnitId = "Banner_Android";
            SessionAds.UnityBannerListener bannerListener = new SessionAds.UnityBannerListener();
            SessionAds.UnityAdsListener myAdsListener = new SessionAds.UnityAdsListener();
            UnityAds.initialize(this, unityGameID, true, myAdsListener);
            com.unity3d.services.banners.BannerView topBanner = new com.unity3d.services.banners.BannerView(RecentViewActivity.this, adUnitId, new UnityBannerSize(320, 50));
            topBanner.setListener(bannerListener);
            topBanner.load();
            FrameLayout adContainer = (FrameLayout) findViewById(R.id.adView);
            adContainer.addView(topBanner);
        }

    }


    public void loadData() {

        Log.d("sdfdsfdsf", "loadData: ");
        sharedPreferences = getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("recent", null);
        Type type = new TypeToken<ArrayList<Channel.Datum>>() {
        }.getType();
        channelList = gson.fromJson(json, type);
        if (channelList == null) {
            channelList = new ArrayList<>();
        } else {
            if (!channelList.isEmpty()) {
                brokenHartLayout.setVisibility(View.GONE);
            }
            for (int i = 0; i < channelList.size(); i++) {
                modelBase.add(channelList.get(i));
                swiperefresh.setRefreshing(false);
            }

            adapterChannelList = new AdapterChannelList(RecentViewActivity.this, modelBase);
            myListRv.setHasFixedSize(true);
            myListRv.setNestedScrollingEnabled(false);

            if (spanCout == 1){
                spanCout = 2;
                nativeAdsCount = 4;
            }

            GridLayoutManager gridLayoutManager1 = new GridLayoutManager(RecentViewActivity.this, spanCout);
            gridLayoutManager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    switch (adapterChannelList.getItemViewType(position)) {
                        case 1:
                            return spanCout;
                        case 0:
                            return 1;
                        default:
                            return -1;
                    }
                }
            });
            myListRv.setLayoutManager(gridLayoutManager1);
            myListRv.setAdapter(adapterChannelList);
            swiperefresh.setRefreshing(false);
            shimmerViewContainer.setVisibility(View.GONE);
            shimmerViewContainer.stopShimmer();
            mainLayoutRecentViewActivity.setVisibility(View.VISIBLE);

            int totalLoadNativeAds =Math.round(modelBase.size()/nativeAdsCount)  ;

            loadNativAds(totalLoadNativeAds);


        }


    }

    public void loadNativAds(int count){
        if (sharedPreferences.getString(ADSKEY, "1").equals("3")) {
            if (sharedPreferences.getString("currentNativeAds", "1").equals("1")) {
                admobloadNativeAds(count);
            } else {
                initNativeAds(count);
            }

        } else if (sharedPreferences.getString(ADSKEY, "1").equals("1")) {

            admobloadNativeAds(count);
        } else if (sharedPreferences.getString(ADSKEY, "2").equals("2")) {

            initNativeAds(count);
        }
    }
    private void insertAdsInMenuItems(int nativeAdsCount11) {
        int index;
        if (!modelAds.isEmpty()) {

            index = nativeAdsCount;
            int offset = nativeAdsCount + 1;


            for (int i =0 ; i < nativeAdsCount11 ; i++){
                if (modelBase.size() < index) {
                    Log.d("TAG", "insertAdsInMenuItems: ");
                } else {
                    modelBase.add(index, modelAds.get(0));
                    index = index + offset;
                }

            }
            adapterChannelList.notifyDataSetChanged();
        } else if (!nativeAd.isEmpty()) {
            index = nativeAdsCount;
            int offset1 = nativeAdsCount + 1;

            for (int i =0 ; i < nativeAdsCount11 ; i++){
                if (!modelBase.isEmpty()) {
                    if (modelBase.size() < index) {
                        Log.d("TAG", "insertAdsInMenuItems: ");
                    } else {
                        modelBase.add(index, nativeAd.get(0));
                        index = index + offset1;
                    }
                }

            }

            adapterChannelList.notifyDataSetChanged();
        }
    }

    private void admobloadNativeAds(int count) {
        AdLoader.Builder builder = new AdLoader.Builder(this, sharedPreferences.getString(GNATIVE_ID, ""));
        adLoader = builder.forUnifiedNativeAd(
                unifiedNativeAd -> {
                    modelAds.add(unifiedNativeAd);
                    if (!adLoader.isLoading()) {

                        insertAdsInMenuItems(count);
                    }
                }).build();
        adLoader.loadAds(new AdRequest.Builder().build(), 2);


    }

    public void initNativeAds(int count) {
        fbNativeManager.setListener(new NativeAdsManager.Listener() {
            @Override
            public void onAdsLoaded() {
                for (int i = 0; i < 2; i++) {
                    NativeAd ad = fbNativeManager.nextNativeAd();
                    nativeAd.add(ad);

                }
                insertAdsInMenuItems(count);
            }

            @Override
            public void onAdError(AdError adError) {

                Log.d("kdslsdk", "onAdError: " + adError.getErrorMessage());
            }
        });
        fbNativeManager.loadAds();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AnimIntent.Builder(RecentViewActivity.this).performSlideToRight();


    }
}