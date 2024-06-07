package com.app.dbug.activity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.dbug.R;
import com.app.dbug.SharedPrefRadio;
import com.app.dbug.SleepTimeReceiver;
import com.app.dbug.adapter.AdapterRadio;
import com.app.dbug.api.ApiInter;
import com.app.dbug.config.Constant;
import com.app.dbug.model.Channel;
import com.app.dbug.retofit.RetrofitClient;
import com.app.dbug.radioservices.RadioPlayerService;
import com.app.dbug.utils.RelativePopupWindow;
import com.app.dbug.utils.Tools;
import com.bumptech.glide.Glide;


import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.dbug.activity.SplashActivity.FBNATIVE_ID;
import static com.app.dbug.activity.SplashActivity.GET_ADD_COUNT_NATIVE;
import static com.app.dbug.activity.SplashActivity.MYPREFERENCE;
import static com.app.dbug.config.Constant.ADMIN_PANEL_URL;
import static com.app.dbug.config.Constant.IMAGE_URL;


public class RadioActivity extends AppCompatActivity {

    private MyBroadRequestReceiver receiver;




    SlidingUpPanelLayout slidingUpPanelLayout;
    ProgressBar progressBar, progressBarCollapse;
    LinearLayout collapseColor, playerExpand, playCollapse;
    RelativeLayout expand, collapse;
    RoundedImageView imgPlayer;
    ImageView imgMusicBackground;
    ImageButton imgNextExpand, imgPreviousExpand, imgVolume, imgFavorite;
    ImageButton imgCollapse, imgTimer, imgShare;
    ImageButton imgPlay, imgNext, imgPrevious;
    FloatingActionButton fabPlay;
    TextView txtName, txtSong, txtRadioExpand, txtRadioMusicSong, txtSongExpand;
    Boolean isExpand = false;
    FragmentManager fragmentManager;
    View musicScreen;
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
    String type_name;
    String type_position;
    String url_id;
    String sID;
    String user_agent;
    String token;
    String cid;

    RecyclerView myListRv;
    AdapterRadio adapterChannelAds;
    List<Object> modelBase = new ArrayList<>();
    List<Channel.Datum> modelBase1 = new ArrayList<>();
    NativeAdsManager fbNativeManager;
    String fbNativeAdId;

    Handler handler = new Handler();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean isDark;
    int nativeAdsCount;
    RelativeLayout backArrow;
    SharedPrefRadio sharedPrefRadio;
    Tools tools;


    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }


    RelativeLayout mainLayoutHome;
    RelativeLayout toolbarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences =getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        nativeAdsCount = sharedPreferences.getInt(GET_ADD_COUNT_NATIVE, 3);
        sharedPrefRadio = new SharedPrefRadio(this);
        sharedPrefRadio.setCheckSleepTime();
        tools = new Tools(this);
        if (isDark) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppThemeLight);
        }
        setContentView(R.layout.activity_radio);
        receiver = new MyBroadRequestReceiver();
        IntentFilter filter = new IntentFilter("okokoko");
        registerReceiver(receiver, filter);


        fbNativeAdId = sharedPreferences.getString(FBNATIVE_ID, "");
        fbNativeManager = new NativeAdsManager(RadioActivity.this, fbNativeAdId, 3);
        sharedPreferences = getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        initFind();
        Intent callingIntent = getIntent();
        id1 = callingIntent.getStringExtra("id");
        catId = callingIntent.getStringExtra("cat_id");
        cid = catId;
        createdAt = callingIntent.getStringExtra("created_at");
        updatedAt = callingIntent.getStringExtra("updated_at");
        imageString = callingIntent.getStringExtra("Image");
        titleString = callingIntent.getStringExtra("Title");
        categoryString = callingIntent.getStringExtra("Category");
        fullString = callingIntent.getStringExtra("Full");
        videoUrl1 = callingIntent.getStringExtra("tv_url");
        videoUpload = callingIntent.getStringExtra("video_upload");
        type_position = callingIntent.getStringExtra("type_postion");
        type_name = callingIntent.getStringExtra("type_name");
        url_id = callingIntent.getStringExtra("url_id");
        sID = callingIntent.getStringExtra("stID");
        user_agent = callingIntent.getStringExtra("user_agent");
        token = callingIntent.getStringExtra("token");
        txtRadioExpand.setText(titleString);
        txtName.setText(titleString);
        txtSongExpand.setText(fullString);
        txtSong.setText(fullString);
        backArrow = findViewById(R.id.back_button);


        Glide.with(getApplicationContext())
                .load(ADMIN_PANEL_URL+ IMAGE_URL +imageString)
                .thumbnail(0.2f)
                .into(imgPlayer);

        Glide.with(getApplicationContext())
                .load(ADMIN_PANEL_URL+ IMAGE_URL +imageString)
                .thumbnail(0.2f)
                .into(imgMusicBackground);
        adapterChannelAds = new AdapterRadio(RadioActivity.this, modelBase);
        myListRv = findViewById(R.id.allChannelInCategory);
        backArrow = findViewById(R.id.back_button);

        backArrow.setOnClickListener(v -> {
            finish();
            new AnimIntent.Builder(v.getContext()).performSlideToRight();
        });

        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        apiInter.getChannelInCategory(cid)
                .enqueue(new Callback<Channel>() {
                    @Override
                    public void onResponse(Call<Channel> call, Response<Channel> response) {
                        if (response.isSuccessful()) {
                            Channel channel = response.body();
                            List<Channel.Datum> post = channel.data;
                            for (int i = 0; i < post.size(); i++) {
                                if (post.get(i).url_id == 9){

                                    modelBase.add(post.get(i));
                                    modelBase1.add(post.get(i));
                                    Constant.item_radio.add(post.get(i));
                                }

                            }

                            adapterChannelAds = new AdapterRadio(RadioActivity.this, modelBase);
                            myListRv.setHasFixedSize(true);
                            myListRv.setNestedScrollingEnabled(false);

                            final GridLayoutManager gridLayoutManager1 = new GridLayoutManager(RadioActivity.this, 2);

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



                            for (int i =0;i<modelBase1.size();i++ ){
                                Channel.Datum c = (Channel.Datum) modelBase.get(i);
                                int id11 = Integer.parseInt(id1);
                                if (c.id == id11){
                                    Constant.item_radio.clear();
                                    Constant.item_radio.addAll(modelBase1);
                                    Constant.position = i;
                                    Intent intent = new Intent(RadioActivity.this, RadioPlayerService.class);
                                    RadioPlayerService.createInstance().initialize(RadioActivity.this, Constant.item_radio.get(i));
                                    intent.setAction(RadioPlayerService.ACTION_PLAY);
                                    startService(intent);
                                    Log.d("klsdfjlsdfklsjd", "position : "+i +" list id: \n"+c.id +" click id \n"+id11);
                                }

                            }
                            adapterChannelAds.setOnItemClickListener((view, obj, position) -> {
                                Log.d("ksdjfewrwe", "itemRecyclerview: "+position);
                                Constant.item_radio.clear();
                                Constant.item_radio.addAll(modelBase1);
                                Constant.position = position;
                                Intent intent = new Intent(RadioActivity.this, RadioPlayerService.class);
                                RadioPlayerService.createInstance().initialize(RadioActivity.this, Constant.item_radio.get(position));
                                intent.setAction(RadioPlayerService.ACTION_PLAY);
                                startService(intent);

                            });

                        }
                    }
                    @Override
                    public void onFailure(Call<Channel> call, Throwable t) {
                        Log.d("logg", "onFailure: ");
                    }
                });

    }

    public void initFind() {
        fragmentManager = getSupportFragmentManager();
        slidingUpPanelLayout = findViewById(R.id.sliding_layout);
        musicScreen = findViewById(R.id.layout_music_player);
        imgMusicBackground = findViewById(R.id.img_music_background);

        progressBar = findViewById(R.id.progress_bar);
        progressBarCollapse = findViewById(R.id.progress_bar_collapse);
        imgShare = findViewById(R.id.img_share);
        imgTimer = findViewById(R.id.img_timer);
        imgPlayer = findViewById(R.id.img_cover_small);
        imgPrevious = findViewById(R.id.img_player_previous);
        imgPreviousExpand = findViewById(R.id.img_previous_expand);
        imgNext = findViewById(R.id.img_player_next);
        imgNextExpand = findViewById(R.id.img_next_expand);
        imgPlay = findViewById(R.id.img_player_play);
        imgFavorite = findViewById(R.id.img_favorite);
        imgVolume = findViewById(R.id.img_volume);
        imgCollapse = findViewById(R.id.img_collapse);
        txtName = findViewById(R.id.txt_radio_name);
        txtSong = findViewById(R.id.txt_metadata);
        fabPlay = findViewById(R.id.fab_play);
        txtRadioExpand = findViewById(R.id.txt_radio_name_expand);
        txtRadioMusicSong = findViewById(R.id.txt_radio_music_expand);
        txtSongExpand = findViewById(R.id.txt_metadata_expand);


        playerExpand = findViewById(R.id.lyt_player_expand);
        playCollapse = findViewById(R.id.lyt_play_collapse);
        collapse = findViewById(R.id.lyt_collapse);
        collapseColor = findViewById(R.id.lyt_collapse_color);
        expand = findViewById(R.id.ll_expand);
        mainLayoutHome = findViewById(R.id.mainLayoutHome);
        toolbarId = findViewById(R.id.toolbarId);
        setIfPlaying();
        collapse.setOnClickListener(v -> slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED));

        expand.setOnClickListener(v -> {

        });

        slidingUpPanelLayout.setDragView(collapse);
        slidingUpPanelLayout.setShadowHeight(0);
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                if (slideOffset == 0.0f) {
                    isExpand = false;
                    expand.setVisibility(View.GONE);
                } else if (slideOffset > 0.0f && slideOffset < 1.0f) {
                    if (isExpand) {
                        collapse.setVisibility(View.VISIBLE);
                        expand.setAlpha(slideOffset);
                        collapse.setAlpha(1.0f - slideOffset);
                    } else {
                        expand.setVisibility(View.VISIBLE);
                        expand.setAlpha(0.0f + slideOffset);
                        collapse.setAlpha(1.0f - slideOffset);
                    }
                } else {
                    isExpand = true;
                    collapse.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    collapse.setVisibility(View.GONE);
                }else if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED){
                    collapse.setVisibility(View.VISIBLE);
                }
            }
        });

        imgPlay.setOnClickListener(view -> {
            if (!tools.isNetworkAvailable()) {
                Toast.makeText(RadioActivity.this, getResources().getString(R.string.internet_not_connected), Toast.LENGTH_SHORT).show();
            } else if (txtName.getText().equals(getString(R.string.app_name))) {
                Toast.makeText(RadioActivity.this, getResources().getString(R.string.no_radio_selected), Toast.LENGTH_SHORT).show();
            } else {
                clickPlay(Constant.position, Constant.radio_type);
            }
        });

        fabPlay.setOnClickListener(view -> {
            if (!tools.isNetworkAvailable()) {
               Toast.makeText(RadioActivity.this, getResources().getString(R.string.internet_not_connected), Toast.LENGTH_SHORT).show();
            } else if (txtName.getText().equals(getString(R.string.app_name))) {
                Toast.makeText(RadioActivity.this, getResources().getString(R.string.no_radio_selected), Toast.LENGTH_SHORT).show();
            } else {
                clickPlay(Constant.position, Constant.radio_type);
            }
        });

        imgNext.setOnClickListener(view -> togglePlayPosition(true));
        imgNextExpand.setOnClickListener(view -> togglePlayPosition(true));
        imgPrevious.setOnClickListener(view -> togglePlayPosition(false));
        imgPreviousExpand.setOnClickListener(view -> togglePlayPosition(false));
        imgTimer.setOnClickListener(v -> {
            if (sharedPrefRadio.getIsSleepTimeOn()) {
                radioOpenTimeDialog();
            } else {
                timerSelectDialog();
            }
        });
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        imgCollapse.setOnClickListener(v -> slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED));
        imgVolume.setOnClickListener(v -> changeRadioVolume());

    }
    public void clickPlay(int position, Boolean isRadio) {
        Constant.radio_type = isRadio;
        Constant.position = position;
        Channel.Datum radio = (Channel.Datum) modelBase.get(Constant.position);
        final Intent intent = new Intent(RadioActivity.this, RadioPlayerService.class);

        if (RadioPlayerService.getInstance() != null) {
            Channel.Datum playerCurrentRadio = RadioPlayerService.getInstance().getPlayingRadioStation();

            if (playerCurrentRadio != null) {
                if (!radio.getUrl().equals(RadioPlayerService.getInstance().getPlayingRadioStation().getUrl())) {
                    RadioPlayerService.getInstance().initialize(RadioActivity.this, radio);
                    intent.setAction(RadioPlayerService.ACTION_PLAY);
                } else {
                    intent.setAction(RadioPlayerService.ACTION_TOGGLE);
                }
            } else {
                RadioPlayerService.getInstance().initialize(RadioActivity.this, radio);
                intent.setAction(RadioPlayerService.ACTION_PLAY);
            }
        } else {
            RadioPlayerService.createInstance().initialize(RadioActivity.this, radio);
            intent.setAction(RadioPlayerService.ACTION_PLAY);
        }


        startService(intent);
    }
    private void togglePlayPosition(Boolean isNext) {
        if (Constant.item_radio.size() > 0) {
            if (RadioPlayerService.getInstance() == null) {
                RadioPlayerService.createInstance().initialize(RadioActivity.this, Constant.item_radio.get(Constant.position));
            }
            Intent intent;
            intent = new Intent(RadioActivity.this, RadioPlayerService.class);
            if (isNext) {
                intent.setAction(RadioPlayerService.ACTION_NEXT);
            } else {
                intent.setAction(RadioPlayerService.ACTION_PREVIOUS);
            }
            startService(intent);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Intent callingIntent = getIntent();
        id1 = callingIntent.getStringExtra("id");
        catId = callingIntent.getStringExtra("cat_id");
        cid = catId;
        createdAt = callingIntent.getStringExtra("created_at");
        updatedAt = callingIntent.getStringExtra("updated_at");
        imageString = callingIntent.getStringExtra("Image");
        titleString = callingIntent.getStringExtra("Title");
        categoryString = callingIntent.getStringExtra("Category");
        fullString = callingIntent.getStringExtra("Full");
        videoUrl1 = callingIntent.getStringExtra("tv_url");
        videoUpload = callingIntent.getStringExtra("video_upload");
        type_position = callingIntent.getStringExtra("type_postion");
        type_name = callingIntent.getStringExtra("type_name");
        url_id = callingIntent.getStringExtra("url_id");
        sID = callingIntent.getStringExtra("stID");
        user_agent = callingIntent.getStringExtra("user_agent");
        token = callingIntent.getStringExtra("token");

        txtRadioExpand.setText(titleString);
        txtName.setText(titleString);
        txtSongExpand.setText(fullString);
        txtSong.setText(fullString);

        Glide.with(getApplicationContext())
                .load(ADMIN_PANEL_URL+ IMAGE_URL +imageString)
                .thumbnail(0.2f)
                .into(imgPlayer);

        Glide.with(getApplicationContext())
                .load(ADMIN_PANEL_URL+ IMAGE_URL +imageString)
                .thumbnail(0.2f)
                .into(imgMusicBackground);


        Log.d("ksdjfkljksg", "onNewIntent: ");
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);



    }

    public void changeSongName(String songName) {
        Constant.metadata = songName;
        txtSong.setText(songName);
        txtSongExpand.setText(songName);
    }

    public void setIfPlaying() {
        if (RadioPlayerService.getInstance() != null) {
            RadioPlayerService.initNewContext(RadioActivity.this);
            playPause(RadioPlayerService.getInstance().isPlaying());

        } else {
            playPause(false);
        }
    }





    public void setBuffer(Boolean flag) {
        if (flag) {
            progressBar.setVisibility(View.VISIBLE);
            playerExpand.setVisibility(View.INVISIBLE);
            progressBarCollapse.setVisibility(View.VISIBLE);
            playCollapse.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            playerExpand.setVisibility(View.VISIBLE);
            progressBarCollapse.setVisibility(View.INVISIBLE);
            playCollapse.setVisibility(View.VISIBLE);
        }
    }

    public void changeRadioVolume() {
        final RelativePopupWindow popupWindow = new RelativePopupWindow(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.radio_volume, null);
        ImageView imageView1 = view.findViewById(R.id.img_volume_max);
        ImageView imageView2 = view.findViewById(R.id.img_volume_min);
        imageView1.setColorFilter(Color.BLACK);
        imageView2.setColorFilter(Color.BLACK);

        VerticalSeekBar seekBar = view.findViewById(R.id.seek_bar_volume);
        seekBar.getThumb().setColorFilter(sharedPrefRadio.getFirstColor(), PorterDuff.Mode.SRC_IN);
        seekBar.getProgressDrawable().setColorFilter(sharedPrefRadio.getSecondColor(), PorterDuff.Mode.SRC_IN);

        final AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        assert am != null;
        seekBar.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        int volume_level = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setProgress(volume_level);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                am.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("logg", "onStartTrackingTouch: ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("fsd", "onStopTrackingTouch: ");
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setContentView(view);
        popupWindow.showOnAnchor(imgVolume, RelativePopupWindow.VerticalPosition.ABOVE, RelativePopupWindow.HorizontalPosition.CENTER);
    }

    public void timerSelectDialog() {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setTitle(getString(R.string.sleep_time));

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.radio_dialog_select_time, null);
        alt_bld.setView(dialogView);

        final TextView tvMin = dialogView.findViewById(R.id.txt_minutes);
        tvMin.setText("1 " + getString(R.string.min));

        SeekBar seekBar = dialogView.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvMin.setText(progress + " " + getString(R.string.min));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        alt_bld.setPositiveButton(getString(R.string.set), (dialog, which) -> {
            String hours = String.valueOf(seekBar.getProgress() / 60);
            String minute = String.valueOf(seekBar.getProgress() % 60);

            if (hours.length() == 1) {
                hours = "0" + hours;
            }

            if (minute.length() == 1) {
                minute = "0" + minute;
            }

            String totalTime = hours + ":" + minute;
            long totalTimer = tools.convertToMilliSeconds(totalTime) + System.currentTimeMillis();

            Random random = new Random();
            int id = random.nextInt(100);

            sharedPrefRadio.setSleepTime(true, totalTimer, id);

            Intent intent = new Intent(RadioActivity.this, SleepTimeReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            assert alarmManager != null;
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, totalTimer, pendingIntent);
        });
        alt_bld.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {

        });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }

    public void radioOpenTimeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RadioActivity.this);
        builder.setTitle(getString(R.string.sleep_time));
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.radio_dialog_time, null);
        builder.setView(dialogView);

        TextView textView = dialogView.findViewById(R.id.txt_time);

        builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {

        });

        builder.setPositiveButton(getString(R.string.stop), (dialog, which) -> {
            Intent i = new Intent(RadioActivity.this, SleepTimeReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(RadioActivity.this, sharedPrefRadio.getSleepID(), i, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            pendingIntent.cancel();
            assert alarmManager != null;
            alarmManager.cancel(pendingIntent);
            sharedPrefRadio.setSleepTime(false, 0, 0);
        });

        updateTimer(textView, sharedPrefRadio.getSleepTime());

        builder.show();
    }


    private void updateTimer(final TextView textView, long time) {
        long timeleft = time - System.currentTimeMillis();
        if (timeleft > 0) {
            @SuppressLint("DefaultLocale") String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeleft),
                    TimeUnit.MILLISECONDS.toMinutes(timeleft) % TimeUnit.HOURS.toMinutes(1),
                    TimeUnit.MILLISECONDS.toSeconds(timeleft) % TimeUnit.MINUTES.toSeconds(1));

            textView.setText(hms);
            handler.postDelayed(() -> {
                if (sharedPrefRadio.getIsSleepTimeOn()) {
                    updateTimer(textView, sharedPrefRadio.getSleepTime());
                }
            }, 1000);
        }
    }

    public void playPause(Boolean flag) {
        Constant.is_playing = flag;
        if (flag) {
            Channel.Datum  radio = RadioPlayerService.getInstance().getPlayingRadioStation();
            if (radio != null) {
                imgPlay.setImageDrawable(ContextCompat.getDrawable(RadioActivity.this, R.drawable.ic_small_pause));
                fabPlay.setImageDrawable(ContextCompat.getDrawable(RadioActivity.this, R.drawable.ic_pause_white));

            }
        } else {
            if (Constant.item_radio.size() > 0) {
                Log.d("logs", "playPause: ");
            }
            imgPlay.setImageDrawable(ContextCompat.getDrawable(RadioActivity.this, R.drawable.ic_play_button_radio));
            fabPlay.setImageDrawable(ContextCompat.getDrawable(RadioActivity.this, R.drawable.ic_play_button_radio));
        }
    }

    public class MyBroadRequestReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String imageUrl =  intent.getStringExtra("imageUrl");
            String name =  intent.getStringExtra("name");
            String description = intent.getStringExtra("description");
            txtRadioExpand.setText(name);
            txtName.setText(name);
            txtSongExpand.setText(description);

            Glide.with(getApplicationContext())
                    .load(ADMIN_PANEL_URL+ IMAGE_URL +imageUrl)

                    .thumbnail(0.2f)
                    .into(imgPlayer);

            Glide.with(getApplicationContext())
                    .load(ADMIN_PANEL_URL+ IMAGE_URL +imageUrl)

                    .thumbnail(0.2f)
                    .into(imgMusicBackground);

            Log.d("fjsdhfjkh", "onReceive: image Url: "+imageUrl+"\n name : "+name+"\n Description:  "+description);

        }
    }


}