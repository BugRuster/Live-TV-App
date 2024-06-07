package com.app.dbug.fragmant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.dbug.R;

import com.app.dbug.activity.SplashActivity;
import com.app.dbug.adapter.AllCategoryAdapter;
import com.app.dbug.api.ApiInter;
import com.app.dbug.model.Category;
import com.app.dbug.retofit.RetrofitClient;
import com.facebook.ads.AdSize;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.app.dbug.activity.SplashActivity.ADSKEY;
import static com.app.dbug.activity.SplashActivity.MYPREFERENCE;


public class CategoryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    Context context;


    public CategoryFragment() {
        // Required empty public constructor
    }

    public CategoryFragment(Context context) {
        this.context = context;
    }



    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    AllCategoryAdapter categoryAdapter;
    LinearLayoutManager linearLayoutManager;

    AdView mAdView;
    com.facebook.ads.AdView fadView;


    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    SwipeRefreshLayout swiperefresh;
    RecyclerView allCategory;
    LinearLayout brokenHartLayout;
    ShimmerFrameLayout shimmerFrameLayout;
    RelativeLayout main_catagory_layout;
    RelativeLayout main_layout_cata_all;
    boolean isDark;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedPreferences = getContext().getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        sharedPreferences = getContext().getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        swiperefresh = view.findViewById(R.id.swiperefresh);
        allCategory = view.findViewById(R.id.allCategory);
        brokenHartLayout = view.findViewById(R.id.broken_hart_layout);
        shimmerFrameLayout =  view.findViewById(R.id.shimmer_view_container);
        main_catagory_layout = view.findViewById(R.id.main_catagory_layout);
        main_layout_cata_all = view.findViewById(R.id.main_layout_cata_all);
        if (isDark) {
            main_layout_cata_all.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.maincolor));
        } else {

            main_layout_cata_all.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
        }
        swiperefresh.setOnRefreshListener(() -> {
            shimmerFrameLayout.setVisibility(View.VISIBLE);
            main_catagory_layout.setVisibility(View.GONE);
            shimmerFrameLayout.startShimmer();
            swiperefresh.setRefreshing(true);
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
                            Log.d("sd", "onFailure: "+t.getMessage());
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
                        Log.d("TAG", "onFailure: "+t.getMessage());
                    }
                });




        if (sharedPreferences.getString(ADSKEY, "1").equals("3")){
            if (sharedPreferences.getString("currentNativeAds","1").equals("1")){
                mAdView = view.findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);

            }else{

                fadView = new com.facebook.ads.AdView(getContext(), sharedPreferences.getString(SplashActivity.FBBANNER_ID, ""), AdSize.BANNER_HEIGHT_50);
                LinearLayout adContainer = (LinearLayout) view.findViewById(R.id.banner_container);
                adContainer.addView(fadView);
                fadView.loadAd();
            }

        }else if (sharedPreferences.getString(ADSKEY, "1").equals("1")){
            mAdView = view.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

        }else if (sharedPreferences.getString(ADSKEY, "2").equals("2")){

            fadView = new com.facebook.ads.AdView(getContext(), sharedPreferences.getString(SplashActivity.FBBANNER_ID, ""), AdSize.BANNER_HEIGHT_50);
            LinearLayout adContainer = (LinearLayout) view.findViewById(R.id.banner_container);
            adContainer.addView(fadView);
            fadView.loadAd();

        }














        return view;
    }

    public void setCategoryRecyclerView(List<Category.Datum> categoryList ){
        categoryAdapter = new AllCategoryAdapter(getContext(),categoryList);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        allCategory.setLayoutManager(linearLayoutManager);
        allCategory.setAdapter(categoryAdapter);

        swiperefresh.setRefreshing(false);
        if(!categoryList.isEmpty()){
            brokenHartLayout.setVisibility(View.GONE);
        }
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        main_catagory_layout.setVisibility(View.VISIBLE);


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}