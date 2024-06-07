package com.app.dbug.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.dbug.R;
import com.app.dbug.adapter.AllCategoryAdapter;
import com.app.dbug.ads.SessionAds;
import com.app.dbug.api.ApiInter;

import com.app.dbug.model.Category;
import com.app.dbug.retofit.RetrofitClient;
import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerListener;
import com.appnext.banners.BannerSize;
import com.appnext.banners.BannerView;
import com.appnext.core.AppnextAdCreativeType;
import com.appnext.core.AppnextError;
import com.facebook.ads.AdSize;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdView;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.UnityBannerSize;

import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.dbug.activity.SplashActivity.ADSKEY;
import static com.app.dbug.activity.SplashActivity.APPNEX_INTER;
import static com.app.dbug.activity.SplashActivity.MYPREFERENCE;
import static com.app.dbug.activity.SplashActivity.UNITY_APP_ID_GAME_ID;

public class AllCategoryActivity extends AppCompatActivity {


    AllCategoryAdapter categoryAdapter;
    com.facebook.ads.AdView fadView;
    String fbBanar;
    String gBanar;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ShimmerFrameLayout shimmerViewContainer;
    RelativeLayout mainLayoutAllCategotyAcrivity;
    RelativeLayout mainLayoutAll;
    boolean isDark;

    SwipeRefreshLayout swiperefresh;
    RelativeLayout backArrow;
    RecyclerView allCategory;
    LinearLayout brokenHartLayout;
    RelativeLayout toolbarId;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        if (isDark) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppThemeLight);
        }
        setContentView(R.layout.activity_all_category);
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        sharedPreferences = getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        swiperefresh = findViewById(R.id.swiperefresh);
        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        sharedPreferences = getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        shimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mainLayoutAllCategotyAcrivity = findViewById(R.id.main_layout_all_categoty_acrivity);
        mainLayoutAll = findViewById(R.id.main_layout_all);
        backArrow = findViewById(R.id.back_button);
        allCategory = findViewById(R.id.allCategory);
        brokenHartLayout = findViewById(R.id.broken_hart_layout);
        toolbarId = findViewById(R.id.toolbarId);



        shimmerViewContainer.startShimmer();

        swiperefresh.setOnRefreshListener(() -> {
            swiperefresh.setRefreshing(true);
            shimmerViewContainer.setVisibility(View.VISIBLE);
            shimmerViewContainer.startShimmer();
            mainLayoutAllCategotyAcrivity.setVisibility(View.GONE);

            apiInter.getCategoryBody()
                    .enqueue(new Callback<Category>() {
                        @Override
                        public void onResponse(Call<Category> call, Response<Category> response) {
                            if (response.isSuccessful()) {
                                Category category = response.body();
                                List<Category.Datum> cat = category.data;
                                setCategoryRecyclerView(cat);

                            }
                        }

                        @Override
                        public void onFailure(Call<Category> call, Throwable t) {
                            Log.d("sd", "onFailure: " + t.getMessage());
                        }
                    });
        });

        apiInter.getCategoryBody()
                .enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response) {
                        if (response.isSuccessful()) {
                            Category category = response.body();
                            List<Category.Datum> cat = category.data;
                            setCategoryRecyclerView(cat);

                        }
                    }

                    @Override
                    public void onFailure(Call<Category> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                    }
                });


        backArrow.setOnClickListener(v -> {
            finish();
            new AnimIntent.Builder(v.getContext()).performSlideToRight();
        });


        if (sharedPreferences.getString(SplashActivity.ADSKEY, "1").equals("1")) {
            gBanar = sharedPreferences.getString(SplashActivity.GBANNER_ID, "");
            FrameLayout adContainer = findViewById(R.id.adView);
            adContainer.removeAllViews();
            com.google.android.gms.ads.AdView adView = new com.google.android.gms.ads.AdView(this);
            adView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
            adView.setAdUnitId(gBanar);
            com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adContainer.addView(adView);

        } else if (sharedPreferences.getString(SplashActivity.ADSKEY, "2").equals("2")) {
            fbBanar = sharedPreferences.getString(SplashActivity.FBBANNER_ID, "");
            FrameLayout adContainer = (FrameLayout) findViewById(R.id.adView);
            adContainer.removeAllViews();
            fadView = new com.facebook.ads.AdView(this, fbBanar, AdSize.BANNER_HEIGHT_50);
            adContainer.addView(fadView);
            fadView.loadAd();
        } else if (sharedPreferences.getString(ADSKEY, "3").equals("3")) {
            FrameLayout adContainer = (FrameLayout) findViewById(R.id.adView);
            adContainer.removeAllViews();
            BannerView banner = new BannerView(this);
            banner.setPlacementId(sharedPreferences.getString(APPNEX_INTER, ""));
            banner.setBannerSize(BannerSize.BANNER);
            banner.loadAd(new BannerAdRequest());
            Log.d("skdjkdjf", "onCreate: " + banner.isInLayout());
            adContainer.addView(banner);


        } else if (sharedPreferences.getString(ADSKEY, "5").equals("5")) {
            String unityGameID = sharedPreferences.getString(UNITY_APP_ID_GAME_ID, "");
            String adUnitId = "Banner_Android";
            SessionAds.UnityBannerListener bannerListener = new SessionAds.UnityBannerListener();
            SessionAds.UnityAdsListener myAdsListener = new SessionAds.UnityAdsListener();
            UnityAds.initialize(this, unityGameID, true, myAdsListener);
            com.unity3d.services.banners.BannerView topBanner = new com.unity3d.services.banners.BannerView(AllCategoryActivity.this, adUnitId, new UnityBannerSize(320, 50));
            topBanner.setListener(bannerListener);
            topBanner.load();
            FrameLayout adContainer = (FrameLayout) findViewById(R.id.adView);
            adContainer.addView(topBanner);
        }

    }

    public void setCategoryRecyclerView(List<Category.Datum> categoryList) {
        categoryAdapter = new AllCategoryAdapter(AllCategoryActivity.this, categoryList);
        int spanCout = sharedPreferences.getInt("spanCout", 3);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(AllCategoryActivity.this, spanCout);

        allCategory.setLayoutManager(gridLayoutManager1);
        allCategory.setAdapter(categoryAdapter);

        swiperefresh.setRefreshing(false);
        if (!categoryList.isEmpty()) {
            brokenHartLayout.setVisibility(View.GONE);
        }
        shimmerViewContainer.setVisibility(View.GONE);
        shimmerViewContainer.stopShimmer();
        mainLayoutAllCategotyAcrivity.setVisibility(View.VISIBLE);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AnimIntent.Builder(AllCategoryActivity.this).performSlideToRight();


    }
}