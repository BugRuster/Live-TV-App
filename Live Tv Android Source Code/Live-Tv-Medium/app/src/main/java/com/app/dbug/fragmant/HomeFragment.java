package com.app.dbug.fragmant;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.dbug.activity.RecentActivity;
import com.app.dbug.adapter.BasicPagerAdapter;
import com.app.dbug.adapter.BasicPagerAdapter1;
import com.app.dbug.adapter.SliderAdapter;
import com.app.dbug.adapter.SliderAdapter2;
import com.app.dbug.ads.SessionAds;
import com.codemybrainsout.ratingdialog.RatingDialog;
import com.app.dbug.R;
import com.app.dbug.activity.AllCategoryActivity;
import com.app.dbug.activity.FavoriteActivity;
import com.app.dbug.activity.RecentViewActivity;
import com.app.dbug.activity.SearchActivity;
import com.app.dbug.adapter.AdapterChannelList;
import com.app.dbug.adapter.CategoryAdapter;
import com.app.dbug.adapter.TvAdapterChannelHome;
import com.app.dbug.adapter.TvAdapterRecentHome;
import com.app.dbug.api.ApiInter;
import com.app.dbug.model.Category;
import com.app.dbug.model.Channel;
import com.app.dbug.model.Slider;
import com.app.dbug.model.SliderHome;
import com.app.dbug.retofit.RetrofitClient;
import com.cpacm.library.SimpleViewPager;
import com.cpacm.library.indicator.ViewpagerIndicator.LinePageIndicator;
import com.cpacm.library.transformers.CyclePageTransformer;
import com.cpacm.library.transformers.ZoomOutSlideTransformer;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.app.dbug.activity.SplashActivity.FBNATIVE_ID;
import static com.app.dbug.activity.SplashActivity.GET_ADD_COUNT_NATIVE;
import static com.app.dbug.activity.SplashActivity.MYPREFERENCE;
import static com.app.dbug.config.Constant.ADMIN_PANEL_URL;
import static com.app.dbug.config.Constant.IMAGE_URL;
import static com.smarteist.autoimageslider.SliderPager.DEFAULT_SCROLL_DURATION;

public class HomeFragment extends Fragment {

    Context context;
    ArrayList<Channel.Datum> channelList;
    ArrayList<Channel.Datum> recentChannelView;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    AdapterChannelList adapterStoreAds;
    List<Object> modelBase = new ArrayList<>();
    List<SliderHome> sliderItemList = new ArrayList<>();
    int nativeAdsCount;
    Timer timer;
    Handler handler = new Handler(Looper.getMainLooper());

    public HomeFragment() {
        // Required empty public constructor
    }

    public HomeFragment(Context context) {
        this.context = context;

    }


    RecyclerView categoryItemRecyclerview;
    RecyclerView favoriteItemRecyclerview;
    LinearLayout favoriteHomeLayout;
    SwipeRefreshLayout swiperefresh;
    CategoryAdapter categoryAdapter;

    TvAdapterChannelHome tvAdapterChannelHome;
    TvAdapterRecentHome tvAdapterRecentHome;
    TvAdapterChannelHome tvAdapterRecentAdd;
    LinearLayout mainMayoutHomeFrag;
    ImageView searchIconButton;


    LinearLayoutManager linearLayoutManager;

    List<UnifiedNativeAd> modelAds = new ArrayList<>();
    List<NativeAd> nativeAd = new ArrayList<>();
    NativeAdsManager fbNativeManager;
    String fbNativeAdId;
    TextView seeAllCategory;
    TextView seeFavorite;
    TextView seeRecent;
    TextView seeRecentAd;
    RecyclerView recentViewItemRecyclerview;
    RecyclerView recentAddedItemRecyclerview;
    RelativeLayout mainLayoutAll;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout recentClickHomeLayout;
    boolean isDark;
    SessionAds sessionAds;
    SimpleViewPager simpleSlider2;
//    LinePageIndicator linePageIndicator;
    RelativeLayout tolbarId;
    LinearLayout sliderDot;
    private int dotsCount = 0;
    private ImageView[] dots;

    SliderAdapter2 sliderAdapter;
    ViewPager2 sliderViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sharedPreferences = getContext().getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        nativeAdsCount = sharedPreferences.getInt(GET_ADD_COUNT_NATIVE, 3);

        sessionAds = new SessionAds(getContext());
        editor = sharedPreferences.edit();
        fbNativeAdId = sharedPreferences.getString(FBNATIVE_ID, "");
        fbNativeManager = new NativeAdsManager(getContext(), fbNativeAdId, 12);
        adapterStoreAds = new AdapterChannelList(getContext(), modelBase);

        categoryItemRecyclerview = view.findViewById(R.id.categoryItemRecyclerview);
        favoriteItemRecyclerview = view.findViewById(R.id.favoriteItemRecyclerview);
        recentViewItemRecyclerview = view.findViewById(R.id.recentItemRecyclerview);
        recentAddedItemRecyclerview = view.findViewById(R.id.recentAdItemRecyclerview);
        favoriteHomeLayout = view.findViewById(R.id.favoriteHomeLayout);
        swiperefresh = view.findViewById(R.id.swiperefresh);
        seeAllCategory = view.findViewById(R.id.seeAllCategory);
        seeFavorite = view.findViewById(R.id.seeFavorite);
        seeRecent = view.findViewById(R.id.seeRecent);
        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
        mainLayoutAll = view.findViewById(R.id.main_layout_all);
        mainMayoutHomeFrag = view.findViewById(R.id.main_mayout_home_frag);
        searchIconButton = view.findViewById(R.id.searchIconButton);
        recentClickHomeLayout = view.findViewById(R.id.recentClickHomeLayout);
        tolbarId = view.findViewById(R.id.tolbarId);
        simpleSlider2 = view.findViewById(R.id.simple_slider2);
//        linePageIndicator = view.findViewById(R.id.line_indicator);
        sliderDot = view.findViewById(R.id.slide_dot);
//        sliderViewPager = view.findViewById(R.id.slider_viewpager);
        seeRecentAd = view.findViewById(R.id.seerecentAd);




        //Slider New
        BasicPagerAdapter1 adapter2 = new BasicPagerAdapter1(getContext(),sliderItemList);
        simpleSlider2.setAdapter(adapter2);

        swiperefresh.setRefreshing(true);
        seeAllCategory.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AllCategoryActivity.class));
            sessionAds.show();
            new AnimIntent.Builder(v.getContext()).performSlideToLeft();
        });
        seeFavorite.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), FavoriteActivity.class));
            sessionAds.show();
            new AnimIntent.Builder(v.getContext()).performSlideToLeft();
        });
        shimmerFrameLayout.startShimmer();
        if (!modelBase.isEmpty()) {
            if (!nativeAd.isEmpty()) {
                nativeAd.clear();
                modelBase.clear();
            } else if (!modelAds.isEmpty()) {
                modelAds.clear();
                modelBase.clear();
            }
        }


        dataLoad();
        recentData();
        swiperefresh.setOnRefreshListener(() -> {
            shimmerFrameLayout.setVisibility(View.VISIBLE);
            shimmerFrameLayout.startShimmer();
            mainMayoutHomeFrag.setVisibility(View.GONE);
            adapterStoreAds = new AdapterChannelList(getContext(), modelBase);
            dataLoad();
            recentData();

        });


        seeRecentAd.setOnClickListener(v -> {
            startActivity(new Intent(v.getContext(), RecentActivity.class));
            sessionAds.show();
            new AnimIntent.Builder(v.getContext()).performSlideToLeft();
        });
        seeRecent.setOnClickListener(v -> {
            startActivity(new Intent(v.getContext(), RecentViewActivity.class));
            sessionAds.show();
            new AnimIntent.Builder(v.getContext()).performSlideToLeft();

        });
        swiperefresh.setRefreshing(false);
        ratingDialog(getContext());
        searchIconButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SearchActivity.class));
            new AnimIntent.Builder(v.getContext()).performSlideToLeft();
        });


        return view;
    }


    public void loadData() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tes", null);
        Type type = new TypeToken<ArrayList<Channel.Datum>>() {
        }.getType();
        channelList = gson.fromJson(json, type);

        if (channelList == null) {
            channelList = new ArrayList<>();
            favoriteHomeLayout.setVisibility(View.GONE);
        } else {
            if (channelList.isEmpty()) {
                favoriteHomeLayout.setVisibility(View.GONE);
            } else {
                favoriteHomeLayout.setVisibility(View.VISIBLE);
                setFavoriteRecyclerView(channelList);
            }


        }
    }

    public void recentData() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("recent", null);
        Type type = new TypeToken<ArrayList<Channel.Datum>>() {
        }.getType();
        recentChannelView = gson.fromJson(json, type);

        if (recentChannelView == null) {
            recentChannelView = new ArrayList<>();
            recentClickHomeLayout.setVisibility(View.GONE);

        } else {
            if (recentChannelView.isEmpty()) {
                //Empty

            } else {
                Log.d("dfsfsd", "recentData: "+recentChannelView.size());
                setRecentRecyclerView(recentChannelView);
                recentClickHomeLayout.setVisibility(View.VISIBLE);
            }


        }

    }
    private void dataLoad() {
        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
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
                        Log.d("Fail", "onFailure: " + t.getLocalizedMessage());
                    }
                });

        apiInter.getChannelBody()
                .enqueue(new Callback<Channel>() {
                    @Override
                    public void onResponse(Call<Channel> call, Response<Channel> response) {
                        Channel channel = response.body();
                        List<Channel.Datum> channelList = channel.data;

                        if (!channelList.isEmpty()){
                            Collections.reverse(channelList);
                            setRecentAddRecyclerView(channelList);
                        }


                    }

                    @Override
                    public void onFailure(Call<Channel> call, Throwable t) {
                        Log.d("logg", "onFailure: "+t.getMessage());
                    }
                });
        apiInter.getSlider()
                .enqueue(new Callback<Slider>() {
                    @Override
                    public void onResponse(Call<Slider> call, Response<Slider> response) {
                        if (response.isSuccessful()) {
                            Slider slider = response.body();
                            List<Slider.Datum> sliders = slider.data;
                            List<Slider.Datum> sliders1 = new ArrayList<>();
                            Collections.shuffle(sliders);

                            if(sliders.size()> 3){
                                for (int i=0;i<3; i++ ){
                                    sliders1.add(sliders.get(i));
                                }
                            }else {
                                sliders1.addAll(sliders);
                            }


                            sliderDot.removeAllViews();
                            sliderImage(sliders1);
                        }

                    }

                    @Override
                    public void onFailure(Call<Slider> call, Throwable t) {
                        Log.d("faild", "onFailure: " + t.getLocalizedMessage());
                    }
                });

        loadData();
        recentData();
        swiperefresh.setOnClickListener(v -> startActivity(new Intent(getContext(), FavoriteActivity.class)));


    }


    public void setCategoryRecyclerView(List<Category.Datum> categoryList) {
        categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryItemRecyclerview.setLayoutManager(linearLayoutManager);
        categoryItemRecyclerview.setAdapter(categoryAdapter);


    }

    public void setFavoriteRecyclerView(List<Channel.Datum> categoryList) {
        Collections.reverse(categoryList);
        tvAdapterChannelHome = new TvAdapterChannelHome(getContext(), categoryList);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        favoriteItemRecyclerview.setLayoutManager(linearLayoutManager);
        favoriteItemRecyclerview.setAdapter(tvAdapterChannelHome);
    }

    public void setRecentRecyclerView(List<Channel.Datum> categoryList) {
        Collections.reverse(categoryList);
        tvAdapterRecentHome = new TvAdapterRecentHome(getContext(), categoryList);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recentViewItemRecyclerview.setLayoutManager(linearLayoutManager);
        recentViewItemRecyclerview.setAdapter(tvAdapterRecentHome);

    }

    public void setRecentAddRecyclerView(List<Channel.Datum> channelList) {
        Collections.reverse(channelList);
        tvAdapterRecentAdd = new TvAdapterChannelHome(getContext(), channelList);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recentAddedItemRecyclerview.setLayoutManager(linearLayoutManager);
        recentAddedItemRecyclerview.setAdapter(tvAdapterRecentAdd);



    }

    public void sliderImage(List<Slider.Datum> sliders) {
        if (!sliderItemList.isEmpty()){
            sliderItemList.clear();
        }

        for (int i = 0; i < sliders.size(); i++) {
            SliderHome sliderItem = new SliderHome();
            Slider.Datum slider = sliders.get(i);
            sliderItem.setId(slider.id);
            sliderItem.setCreated_at(slider.created_at + "");
            sliderItem.setImage_name(slider.image);
            sliderItem.setName(slider.name);
            sliderItem.setChannel_id(slider.id);
            sliderItem.setPro_id(slider.product_id);
            sliderItem.setFullImageUrl(ADMIN_PANEL_URL + IMAGE_URL + slider.image);

            sliderItemList.add(sliderItem);

        }
        simpleSlider2.removeAllViews();
        BasicPagerAdapter adapter2 = new BasicPagerAdapter(getContext(),sliderItemList);

        simpleSlider2.setAdapter(adapter2);
        simpleSlider2.setInfiniteEnable(true);
        simpleSlider2.startAutoScroll(true);
        simpleSlider2.setSliderDuration(3000);
        simpleSlider2.setPageTransformer( new CyclePageTransformer(simpleSlider2)); // page turning animation*/
        simpleSlider2.notifyDataSetChanged();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
////        sliderAdapter.renewItems(sliderItemList);
//        sliderViewPager.setAdapter(new SliderAdapter2(sliderItemList,sliderViewPager));
//        sliderViewPager.setClipToPadding(false);
//        sliderViewPager.setClipChildren(false);
//        sliderViewPager.setOffscreenPageLimit(3);
//        sliderViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
//        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
//
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
////                float r = 1-Math.abs(position);
////                page.setScaleY(0.85f + r * .15f);
////                page.setScaleX(0.8f);
//                float myOffset = position * -(2 * 90 + 150);
//                if (position < -1) {
//                    page.setTranslationX(-myOffset);
//                } else if (position <= 1) {
//                    float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
//                    page.setTranslationX(myOffset);
//                    page.setScaleY(scaleFactor);
////                    page.setAlpha(scaleFactor);
//                } else {
//                    page.setAlpha(0);
//                    page.setTranslationX(myOffset);
//                }
//            }
//        });
//        sliderViewPager.setPageTransformer(compositePageTransformer);
        dotsCount = sliderItemList.size();
        dots = new ImageView[dotsCount];
        for(int i=0; i<dotsCount;i++){
            try {
                dots[i] = new ImageView(getActivity());
                dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(),
                        R.drawable.tab_indicator_default));
                params.setMargins(7,0,8,0);
                sliderDot.addView(dots[i],params);
            }catch (Exception e){

            }
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity()
                ,R.drawable.tab_indicator_selected));

//        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                handler = new Handler();
//                handler.removeCallbacks(sliderRunnable);
//                handler.postDelayed(sliderRunnable, 3000);
//                try {
//                    for(int i=0; i<dotsCount;i++){
//                        dots[i].setImageDrawable(ContextCompat.getDrawable(requireActivity(),
//                                R.drawable.tab_indicator_default));
//                    }
//                    position = (position)%dotsCount;
//                    Log.d("checkResult", position+"");
//                    dots[position].setImageDrawable(ContextCompat.getDrawable(requireActivity()
//                            , R.drawable.tab_indicator_selected));
//                }catch (Exception e){
//
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                super.onPageScrollStateChanged(state);
//            }
//        });

        simpleSlider2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                try {
                    for(int i=0; i<dotsCount;i++){
                        dots[i].setImageDrawable(ContextCompat.getDrawable(requireActivity(),
                                R.drawable.tab_indicator_default));
                    }
                    position = (position)%dotsCount;
                    Log.d("checkResult", position+"");
                    dots[position].setImageDrawable(ContextCompat.getDrawable(requireActivity()
                            , R.drawable.tab_indicator_selected));
                }catch (Exception e){

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        handler = new Handler();
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        int i = sliderViewPager.getCurrentItem();
//                        if (i==dotsCount-1){
//                            i=0;
//                            sliderViewPager.setCurrentItem(i);
//                        }else {
//                            i++;
//                            sliderViewPager.setCurrentItem(i, true);
//                        }
//                    }
//                });
//            }
//        }, 5000,5000);
        shimmerFrameLayout.stopShimmer();
        mainMayoutHomeFrag.setVisibility(View.VISIBLE);
        shimmerFrameLayout.setVisibility(View.GONE);
        swiperefresh.setRefreshing(false);

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
//            sliderViewPager.setCurrentItem(sliderViewPager.getCurrentItem()+1);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        loadData();
        recentData();
//        handler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
        recentData();
    }

    public void ratingDialog(Context context) {
        final RatingDialog ratingDialog = new RatingDialog.Builder(context)
                .session(7)
                .threshold(3)
                .title("How was your experience with us?")
                .titleTextColor(R.color.black)
                .positiveButtonText("Not Now")
                .negativeButtonText("Never")
                .positiveButtonTextColor(R.color.paypalColor)
                .negativeButtonTextColor(R.color.paypalColor)
                .formTitle("Submit Feedback")
                .formHint("Tell us where we can improve")
                .formSubmitText("Submit")
                .formCancelText("Cancel")
                .ratingBarColor(R.color.paypalColor)
                .playstoreUrl("YOUR_URL")
                .onThresholdCleared((ratingDialog1, rating, thresholdCleared) -> {
                    //do something
                    StringBuilder sb = new StringBuilder();
                    sb.append("market://details?id=");
                    sb.append(context.getPackageName());
                    String str = "android.intent.action.VIEW";
                    Intent intent = new Intent(str, Uri.parse(sb.toString()));

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("http://play.google.com/store/apps/details?id=");
                        sb2.append(context.getPackageName());
                        startActivity(new Intent(str, Uri.parse(sb2.toString())));
                    }
                    ratingDialog1.dismiss();
                })
                .onThresholdFailed((ratingDialog12, rating, thresholdCleared) -> {
                })
                .onRatingChanged((rating, thresholdCleared) -> {
                })
                .onRatingBarFormSumbit(feedback -> {
                }).build();

        ratingDialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();
//        handler.postDelayed(sliderRunnable, 3000);
    }
}
