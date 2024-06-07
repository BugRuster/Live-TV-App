package com.app.dbug.fragmant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.dbug.R;
import com.app.dbug.adapter.AdapterChannelList;
import com.app.dbug.model.Channel;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.app.dbug.activity.SplashActivity.ADSKEY;
import static com.app.dbug.activity.SplashActivity.FBBANNER_ID;
import static com.app.dbug.activity.SplashActivity.FBNATIVE_ID;
import static com.app.dbug.activity.SplashActivity.GBANNER_ID;
import static com.app.dbug.activity.SplashActivity.GNATIVE_ID;
import static com.app.dbug.activity.SplashActivity.MYPREFERENCE;

public class FavoriteFragment extends Fragment {

    Context context;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    public FavoriteFragment(Context context) {
        this.context = context;
    }

    ArrayList<Channel.Datum> channelList;
    com.facebook.ads.AdView fadView;
    String fbBanar ;
    String gBanar ;
    AdLoader adLoader;
    RecyclerView myListRv;
    int adStartPos = 4; //FIRST AD POSITION (Index number, means List position + 1)
    AdapterChannelList adapterPDFOffline;
    List<Object> modelBase = new ArrayList<>();
    List<UnifiedNativeAd> modelAds = new ArrayList<>();
    List<NativeAd> nativeAd = new ArrayList<>();
    NativeAdsManager fbNativeManager;
    String fbNativeAdId;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    RecyclerView allRecent;
    LinearLayout brokenHartLayout;
    SwipeRefreshLayout swiperefresh;
    RelativeLayout mainLayoutFarv;
    ShimmerFrameLayout shimmerViewContainer;
    boolean isDark;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = getContext().getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);


        allRecent = view.findViewById(R.id.allRecent);
        brokenHartLayout = view.findViewById(R.id.broken_hart_layout);
        swiperefresh = view.findViewById(R.id.swiperefresh);
        shimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        mainLayoutFarv = view.findViewById(R.id.main_layout_farv);

        if (isDark) {
            mainLayoutFarv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.maincolor));
        } else {

            mainLayoutFarv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
        }

        fbNativeAdId =  sharedPreferences.getString(FBNATIVE_ID, "");
        fbNativeManager = new NativeAdsManager(getContext(), fbNativeAdId, 3);
        sharedPreferences = getContext().getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        myListRv = view.findViewById(R.id.allRecent);

        shimmerViewContainer.startShimmer();


        if (!modelBase.isEmpty()){
            if (!nativeAd.isEmpty()){
                nativeAd.clear();
                modelBase.clear();
            }else if (!modelAds.isEmpty()){
                modelAds.clear();
                modelBase.clear();
            }
        }
        adapterPDFOffline = new AdapterChannelList(getContext(), modelBase);

        loadData();

        swiperefresh.setOnRefreshListener(() -> {
            swiperefresh.setRefreshing(true);
            shimmerViewContainer.setVisibility(View.VISIBLE);
            mainLayoutFarv.setVisibility(View.GONE);
            shimmerViewContainer.startShimmer();
            if (!modelBase.isEmpty()){
                modelBase.clear();
            }
            loadData();

        });

        if (sharedPreferences.getString(ADSKEY, "1").equals("3")){
            if (sharedPreferences.getString(ADSKEY, "1").equals("1")){
                gBanar = sharedPreferences.getString(GBANNER_ID, "");
                admobloadNativeAds();
                Log.d("sdljhfsd", "onCreate: "+gBanar);
                FrameLayout adContainer = (FrameLayout) view.findViewById(R.id.adView);
                adContainer.removeAllViews();
                com.google.android.gms.ads.AdView adView = new com.google.android.gms.ads.AdView(getContext());
                adView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
                adView.setAdUnitId(gBanar);
                com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder().build();
                adView.loadAd(adRequest);
                adContainer.addView(adView);

            }else if (sharedPreferences.getString(ADSKEY, "2").equals("2")){
                initNativeAds();

                fbBanar = sharedPreferences.getString(FBBANNER_ID, "");
                FrameLayout adContainer = (FrameLayout) view.findViewById(R.id.adView);
                adContainer.removeAllViews();
                fadView = new com.facebook.ads.AdView(getContext(),fbBanar, AdSize.BANNER_HEIGHT_50);
                adContainer.addView(fadView);
                fadView.loadAd();
            }

        }else if (sharedPreferences.getString(ADSKEY, "1").equals("1")){
            gBanar = sharedPreferences.getString(GBANNER_ID, "");
            admobloadNativeAds();
            Log.d("sdljhfsd", "onCreate: "+gBanar);
            FrameLayout adContainer = (FrameLayout) view.findViewById(R.id.adView);
            adContainer.removeAllViews();
            com.google.android.gms.ads.AdView adView = new com.google.android.gms.ads.AdView(getContext());
            adView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
            adView.setAdUnitId(gBanar);
            com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adContainer.addView(adView);

        }else if (sharedPreferences.getString(ADSKEY, "2").equals("2")){
            initNativeAds();

            fbBanar = sharedPreferences.getString(FBBANNER_ID, "");
            FrameLayout adContainer = (FrameLayout) view.findViewById(R.id.adView);
            adContainer.removeAllViews();
            fadView = new com.facebook.ads.AdView(getContext(),fbBanar, AdSize.BANNER_HEIGHT_50);
            adContainer.addView(fadView);
            fadView.loadAd();
        }


        return  view;
    }

    public void loadData(){
        sharedPreferences = getContext().getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tes", null);
        Type type = new TypeToken<ArrayList<Channel.Datum>>() {}.getType();
        channelList = gson.fromJson(json, type);
        if (channelList == null) {
            channelList = new ArrayList<>();
            swiperefresh.setRefreshing(false);
            shimmerViewContainer.setVisibility(View.GONE);
            mainLayoutFarv.setVisibility(View.VISIBLE);
            shimmerViewContainer.stopShimmer();
        } else {
            if (!channelList.isEmpty()){
                brokenHartLayout.setVisibility(View.GONE);
            }
            for (int i = 0; i < channelList.size(); i++) {
                modelBase.add(channelList.get(i));
                swiperefresh.setRefreshing(false);
            }

            adapterPDFOffline = new AdapterChannelList(getContext(), modelBase);
            myListRv.setHasFixedSize(true);
            myListRv.setNestedScrollingEnabled(false);
            final GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 2);

            gridLayoutManager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    switch (adapterPDFOffline.getItemViewType(position)) {
                        case 1:
                            return 2;
                        case 0:
                            return 1;
                        default:
                            return -1;
                    }
                }
            });
            myListRv.setLayoutManager(gridLayoutManager1);
            myListRv.setAdapter(adapterPDFOffline);
            swiperefresh.setRefreshing(false);
            shimmerViewContainer.setVisibility(View.GONE);
            mainLayoutFarv.setVisibility(View.VISIBLE);
            shimmerViewContainer.stopShimmer();
        }


    }

    private void insertAdsInMenuItems() {
        int index = adStartPos;
        if (!modelAds.isEmpty()){
            int offset = (modelBase.size() / modelAds.size());

            for (UnifiedNativeAd ad : modelAds) {
                if (!modelBase.isEmpty()) {
                    if (modelBase.size() < index){
                        Log.d("TAG", "insertAdsInMenuItems: ");

                    }else {
                        modelBase.add(index, ad);
                        index = index + offset;
                    }
                }
            }
            adapterPDFOffline.notifyDataSetChanged();
        }else if (!nativeAd.isEmpty()){
            int offset1 = (modelBase.size() / nativeAd.size());

            for (NativeAd ad : nativeAd) {
                if (!modelBase.isEmpty()) {
                    if (modelBase.size() < index){
                        Log.d("TAG", "insertAdsInMenuItems: ");
                    }else {
                        modelBase.add(index, ad);
                        index = index + offset1;
                    }
                }

            }
            adapterPDFOffline.notifyDataSetChanged();
        }
    }

    private void admobloadNativeAds() {
        AdLoader.Builder builder = new AdLoader.Builder(getContext(), sharedPreferences.getString(GNATIVE_ID, ""));
        adLoader = builder.forUnifiedNativeAd(
                unifiedNativeAd -> {
                    modelAds.add(unifiedNativeAd);
                    if (!adLoader.isLoading()) {

                        insertAdsInMenuItems();
                    }
                }).build();
        adLoader.loadAds(new AdRequest.Builder().build(), 3);
    }

    public void initNativeAds() {

        fbNativeManager.setListener(new NativeAdsManager.Listener() {
            @Override
            public void onAdsLoaded() {
                int count = fbNativeManager.getUniqueNativeAdCount();
                for (int i = 0; i < count; i++) {
                    NativeAd ad = fbNativeManager.nextNativeAd();
                    nativeAd.add(ad);

                }
                insertAdsInMenuItems();
            }

            @Override
            public void onAdError(AdError adError) {
                Log.d("TAG", "adError: ");
            }
        });
        fbNativeManager.loadAds();
    }

}