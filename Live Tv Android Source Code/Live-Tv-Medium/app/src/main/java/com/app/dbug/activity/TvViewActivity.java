package com.app.dbug.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.dbug.adapter.AdapterChannelList;
import com.app.dbug.adapter.AdapterChannelListRe;
import com.app.dbug.ads.InterAdClickInterFace;
import com.app.dbug.ads.SessionAds;
import com.app.dbug.api.ApiInter;
import com.app.dbug.model.Channel;
import com.app.dbug.retofit.RetrofitClient;
import com.app.dbug.R;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import alzaichsank.com.intentanimation.AnimIntent;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.doikki.videocontroller.StandardVideoController;
import xyz.doikki.videocontroller.component.GestureView;
import xyz.doikki.videoplayer.player.VideoView;

import static android.content.ContentValues.TAG;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.app.dbug.activity.SplashActivity.GET_ADD_COUNT_NATIVE;
import static com.app.dbug.activity.SplashActivity.MYPREFERENCE;


public class TvViewActivity extends AppCompatActivity implements InterAdClickInterFace {
    String imageString;
    String titleString;
    String categoryString;
    String fullString;
    String id1;
    String catId;
    String createdAt;
    String updatedAt;
    String videoUpload;
    String videoUrl1;
    String typeName;
    String typePosition;
    String userAgent;
    String token;
    String urlId;
    String sID;
    VideoView videoView;
    SessionAds sessionAds;

    ImageView relatedView;
    ImageView hidRelatedChannel;


    AdapterChannelListRe adapterChannelAds;
    List<Object> channelListO = new ArrayList<>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RecyclerView myListRv;
    boolean isDark;
    ViewGroup rootView1;
    RelativeLayout relatedViewRelativeLayout;
    int spanCout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences =getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        isDark = sharedPreferences.getBoolean("dark", false);
        spanCout = sharedPreferences.getInt("spanCout", 1);
        if (isDark) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppThemeLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_view);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("data-loaded"));

        Intent callingIntent = getIntent();
        id1 = callingIntent.getStringExtra("id");
        catId = callingIntent.getStringExtra("cat_id");
        createdAt = callingIntent.getStringExtra("created_at");
        updatedAt = callingIntent.getStringExtra("updated_at");
        imageString = callingIntent.getStringExtra("Image");
        titleString = callingIntent.getStringExtra("Title");
        categoryString = callingIntent.getStringExtra("Category");
        fullString = callingIntent.getStringExtra("Full");
        videoUrl1 = callingIntent.getStringExtra("tv_url");
        videoUpload = callingIntent.getStringExtra("video_upload");
        typePosition = callingIntent.getStringExtra("type_postion");
        typeName = callingIntent.getStringExtra("type_name");
        urlId = callingIntent.getStringExtra("url_id");
        sID = callingIntent.getStringExtra("stID");
        userAgent = callingIntent.getStringExtra("user_agent");
        token = callingIntent.getStringExtra("token");
        this.sessionAds = new SessionAds(TvViewActivity.this, this);
        videoView = findViewById(R.id.player1);
        relatedView = findViewById(R.id.relatedView);
        hidRelatedChannel = findViewById(R.id.hidRelatedChannel);
        myListRv = findViewById(R.id.recentAdItemRecyclerview);
        rootView1 = findViewById(R.id.parentID);
        relatedViewRelativeLayout = findViewById(R.id.channalList);
        videoView.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);

        hidRelatedChannel.setImageResource(R.drawable.ic_baseline_arrow_forward);

        if (sID != null) {
            dataLoad();
        } else {
            if (urlId.equals("1")) {
                Log.d("dsfsdfsd", "onCreate: ");
                extractYoutubeUrl1(videoUrl1, TvViewActivity.this, 18);
            } else {
                if (userAgent != null) {
                    userAgent(userAgent);
                } else if (token != null) {
                    Log.d("sdlkffgfd", "onCreate: " + token);
                    token(token);


                } else {
                    if (videoUrl1.contains(".mp4") || videoUrl1.contains("youtube")) {
                        Log.d("fkjsfksd", "onCreate: 11     " + videoUrl1);

                        playerDK(videoUrl1, titleString, false);
                    } else {

                        Log.d("fkjsfksd", "onCreate: 222     " + videoUrl1);
                        playerDK(videoUrl1, titleString, true);
                    }

                }
            }

        }


        relatedView.setOnClickListener(v -> {
            relatedView.setVisibility(GONE);
            videoListInLand();
        });
        hidRelatedChannel.setOnClickListener(v -> {
            relatedView.setVisibility(VISIBLE);
            videoListGon();
        });




        videoView.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                videoView.setScreenScaleType(VideoView.SCREEN_SCALE_MATCH_PARENT);
            } else {
                // In portrait
                videoView.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);

            }

        });


        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        apiInter.getChannelInCategory(catId)
                .enqueue(new Callback<Channel>() {
                    @Override
                    public void onResponse(Call<Channel> call, Response<Channel> response) {
                        if (response.isSuccessful()) {
                            Channel channel = response.body();
                            List<Channel.Datum> post = channel.data;
                            for (int i = 0; i < post.size(); i++) {

                                if (post.get(i).url_id == 1 || post.get(i).url_id == 11){
                                    channelListO.add(post.get(i));
                                }

                            }

                            adapterChannelAds = new AdapterChannelListRe(TvViewActivity.this, channelListO);
                            myListRv.setHasFixedSize(true);
                            myListRv.setNestedScrollingEnabled(false);

                            final GridLayoutManager gridLayoutManager1 = new GridLayoutManager(TvViewActivity.this, 1);

                            gridLayoutManager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    switch (adapterChannelAds.getItemViewType(position)) {
                                        case 1:
                                            return 1;
                                        case 0:
                                            return 1;
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
                        Log.d("MainActivityLog", "onFailure: " + t.getLocalizedMessage());
                    }
                });

    }


    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        videoView.resume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.release();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }


    @Override
    public void onBackPressed() {
        if (!videoView.onBackPressed()) {
            sessionAds.showInter();
            super.onBackPressed();
        }

        finish();
        sessionAds.showInter();
        new AnimIntent.Builder(TvViewActivity.this).performSlideToRight();
    }






    @Override
    public void onAdClick() {
        Log.d(TAG, "onAdClick: ");
    }

    @Override
    public void onAdFailed() {
        Log.d(TAG, "onAdFailed: ");
    }





    public void token(String url) {
        GetToken getToken = new GetToken(url);
        try {
            String token = getToken.execute().get();
            if (token != null) {
                token = token.replace("{\"token\":\"", "");
                token = token.replace("\"}", "");
                String fullUrl = videoUrl1 + token;
                if (url.contains(".mp4") || url.contains("youtube")) {
                    playerDK(fullUrl, titleString, false);
                } else {
                    playerDK(fullUrl, titleString, true);
                }
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void userAgent(String url) {

        GetToken getToken = new GetToken(url);

        try {
            String useragent = getToken.execute().get();

            if (useragent != null) {
                useragent = useragent.replace("{\"agent\":\"", "");
                useragent = useragent.replace("\"}", "");

                if (url.contains(".mp4") || url.contains("youtube")) {
                    playerDK(videoUrl1, titleString, useragent, false);
                } else {
                    playerDK(videoUrl1, titleString, useragent, true);
                }
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @SuppressLint("StaticFieldLeak")
    public class GetToken extends AsyncTask<String, String, String> {
        private String newToken;
        private String url;
        public GetToken(String url) {
            this.url = url;
            Log.d("slkjsdfs", "GetToken: " + url);
        }

        @Override
        protected String doInBackground(String... params) {
            if (url != null) {
                try {
                    Document document = Jsoup.connect(url).sslSocketFactory(socketFactory()).get();
                    Log.d("sdkfjsdf", "do "+document);
                } catch (IOException e) {

                    Log.d("sdkfjsdf", "doInBackground: "+e.getMessage());
                    e.printStackTrace();
                }
                try {
                    newToken = Jsoup.connect(url)
                            .timeout(10000)
                            .sslSocketFactory(socketFactory())
                            .ignoreHttpErrors(true)
                            .get()
                            .text();

                    Log.d("sdkgjsdgf", "doInBackground: " + url);

                } catch (IOException e) {
                    e.printStackTrace();

                    Log.d("sdkgjsdgf", "doInBackground: " + e.getMessage());
                }
            }


            return newToken;
        }
    }
    private SSLSocketFactory socketFactory() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                Log.d(TAG, "checkClientTrusted: ");
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                Log.d(TAG, "checkServerTrusted: ");
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to create a SSL socket factory", e);
        }
    }









    public void playerDK(String url, String title, boolean live) {
        videoView.setUrl(url);
        StandardVideoController controller = new StandardVideoController(this);
        controller.addDefaultControlComponent(title, live);
        videoView.setVideoController(controller);
        videoView.setKeepScreenOn(true);
        videoView.start();
        videoView.addOnStateChangeListener(new VideoView.OnStateChangeListener() {
            @Override
            public void onPlayerStateChanged(int playerState) {
                if (playerState == VideoView.STATE_ERROR) {
                    findViewById(R.id.linearlayout111).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.linearlayout111).setVisibility(View.GONE);
                }

            }

            @Override
            public void onPlayStateChanged(int playState) {
                if (playState == VideoView.STATE_ERROR) {
                    findViewById(R.id.linearlayout111).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.linearlayout111).setVisibility(View.GONE);
                }


            }
        });

    }

    public void playerDK(String url, String title, String userAgent, boolean live) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("User-Agent", userAgent);
        videoView.setUrl(url, headerMap);
        StandardVideoController controller = new StandardVideoController(this);
        controller.addDefaultControlComponent(title, live);
        controller.addControlComponent(new GestureView(this));
        videoView.setVideoController(controller);
        videoView.setKeepScreenOn(true);
        videoView.start();
        videoView.addOnStateChangeListener(new VideoView.OnStateChangeListener() {
            @Override
            public void onPlayerStateChanged(int playerState) {
                if (playerState == VideoView.STATE_ERROR) {
                    Log.d("sdlfklsdf", "error: " + playerState);
                    findViewById(R.id.linearlayout111).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.linearlayout111).setVisibility(View.GONE);
                }

            }

            @Override
            public void onPlayStateChanged(int playState) {
                if (playState == VideoView.STATE_ERROR) {
                    Log.d("sdlfklsdf", "error: " + playState);
                    findViewById(R.id.linearlayout111).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.linearlayout111).setVisibility(View.GONE);
                }


            }
        });
    }


    private void dataLoad() {
        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        apiInter.getSingleChannelBody(sID)
                .enqueue(new Callback<Channel>() {
                    @Override
                    public void onResponse(Call<Channel> call, Response<Channel> response) {
                        if (response.isSuccessful()) {
                            Channel channel = response.body();
                            List<Channel.Datum> post = channel.data;

                            if (!post.isEmpty()) {
                                id1 = post.get(0).id + "";
                                catId = post.get(0).cat_id + "";
                                createdAt = post.get(0).created_at + "";
                                updatedAt = post.get(0).updated_at + "";
                                imageString = post.get(0).image;
                                titleString = post.get(0).name;
                                categoryString = post.get(0).cat_name;
                                fullString = post.get(0).description;
                                userAgent = post.get(0).user_agent;
                                token = post.get(0).token;
                                videoUrl1 = post.get(0).url;
                                urlId = post.get(0).url_id + "";
                                if (userAgent != null) {
                                    userAgent(userAgent);
                                } else if (token != null) {
                                    token(token);
                                } else {
                                    if (videoUrl1.contains(".mp4") || videoUrl1.contains("youtube")) {
                                        playerDK(videoUrl1, titleString, false);
                                    } else {
                                        playerDK(videoUrl1, titleString, true);
                                    }
                                }

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<Channel> call, Throwable t) {
                        Log.d("mmmmm", t.getMessage());
                    }
                });
    }

    private void extractYoutubeUrl1(String url, final Context context, final int tag) {
        new YouTubeExtractor(context) {
            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles != null) {
                    int itag = tag;
                    String downloadUrl = ytFiles.get(itag).getUrl();

                    Log.d("sdkfjsdjlk", "onExtractionComplete: " + downloadUrl);

                    playerDK(downloadUrl, titleString, false);

                }
            }
        }.extract(url, true, true);
    }

    public void videoListInLand() {
        Transition transition;
        transition = new Slide(Gravity.END);
        Log.d("sdasdasda", "videoListInLand: ");
        transition.setDuration(1000);
        transition.addTarget(R.id.relatedViewRelativeLayout);
        TransitionManager.beginDelayedTransition(rootView1, transition);
        relatedViewRelativeLayout.setVisibility(VISIBLE);
    }

    public void videoListGon() {
        Transition transition;
        transition = new Slide(Gravity.END);
        transition.setDuration(1000);
        transition.addTarget(R.id.relatedViewRelativeLayout);
        TransitionManager.beginDelayedTransition(rootView1, transition);
        relatedViewRelativeLayout.setVisibility(GONE);
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent callingIntent) {
            // Get extra data included in the Intent
            id1 = callingIntent.getStringExtra("id");
            catId = callingIntent.getStringExtra("cat_id");
            createdAt = callingIntent.getStringExtra("created_at");
            updatedAt = callingIntent.getStringExtra("updated_at");
            imageString = callingIntent.getStringExtra("Image");
            titleString = callingIntent.getStringExtra("Title");
            categoryString = callingIntent.getStringExtra("Category");
            fullString = callingIntent.getStringExtra("Full");
            videoUrl1 = callingIntent.getStringExtra("tv_url");
            videoUpload = callingIntent.getStringExtra("video_upload");
            typePosition = callingIntent.getStringExtra("type_postion");
            typeName = callingIntent.getStringExtra("type_name");
            urlId = callingIntent.getStringExtra("url_id");
            sID = callingIntent.getStringExtra("stID");
            userAgent = callingIntent.getStringExtra("user_agent");
            token = callingIntent.getStringExtra("token");
            videoView.release();
            if (sID != null) {
                dataLoad();
            } else {
                if (urlId.equals("1")) {
                    extractYoutubeUrl1(videoUrl1, TvViewActivity.this, 18);
                } else {
                    if (userAgent != null) {
                        userAgent(userAgent);
                    } else if (token != null) {
                        token(token);
                    } else {
                        if (videoUrl1.contains(".mp4") || videoUrl1.contains("youtube")) {
                            playerDK(videoUrl1, titleString, false);
                        } else {
                            playerDK(videoUrl1, titleString, true);
                        }

                    }
                }

            }
        }
    };

}