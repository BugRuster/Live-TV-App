package com.app.dbug;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.app.dbug.activity.LoadActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirebaseMessageReceiver extends FirebaseMessagingService {

    Bitmap bitmap;

    SharedPreferences sharedPreferences;
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.e("newToken", token);
    }

    @Override
    public void
    onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("data1111", "title: "+remoteMessage.getData());
        String imageUrl = null;
        if (remoteMessage.getNotification().getImageUrl() !=  null){
            imageUrl = remoteMessage.getNotification().getImageUrl().toString();
        }

        Log.d("message", "message: "+remoteMessage.getData().get("message"));
        if (remoteMessage.getNotification() != null) {

            String url = remoteMessage.getData().get("url");
            bitmap = getBitmapfromUrl(imageUrl);

            String click_action = remoteMessage.getNotification().getClickAction();

            Intent intent = new Intent(this, LoadActivity.class);
            intent.putExtra("url",url);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            String channel_id = "notification_channel";

            NotificationCompat.Builder builder = new NotificationCompat
                    .Builder(getApplicationContext(), channel_id)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setOnlyAlertOnce(true)
                    .setContentIntent(pendingIntent);

          //  builder = builder.setContent(getCustomDesign(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(),imageUrl));

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(channel_id, "web_app", NotificationManager.IMPORTANCE_HIGH);notificationManager.createNotificationChannel(notificationChannel);
            }
            notificationManager.notify(0, builder.build());
        }


    }
    private RemoteViews getCustomDesign(String title, String message,String imageUrl) {
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.title, title);
        remoteViews.setTextViewText(R.id.message, message);
        remoteViews.setImageViewResource(R.id.icon, R.mipmap.ic_launcher);
        remoteViews.setImageViewBitmap(R.id.imageViewNotification,bitmap);
        Log.d("dsfsfsd", "getCustomDesign: "+imageUrl);

        return remoteViews;
    }
    public void showNotification(String title, String message,String imageUrl,String url) {
       /* if (url != null){

        }else {
            url = "http://www.google.com/";
            notificationIntent = new Intent(Intent.ACTION_VIEW);
            notificationIntent.setData(Uri.parse(url));
        }*/
     /*   Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
        notificationIntent.setData(Uri.parse("https://www.google.com/"));*/


       /* @SuppressLint("WrongConstant")
        PendingIntent pending = PendingIntent.getActivity(this, 0, notificationIntent,
                Intent.FLAG_ACTIVITY_NEW_TASK);
        */
      /*
        if (!url.isEmpty()){
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            Toast.makeText(this, url+"", Toast.LENGTH_SHORT).show();
        }else {

        }*/

        Intent intent = new Intent(getApplicationContext(), LoadActivity.class);
//you can use your launcher Activity insted of SplashActivity, But if the Activity you used here is not launcher Activty than its not work when App is in background.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//Add Any key-value to pass extras to intent
        intent.putExtra("pushnotification", "yes");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        String channel_id = "notification_channel";
        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        builder = builder.setContent(getCustomDesign(title, message,imageUrl));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channel_id, "web_app", NotificationManager.IMPORTANCE_HIGH);notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(0, builder.build());
    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }

    private void sendNotificationUrl(String id, String title, String imageUri, String iconUrl, String message, String url) {


        Bitmap image = getBitmapfromUrl(imageUri);
        Bitmap icon = getBitmapfromUrl(iconUrl);

        Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
        notificationIntent.setData(Uri.parse(url));
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        int NOTIFICATION_ID = Integer.parseInt(id);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";
        String Description = "This is my channel";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(message);

        if (icon!=null){
            builder.setLargeIcon(icon);

        }else{
            builder.setLargeIcon(largeIcon);
        }
        if (image!=null){
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image));
        }


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addParentStack(LoadActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE);

        builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());


    }
}
