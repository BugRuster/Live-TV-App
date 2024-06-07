package com.app.dbug.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.dbug.R;
import com.app.dbug.ads.SessionAds;
import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerSize;
import com.appnext.banners.BannerView;
import com.app.dbug.api.ApiInter;

import com.app.dbug.model.Channel;
import com.app.dbug.adapter.AdapterChannelList;
import com.app.dbug.retofit.RetrofitClient;

import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.UnityBannerSize;

import java.util.ArrayList;
import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.dbug.activity.SplashActivity.ADSKEY;
import static com.app.dbug.activity.SplashActivity.APPNEX_INTER;
import static com.app.dbug.activity.SplashActivity.FBBANNER_ID;
import static com.app.dbug.activity.SplashActivity.FBNATIVE_ID;
import static com.app.dbug.activity.SplashActivity.GBANNER_ID;
import static com.app.dbug.activity.SplashActivity.GET_ADD_COUNT_NATIVE;
import static com.app.dbug.activity.SplashActivity.GNATIVE_ID;
import static com.app.dbug.activity.SplashActivity.MYPREFERENCE;
import static com.app.dbug.activity.SplashActivity.UNITY_APP_ID_GAME_ID;

public class TvInCategoryActivity extends AppCompatActivity {

    String cid;
    String cName;

    GridLayoutManager gridLayoutManager;
    AdapterChannelList channelAdapter;
    RecyclerView allChannelInCategory;
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
    TextView categoryID;
    RelativeLayout backArrow;
    SwipeRefreshLayout swiperefresh;
    LinearLayout brokenHartLayout;
    RelativeLayout toolbardId;
    RelativeLayout mainLayoutHome;
    int spanCout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        isDark = sharedPreferences.getBoolean("dark", false);
        nativeAdsCount = sharedPreferences.getInt(GET_ADD_COUNT_NATIVE, 3);
        spanCout = sharedPreferences.getInt("spanCout", 1);
        if (isDark) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppThemeLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_in_category);
        Intent callingIntent = getIntent();
        cid = callingIntent.getStringExtra("cid");
        cName = callingIntent.getStringExtra("cName");
        if (cid.isEmpty()) {
            return;
        }

        allChannelInCategory = findViewById(R.id.allChannelInCategory);
        categoryID = findViewById(R.id.categoryID);
        backArrow = findViewById(R.id.back_button);
        swiperefresh = findViewById(R.id.swiperefresh);
        brokenHartLayout = findViewById(R.id.broken_hart_layout);
        myListRv = findViewById(R.id.allChannelInCategory);
        mainLayoutHome = findViewById(R.id.mainLayoutHome);
        toolbardId = findViewById(R.id.toolbarId);
        fbNativeAdId = sharedPreferences.getString(FBNATIVE_ID, "");
        fbNativeManager = new NativeAdsManager(TvInCategoryActivity.this, fbNativeAdId, 3);

        categoryID.setText(cName);
        backArrow.setOnClickListener(v -> {
            finish();
            new AnimIntent.Builder(v.getContext()).performSlideToRight();
        });
        swiperefresh.setOnRefreshListener(() -> {
            swiperefresh.setRefreshing(true);
            if (!channelListO.isEmpty()) {
                channelListO.clear();
            }
            dataLoad();
        });

        adapterChannelAds = new AdapterChannelList(TvInCategoryActivity.this, channelListO);

        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        apiInter.getChannelInCategory(cid)
                .enqueue(new Callback<Channel>() {
                    @Override
                    public void onResponse(Call<Channel> call, Response<Channel> response) {
                        if (response.isSuccessful()) {
                            Channel channel = response.body();
                            List<Channel.Datum> post = channel.data;
                            for (int i = 0; i < post.size(); i++) {
                                channelListO.add(post.get(i));
                            }

                            brokenHartLayout.setVisibility(View.GONE);
                            adapterChannelAds = new AdapterChannelList(TvInCategoryActivity.this, channelListO);
                            myListRv.setHasFixedSize(true);
                            myListRv.setNestedScrollingEnabled(false);


                            if (spanCout == 1) {
                                spanCout = 2;
                                nativeAdsCount = 4;
                            }
                            GridLayoutManager gridLayoutManager1 = new GridLayoutManager(TvInCategoryActivity.this, spanCout);

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

                            int totalLoadNativeAds = Math.round(channelListO.size() / nativeAdsCount);
                            loadNativAds(totalLoadNativeAds);
                        }
                    }

                    @Override
                    public void onFailure(Call<Channel> call, Throwable t) {

                        Log.d("MainActivityLog", "onFailure: " + t.getLocalizedMessage());
                    }
                });


        if (sharedPreferences.getString(ADSKEY, "1").equals("1")) {
            gBanar = sharedPreferences.getString(GBANNER_ID, "");
            FrameLayout adContainer = findViewById(R.id.adView);
            adContainer.removeAllViews();
            com.google.android.gms.ads.AdView adView = new com.google.android.gms.ads.AdView(this);
            adView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
            adView.setAdUnitId(gBanar);
            com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adContainer.addView(adView);

        } else if (sharedPreferences.getString(ADSKEY, "2").equals("2")) {
            fbBanar = sharedPreferences.getString(FBBANNER_ID, "");
            FrameLayout adContainer = (FrameLayout) findViewById(R.id.adView);
            adContainer.removeAllViews();
            fadView = new com.facebook.ads.AdView(this, fbBanar, AdSize.BANNER_HEIGHT_50);
            fadView.loadAd();
            adContainer.addView(fadView);
        } else if (sharedPreferences.getString(ADSKEY, "3").equals("3")) {
            FrameLayout adContainer = findViewById(R.id.adView);
            adContainer.removeAllViews();
            BannerView banner = new BannerView(this);
            banner.setPlacementId(sharedPreferences.getString(APPNEX_INTER, ""));
            banner.setBannerSize(BannerSize.BANNER);
            banner.loadAd(new BannerAdRequest());
            adContainer.addView(banner);

        } else if (sharedPreferences.getString(ADSKEY, "5").equals("5")) {
            String unityGameID = sharedPreferences.getString(UNITY_APP_ID_GAME_ID, "");
            String adUnitId = "Banner_Android";
            SessionAds.UnityBannerListener bannerListener = new SessionAds.UnityBannerListener();
            SessionAds.UnityAdsListener myAdsListener = new SessionAds.UnityAdsListener();
            UnityAds.initialize(this, unityGameID, true, myAdsListener);
            com.unity3d.services.banners.BannerView topBanner = new com.unity3d.services.banners.BannerView(TvInCategoryActivity.this, adUnitId, new UnityBannerSize(320, 50));
            topBanner.setListener(bannerListener);
            topBanner.load();
            FrameLayout adContainer = findViewById(R.id.adView);
            adContainer.addView(topBanner);
        }

    }


    private void dataLoad() {
        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        apiInter.getChannelInCategory(cid)
                .enqueue(new Callback<Channel>() {
                    @Override
                    public void onResponse(Call<Channel> call, Response<Channel> response) {
                        if (response.isSuccessful()) {
                            Channel channel = response.body();
                            List<Channel.Datum> post = channel.data;

                            for (int i = 0; i < post.size(); i++) {
                                channelListO.add(post.get(i));
                            }


                            itemRecyclerview();
                            int totalLoadNativeAds = Math.round(channelListO.size() / nativeAdsCount);
                            insertAdsInMenuItems(totalLoadNativeAds);

                        }

                    }

                    @Override
                    public void onFailure(Call<Channel> call, Throwable t) {

                        Log.d("MainActivityLog", "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }


    private void itemRecyclerview() {
        channelAdapter = new AdapterChannelList(TvInCategoryActivity.this, channelListO);
        gridLayoutManager = new GridLayoutManager(TvInCategoryActivity.this, spanCout);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
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
        allChannelInCategory.setLayoutManager(gridLayoutManager);
        allChannelInCategory.setAdapter(channelAdapter);
        swiperefresh.setRefreshing(false);
        if (!channelListO.isEmpty()) {
            brokenHartLayout.setVisibility(View.GONE);
        }

    }

    public void loadNativAds(int count) {
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

            for (int i = 0; i < nativeAdsCount11; i++) {
                if (channelListO.size() < index) {
                    Log.d("TAG", "insertAdsInMenuItems: ");
                } else {
                    channelListO.add(index, modelAds.get(0));
                    index = index + offset;
                }

            }
            adapterChannelAds.notifyDataSetChanged();
        } else if (!nativeAd.isEmpty()) {
            index = nativeAdsCount;
            int offset1 = nativeAdsCount + 1;

            for (int i = 0; i < nativeAdsCount11; i++) {
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
        new AnimIntent.Builder(TvInCategoryActivity.this).performSlideToRight();


    }
}