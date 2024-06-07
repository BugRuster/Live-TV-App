package com.app.dbug.radioservices;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.media.session.MediaButtonReceiver;

import com.app.dbug.activity.RadioActivity;
import com.app.dbug.config.Constant;
import com.app.dbug.model.Channel;
import com.app.dbug.utils.HttpsTrustManager;
import com.app.dbug.utils.Tools;
import com.app.dbug.BuildConfig;
import com.app.dbug.R;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.DefaultHlsExtractorFactory;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import static com.app.dbug.config.Constant.ADMIN_PANEL_URL;
import static com.app.dbug.config.Constant.IMAGE_URL;


@SuppressWarnings("deprecation")
public class RadioPlayerService extends Service {

    static private final int NOTIFICATION_ID = 1;
    @SuppressLint("StaticFieldLeak")
    static private RadioPlayerService service;
    @SuppressLint("StaticFieldLeak")
    static private Context context;
    static NotificationManager mNotificationManager;
    NotificationCompat.Builder mBuilder;
    static Channel.Datum radio;
    LoadSong loadSong;
    private Boolean isCanceled = false;
    RemoteViews bigViews, smallViews;
    Tools tools;
    Bitmap bitmap;
    ComponentName componentName;
    AudioManager mAudioManager;
    PowerManager.WakeLock mWakeLock;

    public static final String ACTION_STOP = BuildConfig.APPLICATION_ID + ".action.STOP";
    public static final String ACTION_PLAY = BuildConfig.APPLICATION_ID + ".action.PLAY";
    public static final String ACTION_PREVIOUS = BuildConfig.APPLICATION_ID + ".action.PREVIOUS";
    public static final String ACTION_NEXT = BuildConfig.APPLICATION_ID + ".action.NEXT";
    public static final String ACTION_TOGGLE = BuildConfig.APPLICATION_ID + ".action.TOGGLE_PLAYPAUSE";

    public void initialize(Context context, Channel.Datum station) {
        RadioPlayerService.context = context;
        radio = station;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    static public void initNewContext(Context context) {
        RadioPlayerService.context = context;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static RadioPlayerService getInstance() {
        return service;
    }

    public static RadioPlayerService createInstance() {
        if (service == null) {
            service = new RadioPlayerService();
        }
        return service;
    }

    public Boolean isPlaying() {
        if (service == null) {
            return false;
        } else {
            if (Constant.simpleExoPlayer != null) {
                return Constant.simpleExoPlayer.getPlayWhenReady();
            } else {
                return false;
            }
        }
    }

    @Override
    public void onCreate() {
        tools = new Tools(context);
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (mAudioManager != null) {
            mAudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        }

        componentName = new ComponentName(getPackageName(), ButtonClickReceiver.class.getName());
        mAudioManager.registerMediaButtonEventReceiver(componentName);

        LocalBroadcastManager.getInstance(this).registerReceiver(onCallIncome, new IntentFilter("android.intent.action.PHONE_STATE"));
        LocalBroadcastManager.getInstance(this).registerReceiver(onHeadPhoneDetect, new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY));

        AdaptiveTrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory();
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(context, trackSelectionFactory);
        Constant.simpleExoPlayer = new SimpleExoPlayer.Builder(context)
                .setTrackSelector(trackSelector)
                .build();

        Constant.simpleExoPlayer.addListener(listener);

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
        mWakeLock.setReferenceCounted(false);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (action != null)
            try {
                switch (action) {
                    case ACTION_STOP:
                        stop(intent);
                        break;
                    case ACTION_PLAY:
                        newPlay();
                        break;
                    case ACTION_TOGGLE:
                        togglePlayPause();
                        break;
                    case ACTION_PREVIOUS:
                        if (tools.isNetworkAvailable()) {
                            previous();
                        } else {
                            tools.showToast(getString(R.string.internet_not_connected));
                        }
                        break;
                    case ACTION_NEXT:
                        if (tools.isNetworkAvailable()) {
                            next();
                        } else {
                            tools.showToast(getString(R.string.internet_not_connected));
                        }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return START_NOT_STICKY;
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadSong extends AsyncTask<String, Void, Boolean> {

        MediaSource mediaSource;

        protected void onPreExecute() {
            ((RadioActivity) context).changeSongName(Constant.item_radio.get(Constant.position).name);
        }

        protected Boolean doInBackground(final String... args) {
            try {
                HttpsTrustManager.allowAllSSL();
                String url = radio.url;

                Log.d("`kfjslkdfjsk`", "doInBackground: "+url);
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(), getUserAgent());
                MediaItem mMediaItem = MediaItem.fromUri(Uri.parse(url));
                MediaItem mMediaItemURLParser = MediaItem.fromUri(Uri.parse(URLParser.getUrl(url)));

                if (url.contains(".m3u8")) {
                    mediaSource = new HlsMediaSource.Factory(dataSourceFactory)
                            .setAllowChunklessPreparation(false)
                            .setExtractorFactory(new DefaultHlsExtractorFactory(DefaultTsPayloadReaderFactory.FLAG_IGNORE_H264_STREAM, false))
                            .createMediaSource(mMediaItem);
                } else if (url.contains(".m3u")) {
                    mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory, new DefaultExtractorsFactory())
                            .createMediaSource(mMediaItemURLParser);
                } else if (url.contains(".pls") || url.contains("listen.pls?sid=")) {
                    mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory, new DefaultExtractorsFactory())
                            .createMediaSource(mMediaItemURLParser);
                } else {
                    mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory, new DefaultExtractorsFactory())
                            .createMediaSource(mMediaItem);
                }
                return true;
            } catch (Exception e1) {
                e1.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (context != null) {
                super.onPostExecute(aBoolean);

                Constant.simpleExoPlayer.seekTo(Constant.simpleExoPlayer.getCurrentWindowIndex(), Constant.simpleExoPlayer.getCurrentPosition());
                Constant.simpleExoPlayer.setMediaSource(mediaSource);

                Constant.simpleExoPlayer.prepare();
                Constant.simpleExoPlayer.setPlayWhenReady(true);
                if (!aBoolean) {
                    ((RadioActivity) context).setBuffer(false);
                    tools.showToast(getString(R.string.error_loading_radio));
                }
            }
        }
    }


    Player.Listener listener = new Player.Listener() {

        @Override
        public void onMetadata(@NonNull Metadata metadata) {
            getMetadata(metadata);
        }
        @Override
        public void onPlaybackStateChanged(int playbackState) {
            if (playbackState == Player.STATE_ENDED) {
                next();
            } else if (playbackState == Player.STATE_READY) {
                if (!isCanceled) {

                    ((RadioActivity) context).setBuffer(false);
                    if (mBuilder == null) {
                        createNotification();
                    } else {
                        updateNotification();
                    }
                    changePlayPause(true);
                } else {
                    isCanceled = false;
                    stopExoPlayer();
                }
            }
        }

        @Override
        public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
            if (playWhenReady) {
                if (!mWakeLock.isHeld()) {
                    mWakeLock.acquire(60000);
                }
            } else {
                if (mWakeLock.isHeld()) {
                    mWakeLock.release();
                }
            }
        }


//        @Override
        public void onPlayerError(@NonNull ExoPlaybackException error) {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Toast.makeText(context, context.getString(R.string.error_loading_radio), Toast.LENGTH_SHORT).show();
                stopExoPlayer();
                stopForeground(true);
                stopSelf();
                ((RadioActivity) context).setBuffer(false);
                ((RadioActivity) context).playPause(false);
            }, 0);
        }

    };

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void getMetadata(Metadata metadata) {
        if (!metadata.get(0).toString().equals("")) {
            String data = metadata.get(0).toString().replace("ICY: ", "");
            ArrayList<String> arrayList = new ArrayList(Arrays.asList(data.split(",")));
            String[] mediaMetadata = arrayList.get(0).split("=");
            String title = mediaMetadata[1].replace("\"", "");
            if ("".equalsIgnoreCase(title)) {
                ((RadioActivity) context).changeSongName(Constant.item_radio.get(Constant.position).getCat_name());
            } else {
                ((RadioActivity) context).changeSongName(title);
                if (mBuilder == null) {
                    createNotification();
                } else {
                    updateNotification();
                }
            }
        }
    }

    private String getUserAgent() {
        StringBuilder result = new StringBuilder(64);
        result.append("Dalvik/");
        result.append(System.getProperty("java.vm.version"));
        result.append(" (Linux; U; Android ");

        String version = Build.VERSION.RELEASE;
        result.append(version.length() > 0 ? version : "1.0");

        if ("REL".equals(Build.VERSION.CODENAME)) {
            String model = Build.MODEL;
            if (model.length() > 0) {
                result.append("; ");
                result.append(model);
            }
        }

        String id = Build.ID;

        if (id.length() > 0) {
            result.append(" Build/");
            result.append(id);
        }

        result.append(")");
        return result.toString();
    }

    private void changePlayPause(Boolean play) {
        ((RadioActivity) context).playPause(play);
    }

    private void togglePlayPause() {
        if (Constant.simpleExoPlayer.getPlayWhenReady()) {
            pause();
        } else {
            if (tools.isNetworkAvailable()) {
                play();
            } else {
                tools.showToast(getString(R.string.internet_not_connected));
            }
        }
    }

    private void pause() {
        Constant.simpleExoPlayer.setPlayWhenReady(false);
        changePlayPause(false);
        updateNotificationPlay(false);
    }

    private void play() {
        Constant.simpleExoPlayer.setPlayWhenReady(true);
        Constant.simpleExoPlayer.seekTo(Constant.simpleExoPlayer.getCurrentWindowIndex(), Constant.simpleExoPlayer.getCurrentPosition());
        changePlayPause(true);
        updateNotificationPlay(true);
    }

    private void newPlay() {
        loadSong = new LoadSong();
        loadSong.execute();
    }

    private void next() {
        tools.getPosition(true);
        radio = Constant.item_radio.get(Constant.position);
        newPlay();
    }

    private void previous() {
        tools.getPosition(false);
        radio = Constant.item_radio.get(Constant.position);
        newPlay();
    }

    public void stop(Intent intent) {
        if (Constant.simpleExoPlayer != null) {
            try {
                mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
                LocalBroadcastManager.getInstance(this).unregisterReceiver(onCallIncome);
                LocalBroadcastManager.getInstance(this).unregisterReceiver(onHeadPhoneDetect);
                mAudioManager.unregisterMediaButtonEventReceiver(componentName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            changePlayPause(false);
            stopExoPlayer();
            service = null;
            stopService(intent);
            stopForeground(true);
        }
    }

    public void stopExoPlayer() {
        if (Constant.simpleExoPlayer != null) {
            Constant.simpleExoPlayer.stop();
            Constant.simpleExoPlayer.addListener(listener);
        }
    }

    private void createNotification() {
        Intent notificationIntent = new Intent(this, RadioActivity.class);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent previousIntent = new Intent(this, RadioPlayerService.class);
        previousIntent.setAction(ACTION_PREVIOUS);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0, previousIntent, 0);

        Intent playIntent = new Intent(this, RadioPlayerService.class);
        playIntent.setAction(ACTION_TOGGLE);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0, playIntent, 0);

        Intent nextIntent = new Intent(this, RadioPlayerService.class);
        nextIntent.setAction(ACTION_NEXT);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0, nextIntent, 0);

        Intent closeIntent = new Intent(this, RadioPlayerService.class);
        closeIntent.setAction(ACTION_STOP);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0, closeIntent, PendingIntent.FLAG_IMMUTABLE);


        String NOTIFICATION_CHANNEL_ID = "app_channel_001";


        mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notify_icon))
                .setTicker(radio.getName())
                .setContentTitle(radio.getName())
                .setContentText(Constant.metadata)
                .setContentIntent(pi)
                .setPriority(Notification.PRIORITY_LOW)
                .setSmallIcon(R.drawable.notify_icon)
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .setOnlyAlertOnce(true);



        NotificationChannel mChannel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, NotificationManager.IMPORTANCE_LOW);
            mNotificationManager.createNotificationChannel(mChannel);

            MediaSessionCompat mMediaSession;
            mMediaSession = new MediaSessionCompat(context, getString(R.string.app_name));
            mMediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);


            mBuilder.setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowCancelButton(true)
                    .setShowActionsInCompactView(0, 1, 2)
                    .setCancelButtonIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(context, PlaybackStateCompat.ACTION_STOP)))
                    .addAction(new NotificationCompat.Action(
                            R.drawable.ic_skip_previous_white, "Previous",
                            ppreviousIntent))
                    .addAction(new NotificationCompat.Action(
                            R.drawable.ic_small_pause, "Pause",
                            pplayIntent))
                    .addAction(new NotificationCompat.Action(
                            R.drawable.ic_skip_next_white, "Next",
                            pnextIntent))
                    .addAction(new NotificationCompat.Action(
                            R.drawable.ic_cross, "Close",
                            pcloseIntent));
        } else {
            bigViews = new RemoteViews(getPackageName(), R.layout.radio_notification_large);
            smallViews = new RemoteViews(getPackageName(), R.layout.radio_notification_small);
            bigViews.setOnClickPendingIntent(R.id.img_notification_play, pplayIntent);
            smallViews.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);

            bigViews.setOnClickPendingIntent(R.id.img_notification_next, pnextIntent);
            smallViews.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);

            bigViews.setOnClickPendingIntent(R.id.img_notification_previous, ppreviousIntent);
            smallViews.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);

            bigViews.setOnClickPendingIntent(R.id.img_notification_close, pcloseIntent);
            smallViews.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);

            bigViews.setImageViewResource(R.id.img_notification_play, R.drawable.ic_small_pause);
            smallViews.setImageViewResource(R.id.status_bar_play, R.drawable.ic_small_pause);

            bigViews.setTextViewText(R.id.txt_notification_name, Constant.item_radio.get(Constant.position).getName());
            bigViews.setTextViewText(R.id.txt_notification_category, Constant.metadata);
            smallViews.setTextViewText(R.id.status_bar_track_name, Constant.item_radio.get(Constant.position).getName());
            smallViews.setTextViewText(R.id.status_bar_artist_name, Constant.metadata);

            bigViews.setImageViewResource(R.id.img_notification, R.drawable.notify_icon);
            smallViews.setImageViewResource(R.id.status_bar_album_art, R.drawable.notify_icon);

            mBuilder.setCustomContentView(smallViews).setCustomBigContentView(bigViews);
        }

        startForeground(NOTIFICATION_ID, mBuilder.build());
        updateNotificationImage();
    }

    private void updateNotificationImage() {
        new Thread(() -> {
            Intent intent = new Intent("okokoko");
            intent.putExtra("imageUrl", Constant.item_radio.get(Constant.position).image);
            intent.putExtra("name", Constant.item_radio.get(Constant.position).name);
            intent.putExtra("description", Constant.metadata);
            sendBroadcast(intent);
            try {
                getBitmapFromURL(ADMIN_PANEL_URL + IMAGE_URL + Constant.item_radio.get(Constant.position).image);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mBuilder.setLargeIcon(bitmap);
                } else {
                    bigViews.setImageViewBitmap(R.id.img_notification, bitmap);
                    smallViews.setImageViewBitmap(R.id.status_bar_album_art, bitmap);
                }
                mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        }).start();
    }

    private void updateNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder.setContentTitle(Constant.item_radio.get(Constant.position).name);
            mBuilder.setContentText(Constant.metadata);
        } else {
            bigViews.setTextViewText(R.id.txt_notification_name, Constant.item_radio.get(Constant.position).getName());
            bigViews.setTextViewText(R.id.txt_notification_category, Constant.metadata);
            smallViews.setTextViewText(R.id.status_bar_track_name, Constant.item_radio.get(Constant.position).getName());
            smallViews.setTextViewText(R.id.status_bar_artist_name, Constant.metadata);
        }
        updateNotificationImage();
        updateNotificationPlay(Constant.simpleExoPlayer.getPlayWhenReady());
    }

    @SuppressLint("RestrictedApi")
    private void updateNotificationPlay(Boolean isPlay) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder.mActions.remove(1);
            Intent playIntent = new Intent(this, RadioPlayerService.class);
            playIntent.setAction(ACTION_TOGGLE);
            PendingIntent pendingIntent = PendingIntent.getService(this, 0, playIntent, 0);
            if (isPlay) {
                mBuilder.mActions.add(1, new NotificationCompat.Action(R.drawable.ic_small_pause, "Pause", pendingIntent));
            } else {
                mBuilder.mActions.add(1, new NotificationCompat.Action(R.drawable.ic_play_button_radio, "Play", pendingIntent));
            }
        } else {
            if (isPlay) {
                bigViews.setImageViewResource(R.id.img_notification_play, R.drawable.ic_small_pause);
                smallViews.setImageViewResource(R.id.status_bar_play, R.drawable.ic_small_pause);
            } else {
                bigViews.setImageViewResource(R.id.img_notification_play, R.drawable.ic_play_button_radio);
                smallViews.setImageViewResource(R.id.status_bar_play, R.drawable.ic_play_button_radio);
            }
        }
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    public Channel.Datum getPlayingRadioStation() {
        return radio;
    }

    private void getBitmapFromURL(String src) {
        try {
            URL url = new URL(src.replace(" ", "%20"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }
    }

    BroadcastReceiver onCallIncome = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (isPlaying()) {
                if (state != null) {
                    if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK) || state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                        Intent intent_stop = new Intent(context, RadioPlayerService.class);
                        intent_stop.setAction(ACTION_TOGGLE);
                        startService(intent_stop);
                        Toast.makeText(context, "there is an call!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "whoops!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

    BroadcastReceiver onHeadPhoneDetect = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Constant.is_playing) {
                togglePlayPause();
            }
        }
    };

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = focusChange -> {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                // Resume your media player here
                if (Constant.RESUME_RADIO_ON_PHONE_CALL) {
                    togglePlayPause();
                }
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                if (isPlaying()) {
                    togglePlayPause();
                }
                break;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        try {
            Constant.simpleExoPlayer.stop();
            Constant.simpleExoPlayer.release();
            Constant.simpleExoPlayer.removeListener(listener);
            if (mWakeLock.isHeld()) {
                mWakeLock.release();
            }
            try {
                mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
                LocalBroadcastManager.getInstance(this).unregisterReceiver(onCallIncome);
                LocalBroadcastManager.getInstance(this).unregisterReceiver(onHeadPhoneDetect);
                mAudioManager.unregisterMediaButtonEventReceiver(componentName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

}