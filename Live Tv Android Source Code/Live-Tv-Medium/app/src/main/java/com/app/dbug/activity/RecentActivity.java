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


import com.app.dbug.ads.SessionAds;
import com.app.dbug.api.ApiInter;

import com.app.dbug.model.Channel;
import com.app.dbug.adapter.AdapterChannelList;
import com.app.dbug.retofit.RetrofitClient;

import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerSize;
import com.appnext.banners.BannerView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.UnityBannerSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.dbug.activity.SplashActivity.ADSKEY;
import static com.app.dbug.activity.SplashActivity.APPNEX_INTER;
import static com.app.dbug.activity.SplashActivity.FBBANNER_ID;
import static com.app.dbug.activity.SplashActivity.GBANNER_ID;
import static com.app.dbug.activity.SplashActivity.GNATIVE_ID;
import static com.app.dbug.activity.SplashActivity.UNITY_APP_ID_GAME_ID;

public class RecentActivity extends AppCompatActivity {



    com.facebook.ads.AdView fadView;
    String fbBanar;
    String gBanar;

    AdLoader adLoader;
    RecyclerView myListRv;
    AdapterChannelList adapterChannelAds;
    List<Object> channelListO = new ArrayList<>();
    List<UnifiedNativeAd> modelAds = new ArrayList<>();
    List<NativeAd> nativeAd = new ArrayList<>();
    NativeAdsManager fbNativeManager;
    String fbNativeAdId;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean isDark;
    int nativeAdsCount;

    RelativeLayout mainContan,backArrow;
    ShimmerFrameLayout shimmerViewContainer;
    SwipeRefreshLayout swiperefresh;
    LinearLayout brokenHartLayout;

    int spanCout;
    RelativeLayout toolbarId;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        spanCout = sharedPreferences.getInt("spanCout", 1);
        nativeAdsCount = sharedPreferences.getInt(SplashActivity.GET_ADD_COUNT_NATIVE, 3);

        if (isDark) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppThemeLight);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        mainContan = findViewById(R.id.main_contan);
        shimmerViewContainer = findViewById(R.id.shimmer_view_container);
        backArrow = findViewById(R.id.back_button);
        swiperefresh = findViewById(R.id.swiperefresh);
        brokenHartLayout = findViewById(R.id.broken_hart_layout);
        toolbarId = findViewById(R.id.toolbarId);



        shimmerViewContainer.startShimmer();
        backArrow.setOnClickListener(v -> {
            finish();
            new AnimIntent.Builder(v.getContext()).performSlideToRight();
        });

        swiperefresh.setOnRefreshListener(() -> {
            mainContan.setVisibility(View.GONE);
            shimmerViewContainer.setVisibility(View.VISIBLE);
            shimmerViewContainer.startShimmer();
            swiperefresh.setRefreshing(false);

            loadData(true);


        });

        fbNativeAdId = sharedPreferences.getString(SplashActivity.FBNATIVE_ID, "");
        fbNativeManager = new NativeAdsManager(RecentActivity.this, fbNativeAdId, 3);
        sharedPreferences = getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        myListRv = findViewById(R.id.allRecent);

        loadData(false);


        if (sharedPreferences.getString(ADSKEY, "1").equals("1")){
            gBanar = sharedPreferences.getString(GBANNER_ID, "");
            FrameLayout adContainer = findViewById(R.id.adView);
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
            fadView.loadAd();
            adContainer.addView(fadView);
        }else if (sharedPreferences.getString(ADSKEY, "3").equals("3")){
            FrameLayout adContainer =findViewById(R.id.adView);
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
            com.unity3d.services.banners.BannerView topBanner = new com.unity3d.services.banners.BannerView(RecentActivity.this, adUnitId, new UnityBannerSize(320, 50));
            topBanner.setListener(bannerListener);
            topBanner.load();
            FrameLayout adContainer =findViewById(R.id.adView);
            adContainer.addView(topBanner);
        }

    }




    public void loadData(boolean isreload){

        if (!channelListO.isEmpty()) {
            channelListO.clear();
            if (!modelAds.isEmpty()) {
                modelAds.clear();
            } else if (!nativeAd.isEmpty()) {
                nativeAd.clear();
            }
        }
        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        apiInter.getChannelBody()
                .enqueue(new Callback<Channel>() {
                    @Override
                    public void onResponse(Call<Channel> call, Response<Channel> response) {
                        if (response.isSuccessful()) {
                            Channel channel = response.body();

                            List<Channel.Datum> post = channel.data;

                            for (int i = 0; i < post.size(); i++) {
                                if (post.get(i).category_type == 1) {
                                    channelListO.add(post.get(i));
                                }
                            }
                            Collections.reverse(channelListO);
                            setRecyclerview(isreload);


                        }
                    }

                    @Override
                    public void onFailure(Call<Channel> call, Throwable t) {

                        Log.d("MainActivityLog", t.getLocalizedMessage());
                    }
                });

    }


    public void setRecyclerview(boolean isreload){

        Log.d("sdfsdfds", "setRecyclerview: ");
        brokenHartLayout.setVisibility(View.GONE);
        adapterChannelAds = new AdapterChannelList(RecentActivity.this, channelListO);
        myListRv.setHasFixedSize(true);
        myListRv.setNestedScrollingEnabled(false);
        if (spanCout == 1){
            spanCout = 2;
            nativeAdsCount = 4;
        }
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(RecentActivity.this, spanCout);
        gridLayoutManager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapterChannelAds.getItemViewType(position)) {
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
        myListRv.setAdapter(adapterChannelAds);
        shimmerViewContainer.setVisibility(View.GONE);
        shimmerViewContainer.stopShimmer();
        mainContan.setVisibility(View.VISIBLE);

        int totalLoadNativeAds =Math.round(channelListO.size()/nativeAdsCount);


        if (isreload){
            insertAdsInMenuItems(totalLoadNativeAds);
        }else {
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
                if (channelListO.size() < index) {
                //Empty method
                } else {
                    channelListO.add(index, modelAds.get(0));
                    index = index + offset;
                }

            }
            adapterChannelAds.notifyDataSetChanged();
        } else if (!nativeAd.isEmpty()) {
            index = nativeAdsCount;
            int offset1 = nativeAdsCount + 1;

            for (int i =0 ; i < nativeAdsCount11 ; i++){
                if (!channelListO.isEmpty()) {
                    if (channelListO.size() < index) {
                        Log.d("TAG", "insertAdsInMenuItems: ");
                    } else {
                        channelListO.add(index, nativeAd.get(0));
                        index = index + offset1;
                    }
                }

            }

            adapterChannelAds.notifyDataSetChanged();
        }
    }
    private void admobloadNativeAds(int count) {
        AdLoader.Builder builder = new AdLoader.Builder(this, sharedPreferences.getString(GNATIVE_ID, ""));
        adLoader = builder.forUnifiedNativeAd(
                unifiedNativeAd -> {
                    modelAds.add(unifiedNativeAd);
                    if (!adLoader.isLoading()) {
                        Log.d("sadasd", "admobloadNativeAds: ");
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
        new AnimIntent.Builder(RecentActivity.this).performSlideToRight();


    }
}