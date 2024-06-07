package com.app.dbug;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import com.app.dbug.activity.SplashActivity;
import com.app.dbug.api.ApiInter;
import com.app.dbug.model.DeviceToken;
import com.app.dbug.retofit.RetrofitClient;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FcmTokenRegistrationService extends IntentService {

    public FcmTokenRegistrationService() {
        super("FcmTokenRegistrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                return;
            }
            String token = task.getResult();
            String deviceName = android.os.Build.MODEL;
            tokenPost(token);
        });
    }

    public void tokenPost(String token){
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences(SplashActivity.MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String oldToken = sharedpreferences.getString("deviceToken", "dfgsg");
        String token1 =  token;
        Log.d("sada", "old "+oldToken);
        Log.d("sada", "new "+token1);
        if (!oldToken.equals(token1)){
            Log.d("fsjsldasdf", "tokenPost: if ");
            ApiInter tokenApi = RetrofitClient.getRetrofit().create(ApiInter.class);
            Date currentTime = Calendar.getInstance().getTime();
            String name = currentTime+"";
            DeviceToken deviceToken = new DeviceToken(name,token1);
            editor.putString("deviceToken",token1).apply();
            tokenApi.postToken(deviceToken)
                    .enqueue(new Callback<DeviceToken>() {
                        @Override
                        public void onResponse(Call<DeviceToken> call, Response<DeviceToken> response) {
                            if (response.isSuccessful()){
                                Log.d("dsfds", "onResponse: ");
                            }

                        }

                        @Override
                        public void onFailure(Call<DeviceToken> call, Throwable t) {
                            Log.d("dsfds", "onFailure: "+t.getMessage());

                        }
                    });
        }else {
            Log.d("fsjsldasdf", "tokenPost: els ");
        }
    }
}
