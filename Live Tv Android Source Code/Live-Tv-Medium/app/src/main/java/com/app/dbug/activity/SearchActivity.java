package com.app.dbug.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.app.dbug.R;
import com.app.dbug.adapter.AdapterChannelList;
import com.app.dbug.api.ApiInter;
import com.app.dbug.model.Channel;
import com.app.dbug.retofit.RetrofitClient;
import com.facebook.ads.NativeAdsManager;

import java.util.ArrayList;
import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    ImageView searchButton ;
    EditText searchEditText;

    RecyclerView myListRv;
    AdapterChannelList adapterChannelAds;
    List<Object> modelBase = new ArrayList<>();

    NativeAdsManager fbNativeManager;


    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    boolean isDark;
    LinearLayout noResultLayout;
    LinearLayout mainLayoutSearch;
    RelativeLayout toolbarId,backArrow;

    int spanCout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences =getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        spanCout = sharedPreferences.getInt("spanCout", 1);
        if (isDark) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppThemeLight);
        }

        setContentView(R.layout.activity_search);
        fbNativeManager = new NativeAdsManager(this, sharedPreferences.getString(SplashActivity.GNATIVE_ID, ""), 3);
        editor = sharedPreferences.edit();
        searchButton = findViewById(R.id.searchButton);
        searchEditText = findViewById(R.id.searchEditText);
        myListRv = findViewById(R.id.searchResultRecyclerview);
        noResultLayout = findViewById(R.id.noResultLayout);
        mainLayoutSearch = findViewById(R.id.mainLayoutSearch);
        backArrow = findViewById(R.id.back_button);
        toolbarId = findViewById(R.id.toolbarId);



        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String ser = searchEditText.getText().toString().trim();
                if (ser.isEmpty()){
                    Toast.makeText(SearchActivity.this, "Enter search input", Toast.LENGTH_SHORT).show();
                }else {
                    search(ser);
                }


                return true;
            }
            return false;
        });

        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.equals("")){
                    Toast.makeText(SearchActivity.this, "Enter search input", Toast.LENGTH_SHORT).show();
                }else {
                    search(cs.toString().trim());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            //Empty
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                //Empty
            }
        });


        backArrow.setOnClickListener(v -> {
            finish();
            new AnimIntent.Builder(v.getContext()).performSlideToRight();
        });
        searchButton.setOnClickListener(v -> {

           String ser = searchEditText.getText().toString().trim();
           if (ser.isEmpty()){
               Toast.makeText(SearchActivity.this, "Enter search input", Toast.LENGTH_SHORT).show();
               return;

           }

            search(ser);
        });

    }


    public void search(String text){
        if (!modelBase.isEmpty()){
            modelBase.clear();
        }
        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        apiInter.getChannalSearch(text)
                .enqueue(new Callback<Channel>() {
                    @Override
                    public void onResponse(Call<Channel> call, Response<Channel> response) {
                        if (response.isSuccessful()) {
                            Channel channel = response.body();

                            List<Channel.Datum> post = channel.data;
                            if (!post.isEmpty()){
                                myListRv.setVisibility(View.VISIBLE);
                                noResultLayout.setVisibility(View.GONE);

                            }

                            for (int i = 0; i < post.size(); i++) {
                                if (post.get(i).category_type == 1){
                                    modelBase.add(post.get(i));
                                }
                            }
                            adapterChannelAds = new AdapterChannelList(SearchActivity.this, modelBase);
                            myListRv.setHasFixedSize(true);
                            myListRv.setNestedScrollingEnabled(false);

                             GridLayoutManager gridLayoutManager1 = new GridLayoutManager(SearchActivity.this, 2);

                            gridLayoutManager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    switch (adapterChannelAds.getItemViewType(position)) {
                                        case 1:
                                            return 1;
                                        case 0:
                                            return spanCout;
                                        default:
                                            return -1;
                                    }
                                }
                            });
                            myListRv.setLayoutManager(gridLayoutManager1);
                            myListRv.setAdapter(adapterChannelAds);


                        }
                    }

                    @Override
                    public void onFailure(Call<Channel> call, Throwable t) {

                        Log.d("MainActivityLog", t.getLocalizedMessage());
                    }



                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AnimIntent.Builder(SearchActivity.this).performSlideToRight();


    }
}